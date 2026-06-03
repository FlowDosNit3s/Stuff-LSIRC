/*  
* Nome: Carlos Alberto Moreira Barbosa
* Número: 8230255
* Turma: LSIRC11T1
 */
package PP_ER_8230255.pickingManagement;

import PP_ER_8230255.core.*;
import com.estg.core.*;
import com.estg.core.exceptions.*;
import com.estg.pickingManagement.*;
import com.estg.pickingManagement.exceptions.RouteException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class RouteGeneratorImpl implements RouteGenerator {

    /**
     * The collection strategy is to separate our aidboxes by sub routes, each
     * vehicle will have 4 routes (one for each container type) and DPS in the
     * end it will be all together in one. The first one will leave the
     * Institution and will collect all the aidboxes he can, when he can't pick
     * up more containers the next vehicle comes and collects, all the
     * containers that are collected will be replaced by containers not used in
     * the Institution
     */
    /**
     * Generates the routes for the given institution, strategy, route
     * restrictions and report
     *
     * @param instn the institution to generate the routes for
     * @return the generated routes
     */
    @Override
    public Route[] generateRoutes(Institution instn) {

        // select the enable vehicles for the routes
        Vehicle[] vehicles = selectEnableVehicles(instn.getVehicles());

        // all aidboxes of the institution
        AidBox[] aidBoxes = instn.getAidBoxes();

        // all possible container types
        ContainerType[] possibleTypes = ((InstitutionImpl) instn).getContainerTypes();

        Route[] subRoutes = new Route[5];
        int nSubRoutes = 0;

        int totalConatiners = 0;

        for (ContainerType type : possibleTypes) {

            AidBox[] possibleAidBoxesForTheType = validAidBoxesForTheType(aidBoxes, type);
            /*
            System.out.println("antes de ordenar");
            for (AidBox aid : possibleAidBoxesForTheType) {
                System.out.println(aid.getCode());
            }
             */

            int nRemainingAidBoxes = possibleAidBoxesForTheType.length;
            possibleAidBoxesForTheType = orderAidBoxesByDistances(possibleAidBoxesForTheType, instn);
            /*
            System.out.println("depois de ordenar");
            for (AidBox aid : possibleAidBoxesForTheType) {
                System.out.println(aid.getCode());
            }
             */

            for (Vehicle vehicle : vehicles) {
                Route route = new RouteImpl(vehicle);

                if (nRemainingAidBoxes == 0) {
                    continue;
                }

                double remaining_capacity = vehicle.getCapacity(type);

                for (int i = 0; i < possibleAidBoxesForTheType.length; i++) {
                    if (possibleAidBoxesForTheType[i] != null) {

                        if (remaining_capacity > 0) {
                            try {

                                Container containerToInsert = ((InstitutionImpl) instn).getFreeContainer(type);
                                Container containerToRemove = possibleAidBoxesForTheType[i].getContainer(type);

                                ((InstitutionImpl) instn).swapContainersInAidBox(possibleAidBoxesForTheType[i], containerToRemove, containerToInsert);
                                System.out.println("The Vehicle " + vehicle.getCode()
                                        + " swapped the container " + containerToRemove.getCode()
                                        + " for the container " + containerToInsert.getCode());

                                // adds the aidbox to the route
                                route.addAidBox(possibleAidBoxesForTheType[i]);
                                // decrease the number of the remaining aidboxes e the vehicle capacity
                                remaining_capacity--;
                                nRemainingAidBoxes--;

                                //increase the number of picked containers
                                totalConatiners++;
                                // remove the aidbox in the array of remaning aidboxes
                                possibleAidBoxesForTheType[i] = null;

                            } catch (RouteException | ContainerException ex) {
                                System.out.println(ex.getMessage());
                            }
                        }

                    }
                }

                if (nSubRoutes == subRoutes.length) {
                    subRoutes = increaseArray(subRoutes);
                }
                subRoutes[nSubRoutes++] = route;
            }

            // print how many containers as are left uncollected
            if (nRemainingAidBoxes > 0) {
                System.out.println("" + nRemainingAidBoxes + " " + type + " containers remained uncollected");
            }

            // Print the code of the containers that were left uncollected
            for (AidBox aidbox : possibleAidBoxesForTheType) {
                if (aidbox != null) {
                    System.out.println("- " + aidbox.getContainer(type).getCode());
                }
            }
        }

        // initialize all possible routes
        Route[] final_routes = initializeEveryRouteWithAVehicle(vehicles);
        int numberOfUsedRoutes = 0;

        for (Route route : final_routes) {
            combineRoutes(route, subRoutes, instn);
        }

        /*
        passes through all the aidboxes and if it doesn't have an aidbox, 
        the route is deleted from the array
         */
        boolean hasAidBoxes;
        for (int i = 0; i < final_routes.length; i++) {
            if (final_routes[i] != null) {
                hasAidBoxes = false;
                for (AidBox aid : final_routes[i].getRoute()) {
                    if (aid != null) {
                        hasAidBoxes = true;
                        numberOfUsedRoutes++;
                        try {
                            instn.disableVehicle(final_routes[i].getVehicle());
                            break;
                        } catch (VehicleException ex) {
                            System.out.println(ex.getMessage());
                        }

                    }
                }

                if (!hasAidBoxes) {
                    final_routes[i] = null;
                }
            }
        }

        Route[] resizedRoutes = new Route[numberOfUsedRoutes];
        int counter = 0;

        for (Route route : final_routes) {
            if (route != null) {
                resizedRoutes[counter++] = route;
            }
        }

        // generates a report about the generated routes
        Report report = generateReport(resizedRoutes, instn, totalConatiners);

        // det the report on the routes
        for (Route rt : resizedRoutes) {
            ((RouteImpl) rt).setReport(report);
        }

        // creates a new picking map and adds to the instituion
        try {
            PickingMap pm = new PickingMapImpl(LocalDateTime.now(), resizedRoutes);
            instn.addPickingMap(pm);
        } catch (PickingMapException ex) {
            System.out.println(ex.getMessage());
        }

        return resizedRoutes;

    }

    /**
     * Joins given submitted subroutes and returns an overall route
     *
     * @param route the original routes
     * @param routes the generated subroutes
     * @param instn the institution
     */
    public void combineRoutes(Route route, Route[] routes, Institution instn) {
        // gets the routes for the vehicle of the given route
        Route[] sameVehicleRoutes = groupRoutes(route, routes);

        AidBox[] routeAidBoxes = new AidBox[5];
        int counter = 0;

        /*
        Goes through all the aidboxes and stores them on a general route
         */
        for (Route rt : sameVehicleRoutes) {
            for (AidBox aid : rt.getRoute()) {
                if (aid != null) {
                    if (!containAidBox(routeAidBoxes, aid)) {
                        if (counter == routeAidBoxes.length) {
                            routeAidBoxes = increaseArray(routeAidBoxes);
                        }
                        routeAidBoxes[counter++] = aid;
                    }
                }

            }

        }

        AidBox[] resizedRouteAidBoxes = new AidBox[counter];
        System.arraycopy(routeAidBoxes, 0, resizedRouteAidBoxes, 0, counter);
        routeAidBoxes = resizedRouteAidBoxes;

        //oredenar rota
        routeAidBoxes = orderAidBoxesByDistances(routeAidBoxes, instn);

        // adds all aidboxes in the route
        for (AidBox aidbox : routeAidBoxes) {
            if (aidbox != null) {
                try {
                    route.addAidBox(aidbox);
                } catch (RouteException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }

    /**
     * Gathers all the aidboxes for the vehicle received on the route
     *
     * @param route route to get the vehicle
     * @param routes all routes
     * @return the routes covered by the given vehicle
     */
    public Route[] groupRoutes(Route route, Route[] routes) {
        Route[] sameVehicle = new Route[routes.length];
        int counter = 0;

        for (Route rt : routes) {
            if (rt != null) {
                if (route.getVehicle().equals(rt.getVehicle())) {
                    sameVehicle[counter++] = rt;
                }
            }
        }

        Route[] resized = new Route[counter];
        System.arraycopy(sameVehicle, 0, resized, 0, counter);
        return resized;
    }

    /**
     * returns if the aidbox are on the given array of aid boxes
     *
     * @param aidboxes array of aidboxes
     * @param aidbox aid box to see if are in the array
     * @return if the aid box are on the array
     */
    private boolean containAidBox(AidBox[] aidboxes, AidBox aidbox) {
        for (AidBox aid : aidboxes) {
            if (aid != null) {
                if (aid.equals(aidbox)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Select the valid aid boxes from the given array and return an array whit
     * the aid boxes, to be valid the aid boxes needs to have measurements or an
     * measurement with the date of the day
     *
     * @param aidBoxes the array of all aid boxes
     * @param type the type for the containers validation
     * @return an array with all valid aid boxes
     */
    public AidBox[] validAidBoxesForTheType(AidBox[] aidBoxes, ContainerType type) {
        boolean valid;
        int nValidAidBoxes = 0;
        AidBox[] temp = new AidBox[aidBoxes.length];

        for (AidBox aidbox : aidBoxes) {
            if (aidbox != null) {
                valid = true;

                // gets the aidbox container with the given type
                Container container = aidbox.getContainer(type);

                // if the aidbox dont have contaiers with the given type
                if (container != null) {
                    try {
                        // gets the last measurement
                        Measurement measurement = ((ContainerImpl) container).getLastMeasuremnt();

                        //if the last measurement lost her vality
                        if (!(measurement.getDate().toLocalDate().isEqual(LocalDate.now()))) {
                            valid = false;
                        }

                        // if the type is not perishable
                        if (!type.toString().equals("perishable food")) {
                            // if the container didnt reach 80% of the the total capacity
                            if (measurement.getValue() < container.getCapacity() * 0.8) {
                                valid = false;
                            }
                        }
                        if (valid) {
                            temp[nValidAidBoxes++] = aidbox;
                        }

                    } catch (MeasurementException ex) {
                    }
                }

            }
        }

        AidBox[] validAidBoxes = new AidBox[nValidAidBoxes];
        System.arraycopy(temp, 0, validAidBoxes, 0, nValidAidBoxes);
        return validAidBoxes;
    }

    /**
     * Initializes a route for each given vehicle
     *
     * @param vehicles vehicle to evry route
     * @return the initialized routes
     */
    private Route[] initializeEveryRouteWithAVehicle(Vehicle[] vehicles) {
        int counter = 0;
        Route[] temp = new Route[vehicles.length];

        for (Vehicle vehicle : vehicles) {
            if (vehicle != null) {
                temp[counter++] = new RouteImpl(vehicle);
            }
        }

        Route[] resizedTemp = new Route[counter];
        System.arraycopy(temp, 0, resizedTemp, 0, counter);

        return resizedTemp;
    }

    /**
     * select the enable vehicles in the given array
     *
     * @param vehicles vehicles to select
     * @return an array with selected vehicles(enable vehicles)
     */
    private Vehicle[] selectEnableVehicles(Vehicle[] vehicles) {
        int counter = 0;
        Vehicle[] temp = new Vehicle[vehicles.length];

        for (Vehicle vehicle : vehicles) {
            if (vehicle != null) {
                if (((VehicleImpl) vehicle).getStatus()) {
                    temp[counter++] = vehicle;
                }
            }
        }

        Vehicle[] resizedTemp = new Vehicle[counter];
        System.arraycopy(temp, 0, resizedTemp, 0, counter);
        return resizedTemp;
    }

    /**
     * duplicate the given array
     *
     * @param routes the array to duplicate
     * @return the dupplicated array
     */
    private Route[] increaseArray(Route[] routes) {
        Route[] temp = new Route[routes.length * 2];
        System.arraycopy(routes, 0, temp, 0, routes.length);
        return temp;
    }

    /**
     * duplicate the given array
     *
     * @param aidboxes the array to duplicate
     * @return the dupplicated array
     */
    private AidBox[] increaseArray(AidBox[] aidboxes) {
        AidBox[] temp = new AidBox[aidboxes.length * 2];
        System.arraycopy(aidboxes, 0, temp, 0, aidboxes.length);
        return temp;
    }

    /**
     * generates a report by the given data
     *
     * @param routes the generated routes
     * @param instn the instituion
     * @param pickedContainers number of picked containers in the routes
     *
     * @return the generated reports
     */
    private Report generateReport(Route[] routes, Institution instn, int pickedContainers) {
        ReportImpl report = new ReportImpl();

        int usedVehicles = routes.length;
        int nonPickedContainers = ((InstitutionImpl) instn).getNumberOfTotalContainers() - pickedContainers;
        int notUsedVehicles = ((InstitutionImpl) instn).getVehicles().length - usedVehicles;

        double totalDistance = 0;
        double totalDuration = 0;

        for (Route route : routes) {
            if (route != null) {
                totalDistance += route.getTotalDistance();
                totalDuration += route.getTotalDuration();
            }
        }

        report.setData(usedVehicles, pickedContainers, totalDistance, totalDuration, nonPickedContainers, notUsedVehicles);
        return report;
    }

    /**
     * order the aidboxes by the distances
     *
     * @param aidboxes all aidboxes
     * @param instn the institution
     * @return an array with all aidbox in order
     */
    private AidBox[] orderAidBoxesByDistances(AidBox[] aidboxes, Institution instn) {
        double[][] distances = getDistances(aidboxes);
        boolean[] visited = new boolean[aidboxes.length];

        AidBox[] path = new AidBox[aidboxes.length];
        int current = 0;

        AidBox closest = closestToTheBase(aidboxes, instn);
        if (closest == null) {
            return path;
        }
        int index = findAidBox(aidboxes, closest);

        path[current++] = aidboxes[index];
        visited[index] = true;

        for (int i = 1; i < aidboxes.length; i++) {
            int next = -1;
            double minDist = Double.MAX_VALUE;

            // Encontra a aidbox mais próxima que não foi visitada
            for (int j = 0; j < aidboxes.length; j++) {
                if (!visited[j] && distances[current][j] < minDist) {
                    next = j;
                    minDist = distances[current][j];
                }
            }

            // Marca a próxima aidbox como visitada e atualiza o caminho
            visited[next] = true;
            path[i] = aidboxes[next];
            current++;
        }

        return path;
    }

    /**
     * finds if the aidbox is on the array
     *
     * @param aidboxes the array to search the aidbox
     * @param aidbox the aidbox to shearch
     * @return the indez of the aidbox or -1 if the aidbox is not on the array
     */
    private int findAidBox(AidBox[] aidboxes, AidBox aidbox) {
        for (int i = 0; i < aidboxes.length; i++) {
            if (aidboxes[i].equals(aidbox)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Gets the closest aidbox to the base
     *
     * @param aidboxes the array of aid boxes
     * @return the closest aidbox to the base
     */
    private AidBox closestToTheBase(AidBox[] aidboxes, Institution instn) {
        double closestDistance = Double.MAX_VALUE, distance;
        AidBox closest = null;

        for (AidBox aidbox : aidboxes) {
            try {
                distance = instn.getDistance(aidbox);

                if (distance < closestDistance) {
                    closest = aidbox;
                    closestDistance = distance;

                }

            } catch (AidBoxException ex) {
                System.out.println(ex.getMessage());
            }

        }

        return closest;
    }

    /**
     * gets an array with all the distances between all aidboxes
     *
     * @param aidboxes all aidboxes
     * @return an array with all distances
     */
    private double[][] getDistances(AidBox[] aidboxes) {
        double[][] distances = new double[aidboxes.length][aidboxes.length];

        for (int i = 0; i < aidboxes.length; i++) {
            for (int j = 0; j < aidboxes.length; j++) {
                try {
                    distances[i][j] = aidboxes[i].getDistance(aidboxes[j]);
                } catch (AidBoxException ex) {
                    distances[i][j] = Double.MAX_VALUE;
                }
            }
        }

        return distances;
    }

}
