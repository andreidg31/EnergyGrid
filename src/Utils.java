import java.util.Comparator;

public final class Utils {
  public static Comparator<Distributor> sortProdCost() {
    return new Comparator<Distributor>() {
      @Override
      public int compare(Distributor d1, Distributor d2) {
        return Double.compare(d1.calculateContractCost(), d2.calculateContractCost());
      }
    };
  }

  public static Comparator<Distributor> sortById() {
    return new Comparator<Distributor>() {
      @Override
      public int compare(Distributor d1, Distributor d2) {
        return Integer.compare(d1.getId(), d2.getId());
      }
    };
  }
}
