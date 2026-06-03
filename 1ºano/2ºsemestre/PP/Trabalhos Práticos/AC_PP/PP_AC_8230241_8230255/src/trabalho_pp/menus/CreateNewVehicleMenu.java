/*
Nome: Jose Pedro Vieira Barbosa
Número: 8230241
Turma: LEIT3
*
Nome: Carlos Alberto Moreira Barbosa
Número: 8230255
Turma: LSIRCT2
 */
package trabalho_pp.menus;

import trabalho_pp.pickingManagement.*;
import com.estg.core.*;
import com.estg.core.exceptions.*;
import com.estg.pickingManagement.*;
import java.util.InputMismatchException;

import java.util.Scanner;

public class CreateNewVehicleMenu {

    /**
     * this function gives a user the chance to create a new vehicle, the
     * method asks for a vehicle type and capacity, and creates a new vehicle
     * with these characteristics
     *
     * @return one instance of a vehicle
     * @throws VehicleException if occurs an error wile creating an normal
     * vehicle or an refrigerated vehicle
     */
    public static Vehicle createNewVehicleMenu(Scanner input) throws VehicleException {
        int opcSubMenuVehicleType;
        ItemType itemType = null;
        do {
            opcSubMenuVehicleType = subMenuVehicle.showVehicleMenu();

            switch (opcSubMenuVehicleType) {
                case 1:
                    itemType = ItemType.PERISHABLE_FOOD;
                    break;
                case 2:
                    itemType = ItemType.NON_PERISHABLE_FOOD;
                    break;
                case 3:
                    itemType = ItemType.CLOTHING;
                    break;
                case 4:
                    itemType = ItemType.MEDICINE;
                    break;
                default:
                    System.out.println("Invalid option.");
            }

        } while (opcSubMenuVehicleType < 1 || opcSubMenuVehicleType > 4);

        double vehicleCapacity = 0;
        boolean validNumber = false;
        
        do {
            System.out.println("Enter the vehicle capacity: ");

            try {
                vehicleCapacity = input.nextDouble();
                validNumber = true;
            } catch (InputMismatchException ex) {
                System.out.println("ENTER A VALID OPTION");
                input.next();
            }
        } while (!validNumber && vehicleCapacity <= 0);
                
        VehicleImpl vehicle;

        if (itemType == ItemType.PERISHABLE_FOOD) {
            vehicle = new RefrigeratedVehiclesImpl(vehicleCapacity);
        } else {
            vehicle = new NormalVehicleImpl(itemType, vehicleCapacity);
        }

        return vehicle;
    }
}
