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
import com.estg.core.ItemType;
import com.estg.core.Measurement;
import com.estg.core.exceptions.AidBoxException;
import com.estg.pickingManagement.Route;
import com.estg.pickingManagement.RouteValidator;
import com.estg.pickingManagement.Vehicle;
import trabalho_pp.core.InstitutionImpl;

import java.time.LocalDate;

/**
 * Class responsible for the Route Validator implementation
 */
public class RouteValidatorImpl implements RouteValidator {

    //The maximum distance that a refrigerated vehicle can drive
    private static final double MAX_REFRIGERATED_VEHICLE_DISTANCE = 30000.0;

    /**
     * Validates if the given aidbox is valid to add to the route, if the aidbox
     * have a valid container type for the route vehicle item type if the max
     * capacity of the vehicle is not exceeded and if the aidbox is not on the
     * route.
     *
     * @param route The route to validate.
     * @param aidBox The aid box to validate.
     *
     * @return boolean
     */
    @Override
    public boolean validate(Route route, AidBox aidBox) {
        if (!validateContainerType(route, aidBox)) {
            return false;
        }

        if (!validateMaxCapacity(route, aidBox)) {
            return false;
        }

        if (route.getVehicle().getSupplyType() == ItemType.PERISHABLE_FOOD) {
            if (exceedsRefrigeratedDistance(route, aidBox)) {
                return false;
            }
        }

        return !isAidBoxInRoute(route, aidBox);
    }

    /**
     * Checks if the aid box is already in the route.
     *
     * @param route The route to check.
     * @param aidBox The aid box to check.
     * @return boolean true if the aid box is already in the route.
     */
    public boolean isAidBoxInRoute(Route route, AidBox aidBox) {
        for (AidBox aid : route.getRoute()) {
            if (aidBox.equals(aid)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Validates if the container type in the aid box matches the vehicle's
     * supply type.
     *
     * @param route The route to validate.
     * @param aidBox The aid box to check if it as container type that matches
     * the vehicle sypply type.
     * @return boolean true if the container type matches the vehicle's supply
     * type.
     */
    public boolean validateContainerType(Route route, AidBox aidBox) {
        Vehicle vehicle = route.getVehicle();
        Container container = aidBox.getContainer(vehicle.getSupplyType());
        return container != null;
    }

    /**
     * Validates if the total capacity of the route plus the new container value
     * to add does not exceed the vehicle's maximum capacity, if the aidbox have
     * a valid container, if the container have measurements and checks if the
     * last measurement of the container is equals to current date.
     *
     * @param route the route to validate
     * @param aidBox The aid box to get the container with the route vehicle
     * supply type.
     * @return boolean
     */
    public boolean validateMaxCapacity(Route route, AidBox aidBox) {
        Vehicle vehicle = route.getVehicle();
        Container container = aidBox.getContainer(vehicle.getSupplyType());

        if (container == null) {
            return false;
        }

        Measurement[] measurements = container.getMeasurements();
        if (measurements.length == 0) {
            return false;
        }

        Measurement lastMeasurement = measurements[measurements.length - 1];
        if (!lastMeasurement.getDate().toLocalDate().equals(LocalDate.now())) {
            return false;
        }

        double containerValue = lastMeasurement.getValue();
        return calculateTotalRouteCapacity(route) + containerValue <= vehicle.getMaxCapacity();
    }

    /**
     * Calculates the total route capacity going to the last measurement value
     * of each container of the aid boxes that have a specific item type
     * matching the vehicle of the route.
     *
     * @param route The route that we want to check the current capacity .
     * @return double
     */
    private double calculateTotalRouteCapacity(Route route) {
        double totalRouteCapacity = 0;

        for (AidBox aidbox : route.getRoute()) {
            if (aidbox.getCode().equals("Base")) {
                totalRouteCapacity = 0;
            }
            Container container = aidbox.getContainer(route.getVehicle().getSupplyType());

            if (container != null) {
                Measurement[] measurements = container.getMeasurements();
                if (measurements.length != 0) {
                    //gets the last measurement value.
                    double containerValue = measurements[measurements.length - 1].getValue();
                    totalRouteCapacity += containerValue;
                }
            }
        }

        return totalRouteCapacity;
    }

    /**
     * This function will calculate if Aidbox or is inserted on the route will
     * exceed the maximum capacity that a refrigerated vehicle can exceed during
     * collection
     *
     * @param route the atual route
     * @param aidBox the aidbox that i want to insert
     * @return is the aidbox exceeds the maximum distance or not
     */
    private boolean exceedsRefrigeratedDistance(Route route, AidBox aidBox) {
        Vehicle vehicle = route.getVehicle();
        if (vehicle.getSupplyType() == ItemType.PERISHABLE_FOOD) {
            try {
                double projectedDistance = this.calculateTotalDistanceInRoute(route) + calculateDistanceToAdd(route, aidBox);
                return projectedDistance > MAX_REFRIGERATED_VEHICLE_DISTANCE;
            } catch (AidBoxException ex) {
                System.out.println(ex.getMessage());
            }

        }
        return false;
    }

    /**
     * This function, gets the total distance in route, if the vehicle goes to
     * the base the total distance resets to 0
     *
     * @param route atual route
     * @return double, the atual distance in route
     */
    private double calculateTotalDistanceInRoute(Route route) {
        double totalRouteDistance = 0;

        AidBox[] aidboxes = route.getRoute();
        try {
            for (int i = 0; i < aidboxes.length - 1; i++) {
                //if the actual aid box is on the base, gets the distance to the next aid box
                if (aidboxes[i].getCode().equals("Base")) {
                    totalRouteDistance += aidboxes[i + 1].getDistance(new BaseCollection());
                //if the next aid box is the base, the distance is 0
                } else if (aidboxes[i + 1].getCode().equals("Base")) {
                    totalRouteDistance = 0;
                //none of the cases above, calculates normal
                } else {
                    totalRouteDistance += aidboxes[i].getDistance(aidboxes[i + 1]);
                }
            }
        } catch (AidBoxException ex) {
            System.out.println("ERROR" + ex.getMessage());
            return 0;
        }

        return totalRouteDistance;
    }

    /**
     * This function will calculate the distance necessary to go through the Aid
     * box we want to collect, to calculate this it goes to the last Aid box of
     * the route and calculates the distance between this Aid box to the other
     * that we want to insert
     *
     * @param route the route
     * @param aidBox the aidbox that i want to insert
     * @return The distance necessary to go through the Aidbox
     * @throws AidBoxException if an error occurs wile calculating the distance
     */
    private double calculateDistanceToAdd(Route route, AidBox aidBox) throws AidBoxException {
        AidBox[] AidboxesOnRoute = route.getRoute();

        try {
            if (AidboxesOnRoute.length != 0) {
                if (AidboxesOnRoute[AidboxesOnRoute.length - 1].getCode().equals("Base")) {
                    return (new InstitutionImpl("Base")).getDistance(aidBox);
                } else {
                    return AidboxesOnRoute[AidboxesOnRoute.length - 1].getDistance(aidBox);
                }
            }
        } catch (AidBoxException ex) {
            throw new AidBoxException("The distance between aid boxes is invalid");
        }

        return 0;
    }
}
