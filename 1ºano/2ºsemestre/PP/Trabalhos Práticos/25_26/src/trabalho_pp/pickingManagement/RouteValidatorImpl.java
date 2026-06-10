package trabalho_pp.pickingManagement;

import com.estg.core.AidBox;
import com.estg.core.Container;
import com.estg.core.ItemType;
import com.estg.core.Measurement;
import com.estg.core.exceptions.AidBoxException;
import com.estg.pickingManagement.Route;
import com.estg.pickingManagement.RouteValidator;
import com.estg.pickingManagement.Vehicle;
import java.time.LocalDate;

public class RouteValidatorImpl implements RouteValidator {

    @Override
    public boolean validate(Route route, AidBox aidBox) {
        if (route == null || aidBox == null) {
            return false;
        }

        // 1. If it's Base, it is always valid to add
        if (aidBox.getCode().equalsIgnoreCase("Base")) {
            return true;
        }

        // 2. Check compatibility: AidBox must have a container of the vehicle's supply type
        Vehicle vehicle = route.getVehicle();
        Container container = aidBox.getContainer(vehicle.getSupplyType());
        if (container == null) {
            return false;
        }

        // 3. Check if AidBox is already in the route
        for (AidBox ab : route.getRoute()) {
            if (ab.equals(aidBox) && !ab.getCode().equalsIgnoreCase("Base")) {
                return false;
            }
        }

        // 4. Validate capacity constraints
        if (!validateCapacity(route, container)) {
            return false;
        }

        // 5. Validate distance constraints for Refrigerated Vehicles
        if (vehicle instanceof RefrigeratedVehiclesImpl) {
            if (!validateRefrigeratedDistance(route, aidBox, (RefrigeratedVehiclesImpl) vehicle)) {
                return false;
            }
        }

        return true;
    }

    private boolean validateCapacity(Route route, Container newContainer) {
        Vehicle vehicle = route.getVehicle();
        
        // Sum the load of all containers currently in the route
        double currentTotalLoad = 0.0;
        for (AidBox ab : route.getRoute()) {
            if (ab.getCode().equalsIgnoreCase("Base")) {
                continue;
            }
            Container c = ab.getContainer(vehicle.getSupplyType());
            if (c != null) {
                currentTotalLoad += getLatestMeasurementValue(c);
            }
        }

        double newLoad = getLatestMeasurementValue(newContainer);
        return (currentTotalLoad + newLoad) <= vehicle.getMaxCapacity();
    }

    private double getLatestMeasurementValue(Container container) {
        Measurement[] measurements = container.getMeasurements();
        if (measurements == null || measurements.length == 0) {
            return 0.0;
        }
        // Return value of the latest measurement
        return measurements[measurements.length - 1].getValue();
    }

    private boolean validateRefrigeratedDistance(Route route, AidBox newAidBox, RefrigeratedVehiclesImpl refrigeratedVehicle) {
        // Calculate the projected distance if we add the newAidBox before returning to Base.
        // The path is typically: Base -> Box1 -> Box2 -> ... -> BoxN -> Base
        // If we add newAidBox, the projected path: Base -> Box1 -> Box2 -> ... -> BoxN -> newAidBox -> Base
        AidBox[] currentBoxes = route.getRoute();
        
        double projectedDistance = 0.0;
        
        try {
            if (currentBoxes.length == 0) {
                // Path: Base -> newAidBox -> Base
                double d1 = newAidBox.getDistance(new BaseCollection());
                projectedDistance = d1 * 2; // to and from Base
            } else {
                // Find the last box before the ending Base (if there's a Base at the end)
                // Otherwise, get the last box in the array
                AidBox lastBox = currentBoxes[currentBoxes.length - 1];
                
                if (lastBox.getCode().equalsIgnoreCase("Base")) {
                    // Route already ends with Base.
                    if (currentBoxes.length >= 2) {
                        AidBox beforeBase = currentBoxes[currentBoxes.length - 2];
                        // Remove beforeBase -> Base, add beforeBase -> newAidBox -> Base
                        double baseDist = beforeBase.getDistance(new BaseCollection());
                        double toNewDist = beforeBase.getDistance(newAidBox);
                        double newToBaseDist = newAidBox.getDistance(new BaseCollection());
                        
                        projectedDistance = route.getTotalDistance() - baseDist + toNewDist + newToBaseDist;
                    } else {
                        // Route only contains Base.
                        double d1 = newAidBox.getDistance(new BaseCollection());
                        projectedDistance = d1 * 2;
                    }
                } else {
                    // Route does not end with Base. We add lastBox -> newAidBox -> Base
                    double toNewDist = lastBox.getDistance(newAidBox);
                    double newToBaseDist = newAidBox.getDistance(new BaseCollection());
                    
                    projectedDistance = route.getTotalDistance() + toNewDist + newToBaseDist;
                }
            }
            
            return projectedDistance <= refrigeratedVehicle.getMaxKmWithLoad();
            
        } catch (AidBoxException e) {
            return false; // if distance cannot be calculated, do not allow
        }
    }
}
