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

import com.estg.core.Institution;
import java.util.InputMismatchException;
import java.util.Scanner;
import trabalho_pp.Data.APImethods;
import trabalho_pp.exceptions.ReadException;

public class ShowsInstitutionInfoMenu {

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
            System.out.println("3 - Back");

            try {
                opc = input.nextInt();
                validNumber = true;
            } catch (InputMismatchException ex) {
                System.out.println("ENTER A VALID OPTION");
                input.next();
            }
        } while (!validNumber || opc < 1 || opc > 3);

        switch (opc) {
            case 1:
                return ShowsInstitutionInfoMenu.showInstitutionInfo(institution);
            case 2:
                return showAidBoxInfo(input);
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
     * this method ask the user for a specific aid box code, in order to get the
     * information, if the code is invalid return "INVALID CODE"
     *
     * @param input the scanner
     * @return String
     */
    private static String showAidBoxInfo(Scanner input) {

        input.nextLine();
        System.out.print("Enter the Code of an AidBox: ");
        String AidBoxcode = input.nextLine();

        try {
            return APImethods.jsonToAidboxByCode(AidBoxcode).toString();
        } catch (ReadException ex) {
            return "INVALID CODE";
        }

    }
}
