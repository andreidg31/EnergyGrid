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
}
