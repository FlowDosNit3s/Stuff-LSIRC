/*
Nome: Jose Pedro Vieira Barbosa
Número: 8230241
Turma: LEIT3
*
Nome: Carlos Alberto Moreira Barbosa
Número: 8230255
Turma: LSIRCT2
 */
package trabalho_pp.pickingManagement;

import com.estg.core.AidBox;
import com.estg.core.Institution;
import com.estg.core.exceptions.PickingMapException;
import com.estg.pickingManagement.*;

/**
 * Class responsible for the Route Generator implementation
 */
public class RouteGeneratorImpl implements RouteGenerator {

    /**
     * Method that generate a route to a specific institution, it counts all the
     * information to the report and set all the data to specific report.
     * Creates a picking map with the generated routes and adds it to the given
     * institution picking maps collection.
     *
     * @param institution Institution
     * @param strategy Strategy
     * @param routeValidator Route Validator
     * @param report Report
     * @return Route[]
     * @throws PickingMapException If the provided Route is null.
     */
    @Override
    public Route[] generateRoutes(Institution institution, Strategy strategy, RouteValidator routeValidator, Report report) throws PickingMapException {
        Route[] routes = strategy.generate(institution, routeValidator);

        if (routes == null) {
            throw new PickingMapException("Generated routes array is null");
        }

        int totalPickedContainers = 0;
        int totalUsedVehicles = 0;
        double totalDistance = 0;
        double totalDuration = 0;

        for (Route route : routes) {
            if (route != null) {
                totalUsedVehicles++;
                totalPickedContainers += getPickedContainersExceptBases(route);
                totalDistance += route.getTotalDistance();
                totalDuration += route.getTotalDuration();
            }
        }

        int totalContainers = calculateTotalContainers(institution.getAidBoxes());
        int nonPickedContainers = (totalContainers - totalPickedContainers);
        int nonUsedVehicles = (institution.getVehicles().length - totalUsedVehicles);

        if (report instanceof ReportImpl) {
            ((ReportImpl) report).setReportData(totalDistance, totalDuration, nonPickedContainers, nonUsedVehicles, totalUsedVehicles, totalPickedContainers);
        }

        PickingMap pickingMap = new PickingMapImpl(routes);
        institution.addPickingMap(pickingMap);

        return routes;
    }

    /**
     * Method to calculate total containers of a collection of aid boxes.
     *
     * @param aidBoxes array of aid boxes to calculate total containers.
     * @return int
     */
    private int calculateTotalContainers(AidBox[] aidBoxes) {
        int totalContainers = 0;
        for (AidBox aidBox : aidBoxes) {
            totalContainers += aidBox.getContainers().length;
        }
        return totalContainers;
    }

    /**
     * method to decrement the base in total aid boxes on the route(if the
     * aidbox is on the base it is not considered as a picked container)
     *
     * @param route route
     * @return int
     */
    public int getPickedContainersExceptBases(Route route) {

        AidBox[] aidboxes = route.getRoute();
        int totalAidBoxes = aidboxes.length;

        for (AidBox aid : aidboxes) {
            if (aid.getCode().equals("Base")) {
                totalAidBoxes--;
            }
        }

        return totalAidBoxes;
    }

}