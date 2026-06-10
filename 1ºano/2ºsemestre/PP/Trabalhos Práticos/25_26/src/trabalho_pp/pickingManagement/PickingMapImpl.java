package trabalho_pp.pickingManagement;

import com.estg.pickingManagement.PickingMap;
import com.estg.pickingManagement.Route;
import java.time.LocalDateTime;

public class PickingMapImpl implements PickingMap {
    private LocalDateTime date;
    private Route[] routes;

    public PickingMapImpl(LocalDateTime date, Route[] routes) {
        this.date = date;
        this.routes = routes;
    }

    @Override
    public LocalDateTime getDate() {
        return this.date;
    }

    @Override
    public Route[] getRoutes() {
        Route[] copy = new Route[this.routes.length];
        System.arraycopy(this.routes, 0, copy, 0, this.routes.length);
        return copy;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Picking Map [").append(date).append("] ===\n");
        for (int i = 0; i < routes.length; i++) {
            sb.append("Route #").append(i + 1).append(":\n");
            sb.append(routes[i].toString());
        }
        sb.append("=============================\n");
        return sb.toString();
    }
}
