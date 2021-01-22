package strategies;

import entities.Producer;
import entities.Utils;

import java.util.ArrayList;
import java.util.stream.Collectors;

public final class GreenStrategy implements Strategy {

  private static final GreenStrategy instance = new GreenStrategy();

  /**
   * For conding style;
   */
  private GreenStrategy() {

  }

  public static GreenStrategy getInstance() {
    return instance;
  }
  @Override
  public ArrayList<Producer> selectProducers(ArrayList<Producer> producers) {
    return new ArrayList<Producer>(producers
            .stream()
            .sorted(Utils.sortProducersGreen())
            .collect(Collectors.toList()));
  }
}
