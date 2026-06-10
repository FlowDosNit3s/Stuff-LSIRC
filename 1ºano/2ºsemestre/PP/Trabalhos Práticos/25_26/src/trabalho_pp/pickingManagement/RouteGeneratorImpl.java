package trabalho_pp.pickingManagement;

import com.estg.core.AidBox;
import com.estg.core.Institution;
import com.estg.core.exceptions.PickingMapException;
import com.estg.pickingManagement.*;
import java.time.LocalDateTime;

public class RouteGeneratorImpl implements RouteGenerator {

    @Override
    public Route[] generateRoutes(Institution institution, Strategy strategy, RouteValidator routeValidator, Report report) {
        if (institution == null || strategy == null || routeValidator == null) {
            throw new IllegalArgumentException("Parameters cannot be null");
        }

        Route[] routes = strategy.generate(institution, routeValidator);
        if (routes == null) {
            throw new IllegalStateException("Generated routes array is null");
        }

        int totalPickedContainers = 0;
        int totalUsedVehicles = 0;
        double totalDistance = 0.0;
        double totalDuration = 0.0;

        for (Route route : routes) {
            if (route != null) {
                totalUsedVehicles++;
                totalPickedContainers += getPickedContainersExceptBases(route);
                totalDistance += route.getTotalDistance();
                totalDuration += route.getTotalDuration();
            }
        }

        // Count total containers in the institution
        int totalContainersInInstitution = 0;
        for (AidBox ab : institution.getAidBoxes()) {
            if (ab != null) {
                totalContainersInInstitution += ab.getContainers().length;
            }
        }

        int nonPickedContainers = Math.max(0, totalContainersInInstitution - totalPickedContainers);
        int totalVehiclesInInstitution = institution.getVehicles().length;
        int nonUsedVehicles = Math.max(0, totalVehiclesInInstitution - totalUsedVehicles);

        if (report instanceof ReportImpl) {
            ((ReportImpl) report).setReportData(
                totalDistance,
                totalDuration,
                nonPickedContainers,
                nonUsedVehicles,
                totalUsedVehicles,
                totalPickedContainers
            );
        }

        // Create PickingMap and add it to the institution
        PickingMap pickingMap = new PickingMapImpl(LocalDateTime.now(), routes);
        try {
            institution.addPickingMap(pickingMap);
        } catch (com.estg.core.exceptions.PickingMapException e) {
            throw new RuntimeException("Error adding picking map: " + e.getMessage());
        }

        return routes;
    }

    private int getPickedContainersExceptBases(Route route) {
        AidBox[] path = route.getRoute();
        int count = 0;
        for (AidBox ab : path) {
            if (ab != null && !ab.getCode().equalsIgnoreCase("Base")) {
                count++;
            }
        }
        return count;
    }
}
