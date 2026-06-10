package trabalho_pp.pickingManagement;

import com.estg.pickingManagement.Report;
import java.time.LocalDateTime;

public class ReportImpl implements Report {
    private LocalDateTime date;
    private int totalNonPickedContainers;
    private int totalNonUsedVehicles;
    private int totalUsedVehicles;
    private int totalPickedContainers;
    private double totalDistance;
    private double totalDuration;

    public ReportImpl() {
        this.date = LocalDateTime.now();
        this.totalNonPickedContainers = 0;
        this.totalNonUsedVehicles = 0;
        this.totalUsedVehicles = 0;
        this.totalPickedContainers = 0;
        this.totalDistance = 0.0;
        this.totalDuration = 0.0;
    }

    public void setReportData(double totalDistance, double totalDuration, int totalNonPickedContainers, int totalNonUsedVehicles, int totalUsedVehicles, int totalPickedContainers) {
        this.totalDistance = totalDistance;
        this.totalDuration = totalDuration;
        this.totalNonPickedContainers = totalNonPickedContainers;
        this.totalNonUsedVehicles = totalNonUsedVehicles;
        this.totalUsedVehicles = totalUsedVehicles;
        this.totalPickedContainers = totalPickedContainers;
    }

    @Override
    public int getUsedVehicles() {
        return this.totalUsedVehicles;
    }

    @Override
    public int getNotUsedVehicles() {
        return this.totalNonUsedVehicles;
    }

    @Override
    public int getPickedContainers() {
        return this.totalPickedContainers;
    }

    @Override
    public int getNonPickedContainers() {
        return this.totalNonPickedContainers;
    }

    @Override
    public double getTotalDistance() {
        return this.totalDistance;
    }

    @Override
    public double getTotalDuration() {
        return this.totalDuration;
    }

    @Override
    public LocalDateTime getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Picking Report [").append(date).append("] ===\n");
        sb.append("  Used Vehicles: ").append(totalUsedVehicles).append("\n");
        sb.append("  Not Used Vehicles: ").append(totalNonUsedVehicles).append("\n");
        sb.append("  Picked Containers: ").append(totalPickedContainers).append("\n");
        sb.append("  Non-Picked Containers: ").append(totalNonPickedContainers).append("\n");
        sb.append("  Total Distance Covered: ").append(totalDistance).append(" m\n");
        sb.append("  Total Duration: ").append(totalDuration).append(" s\n");
        sb.append("=====================================\n");
        return sb.toString();
    }
}
