package trabalho_pp.pickingManagement;

import com.estg.core.AidBox;
import com.estg.core.Container;
import com.estg.core.Institution;
import com.estg.core.ItemType;
import com.estg.core.Measurement;
import com.estg.pickingManagement.Route;
import com.estg.pickingManagement.RouteValidator;
import com.estg.pickingManagement.Strategy;
import com.estg.pickingManagement.Vehicle;
import com.estg.pickingManagement.exceptions.RouteException;
import java.time.LocalDate;

public class StrategyImpl implements Strategy {

    @Override
    public Route[] generate(Institution institution, RouteValidator routeValidator) {
        Vehicle[] allVehicles = institution.getVehicles();
        AidBox[] allAidBoxes = institution.getAidBoxes();
        int maxAidBoxes = allAidBoxes.length;

        // Count active vehicles by type
        int countV = 0, countM = 0, countP = 0, countN = 0;
        int activeCount = 0;
        for (Vehicle v : allVehicles) {
            if (v != null && ((VehicleImpl) v).isActive()) {
                activeCount++;
                switch (v.getSupplyType()) {
                    case PERISHABLE_FOOD:
                        countP++;
                        break;
                    case NON_PERISHABLE_FOOD:
                        countN++;
                        break;
                    case CLOTHING:
                        countV++;
                        break;
                    case MEDICINE:
                        countM++;
                        break;
                }
            }
        }

        // Initialize routes for active vehicles
        Route[] routesV = initializeRoutes(allVehicles, ItemType.CLOTHING, countV);
        Route[] routesM = initializeRoutes(allVehicles, ItemType.MEDICINE, countM);
        Route[] routesP = initializeRoutes(allVehicles, ItemType.PERISHABLE_FOOD, countP);
        Route[] routesN = initializeRoutes(allVehicles, ItemType.NON_PERISHABLE_FOOD, countN);

        // Separate compatible AidBoxes by type
        AidBox[] boxesV = new AidBox[maxAidBoxes];
        AidBox[] boxesM = new AidBox[maxAidBoxes];
        AidBox[] boxesP = new AidBox[maxAidBoxes];
        AidBox[] boxesN = new AidBox[maxAidBoxes];

        int idxV = 0, idxM = 0, idxP = 0, idxN = 0;
        for (AidBox box : allAidBoxes) {
            if (box == null) continue;
            Container[] containers = box.getContainers();
            for (Container c : containers) {
                if (c == null) continue;
                if (c.getType() == ItemType.CLOTHING) {
                    boxesV[idxV++] = box;
                } else if (c.getType() == ItemType.MEDICINE) {
                    boxesM[idxM++] = box;
                } else if (c.getType() == ItemType.PERISHABLE_FOOD) {
                    boxesP[idxP++] = box;
                } else if (c.getType() == ItemType.NON_PERISHABLE_FOOD) {
                    boxesN[idxN++] = box;
                }
            }
        }

        // Generate routes for each type
        if (countV > 0) generateRoute(routesV, boxesV, routeValidator);
        if (countM > 0) generateRoute(routesM, boxesM, routeValidator);
        if (countN > 0) generateRoute(routesN, boxesN, routeValidator);
        if (countP > 0) generateRoute(routesP, boxesP, routeValidator);

        // Concatenate all routes
        Route[] finalRoutes = new Route[activeCount];
        int idx = 0;
        System.arraycopy(routesV, 0, finalRoutes, idx, countV);
        idx += countV;
        System.arraycopy(routesM, 0, finalRoutes, idx, countM);
        idx += countM;
        System.arraycopy(routesN, 0, finalRoutes, idx, countN);
        idx += countN;
        System.arraycopy(routesP, 0, finalRoutes, idx, countP);

        return finalRoutes;
    }

    private Route[] initializeRoutes(Vehicle[] vehicles, ItemType type, int count) {
        Route[] temp = new Route[count];
        int idx = 0;
        for (Vehicle v : vehicles) {
            if (v != null && ((VehicleImpl) v).isActive() && v.getSupplyType() == type) {
                temp[idx++] = new RouteImpl(v);
            }
        }
        return temp;
    }

    private void generateRoute(Route[] routes, AidBox[] boxes, RouteValidator routeValidator) {
        int totalRoutes = routes.length;
        int currentRouteIdx = 0;
        AidBox[] remainingBoxes = boxes;
        int remainingLoops = 10;
        boolean continuing = true;

        while (continuing) {
            // Add initial Base if route is empty
            if (routes[currentRouteIdx].getRoute().length == 0) {
                try {
                    routes[currentRouteIdx].addAidBox(new BaseCollection());
                } catch (RouteException e) {
                    // ignore
                }
            }

            for (int i = 0; i < remainingBoxes.length; i++) {
                AidBox box = remainingBoxes[i];
                if (box != null) {
                    if (routeValidator.validate(routes[currentRouteIdx], box)) {
                        try {
                            routes[currentRouteIdx].addAidBox(box);
                        } catch (RouteException e) {
                            // ignore
                        }
                    } else if (!isValidAidBoxForAnyRoute(routes, box)) {
                        // If it can't fit in any route (e.g. exceeds capacity or no measurements), discard it
                        remainingBoxes[i] = null;
                    }
                }
            }

            // End route by returning to Base
            try {
                routes[currentRouteIdx].addAidBox(new BaseCollection());
            } catch (RouteException e) {
                // ignore
            }

            // Update remaining boxes
            int uncollectedCount = 0;
            AidBox[] newRemaining = new AidBox[remainingBoxes.length];
            AidBox[] currentRoutePath = routes[currentRouteIdx].getRoute();
            for (AidBox box : remainingBoxes) {
                if (box != null) {
                    if (!findAidBoxInRoute(currentRoutePath, box)) {
                        newRemaining[uncollectedCount++] = box;
                    }
                }
            }
            remainingBoxes = newRemaining;

            // Move to next route
            currentRouteIdx++;
            if (currentRouteIdx == totalRoutes) {
                currentRouteIdx = 0;
            }

            if (uncollectedCount == 0 || remainingLoops-- == 0) {
                continuing = false;
            }
        }
    }

    private boolean findAidBoxInRoute(AidBox[] path, AidBox box) {
        for (AidBox b : path) {
            if (b.equals(box) && !b.getCode().equalsIgnoreCase("Base")) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidAidBoxForAnyRoute(Route[] routes, AidBox box) {
        ItemType type = routes[0].getVehicle().getSupplyType();
        Container c = box.getContainer(type);
        if (c == null) return false;

        Measurement[] measurements = c.getMeasurements();
        if (measurements == null || measurements.length == 0) {
            return false;
        }

        Measurement last = measurements[measurements.length - 1];
        // Must fit at least in one vehicle's max capacity when vehicle is empty
        for (Route r : routes) {
            if (r.getVehicle().getMaxCapacity() >= last.getValue()) {
                return true;
            }
        }
        return false;
    }
}
