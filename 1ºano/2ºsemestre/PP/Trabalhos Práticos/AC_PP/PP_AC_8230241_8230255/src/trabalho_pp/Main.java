/*
Nome: Jose Pedro Vieira Barbosa
Número: 8230241
Turma: LEIT3
*
Nome: Carlos Alberto Moreira Barbosa
Número: 8230255
Turma: LSIRCT2
*/

package trabalho_pp;

import trabalho_pp.io.ImporterImpl;
import trabalho_pp.core.InstitutionImpl;
import trabalho_pp.Data.ExporterInstitution;
import trabalho_pp.menus.*;
import com.estg.core.*;
import com.estg.core.exceptions.*;

import java.io.*;
import java.net.URISyntaxException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Institution institution = new InstitutionImpl("Nações Unidas");
        
        
        try {
            //import all the data from json to institution
            ImporterImpl importer = new ImporterImpl();
            importer.importData(institution);

        } catch (InstitutionException | IOException ex) {
            throw new RuntimeException("THE DATA HAS NOT BEEN LOADED");
        }

        int mainOption;
        Scanner input = new Scanner(System.in);

        do {
            mainOption = mainMenu.showMenu(input);

            switch (mainOption) {
                case 1:
                    try {
                    institution.addVehicle(CreateNewVehicleMenu.createNewVehicleMenu(input));
                } catch (VehicleException ex) {
                    System.out.println("ERROR:" + ex.getMessage());
                }
                break;

                case 2:
                    try {
                    GenerateNewRoute.generates(institution);
                } catch (PickingMapException ex) {
                    System.out.println("ERROR: " + ex.getMessage());
                }
                break;

                case 3:
                    System.out.println(ShowsInstitutionInfoMenu.InstitutionInfo(institution, input));
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Invalid option.");
            }

        } while (mainOption != 4);

        input.close();

        ExporterInstitution export = new ExporterInstitution();

        try {
            File currentClass = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            String FilePath = currentClass.getParentFile().getParentFile().getPath();
            String FileInstitution = FilePath + "\\src\\trabalho_pp\\Institution.json";

            export.saveData(FileInstitution, institution);
        } catch (URISyntaxException ex) {
            System.out.println("ERROR: Data has not been saved");
        }

    }

}
