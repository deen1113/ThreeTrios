package cs3500.threetrios.providernew.view;

import cs3500.threetrios.providernew.controller.PlayerAction;
import cs3500.threetrios.providernew.model.Card;
import cs3500.threetrios.providernew.model.ReadOnlyTrioModel;

/**
 * Interface for the Three Trios game GUI view.
 */
public interface TriosViewGUI<C extends Card<C>> {

  /**
   * Adds a click listener to handle mouse events.
   */
  void addPlayerListener(PlayerAction listener);

  /**
   * Refreshes the updated view.
   */
  void refresh();

  /**
   * Makes the view visible.
   */
  void makeVisible();

  /**
   * Sets the model for the view and makes sure we only use ReadOnly vers for the model.
   */
  void setModel(ReadOnlyTrioModel<C> model);

  void setHeader(String title);
}