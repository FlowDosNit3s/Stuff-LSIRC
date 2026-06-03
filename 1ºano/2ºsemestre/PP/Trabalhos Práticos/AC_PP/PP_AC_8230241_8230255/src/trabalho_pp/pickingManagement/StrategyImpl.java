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

/**
 * Class responsible for the Strategy implementation
 */
public class StrategyImpl implements Strategy {

    /**
     * This function is used to create routes using the aid boxes received at
     * institution the strategy is to create a route for each vehicle and
     * separate them by type so that the containers can be treated
     * independently. Aid boxes will also be distributed to routes if they have a
     * container matching that route (it contains a container of the type of
     * the route). In the end, the routes are added to the same array, which will
     * then be returned.
     *
     * @param institution the institution
     * @param routeValidator The restrictions to the route
     * @return Route[]
     */
    @Override
    public Route[] generate(Institution institution, RouteValidator routeValidator) {
        Vehicle[] vehicles = institution.getVehicles();
        AidBox[] aidBoxes = institution.getAidBoxes();
        int MaxAidBoxes = aidBoxes.length;

        // counter for all vehicles Types
        int vehicleV = 0, vehicleM = 0, vehicleP = 0, vehicleN = 0;

        // see how many vehicles exists to make the routes
        for (Vehicle vehicle : vehicles) {
            if (vehicle != null) {
                switch (vehicle.getSupplyType()) {
                    case PERISHABLE_FOOD:
                        vehicleP++;
                        break;
                    case NON_PERISHABLE_FOOD:
                        vehicleN++;
                        break;
                    case CLOTHING:
                        vehicleV++;
                        break;
                    default:
                        vehicleM++;
                        break;
                }
            }
        }

        // create a route for every vehicle
        Route[] routeV = intitializeRoutes(vehicles, ItemType.CLOTHING, vehicleV);
        Route[] routeM = intitializeRoutes(vehicles, ItemType.MEDICINE, vehicleM);
        Route[] routeP = intitializeRoutes(vehicles, ItemType.PERISHABLE_FOOD, vehicleP);
        Route[] routeN = intitializeRoutes(vehicles, ItemType.NON_PERISHABLE_FOOD, vehicleN);

        //an array for every type
        AidBox[] Aidboxes_V = new AidBox[MaxAidBoxes];
        AidBox[] Aidboxes_M = new AidBox[MaxAidBoxes];
        AidBox[] Aidboxes_P = new AidBox[MaxAidBoxes];
        AidBox[] Aidboxes_N = new AidBox[MaxAidBoxes];

        // counters for every array
        int count_V = 0;
        int count_M = 0;
        int count_P = 0;
        int count_N = 0;

        // Separates all containers from all aid boxes by types(associate the aid box to the types)
        // if the aid box contains a container with that type, it will be associate to the array of the type
        for (int i = 0; i < aidBoxes.length; i++) {
            Container[] containers = aidBoxes[i].getContainers();
            for (int j = 0; j < containers.length; j++) {
                if (containers[j].getType() == ItemType.CLOTHING && count_V < MaxAidBoxes) {
                    Aidboxes_V[count_V++] = aidBoxes[i];
                } else if (containers[j].getType() == ItemType.MEDICINE && count_V < MaxAidBoxes) {
                    Aidboxes_M[count_M++] = aidBoxes[i];
                } else if (containers[j].getType() == ItemType.PERISHABLE_FOOD && count_V < MaxAidBoxes) {
                    Aidboxes_P[count_P++] = aidBoxes[i];
                } else if (containers[j].getType() == ItemType.NON_PERISHABLE_FOOD && count_V < MaxAidBoxes) {
                    Aidboxes_N[count_N++] = aidBoxes[i];
                }
            }
        }

        /**
         * will implement the routes for each type
         */
        System.out.println("A GERAR...");
        if (vehicleV != 0) {
            generateRoute(routeV, Aidboxes_V, routeValidator);
        }
        if (vehicleM != 0) {
            generateRoute(routeM, Aidboxes_M, routeValidator);
        }
        if (vehicleN != 0) {
            generateRoute(routeN, Aidboxes_N, routeValidator);
        }
        //perishable food
        if (vehicleP != 0) {
            generateRoute(routeP, Aidboxes_P, routeValidator);
        }

        /**
         * joins all aidboxes to the same array, the array will be initialized
         * with the size of the number of all vehicles, because each route
         * contains only one vehicle, i.e. the total number of routes will be
         * the number of vehicles
         */
        Route[] finalRoutes = new Route[vehicleV + vehicleM + vehicleN + vehicleP];

        int indexRoutes = 0;
        System.arraycopy(routeV, 0, finalRoutes, indexRoutes, vehicleV);

        indexRoutes += vehicleV;
        System.arraycopy(routeM, 0, finalRoutes, indexRoutes, vehicleM);

        indexRoutes += vehicleM;
        System.arraycopy(routeN, 0, finalRoutes, indexRoutes, vehicleN);

        indexRoutes += vehicleN;
        System.arraycopy(routeP, 0, finalRoutes, indexRoutes, vehicleP);

        return finalRoutes;
    }

    /**
     * This function separates all the aidboxes by the routes received by
     * argument, it will calculate the number of routes received of a specific
     * type, and these will be our possible routes.
     *
     * the strategy is to try to collect all the aidboxes of that type and
     * distribute them over the possible routes, first we will go through the
     * array of missing aidboxes and try to insert the possible aidboxes on the
     * same route, when it is not possible to collect anymore the vehicle will
     * go to the base and deliver all its cargo, to be able to collect the
     * remaining aid boxes. if there is more than one vehicle for that type, when
     * the first one is full and returns to the base, the aid boxes will be
     * distributed to that vehicle until it is not possible to insert anymore.
     * Aid boxes that are invalid for that route (exceed the vehicle's maximum
     * capacity or have no valid measurements) are excluded from the collection.
     * This cycle will repeat until all the aid boxes have been collected, or
     * exceed the number of times they can go to the base, which in this case is
     * 10.
     *
     * @param routes the routes to which the aidboxes will be added
     * @param aidBoxes all the aid boxes of the institution that will be
     * collected
     * @param routeValidator the restrictions for an aid box to be valid
     */
    private void generateRoute(Route[] routes, AidBox[] aidBoxes, RouteValidator routeValidator) {
        int totalPossibleRoutes = routes.length;
        int currentRoute = 0;

        AidBox[] remainingAidBoxes = aidBoxes;
        int counterRemainingAidBoxes;
        int remainingLoops = 10;
        boolean stay = true;

        while (stay) {
            for (int i = 0; i < remainingAidBoxes.length; i++) {
                if (remainingAidBoxes[i] != null) {
                    if (routeValidator.validate(routes[currentRoute], remainingAidBoxes[i])) {
                        try {
                            routes[currentRoute].addAidBox(remainingAidBoxes[i]);
                        } catch (RouteException | OutOfMemoryError e) {
                            e.printStackTrace();
                        }
                    } else if (!ValidAidBoxOnRoute(routes, remainingAidBoxes[i])) {
                        remainingAidBoxes[i] = null;
                    }
                }
            }

            AidBox base = new BaseCollection();
            try {
                routes[currentRoute].addAidBox(base);
            } catch (RouteException | OutOfMemoryError e) {
                e.printStackTrace();
            }

            counterRemainingAidBoxes = 0;
            AidBox[] newAidBoxArray = new AidBox[remainingAidBoxes.length];
            AidBox[] currentAidBoxes = routes[currentRoute].getRoute();

            for (AidBox aid : remainingAidBoxes) {
                if (aid != null) {
                    if (!findAidBoxInRoute(currentAidBoxes, aid)) {
                        newAidBoxArray[counterRemainingAidBoxes++] = aid;
                    }
                }
            }

            remainingAidBoxes = newAidBoxArray;

            if (++currentRoute == totalPossibleRoutes) {
                currentRoute = 0;
            }

            if (counterRemainingAidBoxes == 0 || remainingLoops-- == 0) {
                stay = false;
            }

        }

    }

    /**
     * Try to find if a specific aidBox is in the paramAidBoxes array.
     *
     * @param paramAidBoxes Aidboxes collection
     * @param aidBox Aidbox to verify
     * @return boolean
     */
    private boolean findAidBoxInRoute(AidBox[] paramAidBoxes, AidBox aidBox) {

        for (int i = 0; i < paramAidBoxes.length; i++) {
            if (paramAidBoxes[i].equals(aidBox)) {
                return true;
            }
        }

        return false;
    }

    /**
     * this function creates an array of routes and create a route for every
     * vehicle of that type
     *
     * @param vehicles the array of every vehicles of the institution
     * @param type the type of route
     * @param counterVehicles the number of vehicles of that type
     * @return the routes with the vehicles inserted
     */
    private Route[] intitializeRoutes(Vehicle[] vehicles, ItemType type, int counterVehicles) {
        int counter = 0;
        Route[] routeTemp = new Route[counterVehicles];

        for (Vehicle vehicle : vehicles) {
            if (vehicle.getSupplyType() == type) {
                routeTemp[counter++] = new RouteImpl(vehicle);
            }
        }
        return routeTemp;
    }

    /**
     * This function will see if the Aidbox I want to insert is valid, ie your
     * measurement is valid for that day or if the autal load exceeds the
     * capacity of the vehicle
     *
     *
     * @param routes routes
     * @param aidbox aidbox that i want to see if is valid
     * @return if the aidbox is valid our not
     */
    private boolean ValidAidBoxOnRoute(Route[] routes, AidBox aidbox) {

        ItemType RoutesType = routes[0].getVehicle().getSupplyType();
        Container container = aidbox.getContainer(RoutesType);

        Measurement[] measurements = container.getMeasurements();
        if (measurements.length == 0) {
            return false;
        }

        Measurement lastMeasurement = measurements[measurements.length - 1];
        if (!lastMeasurement.getDate().toLocalDate().equals(LocalDate.now())) {
            return false;
        }

        
        boolean isValid = false;
        for (Route route : routes) {
            if (route.getVehicle().getMaxCapacity() > lastMeasurement.getValue()) {
                isValid = true;
            }
        }
        return isValid;

    }

}
