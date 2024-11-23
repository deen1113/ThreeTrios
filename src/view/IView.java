package view;

import controller.IPlayerActions;

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
