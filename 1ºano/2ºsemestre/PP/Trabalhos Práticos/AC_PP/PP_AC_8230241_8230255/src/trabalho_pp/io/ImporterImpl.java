/*
Nome: Jose Pedro Vieira Barbosa
Número: 8230241
Turma: LEIT3
*
Nome: Carlos Alberto Moreira Barbosa
Número: 8230255
Turma: LSIRCT2
 */
package trabalho_pp.io;

import trabalho_pp.Data.ImportInstitution;
import trabalho_pp.Data.APImethods;
import com.estg.core.Institution;
import com.estg.core.exceptions.InstitutionException;
import com.estg.io.HTTPProvider;
import com.estg.io.Importer;
import java.io.BufferedReader;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import trabalho_pp.Data.ImportDistanceFromJson;
import trabalho_pp.distances.Distances;

/**
 * Class responsible for the Importer implementation
 */
public class ImporterImpl implements Importer {

    /**
     * Reads the input JSON file
     *
     * This function import all the data from the json file (if there is any
     * content inside), otherwise imports the data from the web API and add this
     * information to the given institution
     *
     * @param institution Institution
     * @throws FileNotFoundException if the file does not exist
     * @throws IOException if the file cannot be read
     * @throws InstitutionException if the institution is null
     */
    @Override
    public void importData(Institution institution) throws FileNotFoundException, IOException, InstitutionException {
        if (institution == null) {
            throw new InstitutionException("The institution can not be null.");
        }

        HTTPProvider httpProvider = new HTTPProvider();
        ImportInstitution importer = new ImportInstitution();
        ImportDistanceFromJson importDistances = new ImportDistanceFromJson();
        Distances distances = Distances.getInstance();

        String urlmeasurements = "https://data.mongodb-api.com/app/data-docuz/endpoint/readings";
        String aidBoxesUrl = "https://data.mongodb-api.com/app/data-docuz/endpoint/aidboxes";

        try {

            File currentClass = new File(ImportDistanceFromJson.class.getProtectionDomain().getCodeSource().getLocation().toURI());

            String FilePath = currentClass.getParentFile().getParentFile().getPath();

            String FileInstitution = FilePath + "\\src\\trabalho_pp\\Institution.json";
            String FileDistances = FilePath + "\\src\\trabalho_pp\\Distances.json";

            try {
                importer.importData(FileInstitution, institution);

            } catch (IOException ex) {
                String aidboxesJSON = httpProvider.getFromURL(aidBoxesUrl);
                APImethods.importToInstitution(institution, aidboxesJSON);
            }

            String measurements = httpProvider.getFromURL(urlmeasurements);
            APImethods.importMeasurements(institution, measurements);

            importDistances.importAllDistances(FileDistances, distances);

        } catch (URISyntaxException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
