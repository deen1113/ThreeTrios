package cs3500.threetrios.providernew.model;

import java.io.File;
import java.util.List;

/**
 * A reader designed to take in configuration files and read them in context of a Three Trios Deck.
 */
public interface DeckReader<C extends Card<C>> {

  /**
   * Reads from a valid deck config file and build a deck out of it.
   *
   * @param file the given config file
   * @return a List of our current implementation of Card.
   * @throws IllegalArgumentException if file is null
   * @throws IllegalArgumentException if file is not formatted correctly to build a Card.
   */
  List<C> read(File file);
}
