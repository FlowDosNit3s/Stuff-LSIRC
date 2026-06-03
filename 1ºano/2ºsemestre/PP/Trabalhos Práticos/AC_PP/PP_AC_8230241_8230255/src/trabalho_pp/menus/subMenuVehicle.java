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

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Class responsible for the sub vehicle menu implementation
 */
public class subMenuVehicle {

    /**
     * Method to show the menu and get the user choice.
     *
     * @return int
     */
    public static int showVehicleMenu() {
        int opc = 0;
        boolean validNumber = false;

        Scanner input = new Scanner(System.in);
        do {
            System.out.println("Choose the vehicle item type");
            System.out.println("-------------------------\n");
            System.out.println("1. Perishable food");
            System.out.println("2. Non-perishable food");
            System.out.println("3. Clothing");
            System.out.println("4. Medicine");

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
}
