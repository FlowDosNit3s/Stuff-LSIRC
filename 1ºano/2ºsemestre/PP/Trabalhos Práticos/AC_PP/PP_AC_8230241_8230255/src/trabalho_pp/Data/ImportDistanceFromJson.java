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

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import org.json.simple.*;
import org.json.simple.parser.*;
import trabalho_pp.distances.*;

/**
 * Class responsible for the Import Distance from Json implementation
 */
public class ImportDistanceFromJson {

    /**
     *  Converts the given information from the json object to instance of Distance.
     *
     * @param jsonObject Given json object with the information about the distance, duration and the destination.
     * @param From the "source" to calculate de distance
     * @return Distance
     */
    public Distance jsonDistanceToObject(JSONObject jsonObject, String From) {
        try {
            Distance distance = null;

            String To = (String) jsonObject.get("name");
            double distancia = ((Long) jsonObject.get("distance")).doubleValue();
            double duracao = ((Long) jsonObject.get("duration")).doubleValue();

            distance = new Distance(From, To, distancia, duracao);

            return distance;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }

    /**
     * Imports distances from a JSON file and adds them to the instance of distances collection.
     *
     * @param FilePath The path to the JSON file
     * @param distances Instance distances
     * @throws IOException if an Input/Output error occurs while reading the distances from file.
     */

    public void importAllDistances(String FilePath, Distances distances) throws IOException {
        try {
           
            FileReader file = new FileReader(FilePath);
            JSONParser parser = new JSONParser();
            JSONArray DistancesJSONArray = (JSONArray) parser.parse(file);

            for (int i = 0; i < DistancesJSONArray.size(); i++) {

                JSONObject distanceObj = (JSONObject) DistancesJSONArray.get(i);

                String From = (String) distanceObj.get("from");
                JSONArray DistanceToJSONArray = (JSONArray) distanceObj.get("to");

                for (int j = 0; j < DistanceToJSONArray.size(); j++) {
                    JSONObject distanceToObj = (JSONObject) DistanceToJSONArray.get(j);
                    Distance distance = jsonDistanceToObject(distanceToObj, From);

                    distances.addDistance(distance);
                }

            }
            file.close();

        } catch (IOException | ParseException ex) {
            ex.printStackTrace();
        }

    }
}