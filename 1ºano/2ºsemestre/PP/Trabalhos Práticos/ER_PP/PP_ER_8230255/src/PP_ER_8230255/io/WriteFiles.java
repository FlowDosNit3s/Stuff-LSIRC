/*  
* Nome: Carlos Alberto Moreira Barbosa
* Número: 8230255
* Turma: LSIRC11T1
 */
package PP_ER_8230255.io;

import com.estg.core.AidBox;
import com.estg.core.Container;
import com.estg.core.Institution;
import com.estg.core.Measurement;
import com.estg.pickingManagement.PickingMap;
import com.estg.pickingManagement.Vehicle;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class WriteFiles {

    /**
     * This method captures the error message and the stack trace of the
     * provided exception, and writes this information to a log file, including
     * the date and time when the error occurred.
     *
     * @param ex the exception to be logged to the file
     */
    public static void writeErrorToFile(Exception ex) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("errors.log", true))) {

            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);

            String errorLog = "";
            errorLog += "----------------------------------------\n";
            errorLog += ex.getMessage() + "\n";
            errorLog += "----------------------------------------\n";
            errorLog += "Data Do Erro Ocorrido: " + LocalDateTime.now() + "\n";
            errorLog += "Stack Trace:\n " + sw.toString() + "\n";
            errorLog += "----------------------------------------\n\n\n";

            writer.write(errorLog);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Falha ao escrever no arquivo de log: " + e.getMessage());
        }
    }

    /**
     * This method serializes and writes the measurements of the institution to
     * a JSON file named "Measurements.json", and serializes the vehicles and
     * picking maps arrays to binary files named "Vehicles.ser" and
     * "PickingMaps.ser" respectively.
     *
     * @param institution the institution whose data is to be saved
     */
    public static void saveData(Institution institution) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Measurements.json", false))) {

            JSONArray measurementsJson = InstitutionMeasurementsToJSONArray(institution);

            writer.write(measurementsJson.toString());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Falha ao escrever no arquivo: " + e.getMessage());
        }

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("Vehicles.ser", false))) {
            Vehicle[] vehicles = institution.getVehicles();
            outputStream.writeObject(vehicles);
        } catch (IOException e) {
            System.err.println("Erro ao armazenar o array de pessoas: " + e.getMessage());
        }

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("PickingMaps.ser", false))) {
            PickingMap[] pickingMaps = institution.getPickingMaps();
            outputStream.writeObject(pickingMaps);
        } catch (IOException e) {
            System.err.println("Falha ao escrever no arquivo: " + e.getMessage());
        }
    }

    /**
     * Converts whole measurements from the Institution instance to a JSONArray.
     *
     * @param institution Institution instance which containins the
     * measurements.
     * @return MeasurementsJson
     */
    private static JSONArray InstitutionMeasurementsToJSONArray(Institution institution) {
        JSONArray MeasurementsJson = new JSONArray();

        for (AidBox aidbox : institution.getAidBoxes()) {
            for (Container container : aidbox.getContainers()) {
                for (Measurement measurement : container.getMeasurements()) {
                    if (measurement != null) {
                        MeasurementsJson.add(MeasurementToJSONObject(measurement, container.getCode()));
                    }

                }
            }
        }

        return MeasurementsJson;
    }

    /**
     * Converts a Measurement object to a JSONObject.
     *
     * @param measurement Measurement object to be converted.
     * @param code Code of the container that is associated with the
     * measurement.
     * @return measurementJson
     */
    private static JSONObject MeasurementToJSONObject(Measurement measurement, String code) {
        JSONObject measurementJson = new JSONObject();

        measurementJson.put("contentor", code);
        measurementJson.put("Data", measurement.getDate().toString());
        measurementJson.put("valor", measurement.getValue());

        return measurementJson;
    }

}
