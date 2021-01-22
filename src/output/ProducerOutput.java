package output;

import java.util.ArrayList;

public final class ProducerOutput {
  private int id;
  private int maxDistributors;
  private float priceKW;
  private String energyType;
  private int energyPerDistributor;
  private ArrayList<MonthlyStatsOutput> monthlyStats;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getMaxDistributors() {
    return maxDistributors;
  }

  public void setMaxDistributors(int maxDistributors) {
    this.maxDistributors = maxDistributors;
  }

  public float getPriceKW() {
    return priceKW;
  }

  public void setPriceKW(float priceKW) {
    this.priceKW = priceKW;
  }

  public String getEnergyType() {
    return energyType;
  }

  public void setEnergyType(String energyType) {
    this.energyType = energyType;
  }

  public int getEnergyPerDistributor() {
    return energyPerDistributor;
  }

  public void setEnergyPerDistributor(int energyPerDistributor) {
    this.energyPerDistributor = energyPerDistributor;
  }

  public ArrayList<MonthlyStatsOutput> getMonthlyStats() {
    return monthlyStats;
  }

  public void setMonthlyStats(ArrayList<MonthlyStatsOutput> monthlyStats) {
    this.monthlyStats = monthlyStats;
  }
}
