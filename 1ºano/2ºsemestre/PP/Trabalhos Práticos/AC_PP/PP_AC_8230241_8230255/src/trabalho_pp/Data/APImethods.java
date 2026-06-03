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

import trabalho_pp.core.*;
import trabalho_pp.exceptions.*;
import org.json.simple.*;
import org.json.simple.parser.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import com.estg.core.*;
import com.estg.core.exceptions.*;
import com.estg.io.HTTPProvider;

/**
 * Class responsible for the Json Methods implementation
 */
public class APImethods {

    /**
     * We use the json library to get the information about an aid box and we
     * create an instance of AidboxImpl with all the information.
     *
     * @param jsonString Json String that contains information about the aid box.
     * @return AidBox
     * @throws ReadException If the given aid box does not exist on the API.
     */
    public static AidBox jsonToAidbox(String jsonString) throws ReadException {
        if (jsonString.equals("null")) {
            throw new ReadException("The AidBox does not exist.");
        }

        AidBox aidbox = null;

        try {
            JSONParser parser = new JSONParser();
            JSONObject objectJson = (JSONObject) parser.parse(jsonString);

            String codigo = (String) objectJson.get("Codigo");
            String zona = (String) objectJson.get("Zona");
            double latitude = ((Double) objectJson.get("Latitude"));
            double longitude = ((Double) objectJson.get("Longitude"));
            JSONArray contentoresJson = (JSONArray) objectJson.get("Contentores");

            GeographicCoordinatesImpl coords = new GeographicCoordinatesImpl(latitude, longitude);
            Container[] containers = jsonToContainer(contentoresJson);

            aidbox = new AidBoxImpl(codigo, zona, coords, containers);

        } catch (ParseException e) {
            e.getStackTrace();
        }

        return aidbox;
    }

    /**
     * We use the json library to get the information about an aidbox and we
     * create an instance of AidboxImpl with all the information.
     *
     * @param jsonObj Json Object that contains information about the aidbox.
     * @return AidBox
     */
    private static AidBox jsonToAidbox(JSONObject jsonObj) {
        AidBox aidbox = null;

        String codigo = (String) jsonObj.get("Codigo");
        String zona = (String) jsonObj.get("Zona");
        double latitude = ((Double) jsonObj.get("Latitude"));
        double longitude = ((Double) jsonObj.get("Longitude"));
        JSONArray contentoresJson = (JSONArray) jsonObj.get("Contentores");

        GeographicCoordinatesImpl cords = new GeographicCoordinatesImpl(latitude, longitude);
        Container[] containers = jsonToContainer(contentoresJson);

        aidbox = new AidBoxImpl(codigo, zona, cords, containers);

        return aidbox;
    }

    /**
     * We use the json library to get the information about all the containers
     * of the aidbox, we create an array of containers and we save all the
     * information of all the containers.
     *
     * @param jsonarray Json Array that contains information about all the
     * containers of the aidbox.
     * @return Container[]
     */
    private static Container[] jsonToContainer(JSONArray jsonarray) {

        Container[] containers = new Container[jsonarray.size()];

        for (int i = 0; i < jsonarray.size(); i++) {
            JSONObject containerobj = (JSONObject) jsonarray.get(i);

            String code = (String) containerobj.get("codigo");
            int capacity = ((Long) containerobj.get("capacidade")).intValue();

            char charType = code.charAt(0);

            ItemType type;
            switch (charType) {
                case 'N':
                    type = ItemType.NON_PERISHABLE_FOOD;
                    break;
                case 'P':
                    type = ItemType.PERISHABLE_FOOD;
                    break;
                case 'M':
                    type = ItemType.MEDICINE;
                    break;
                default:
                    type = ItemType.CLOTHING;
            }

            ContainerImpl x = new ContainerImpl(code, capacity, type);
            containers[i] = x;
        }

        return containers;
    }

    /**
     * We use the json library to get all the information about the existing
     * aidboxes in the API, we create an array of AidBoxes with all the
     * information and return.
     *
     * @param jsonString Json String that contains information about all the
     * aidboxes.
     * @return AidBox[]
     */
    public static AidBox[] importAidBoxes(String jsonString) {
        AidBox[] aidboxes = null;
        try {
            JSONParser parser = new JSONParser();
            JSONArray objectJson = (JSONArray) parser.parse(jsonString);

            aidboxes = new AidBox[objectJson.size()];

            for (int i = 0; i < objectJson.size(); i++) {
                JSONObject aidBoxobj = (JSONObject) objectJson.get(i);

                AidBox a = APImethods.jsonToAidbox(aidBoxobj);

                aidboxes[i] = a;
            }
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }

        return aidboxes;
    }

    /**
     * We create an array of aidboxes importing all aidboxes from json and we add them to the given institution.
     *
     * @param institution Given institution
     * @param aidboxesJSON Given string aid boxes json from API
     */
    public static void importToInstitution(Institution institution, String aidboxesJSON){
        AidBox[] aidboxes = APImethods.importAidBoxes(aidboxesJSON);

        for (int i = 0; i < aidboxes.length; i++) {
            if (aidboxes[i] != null) {
                try {
                    institution.addAidBox(aidboxes[i]);
                } catch (AidBoxException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    /**
     * We use the json library to get all the information about the measurements
     * in the API, we create an instance of MeasurementImpl with all the
     * information and add them to the containers of the Institution.
     *
     * @param institution Institution
     * @param jsonString Json String that contains information about all the
     * measurements.
     */
    public static void importMeasurements(Institution institution, String jsonString) {

        try {
            JSONParser parser = new JSONParser();
            JSONArray objectJson = (JSONArray) parser.parse(jsonString);

            for (int i = 0; i < objectJson.size(); i++) {
                JSONObject measurementObj = (JSONObject) objectJson.get(i);

                String code = (String) measurementObj.get("contentor");
                String date = (String) measurementObj.get("data");
                int value = ((Long) measurementObj.get("valor")).intValue();

                DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
                ZonedDateTime zonedDateTime = ZonedDateTime.parse(date, formatter);
                LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();

                Measurement measurement = new MeasurementImpl(localDateTime, value);
                Container container = ((InstitutionImpl)institution).getContainer(code);
                try {
                    institution.addMeasurement(measurement, container);
                } catch (ContainerException | MeasurementException e) {
                    e.getStackTrace();
                }

            }

        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }

    }
    
    
    /**
     * This function returns an instance of aidbox, based on the information received by the api
     * 
     * @param code the code of an aidbox
     * @return AidBox
     * @throws ReadException if an error occurs while reading teh api imformation
     */
    public static AidBox jsonToAidboxByCode(String code) throws ReadException{
        
        HTTPProvider httprovider = new HTTPProvider();
        String url = "https://data.mongodb-api.com/app/data-docuz/endpoint/aidboxesbyid?codigo=" + code;
        String jsonString = httprovider.getFromURL(url);
        
        AidBox aidbox = jsonToAidbox(jsonString);
        
        return aidbox;
    }
    
    
}
