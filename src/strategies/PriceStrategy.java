package strategies;

import entities.Producer;
import entities.Utils;

import java.util.ArrayList;
import java.util.stream.Collectors;

public final class PriceStrategy implements Strategy {

  private static final PriceStrategy instance = new PriceStrategy();

  /**
   * For conding style;
   */
  private PriceStrategy() {

  }

  public static PriceStrategy getInstance() {
    return instance;
  }

  @Override
  public ArrayList<Producer> selectProducers(ArrayList<Producer> producers) {
    return new ArrayList<Producer>(producers
            .stream()
            .sorted(Utils.sortProducersPrice())
            .collect(Collectors.toList()));
  }
}
