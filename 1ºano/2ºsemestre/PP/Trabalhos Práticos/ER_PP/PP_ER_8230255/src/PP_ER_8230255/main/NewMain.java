/*  
* Nome: Carlos Alberto Moreira Barbosa
* Número: 8230255
* Turma: LSIRC11T1
 */
package PP_ER_8230255.main;

import com.estg.io.*;
import com.estg.core.*;
import com.estg.core.exceptions.*;
import com.estg.pickingManagement.*;
import com.estg.pickingManagement.exceptions.*;
import PP_ER_8230255.core.*;
import PP_ER_8230255.core.InstitutionImpl;
import PP_ER_8230255.io.*;
import PP_ER_8230255.pickingManagement.*;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author carlo
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
   
    public static void main(String[] args) {
        Institution instn = InstitutionImpl.getInstance("NAÇÕES UNIDAS");

        try {
            Importer importer = new ImporterImpl();
            importer.importData(instn);
        } catch (IOException | InstitutionException ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException();
        }

        //System.out.println(instn.toString());
        RouteGenerator generate = new RouteGeneratorImpl();
        generate.generateRoutes(instn);

        System.out.println(((InstitutionImpl) instn).printInsitutionInfo1());

    }

}
