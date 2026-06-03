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

import com.estg.pickingManagement.Report;

import java.time.LocalDateTime;

/**
 * Class responsible for the Report implementation
 */
public class ReportImpl implements Report {

  /**
   * The Report's date
   */
  private LocalDateTime date;

  /**
   * The total non picked containers
   */
  private int totalNonPickedContainers = 0;

  /**
   * The total non used vehicles
   */
  private int totalNonUsedVehicles = 0;

  /**
   * The total used vehicles
   */
  private int totalUsedVehicles = 0;

  /**
   * The total picked containers
   */
  private int totalPickedContainers = 0;

  /**
   * The total distance
   */
  private double totalDistance = 0;

  /**
   * The total duration
   */
  private double totalDuration = 0;

  /**
   * Constructor for the Report implementation
   * The date is set to now.
   */
  public ReportImpl() {
    this.date = LocalDateTime.now();
  }

  /**
   * Method to set specific report data.
   *
   * @param totalDistance Total distance covered by vehicle.
   * @param totalDuration Total duration covered by vehicle.
   * @param totalNonPickedContainers Total non picked containers.
   * @param totalNonUsedVehicles Total non used vehicles.
   * @param totalUsedVehicles Total used vehicles.
   * @param totalPickedContainers Total picked containers.
   */
  public void setReportData(double totalDistance, double totalDuration, int totalNonPickedContainers, int totalNonUsedVehicles, int totalUsedVehicles, int totalPickedContainers) {
    this.totalDistance = totalDistance;
    this.totalDuration = totalDuration;
    this.totalUsedVehicles = totalUsedVehicles;
    this.totalNonUsedVehicles = totalNonUsedVehicles;
    this.totalPickedContainers = totalPickedContainers;
    this.totalNonPickedContainers = totalNonPickedContainers;
  }

  /**
   * Gets the total number of used vehicles.
   * @return int
   */
  @Override
  public int getUsedVehicles() {
    return this.totalUsedVehicles;
  }

  /**
   * Gets the total number of non used vehicles.
   * @return int
   */
  @Override
  public int getNotUsedVehicles() {
    return this.totalNonUsedVehicles;
  }

  /**
   * Gets the total number of picked containers.
   * @return int
   */
  @Override
  public int getPickedContainers() {
    return this.totalPickedContainers;
  }

  /**
   * Gets the total number of non picked containers.
   * @return int
   */
  @Override
  public int getNonPickedContainers() {
    return this.totalNonPickedContainers;
  }

  /**
   * Gets the total distance covered by the vehicle.
   * @return double
   */
  @Override
  public double getTotalDistance() {
    return this.totalDistance;
  }

  /**
   * Gets the total duration covered by the vehicle.
   * @return double
   */
  @Override
  public double getTotalDuration() {
    return this.totalDuration;
  }

  /**
   * Gets the date of the Report
   *
   * @return LocalDateTime
   */
  @Override
  public LocalDateTime getDate() {
    return this.date;
  }

  /**
   * Create a detailed string with all the report information.
   * @return String
   */
  public String toString(){
    String s = "Date Report: " + getDate() + '\n';
    s += "Total Non Used Vehicles: " + getNotUsedVehicles() + '\n';
    s += "Total Used Vehicles: " + getUsedVehicles() + '\n';
    s += "Total Non Picked Containers: " + getNonPickedContainers() + '\n';
    s += "Total Picked Containers: " + getPickedContainers() + '\n';
    s += "Total Distance: " + getTotalDistance() + '\n';
    s += "Total Duration: " + getTotalDuration() + '\n';

    return s;
  }

}
