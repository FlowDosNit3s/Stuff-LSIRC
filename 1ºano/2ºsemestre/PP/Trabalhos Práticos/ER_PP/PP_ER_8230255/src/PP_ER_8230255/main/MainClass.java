/*  
* Nome: Carlos Alberto Moreira Barbosa
* Número: 8230255
* Turma: LSIRC11T1
 */
package PP_ER_8230255.main;

import PP_ER_8230255.core.InstitutionImpl;
import PP_ER_8230255.io.ImporterImpl;
import PP_ER_8230255.io.WriteFiles;
import PP_ER_8230255.pickingManagement.PickingMapImpl;
import com.estg.core.Institution;
import com.estg.core.exceptions.InstitutionException;
import com.estg.core.exceptions.PickingMapException;
import com.estg.core.exceptions.VehicleException;
import com.estg.io.Importer;
import com.estg.pickingManagement.PickingMap;
import java.io.IOException;
import java.util.Scanner;

public class MainClass {

    public static void main(String[] args) {
        Institution instn =  InstitutionImpl.getInstance("NAÇÕES UNIDAS");

        try {
            Importer importer = new ImporterImpl();
            importer.importData(instn);
        } catch (IOException | InstitutionException ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException();
        }

        int mainOption;
        Scanner input = new Scanner(System.in);

        do {
            mainOption = Menus.showMenu(input);

            switch (mainOption) {
                case 1:
                    try {
                    instn.addVehicle(Menus.showAddVehicleMenu(input, instn));
                    System.out.println("Vehicle Added Successfully");
                } catch (VehicleException ex) {
                    System.out.println("ERROR:" + ex.getMessage());
                }
                break;

                case 2:
                    Menus.generateRoute(instn);
                    break;

                case 3:
                    try {
                    PickingMap currentPickingMap = instn.getCurrentPickingMap();
                    System.out.println(((PickingMapImpl) currentPickingMap).printPickingMapInfo());
                } catch (PickingMapException ex) {
                    System.out.println(ex.getMessage());
                }

                break;
                case 4:
                    System.out.println(Menus.InstitutionInfo(instn, input));
                    break;

                case 5:
                    break;
                default:
                    System.out.println("Invalid option.");
            }

        } while (mainOption != 5);
        
        WriteFiles.saveData(instn);

    }

}
