/*  
* Nome: Carlos Alberto Moreira Barbosa
* Número: 8230255
* Turma: LSIRC11T1
 */
package PP_ER_8230255.main;

import PP_ER_8230255.core.InstitutionImpl;
import PP_ER_8230255.pickingManagement.Capacity;
import PP_ER_8230255.pickingManagement.RouteGeneratorImpl;
import PP_ER_8230255.pickingManagement.VehicleImpl;
import com.estg.core.AidBox;
import com.estg.core.ContainerType;
import com.estg.core.Institution;
import com.estg.core.exceptions.PickingMapException;
import com.estg.pickingManagement.PickingMap;
import com.estg.pickingManagement.Route;
import com.estg.pickingManagement.RouteGenerator;
import com.estg.pickingManagement.Vehicle;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menus {

    public static int showMenu(Scanner input) {
        int opc = 0;
        boolean validNumber = false;

        do {
            System.out.println("Choose from these choices");
            System.out.println("-------------------------\n");
            System.out.println("1 - Add Vehicle");
            System.out.println("2 - Generate Routes");
            System.out.println("3 - Show Last Picking Map");
            System.out.println("4 - Institution Info");
            System.out.println("5 - Quit");

            try {
                opc = input.nextInt();
                validNumber = true;
            } catch (InputMismatchException ex) {
                System.out.println("ENTER A VALID OPTION");
                input.next();
            }
        } while (!validNumber);

        return opc;
    }

    public static Vehicle showAddVehicleMenu(Scanner input, Institution instn) {
        ContainerType[] possibleTypes = ((InstitutionImpl) instn).getContainerTypes();
        Capacity[] capacities = new Capacity[possibleTypes.length];
        int counter = 0;

        input.nextLine();
        System.out.print("Enter the Code Of The Vehicle: \n");
        String vehicleCode = input.nextLine();

        for (ContainerType type : possibleTypes) {
            double vehicleCapacity = -1;
            boolean validNumber = false;

            do {
                System.out.println("Enter The Vehicle Capacity For " + type + ":");

                try {
                    vehicleCapacity = input.nextDouble();
                    validNumber = true;
                } catch (InputMismatchException ex) {
                    System.out.println("ENTER A VALID OPTION");
                    input.next();
                }
            } while (!validNumber && vehicleCapacity < 0);

            Capacity capacity = new Capacity(type, vehicleCapacity);
            capacities[counter++] = capacity;
        }

        Vehicle vehicle = new VehicleImpl(vehicleCode, capacities);
        return vehicle;
    }

    public static void generateRoute(Institution instn) {
        RouteGenerator routeGenerator = new RouteGeneratorImpl();
        Route[] routes = routeGenerator.generateRoutes(instn);

        System.out.println("\n\nGenerated Routes: " + routes.length + "\n");

        System.out.println("-----------------------------------");

        System.out.println("Report About Generated Route : ");
        if (routes.length != 0 && routes[0] != null ) {
            System.out.println(routes[0].getReport().toString());
        } else {
            System.out.println("NO ROUTES GENERATED");
        }
        System.out.println("-----------------------------------");
    }

    /**
     * return the last picking map in the institution
     *
     * @param instn the institution
     * @return the last picking map
     * @throws PickingMapException if the institution does not have picking maps
     */
    public PickingMap getLastPickingMap(Institution instn) throws PickingMapException {
        return instn.getCurrentPickingMap();
    }

    /**
     * shows the user the options to view the institution's information or to
     * view a specific aidbox details
     *
     * @param institution institution to get the info
     * @param input the scanner
     * @return String
     */
    public static String InstitutionInfo(Institution institution, Scanner input) {
        int opc = 0;
        boolean validNumber = false;
        do {
            System.out.println("Choose from these choices");
            System.out.println("-------------------------\n");
            System.out.println("1 - Show Institution Info");
            System.out.println("2 - Shows Information About a Specific AidBox");
            System.out.println("3 - Show All Vehicles In The Institution");
            System.out.println("4 - Back");

            try {
                opc = input.nextInt();
                validNumber = true;
            } catch (InputMismatchException ex) {
                System.out.println("ENTER A VALID OPTION");
                input.next();
            }
        } while (!validNumber || opc < 1 || opc > 4);

        switch (opc) {
            case 1:
                return Menus.showInstitutionInfo(institution);
            case 2:
                return Menus.showAidBoxInfo(input, institution);
            case 3:
                return Menus.showVehiclesInfo(institution);
            default:
                return "";
        }
    }

    /**
     * returns a string with all the information about a institution
     *
     * @param institution the instituion
     * @return String
     */
    private static String showInstitutionInfo(Institution institution) {
        return institution.toString();
    }

    /**
     * returns a string with all the information about the vehicles in the
     * institution
     *
     * @param institution the insitution
     * @return a string with all the vehicles in the institution
     */
    private static String showVehiclesInfo(Institution institution) {
        String s = "Vehicles In The Institution:\n";

        for (Vehicle vehicle : institution.getVehicles()) {
            s += "- " + vehicle.getCode() + "\n";
        }
        return s;
    }

    /**
     * this method ask the user for a specific aid box code, in order to get the
     * information, if the code is invalid return "INVALID CODE"
     *
     * @param input the scanner
     * @return String
     */
    private static String showAidBoxInfo(Scanner input, Institution institution) {

        input.nextLine();
        System.out.print("Enter the Code of an AidBox: ");
        String AidBoxcode = input.nextLine();

        AidBox[] aidboxes = ((InstitutionImpl) institution).getAidBoxes();

        for (AidBox aidbox : aidboxes) {
            if (aidbox.getCode().equalsIgnoreCase(AidBoxcode)) {
                return aidbox.toString();
            }
        }

        return "INVALID CODE";
    }

}
