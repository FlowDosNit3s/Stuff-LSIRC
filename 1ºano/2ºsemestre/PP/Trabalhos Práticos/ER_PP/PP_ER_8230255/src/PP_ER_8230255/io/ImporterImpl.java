/*  
* Nome: Carlos Alberto Moreira Barbosa
* Número: 8230255
* Turma: LSIRC11T1
 */
package PP_ER_8230255.io;

import com.estg.core.*;
import com.estg.core.exceptions.*;
import com.estg.io.*;
import com.estg.pickingManagement.Vehicle;

import PP_ER_8230255.core.*;
import PP_ER_8230255.pickingManagement.*;
import com.estg.pickingManagement.PickingMap;
import java.io.BufferedReader;
import java.io.FileInputStream;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.json.simple.*;
import org.json.simple.parser.*;

public class ImporterImpl implements Importer {

    private Container[] containers;
    private boolean[] picked;

    /**
     * Reads the input JSON file
     *
     * @param instn The city in which the file data will be read
     * @throws FileNotFoundException if the file is not found
     * @throws IOException if the file cannot be read
     * @throws InstitutionException if the city is null
     */
    @Override
    public void importData(Institution instn) throws FileNotFoundException, IOException, InstitutionException {
        if (instn == null) {
            throw new InstitutionException("The Institution can't be null");
        }
        HTTPProvider httpProvider = new HTTPProvider();
        JSONParser parser = new JSONParser();

        String AidBoxesJSON = httpProvider.getFromURL("https://data.mongodb-api.com/app/data-docuz/endpoint/aidboxes");
        String ContainersJSON = httpProvider.getFromURL("https://data.mongodb-api.com/app/data-docuz/endpoint/containers");
        String TypesJSON = httpProvider.getFromURL("https://data.mongodb-api.com/app/data-docuz/endpoint/types");
        String DistancesJSON = httpProvider.getFromURL("https://data.mongodb-api.com/app/data-docuz/endpoint/distances");
        String VehiclesJSON = httpProvider.getFromURL("https://data.mongodb-api.com/app/data-docuz/endpoint/vehicles");
        String ReadingsJSON = httpProvider.getFromURL("https://data.mongodb-api.com/app/data-docuz/endpoint/readings");

        String MeasurementsFile = "Measurements.json";
        String VehiclesFile = "Vehicles.ser";
        String PickingMapsFile = "PickingMaps.ser";

        // LOAD ALL THE CONTAINERS AND CONTAINER TYPES THROUGH THE WEB API
        try {
            // set all the containers in the json file to the institution
            ((InstitutionImpl) instn).setContainerTypes(this.jsonContainerTypesToObject((JSONArray) parser.parse(TypesJSON)));
            this.setContainers(this.jsonContainersToObject((JSONArray) parser.parse(ContainersJSON), instn));

        } catch (ParseException ex) {
            WriteFiles.writeErrorToFile(ex);
            throw new IOException("ERROR WHILE IMPORTING THE DATA");
        }

        // LOAD THE SAVED MEASUREMENTS
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(MeasurementsFile))) {
            String output = bufferedReader.readLine();
            this.jsonMeasurementsToObjectAndImport((JSONArray) parser.parse(output), 1);

        } catch (FileNotFoundException | ParseException | NullPointerException ex) {
            WriteFiles.writeErrorToFile(ex);
            System.out.println(ex.getMessage());
        }

        try {
            // IMPORT ALL MEASUREMENTS THROUGH THE WEB API
            this.jsonMeasurementsToObjectAndImport((JSONArray) parser.parse(ReadingsJSON));

            // IMPORT ALL THE AIDBOXES THROUGH THE WEB API
            AidBox[] aidboxes = this.AidBoxesJSONtoObject((JSONArray) parser.parse(AidBoxesJSON));

            for (AidBox aidbox : aidboxes) {
                if (aidbox != null) {
                    try {
                        instn.addAidBox(aidbox);
                    } catch (AidBoxException ex) {
                        System.out.println(ex.getMessage());
                    }

                }
            }

            Container[] containersNotPicked = this.getNonPickedContainers();
            ((InstitutionImpl) instn).setNonPickedContainers(containersNotPicked);

            this.jsonDistancesToObjectAndImportToAidBoxes((JSONArray) parser.parse(DistancesJSON), instn);
        } catch (ParseException ex) {
            WriteFiles.writeErrorToFile(ex);
            throw new IOException("ERROR WHILE IMPORTING THE DATA");
        }

        // LOAD THE SAVED VEHICLES
        boolean error = false;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(VehiclesFile))) {
            Vehicle[] vehicles = (Vehicle[]) inputStream.readObject();
            for (Vehicle vehicle : vehicles) {
                try {
                    instn.addVehicle(vehicle);
                } catch (VehicleException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (IOException | ClassNotFoundException | NullPointerException ex) {
            error = true;
            WriteFiles.writeErrorToFile(ex);
            System.out.println(ex.getMessage());
        }

        // IF AN ERROR OCCURS LOAD THE VEHICLES THROUGH THE WEB API
        if (error) {
            try {
                Vehicle[] vehicles = this.jsonVehiclesToObjcet((JSONArray) parser.parse(VehiclesJSON), instn);

                for (Vehicle vehicle : vehicles) {
                    if (vehicle != null) {
                        try {
                            instn.addVehicle(vehicle);
                        } catch (VehicleException ex) {
                            System.out.println(ex.getMessage());
                        }

                    }
                }
            } catch (ParseException ex) {
                WriteFiles.writeErrorToFile(ex);
                throw new IOException("ERROR WHILE IMPORTING THE DATA");
            }
        }

        // LOAD THE SAVED PICKING MAPS
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(PickingMapsFile))) {
            PickingMap[] pickingMaps = (PickingMap[]) inputStream.readObject();
            for (PickingMap pickingMap : pickingMaps) {
                try {
                    instn.addPickingMap(pickingMap);
                } catch (PickingMapException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (IOException | ClassNotFoundException | NullPointerException ex) {
            WriteFiles.writeErrorToFile(ex);
            System.out.println(ex.getMessage());
        }

    }

    //------------------------------------------------------------
    /**
     * Converts a JSONArray of AidBoxes to an array of AidBox objects.
     *
     * @param jsonArray The JSONArray containing AidBox data
     * @return An array of AidBox objects
     */
    private AidBox[] AidBoxesJSONtoObject(JSONArray jsonArray) {
        AidBox[] aidboxes = new AidBox[jsonArray.size()];

        for (int i = 0; i < jsonArray.size(); i++) {
            AidBox aidBox = this.jsonAidBoxToObject((JSONObject) jsonArray.get(i));
            aidboxes[i] = aidBox;
        }
        return aidboxes;
    }

    /**
     * Converts a JSONObject of an AidBox to an AidBox object.
     *
     * @param jsonObject The JSONObject containing AidBox data.
     * @return An AidBox object
     */
    private AidBox jsonAidBoxToObject(JSONObject jsonObject) {
        String code = (String) jsonObject.get("code");
        String zone = (String) jsonObject.get("Zona");

        JSONArray contentoresJson = (JSONArray) jsonObject.get("containers");
        Container[] containersTemp = this.jsonContainersToObjectByCode(contentoresJson);

        AidBox aidbox = new AidBoxImpl(code, zone);

        for (Container container : containersTemp) {
            if (container != null) {
                try {
                    aidbox.addContainer(container);
                } catch (ContainerException ex) {
                    WriteFiles.writeErrorToFile(ex);
                    System.out.println(ex.getMessage());
                }
            }
        }
        return aidbox;
    }

    /**
     * Converts a array of an container to an container object by the given
     * codes.
     *
     * @param jsonArray The JSONArray containing containers codes
     * @return an array of container objects
     */
    private Container[] jsonContainersToObjectByCode(JSONArray jsonArray) {
        Container[] containersTemp = new Container[jsonArray.size()];

        for (int i = 0; i < jsonArray.size(); i++) {
            String code = (String) jsonArray.get(i);
            Container container = this.getContainer(code);
            containersTemp[i] = container;
        }
        return containersTemp;
    }

    /**
     * Converts a array of an container to an container object
     *
     * @param jsonArray The JSONArray containing container
     * @param instn The institution
     * @return an array of container objects
     */
    private Container[] jsonContainersToObject(JSONArray jsonArray, Institution instn) {
        Container[] containersTemp = new Container[jsonArray.size()];

        for (int i = 0; i < jsonArray.size(); i++) {
            Container container = this.jsonContainerToObject((JSONObject) jsonArray.get(i), instn);
            containersTemp[i] = container;
        }
        return containersTemp;
    }

    /**
     * Converts a JSONObject of an container to an container object.
     *
     * @param jsonObject The JSONObject containing container data.
     * @param instn The institution
     * @return An container object
     */
    private Container jsonContainerToObject(JSONObject jsonObject, Institution instn) {
        String code = (String) jsonObject.get("code");
        double capacity = ((Long) jsonObject.get("capacity")).doubleValue();
        String typeString = (String) jsonObject.get("type");

        ContainerType containerType = ((InstitutionImpl) instn).getContainerType(typeString);
        Container container = new ContainerImpl(code, capacity, containerType);
        return container;
    }

    /**
     * Converts a array of an container types to an container type object
     *
     * @param jsonArray The JSONArray containing container types
     * @return an array of container types objects
     */
    private ContainerType[] jsonContainerTypesToObject(JSONArray jsonArray) {
        JSONObject jsonObject = (JSONObject) jsonArray.get(0);
        JSONArray typesJSON = (JSONArray) jsonObject.get("types");
        ContainerType[] containertypes = new ContainerType[typesJSON.size()];

        for (int i = 0; i < typesJSON.size(); i++) {
            String type = (String) typesJSON.get(i);
            containertypes[i] = new ContainerTypeImpl(type);
        }

        return containertypes;
    }

    /**
     * import all the given measurements to the institution
     *
     * @param jsonArray an json array containing all measurements to import
     */
    private void jsonMeasurementsToObjectAndImport(JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.size(); i++) {

            JSONObject measurementJSON = (JSONObject) jsonArray.get(i);

            String code = (String) measurementJSON.get("contentor");
            String stringDate = (String) measurementJSON.get("data");
            double value = ((Long) measurementJSON.get("valor")).doubleValue();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
            LocalDateTime date = LocalDateTime.parse(stringDate, formatter);

            Measurement measurement = new MeasurementImpl(date, value);

            try {
                importMeasurementsToContainer(measurement, code);
            } catch (MeasurementException ex) {
                WriteFiles.writeErrorToFile(ex);
                System.out.println(ex.getMessage());
            }

        }
    }

    /**
     * import all the given measurements to the institution
     *
     * @param jsonArray an json array containing all measurements to import
     * @param a object to see that we need to used an function to read the saved
     * measurements in the files
     */
    private void jsonMeasurementsToObjectAndImport(JSONArray jsonArray, int a) {
        for (int i = 0; i < jsonArray.size(); i++) {

            JSONObject measurementJSON = (JSONObject) jsonArray.get(i);

            String code = (String) measurementJSON.get("contentor");
            String stringDate = (String) measurementJSON.get("Data");
            double value = ((Double) measurementJSON.get("valor"));

            LocalDateTime date = LocalDateTime.parse(stringDate);

            Measurement measurement = new MeasurementImpl(date, value);

            try {
                importMeasurementsToContainer(measurement, code);
            } catch (MeasurementException ex) {
                WriteFiles.writeErrorToFile(ex);
                System.out.println(ex.getMessage());
            }

        }
    }

    /**
     * imports the measurements to a container
     *
     * @param meas the measurement
     * @param code the container code
     * @throws MeasurementException if an error occurs when adding measurements
     * to the container
     */
    private void importMeasurementsToContainer(Measurement meas, String code) throws MeasurementException {
        for (Container cont : this.containers) {
            if (cont.getCode().equals(code)) {
                cont.addMeasurement(meas);
            }
        }
    }

    /**
     * import all distances to the aidboxes
     *
     * @param jsonArray an json array with all the distances
     * @param instn the institution
     */
    private void jsonDistancesToObjectAndImportToAidBoxes(JSONArray jsonArray, Institution instn) {
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            String from = (String) jsonObject.get("from");

            JSONArray distancesJSON = (JSONArray) jsonObject.get("to");

            ((InstitutionImpl) instn).importDistanceToAidBox(this.jsonDistancesToObject(distancesJSON), from);
        }
    }

    /**
     * retuens an array with all the distances to other aidboxes
     *
     * @param jsonArray an json array with all the distances
     * @return an array with all distances to other aidboxes
     */
    private DistanceToOtherDestination[] jsonDistancesToObject(JSONArray jsonArray) {
        DistanceToOtherDestination[] distanceToOtherDestinations = new DistanceToOtherDestination[jsonArray.size()];

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);

            String name = (String) jsonObject.get("name");
            double distance = ((Long) jsonObject.get("distance")).doubleValue();
            double duration = ((Long) jsonObject.get("duration")).doubleValue();

            DistanceToOtherDestination distanceToOtherDestination = new DistanceToOtherDestination(name, duration, distance);
            distanceToOtherDestinations[i] = distanceToOtherDestination;
        }

        return distanceToOtherDestinations;
    }

    /**
     * an array with all the vehicles
     *
     * @param jsonArray an json array with all the vehicles
     * @param instn the institution
     * @return an array with all the vehicles
     */
    private Vehicle[] jsonVehiclesToObjcet(JSONArray jsonArray, Institution instn) {
        Vehicle[] vehicles = new Vehicle[jsonArray.size()];

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            vehicles[i] = this.jsonVehicleToObjcet(jsonObject, instn);
        }

        return vehicles;
    }

    /**
     * return an vehicle
     *
     * @param jsonObject json object containing all the vehicle info
     * @param instn the instituion
     * @return an vehilcle
     */
    private Vehicle jsonVehicleToObjcet(JSONObject jsonObject, Institution instn) {
        String code = (String) jsonObject.get("code");
        JSONObject capacities = (JSONObject) jsonObject.get("capacity");

        ContainerType[] possibleContainers = ((InstitutionImpl) instn).getContainerTypes();
        Capacity[] vehicleCapacities = new Capacity[possibleContainers.length];
        int counter = 0;

        for (ContainerType containerType : possibleContainers) {
            String type = containerType.toString();
            if (capacities.containsKey(type)) {
                double capacity = ((Long) capacities.get(type)).doubleValue();

                Capacity VehicleCapacity = new Capacity(containerType, capacity);
                vehicleCapacities[counter++] = VehicleCapacity;
            }
        }

        Vehicle vehicle = new VehicleImpl(code, vehicleCapacities);
        return vehicle;
    }

    /**
     * set the containers to the variable
     *
     * @param containers an array with all the containers
     */
    public void setContainers(Container[] containers) {
        Container[] temp = new Container[containers.length];
        int counter = 0;

        for (Container container : containers) {
            if (container != null) {
                temp[counter++] = container;
            }
        }

        this.containers = new Container[counter];
        this.picked = new boolean[counter];
        System.arraycopy(temp, 0, this.containers, 0, counter);
    }

    /**
     * get an container by the given code
     *
     * @param code the container code
     * @return an container
     */
    public Container getContainer(String code) {
        for (int i = 0; i < this.containers.length; i++) {
            if (this.containers[i].getCode().equals(code)) {
                try {
                    this.picked[i] = true;
                    return (Container) ((ContainerImpl) this.containers[i]).clone();
                } catch (CloneNotSupportedException ex) {
                    System.out.println("ERROR WHILE CLONING CONTAINERS");
                }

            }

        }
        return null;
    }

    /**
     * gets an array with all the non used containers
     *
     * @return
     */
    public Container[] getNonPickedContainers() {
        Container[] temp = new Container[this.containers.length];
        int counter = 0;

        for (int i = 0; i < this.containers.length; i++) {
            if (!this.picked[i]) {
                temp[counter++] = this.containers[i];
            }
        }
        Container[] containerNotPicked = new Container[counter];
        System.arraycopy(temp, 0, containerNotPicked, 0, counter);
        return containerNotPicked;
    }

    /**
     * returns an array with all the aidboxes by the given codes
     *
     * @param jsonArray an json array with all the aidboxes codes
     * @param instn the institution
     * @return an array with all aidboxes
     */
    private AidBox[] jsonAidBoxesByCodeToObjcet(JSONArray jsonArray, Institution instn) {
        AidBox[] aidboxes = new AidBox[jsonArray.size()];

        for (int i = 0; i < jsonArray.size(); i++) {
            aidboxes[i] = this.getAidBoxByCode(instn, (String) jsonArray.get(i));
        }

        return aidboxes;
    }

    /**
     * gets an aidbox by the given code
     *
     * @param instn the institution
     * @param code the aidbox code
     * @return an aidbox with the given code
     */
    private AidBox getAidBoxByCode(Institution instn, String code) {
        for (AidBox aidbox : instn.getAidBoxes()) {
            if (aidbox.getCode().equals(code)) {
                return aidbox;
            }
        }
        return null;
    }

    /**
     * gets an vehicle by the given code
     *
     * @param instn the institution
     * @param code the vehicle code
     * @return an vehicle with the given code
     */
    private Vehicle getVehicleByCode(Institution instn, String code) {
        for (Vehicle vehicle : instn.getVehicles()) {
            if (vehicle.getCode().equals(code)) {
                return vehicle;
            }
        }
        return null;
    }

}
