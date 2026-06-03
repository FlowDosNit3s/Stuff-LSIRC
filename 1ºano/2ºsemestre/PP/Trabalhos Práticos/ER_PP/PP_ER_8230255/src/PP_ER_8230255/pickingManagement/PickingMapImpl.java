/*  
* Nome: Carlos Alberto Moreira Barbosa
* Número: 8230255
* Turma: LSIRC11T1
 */
package PP_ER_8230255.pickingManagement;

import com.estg.core.AidBox;
import com.estg.pickingManagement.*;
import java.io.Serializable;
import java.time.LocalDateTime;

public class PickingMapImpl implements PickingMap, Serializable {

    // The Picking Map date
    private final LocalDateTime date;

    // The Picking Map Routes
    private final Route[] routes;

    // The number of routes
    private final int nRoutes;

    /**
     * the constructor for the picking maps
     * @param date the pickig map date
     * @param routes the picking map routes
     */
    public PickingMapImpl(LocalDateTime date, Route[] routes) {
        this.date = date;

        Route[] temp = new Route[routes.length];
        int tempCounter = 0;
        for (Route route : routes) {
            temp[tempCounter++] = route;
        }

        this.nRoutes = tempCounter;
        this.routes = new Route[this.nRoutes];
        System.arraycopy(temp, 0, this.routes, 0, this.nRoutes);
    }

    /**
     * Getter for the date of the PickingMap
     *
     * @return the date of the PickingMap
     */
    @Override
    public LocalDateTime getDate() {
        return this.date;
    }

    /**
     * Getter for the routes of the PickingMap
     *
     * @return the routes of the PickingMap
     */
    @Override
    public Route[] getRoutes() {
        Route[] temp = new Route[this.nRoutes];
        int counter = 0;

        for (Route route : this.routes) {
            if (route != null) {
                temp[counter++] = this.getRouteClone(route);
            }
        }
        return temp;
    }

    /**
     * gets a copy of an given route
     *
     * @param route route to copy
     * @return a copy of an route
     */
    public Route getRouteClone(Route route) {
        Route newRoute = new RouteImpl(route.getVehicle(), route.getRoute(), route.getReport());
        return newRoute;
    }

    /**
     * Compare if the given object is equal to this object
     *
     * @param obj object to compare
     * @return true if the object is the same or false if the object is not the
     * same
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PickingMapImpl)) {
            return true;
        }

        PickingMapImpl pm = (PickingMapImpl) obj;
        return this.date.isEqual(pm.getDate());
    }

    /**
     * creates a clone of a picking map
     *
     * @return a copy of a picking map
     * @throws CloneNotSupportedException if the object class does not support
     * the interface(Cloneable)
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * gets a string with all the info
     *
     * @return an string with all the picking map info
     */
    @Override
    public String toString() {
        String s = "PICKING MAP:\n";
        s += "DATA: " + this.date + '\n';

        for (Route route : this.routes) {
            if (route != null) {
                s += route.toString();
            }
        }

        if (this.routes[0] != null) {
            s += "REPORT: " + this.routes[0].getReport().toString();
        }

        return s;
    }

    /**
     * gets a string with all the info
     *
     * @return an string with all the picking map info
     */
    public String printPickingMapInfo() {
        String s = "PICKING MAP:\n";
        s += "DATA: " + this.date + '\n';

        int i = 0;
        for (Route route : this.routes) {
            if (route != null) {
                s += "Route " + i++ + "\n";
                s += "Vehicle: " + route.getVehicle().getCode() + "\n";
                s += "AidBoxes:\n";
                for (AidBox aid : route.getRoute()) {
                    if (aid != null) {
                        s += aid.getCode() + "\n";
                    }
                }
                s += "---\n";

            }
        }

        if (this.routes[0] != null) {
            s += "" + this.routes[0].getReport().toString();
        }

        return s;
    }
    
}
