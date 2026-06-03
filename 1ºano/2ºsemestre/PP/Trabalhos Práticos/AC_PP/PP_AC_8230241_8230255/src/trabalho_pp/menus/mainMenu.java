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
 * Class responsible for the main menu implementation
 */
public class mainMenu {

    /**
     * This method gives the user several options, as soon as the user enters a valid option, it returns
     * 
     * @param input the scanner
     * @return int
     */
    public static int showMenu(Scanner input) {
        int opc = 0;
        boolean validNumber = false;

        do {
            System.out.println("Choose from these choices");
            System.out.println("-------------------------\n");
            System.out.println("1 - Add Vehicle");
            System.out.println("2 - Generate Routes");
            System.out.println("3 - Institution Info");
            System.out.println("4 - Quit");

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
