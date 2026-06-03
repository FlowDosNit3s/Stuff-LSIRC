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

import com.estg.pickingManagement.PickingMap;
import com.estg.pickingManagement.Route;

import java.time.LocalDateTime;

/**
 * Class responsible for the Picking Map implementation
 */
public class PickingMapImpl implements PickingMap {

    /**
     * The Picking Map's date
     */
    private LocalDateTime date;

    /**
     * The Picking Map's routes array
     */
    private Route[] routes;

    /**
     * The Picking Map's number of routes
     */
    private int nRoutes;

    /**
     * The Picking Map's initial number of routes
     */
    private static final int INITIAL_ROUTES = 10;

    /**
     * Constructor for the Picking Map The nRoutes is set as 0, the routes array
     * is initialized with the initial size of 10 and the date is set to a
     * specific date.
     */
    public PickingMapImpl(LocalDateTime date) {
        this();
        this.date = date;
    }

    /**
     * Constructor for the Picking Map The nRoutes is set as 0, the routes array
     * is initialized with the initial size of 10 and the date is set to now.
     */
    public PickingMapImpl() {
        this.date = LocalDateTime.now();
        this.nRoutes = 0;
        this.routes = new Route[INITIAL_ROUTES];
    }

    /**
     * Constructor for the Picking Map The nRoutes is set as the number of
     * routes, the routes array is initialized with the initial size of the
     * given routes array length and the date is set to now.
     */
    public PickingMapImpl(Route[] routes) {
        this.date = LocalDateTime.now();
        this.routes = new Route[routes.length];

        int counter = 0;

        for (Route route : routes) {
            if (route != null) {
                this.routes[counter++] = route;
            }
        }

        this.nRoutes = counter;
    }

    /**
     * Constructor for the Picking Map The nRoutes is set as the number of
     * routes, the routes array is initialized with the initial size of the
     * given routes array length and the date is set to the given date.
     */
    public PickingMapImpl(LocalDateTime date, Route[] routes) {
        this.date = date;
        this.routes = new Route[routes.length];

        int counter = 0;

        for (Route route : routes) {
            if (route != null) {
                this.routes[counter++] = route;
            }
        }

        this.nRoutes = counter;
    }

    /**
     * Gets the date of the Picking Map
     *
     * @return LocalDateTime
     */
    @Override
    public LocalDateTime getDate() {
        return this.date;
    }

    /**
     * Creates a temporary array then copy the current array pickingmaps[] to
     * the temporary one and return.
     *
     * @return Route[]
     */
    @Override
    public Route[] getRoutes() {
        Route[] tmpRoutes = new Route[this.nRoutes];

        for (int i = 0; i < this.nRoutes; i++) {
            tmpRoutes[i] = this.routes[i];
        }

        return tmpRoutes;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PickingMapImpl)) {
            return false;
        }

        PickingMapImpl pickingMapImpl = (PickingMapImpl) obj;
        return (this.getDate().toLocalDate()).compareTo(pickingMapImpl.getDate().toLocalDate()) == 0;
    }

    /**
     * Create a detailed string with all the picking maps information.
     *
     * @return String
     */
    public String toString() {
        String s = "Date: " + this.getDate() + '\n';

        for (int i = 0; i < nRoutes; i++) {
            s += this.routes[i].toString();
        }

        return s;
    }

}