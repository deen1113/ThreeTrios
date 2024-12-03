package cs3500.threetrios.provider.view;

import controller.IPlayerActions;

/**
 * This interface defines all the common methods between the
 * multiple different views that are necessary for them to be represented.
 * The GridView is fully implemented with these two methods, while the
 * HandView and MainView still need more methods.
 */
public interface IView {
  /**
   * Refreshes the view to reflect what the current state
   * of the model is.
   */
  void refresh();

  /**
   * This method takes in the PlayerActions interface.
   * This allows the view to know and respond to the players
   * inputs from the controller.
   *
   * @param features the PlayerActions interface
   */
  void setFeatures(IPlayerActions features);
}
