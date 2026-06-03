/*
Nome: Jose Pedro Vieira Barbosa
Número: 8230241
Turma: LEIT3
*
Nome: Carlos Alberto Moreira Barbosa
Número: 8230255
Turma: LSIRCT2
*/

package trabalho_pp.Data;

import com.estg.core.*;
import com.estg.core.exceptions.*;
import com.estg.pickingManagement.*;
import com.estg.pickingManagement.exceptions.RouteException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import org.json.simple.*;
import org.json.simple.parser.*;
import trabalho_pp.core.*;
import trabalho_pp.pickingManagement.*;

/**
 * Class responsible for the Import Institution implementation
 */
public class ImportInstitution {

    /**
     * We create an array of aidboxes with the given json array size, we iterate the given array from json and add each aid box object to the aid boxes collection.
     *
     * @param jsonArray Given array from json file
     * @return AidBox[]
     */
    public AidBox[] jsonAidBoxesToObject(JSONArray jsonArray) {
        AidBox[] aidboxes = new AidBox[jsonArray.size()];

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject aidBoxobj = (JSONObject) jsonArray.get(i);
            AidBox aidBox = jsonAidBoxToObject(aidBoxobj);
            aidboxes[i] = aidBox;
        }
        return aidboxes;
    }

    /**
     * We get all the information from the given json object and add them to a new aid box object.
     *
     * @param jsonObject Given json object
     * @return AidBox
     */
    public AidBox jsonAidBoxToObject(JSONObject jsonObject) {
        AidBox aidbox = null;

        String codigo = (String) jsonObject.get("Codigo");
        String zona = (String) jsonObject.get("Zona");
        double latitude = ((Double) jsonObject.get("Latitude"));
        double longitude = ((Double) jsonObject.get("Longitude"));

        JSONArray contentoresJson = (JSONArray) jsonObject.get("Contentores");
        Container[] containers = jsonContainersToObject(contentoresJson);

        GeographicCoordinatesImpl cords = new GeographicCoordinatesImpl(latitude, longitude);
        aidbox = new AidBoxImpl(codigo, zona, cords, containers);

        return aidbox;
    }

    /**
     * We create an array of containers with the given json array size, we iterate the given array from json and add each container object to the containers collection.
     *
     * @param jsonArray Given array from json file
     * @return Container[]
     */
    public Container[] jsonContainersToObject(JSONArray jsonArray) {
        Container[] containers = new Container[jsonArray.size()];
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject containerobj = (JSONObject) jsonArray.get(i);
            Container container = jsonContainerToObject(containerobj);
            containers[i] = container;
        }
        return containers;
    }

    /**
     * We get all the information from the given json object and add them to a new container object including all the measurements.
     *
     * @param jsonObject Given json object
     * @return Container
     */
    public Container jsonContainerToObject(JSONObject jsonObject) {
        Container container = null;

        String codigo = (String) jsonObject.get("Codigo");
        double capacidade = ((Double) jsonObject.get("Capacidade"));
        JSONArray measurementsObj = (JSONArray) jsonObject.get("Medicoes");

        Measurement[] measurements = jsonMeasurementsToObject(measurementsObj);
        ItemType tipo = codeToType(codigo);

        container = new ContainerImpl(codigo, capacidade, tipo);

        for (Measurement measurement : measurements) {
            try {
                container.addMeasurement(measurement);
            } catch (MeasurementException ex) {
                ex.printStackTrace();
            }
        }

        return container;
    }

    /**
     * We create an array of measurements with the given json array size, we iterate the given array from json and add each measurement object to the measurements collection.
     *
     * @param jsonArray Given array from json file
     * @return Measurement[]
     */
    public Measurement[] jsonMeasurementsToObject(JSONArray jsonArray) {
        Measurement[] measurements = new Measurement[jsonArray.size()];
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject measurementobj = (JSONObject) jsonArray.get(i);
            Measurement measurement = jsonMeasurementToObject(measurementobj);
            measurements[i] = measurement;
        }
        return measurements;
    }

    /**
     * We get all the information from the given json object and add them to a new measurement object.
     *
     * @param jsonObject Given json object
     * @return Measurement
     */
    public Measurement jsonMeasurementToObject(JSONObject jsonObject) {
        Measurement measurement = null;

        String data = (String) jsonObject.get("Data");
        double valor = ((Double) jsonObject.get("Valor"));

        LocalDateTime dataHora = LocalDateTime.parse(data);

        measurement = new MeasurementImpl(dataHora, valor);

        return measurement;
    }

    /**
     * We create an array of vehicles with the given json array size, we iterate the given array from json and add each vehicle object to the vehicles collection.
     *
     * @param jsonArray Given array from json file
     * @return Vehicle[]
     */
    public Vehicle[] jsonVehiclesToObject(JSONArray jsonArray) {
        Vehicle[] vehicles = new Vehicle[jsonArray.size()];
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject vehicleobj = (JSONObject) jsonArray.get(i);
            Vehicle vehicle = jsonVehicleToObject(vehicleobj);
            vehicles[i] = vehicle;
        }
        return vehicles;
    }

    /**
     * We get all the information from the given json object and add them to a new vehicle object.
     *
     * @param jsonObject Given json object
     * @return Vehicle
     */
    public Vehicle jsonVehicleToObject(JSONObject jsonObject) {
        try {
            Vehicle vehicle = null;

            String type = (String) jsonObject.get("Tipo");
            double capacidade = ((Double) jsonObject.get("Capacidade"));

            ItemType itemType = stringToType(type);
            if (itemType == ItemType.PERISHABLE_FOOD) {
                vehicle = new RefrigeratedVehiclesImpl(capacidade);
            } else {
                vehicle = new NormalVehicleImpl(itemType, capacidade);
            }

            return vehicle;
        } catch (VehicleException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * We create an array of picking maps with the given json array size, we iterate the given array from json and add each picking map object to the picking maps collection.
     *
     * @param jsonArray Given array from json file
     * @return PickingMap[]
     */
    public PickingMap[] jsonPickingMapsToObject(JSONArray jsonArray) {
        PickingMap[] pickingMaps = new PickingMap[jsonArray.size()];
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject pickingMapobj = (JSONObject) jsonArray.get(i);
            PickingMap pickingMap = jsonPickingMapToObject(pickingMapobj);
            pickingMaps[i] = pickingMap;
        }
        return pickingMaps;
    }

    /**
     * We get all the information from the given json object and add them to a new picking map object.
     *
     * @param jsonObject Given json object
     * @return PickingMap
     */
    public PickingMap jsonPickingMapToObject(JSONObject jsonObject) {
        PickingMap pickingMap = null;

        String data = (String) jsonObject.get("Data");
        JSONArray routesObj = (JSONArray) jsonObject.get("Rotas");
        Route[] routes = jsonRoutesToObject(routesObj);

        LocalDateTime dataHora = LocalDateTime.parse(data);

        pickingMap = new PickingMapImpl(dataHora, routes);

        return pickingMap;
    }

    /**
     * We create an array of routes with the given json array size, we iterate the given array from json and add each route object to the routes collection.
     *
     * @param jsonArray Given array from json file
     * @return Route[]
     */
    public Route[] jsonRoutesToObject(JSONArray jsonArray) {
        Route[] routes = new Route[jsonArray.size()];
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject routeObj = (JSONObject) jsonArray.get(i);
            Route route = jsonRouteToObject(routeObj);
            routes[i] = route;
        }
        return routes;
    }

    /**
     * We get all the information from the given json object and add them to a new route object.
     *
     * @param jsonObject Given json object
     * @return Route
     */
    public Route jsonRouteToObject(JSONObject jsonObject) {
        Route route = null;

        JSONObject vehicleJson = (JSONObject) jsonObject.get("Veiculo");
        Vehicle vehicle = jsonVehicleToObject(vehicleJson);

        JSONArray aidBoxJson = (JSONArray) jsonObject.get("AidBoxes");
        AidBox[] AidBoxes = jsonAidBoxesToObject(aidBoxJson);

        route = new RouteImpl(vehicle);
        for (AidBox aidbox : AidBoxes) {
            try {
                route.addAidBox(aidbox);
            } catch (RouteException ex) {
                ex.printStackTrace();
            }
        }

        return route;
    }

    /**
     * We read the given file with all the information about an institution (vehicles, aid boxes, picking maps) and add all of that to the given institution.
     *
     * @param filePath Given file path
     * @param institution Given institution
     * @throws IOException If there is any error related to the file reading.
     */
    public void importData(String filePath, Institution institution) throws IOException {

        try {
            FileReader file = new FileReader(filePath);

            JSONParser parser = new JSONParser();
            JSONObject instituionJSONObject = (JSONObject) parser.parse(file);

            JSONArray aidBoxJson = (JSONArray) instituionJSONObject.get("AidBoxes");
            AidBox[] AidBoxes = jsonAidBoxesToObject(aidBoxJson);

            JSONArray vehiclesJson = (JSONArray) instituionJSONObject.get("Veiculos");
            Vehicle[] vehicles = jsonVehiclesToObject(vehiclesJson);

            JSONArray pickingMapJson = (JSONArray) instituionJSONObject.get("PickingMap");
            PickingMap[] pickingMaps = jsonPickingMapsToObject(pickingMapJson);

            for (AidBox aid : AidBoxes) {
                institution.addAidBox(aid);
            }
            for (Vehicle ve : vehicles) {
                institution.addVehicle(ve);
            }

            for (PickingMap pick : pickingMaps) {
                institution.addPickingMap(pick);
            }
            file.close();

        } catch (ParseException | AidBoxException | VehicleException | PickingMapException ex) {
            throw new IOException("ERROR WILE READING FILE: " + ex.getMessage());
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("FILE NOT FOUND");
        } catch (IOException ex) {
            throw new IOException("ERROR WILE READING FILE: " + ex.getMessage());
        }

    }

    /**
     * We convert a given container code to a specific item type based on the first character of the code.
     *
     * @param code Given container code
     * @return ItemType
     */
    public ItemType codeToType(String code) {
        char charType = code.charAt(0);

        switch (charType) {
            case 'N':
                return ItemType.NON_PERISHABLE_FOOD;
            case 'P':
                return ItemType.PERISHABLE_FOOD;
            case 'M':
                return ItemType.MEDICINE;
            default:
                return ItemType.CLOTHING;
        }
    }

    /**
     * We convert a given string type to a specific item type.
     *
     * @param type Given string type
     * @return ItemType
     */
    public ItemType stringToType(String type) {

        switch (type) {
            case "NON_PERISHABLE_FOOD":
                return ItemType.NON_PERISHABLE_FOOD;
            case "PERISHABLE_FOOD":
                return ItemType.PERISHABLE_FOOD;
            case "MEDICINE":
                return ItemType.MEDICINE;
            default:
                return ItemType.CLOTHING;
        }
    }

}