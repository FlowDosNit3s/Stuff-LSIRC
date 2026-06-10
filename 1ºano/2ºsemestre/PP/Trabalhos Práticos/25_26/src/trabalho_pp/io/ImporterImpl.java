package trabalho_pp.io;

import com.estg.core.*;
import com.estg.core.exceptions.AidBoxException;
import com.estg.core.exceptions.ContainerException;
import com.estg.core.exceptions.InstitutionException;
import com.estg.io.Importer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import trabalho_pp.core.AidBoxImpl;
import trabalho_pp.core.ContainerImpl;
import trabalho_pp.core.GeographicCoordinatesImpl;
import trabalho_pp.distances.Distance;
import trabalho_pp.distances.Distances;

public class ImporterImpl implements Importer {
    private String aidBoxesFile = "AidBoxes.json";
    private String distancesFile = "Distances.json";

    public ImporterImpl() {
        // default files
    }

    public ImporterImpl(String aidBoxesFile, String distancesFile) {
        this.aidBoxesFile = aidBoxesFile;
        this.distancesFile = distancesFile;
    }

    private File resolveFile(String filepath) throws FileNotFoundException {
        File f = new File(filepath);
        if (f.exists()) return f;

        // Try standard filename relative locations
        String filename = new File(filepath).getName();
        String[] potentialPaths = {
            "Recursos/jsonFiles/jsonFiles/" + filename,
            "c:\\Users\\diogo\\Documents\\GitHub\\Stuff-LSIRC\\1ºano\\2ºsemestre\\PP\\Trabalhos Práticos\\25_26\\Recursos\\jsonFiles\\jsonFiles\\" + filename,
            "../Recursos/jsonFiles/jsonFiles/" + filename,
            "../../Recursos/jsonFiles/jsonFiles/" + filename
        };

        for (String path : potentialPaths) {
            File temp = new File(path);
            if (temp.exists()) {
                return temp;
            }
        }
        throw new FileNotFoundException("Could not resolve JSON file path: " + filepath);
    }

    @Override
    public void importData(Institution institution) throws FileNotFoundException, IOException, InstitutionException {
        if (institution == null) {
            throw new InstitutionException("Institution cannot be null");
        }

        File abFile = resolveFile(aidBoxesFile);
        File distFile = resolveFile(distancesFile);

        JSONParser parser = new JSONParser();

        // 1. Import AidBoxes
        try (FileReader reader = new FileReader(abFile)) {
            JSONArray boxesArray = (JSONArray) parser.parse(reader);
            for (Object obj : boxesArray) {
                JSONObject boxJson = (JSONObject) obj;
                String code = (String) boxJson.get("Codigo");
                String zone = (String) boxJson.get("Zona");
                double latitude = ((Number) boxJson.get("Latitude")).doubleValue();
                double longitude = ((Number) boxJson.get("Longitude")).doubleValue();

                GeographicCoordinates coords = new GeographicCoordinatesImpl(latitude, longitude);
                AidBox aidBox = new AidBoxImpl(code, zone, coords);

                // Import Containers for this AidBox
                JSONArray containersArray = (JSONArray) boxJson.get("Contentores");
                if (containersArray != null) {
                    for (Object cObj : containersArray) {
                        JSONObject containerJson = (JSONObject) cObj;
                        String containerCode = (String) containerJson.get("codigo");
                        double capacity = ((Number) containerJson.get("capacidade")).doubleValue();

                        ItemType itemType = mapCodeToItemType(containerCode);
                        if (itemType != null) {
                            Container container = new ContainerImpl(containerCode, capacity, itemType);
                            try {
                                aidBox.addContainer(container);
                            } catch (ContainerException e) {
                                // Container duplicate or invalid type, ignore or handle
                            }
                        }
                    }
                }

                try {
                    institution.addAidBox(aidBox);
                } catch (AidBoxException e) {
                    // duplicate or invalid, ignore
                }
            }
        } catch (ParseException e) {
            throw new IOException("Error parsing AidBoxes.json: " + e.getMessage(), e);
        }

        // 2. Import Distances
        Distances.getInstance().clear();
        try (FileReader reader = new FileReader(distFile)) {
            JSONArray distancesArray = (JSONArray) parser.parse(reader);
            for (Object obj : distancesArray) {
                JSONObject distJson = (JSONObject) obj;
                String from = (String) distJson.get("from");
                JSONArray toArray = (JSONArray) distJson.get("to");

                if (toArray != null) {
                    for (Object toObj : toArray) {
                        JSONObject toDestination = (JSONObject) toObj;
                        String to = (String) toDestination.get("name");
                        double distance = ((Number) toDestination.get("distance")).doubleValue();
                        double duration = ((Number) toDestination.get("duration")).doubleValue();

                        Distance dist = new Distance(from, to, distance, duration);
                        Distances.getInstance().addDistance(dist);
                    }
                }
            }
        } catch (ParseException e) {
            throw new IOException("Error parsing Distances.json: " + e.getMessage(), e);
        }
    }

    private ItemType mapCodeToItemType(String containerCode) {
        if (containerCode == null || containerCode.isEmpty()) {
            return null;
        }
        char prefix = Character.toUpperCase(containerCode.charAt(0));
        switch (prefix) {
            case 'N':
                return ItemType.NON_PERISHABLE_FOOD;
            case 'V':
                return ItemType.CLOTHING;
            case 'M':
                return ItemType.MEDICINE;
            case 'P':
                return ItemType.PERISHABLE_FOOD;
            default:
                return null;
        }
    }
}
