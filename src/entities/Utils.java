package entities;

import entities.Distributor;
import entities.EnergyType;

import java.util.Comparator;

public final class Utils {

  /**
   * For coding style
   */
  private Utils() {

  }

  /**
   * Comparator for sorting for lowest cost
   * @return
   */
  public static Comparator<Distributor> sortProdCost() {
    return new Comparator<Distributor>() {
      @Override
      public int compare(final Distributor d1, final Distributor d2) {
        return Double.compare(d1.calculateContractCost(), d2.calculateContractCost());
      }
    };
  }

  /**
   * Comparator for sorting by Id for output
   * @return
   */
  public static Comparator<Distributor> sortById() {
    return new Comparator<Distributor>() {
      @Override
      public int compare(final Distributor d1, final Distributor d2) {
        return Integer.compare(d1.getId(), d2.getId());
      }
    };
  }

  /**
   * Comparator for sorting producers by green energy
   * @return
   */
  public static Comparator<Producer> sortProducersGreen() {
    return new Comparator<Producer>() {
      @Override
      public int compare(final Producer p1, Producer p2) {
        if (!(p1.isGreen() && p2.isGreen())) {
          return Boolean.compare(p1.isGreen(), p2.isGreen());
        }

        if (Float.compare(p1.getPriceKW(), p2.getPriceKW()) != 0) {
          return Float.compare(p1.getPriceKW(), p1.getPriceKW());
        }

        if (Integer.compare(p1.getEnergyPerDistributor(), p2.getEnergyPerDistributor()) != 0) {
          return Integer.compare(p2.getEnergyPerDistributor(), p1.getEnergyPerDistributor());
        }
        return Integer.compare(p1.getId(), p2.getId());
      }
    };
  }

  /**
   * Comparator for sorting producers by price
   * @return
   */
  public static Comparator<Producer> sortProducersPrice() {
    return new Comparator<Producer>() {
      @Override
      public int compare(final Producer p1, Producer p2) {

        if (Float.compare(p1.getPriceKW(), p2.getPriceKW()) != 0) {
          return Float.compare(p1.getPriceKW(), p1.getPriceKW());
        }

        if (Integer.compare(p1.getEnergyPerDistributor(), p2.getEnergyPerDistributor()) != 0) {
          return Integer.compare(p2.getEnergyPerDistributor(), p1.getEnergyPerDistributor());
        }
        return Integer.compare(p1.getId(), p2.getId());
      }
    };
  }

  /**
   * Comparator for sorting producers by quantity
   * @return
   */
  public static Comparator<Producer> sortProducersQuantity() {
    return new Comparator<Producer>() {
      @Override
      public int compare(final Producer p1, Producer p2) {

        if (Integer.compare(p1.getEnergyPerDistributor(), p2.getEnergyPerDistributor()) != 0) {
          return Integer.compare(p2.getEnergyPerDistributor(), p1.getEnergyPerDistributor());
        }

        if (Float.compare(p1.getPriceKW(), p2.getPriceKW()) != 0) {
          return Float.compare(p1.getPriceKW(), p1.getPriceKW());
        }

        return Integer.compare(p1.getId(), p2.getId());
      }
    };
  }

  /**
   * Converts string to EnergyType enum
   * @param power string for power type
   * @return EnergyType enum
   */
  public static EnergyType stringToEnergy(String power) {
    switch (power) {
      case "WIND":
        return EnergyType.WIND;
      case "HYDRO":
        return EnergyType.HYDRO;
      case "SOLAR":
        return EnergyType.SOLAR;
      case "COAL":
        return EnergyType.COAL;
      case "NUCLEAR":
        return EnergyType.NUCLEAR;
      default:
        return null;
    }
  }
}
