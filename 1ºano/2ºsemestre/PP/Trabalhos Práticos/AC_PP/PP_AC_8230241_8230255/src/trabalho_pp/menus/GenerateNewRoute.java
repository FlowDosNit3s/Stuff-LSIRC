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
import com.estg.core.exceptions.PickingMapException;
import com.estg.pickingManagement.Report;
import com.estg.pickingManagement.Route;
import com.estg.pickingManagement.RouteGenerator;
import com.estg.pickingManagement.RouteValidator;
import com.estg.pickingManagement.Strategy;
import trabalho_pp.pickingManagement.ReportImpl;
import trabalho_pp.pickingManagement.RouteGeneratorImpl;
import trabalho_pp.pickingManagement.RouteValidatorImpl;
import trabalho_pp.pickingManagement.StrategyImpl;

public class GenerateNewRoute {

    /**
     * This function generate a new route, for an giving institution
     * 
     * @param institution the institution
     * @throws PickingMapException if an error occurs wile generating the routes
     */
    public static void generates(Institution institution) throws PickingMapException {
        RouteGenerator routeGenerator = new RouteGeneratorImpl();
        Report report = new ReportImpl();
        Strategy strategy = new StrategyImpl();
        RouteValidator routeValidator = new RouteValidatorImpl();
        Route[] array = routeGenerator.generateRoutes(institution, strategy, routeValidator, report);

        System.out.println("Generated Routes: " + array.length + "\n");

        for (Route routes : array) {
            System.out.println(routes.toString());
        }
        System.out.println("-----------------------------------");
        
        System.out.println("Report About Generated Route : ");
        System.out.println(report.toString());
        System.out.println("-----------------------------------");
    }
}
