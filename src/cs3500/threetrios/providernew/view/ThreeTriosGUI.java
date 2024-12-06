package cs3500.threetrios.providernew.view;

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

import cs3500.threetrios.providernew.controller.PlayerAction;
import cs3500.threetrios.providernew.model.Card;
import cs3500.threetrios.providernew.model.PlayerColor;
import cs3500.threetrios.providernew.model.ReadOnlyTrioModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

/**
 * This is our Graphic User Interface for the Three Trios Game.  This creates a visual
 * representation of the game, with a grid board, 2 panels of hands of cards, and renders the cards
 * themselves.  Users can play the game by clicking the cards and then their desired placement on
 * the grid.
 */public class ThreeTriosGUI<C extends Card<C>> extends JFrame implements TriosViewGUI<C> {

  private final GridPanel<C> gridPanel;
  private final HandPanel<C> redHandPanel;
  private final HandPanel<C> blueHandPanel;
  private ReadOnlyTrioModel<C> model;
  private static final int WINDOW_WIDTH = 1000;
  private static final int PANEL_HEIGHT = 1000;
  private static final int SIDE_PANEL_WIDTH = 200;

  /**
   * Constructs a ThreeTriosGUI with a ReadOnly version of a Three Trios Model.
   *
   * @param model a read only model for grabbing information for display.
   */
  public ThreeTriosGUI(ReadOnlyTrioModel<C> model) {
    super();
    this.model = model;

    gridPanel = new GridPanel<>();
    redHandPanel = new HandPanel<>(PlayerColor.RED);
    blueHandPanel = new HandPanel<>(PlayerColor.BLUE);

    gridPanel.setModel(model);
    redHandPanel.setModel(model);
    blueHandPanel.setModel(model);

    setupLayout();
    setupWindow();
  }

  private void setupLayout() {
    setLayout(new BorderLayout());

    // creates and add red panel
    JPanel redPanel = new JPanel(new BorderLayout());
    redPanel.setPreferredSize(new Dimension(SIDE_PANEL_WIDTH, PANEL_HEIGHT));
    redPanel.add(redHandPanel, BorderLayout.CENTER);
    redPanel.setBorder(BorderFactory.createTitledBorder("red player"));
    add(redPanel, BorderLayout.WEST);

    // creates and adds the blue panel
    JPanel bluePanel = new JPanel(new BorderLayout());
    bluePanel.setPreferredSize(new Dimension(SIDE_PANEL_WIDTH, PANEL_HEIGHT));
    bluePanel.add(blueHandPanel, BorderLayout.CENTER);
    bluePanel.setBorder(BorderFactory.createTitledBorder("blue player"));
    add(bluePanel, BorderLayout.EAST);

    // adds the board panel
    JPanel boardPanel = new JPanel(new BorderLayout());
    boardPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    boardPanel.add(gridPanel, BorderLayout.CENTER);
    add(boardPanel, BorderLayout.CENTER);

  }

  private void setupWindow() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setPreferredSize(new Dimension(WINDOW_WIDTH, PANEL_HEIGHT));
    pack();
    setLocationRelativeTo(null);
  }

  @Override
  public void addPlayerListener(PlayerAction listener) {
    gridPanel.addPlayerListener(listener);
    redHandPanel.addPlayerListener(listener);
    blueHandPanel.addPlayerListener(listener);
  }

  @Override
  public void setHeader(String title) {
    setTitle(title);
  }


  @Override
  public void refresh() {
    gridPanel.repaint();
    redHandPanel.repaint();
    blueHandPanel.repaint();
  }

  @Override
  public void makeVisible() {
    setVisible(true);
  }

  @Override
  public void setModel(ReadOnlyTrioModel<C> model) {
    this.model = model;
    gridPanel.setModel(model);
    redHandPanel.setModel(model);
    blueHandPanel.setModel(model);
    refresh();
  }
}