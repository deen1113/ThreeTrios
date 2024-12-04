package cs3500.threetrios.providernew.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CardReader implements DeckReader {
  @Override
  public List read(File file) {
    List<Card> cards = new ArrayList<>();
    Scanner scanner;

    try {
      scanner = new Scanner(file);
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File not found");
    }

    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      String[] values = line.split(" ");

      if (values.length != 5) {
        throw new IllegalArgumentException("Invalid file format");
      }

      String name = values[0];
      List<Value> attackValues = new ArrayList<>();
      for (int i = 1; i < values.length; i++) {
        if (Integer.parseInt(values[i]) > 0 && Integer.parseInt(values[i]) < 10) {
          attackValues.add(Value.valueOf(values[i]));
        } else {
          throw new IllegalArgumentException("Invalid attack value");
        }
      }
      cards.add(new PlayCard(name, attackValues, PlayerColor.RED));
    }
    scanner.close();
    return cards;
  }
}
