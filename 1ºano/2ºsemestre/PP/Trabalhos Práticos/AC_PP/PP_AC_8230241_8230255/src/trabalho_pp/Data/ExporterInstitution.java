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
import com.estg.pickingManagement.*;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import org.json.simple.*;
import trabalho_pp.core.AidBoxImpl;

/**
 * Class responsible for the Exporter Institution implementation
 */
public class ExporterInstitution {

    /**
     * We create a json array for the aid boxes to export to the json, we iterate all the given aid boxes and add each one as an object to the json array.
     *
     * @param aidboxes Aid boxes array
     * @return JSONArray
     */
    private JSONArray AidBoxesToJsonArray(AidBox[] aidboxes) {
        JSONArray aidBoxesJson = new JSONArray();

        for (AidBox aidBox : aidboxes) {
            aidBoxesJson.add(AidBoxToJSONObject(aidBox));
        }

        return aidBoxesJson;
    }

    /**
     * We create a json object and we add all the details from the given aidbox.
     *
     * @param aidbox Given aidbox
     * @return JSONObject
     */
    private JSONObject AidBoxToJSONObject(AidBox aidbox) {
        JSONObject aidBoxJson = new JSONObject();

        aidBoxJson.put("Codigo", aidbox.getCode());
        aidBoxJson.put("Zona", aidbox.getZone());
        aidBoxJson.put("Latitude", ((AidBoxImpl) aidbox).getLatitude());
        aidBoxJson.put("Longitude", ((AidBoxImpl) aidbox).getLongitude());
        aidBoxJson.put("Contentores", ContainersToJSONArray(aidbox.getContainers()));

        return aidBoxJson;
    }

    /**
     * We create a json array for the containers to export to the json, we iterate all the given containers and add each one as an object to the json array.
     *
     * @param containers Containers array
     * @return JSONArray
     */
    private JSONArray ContainersToJSONArray(Container[] containers) {
        JSONArray ContainersJson = new JSONArray();

        for (Container container : containers) {
            ContainersJson.add(ContainerToJSONObject(container));
        }

        return ContainersJson;
    }

    /**
     * We create a json object and we add all the details from the given container.
     *
     * @param container Given container
     * @return JSONObject
     */
    private JSONObject ContainerToJSONObject(Container container) {
        JSONObject containerJson = new JSONObject();

        containerJson.put("Codigo", container.getCode());
        containerJson.put("Capacidade", container.getCapacity());
        containerJson.put("Medicoes", MeasurementsToJSONArray(container.getMeasurements()));

        return containerJson;
    }

    /**
     * We create a json array for the measurements to export to the json, we iterate all the given measurements and add each one as an object to the json array.
     *
     * @param measurements Measurements array
     * @return JSONArray
     */
    private JSONArray MeasurementsToJSONArray(Measurement[] measurements) {
        JSONArray MeasurementsJson = new JSONArray();

        for (Measurement measurement : measurements) {
            MeasurementsJson.add(MeasurementToJSONObject(measurement));
        }

        return MeasurementsJson;
    }

    /**
     * We create a json object and we add all the details from the given measurement.
     *
     * @param measurement Given measurement
     * @return JSONObject
     */
    private JSONObject MeasurementToJSONObject(Measurement measurement) {
        JSONObject measurementJson = new JSONObject();

        measurementJson.put("Data", measurement.getDate().toString());
        measurementJson.put("Valor", measurement.getValue());

        return measurementJson;
    }


    /**
     * We create a json array for the vehiles to export to the json, we iterate all the given vehicles and add each one as an object to the json array.
     *
     * @param vehicles Vehicles array
     * @return JSONArray
     */
    private JSONArray VehiclesToJsonArray(Vehicle[] vehicles) {
        JSONArray vehiclesJson = new JSONArray();

        for (Vehicle vehicle : vehicles) {
            vehiclesJson.add(VehicleToJSONObject(vehicle));
        }

        return vehiclesJson;
    }

    /**
     * We create a json object and we add all the details from the given vehicle.
     *
     * @param vehicle Given vehicle
     * @return JSONObject
     */
    private JSONObject VehicleToJSONObject(Vehicle vehicle) {
        JSONObject vehicleJson = new JSONObject();

        vehicleJson.put("Tipo", vehicle.getSupplyType().toString());
        vehicleJson.put("Capacidade", vehicle.getMaxCapacity());

        return vehicleJson;
    }

    /**
     * We create a json array for the picking maps to export to the json, we iterate all the given picking maps and add each one as an object to the json array.
     *
     * @param pickingMaps Picking maps array
     * @return JSONArray
     */
    private JSONArray PickingMapsToJSONOArray(PickingMap[] pickingMaps) {
        JSONArray pickingMapsJson = new JSONArray();

        for (PickingMap pickingMap : pickingMaps) {
            pickingMapsJson.add(PickingMapToJSONObject(pickingMap));
        }

        return pickingMapsJson;
    }

    /**
     * We create a json object and we add all the details from the given picking map.
     *
     * @param pickingMap Given picking map
     * @return JSONObject
     */
    private JSONObject PickingMapToJSONObject(PickingMap pickingMap) {
        JSONObject pickingMapJson = new JSONObject();

        pickingMapJson.put("Data", pickingMap.getDate().toString());
        pickingMapJson.put("Rotas", RoutesToJsonArray(pickingMap.getRoutes()));

        return pickingMapJson;
    }

    /**
     * We create a json array for the routes to export to the json, we iterate all the given routes and add each one as an object to the json array.
     *
     * @param routes Routes array
     * @return JSONArray
     */
    private JSONArray RoutesToJsonArray(Route[] routes) {
        JSONArray routesJson = new JSONArray();

        for (Route route : routes) {
            routesJson.add(RouteToJSONObject(route));
        }

        return routesJson;
    }

    /**
     * We create a json object and we add all the details from the given route.
     *
     * @param route Given route
     * @return JSONObject
     */
    private JSONObject RouteToJSONObject(Route route) {
        JSONObject vehicleJson = new JSONObject();

        vehicleJson.put("Veiculo", VehicleToJSONObject(route.getVehicle()));
        vehicleJson.put("AidBoxes", AidBoxesToJsonArray(route.getRoute()));

        return vehicleJson;
    }

    /**
     * We create a json object and we add all the details from the given institution to the json file.
     *
     * @param filePath Given file path
     * @param institution Given institution
     */
    public void saveData(String filePath, Institution institution) {

        JSONObject institutionJson = new JSONObject();

        institutionJson.put("AidBoxes", AidBoxesToJsonArray(institution.getAidBoxes()));
        institutionJson.put("Veiculos", VehiclesToJsonArray(institution.getVehicles()));
        institutionJson.put("PickingMap", PickingMapsToJSONOArray(institution.getPickingMaps()));

        try {
            FileOutputStream file = new FileOutputStream(filePath);
            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(file));
            output.write(institutionJson.toJSONString());
            output.close();
            file.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
