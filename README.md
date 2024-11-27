# Three Trios Game

`ThreeTrioModel` implements the core functionality and rules for a two player card based grid game, 
called **Three Trios**. This code is for managing gameplay, enforcing game mechanics, 
and determining victory conditions.

## Overview

The primary challenge this codebase addresses is managing the state, actions, and turn-based logic 
in a complex card game. Key assumptions include:
- **Gameplay Knowledge**: Familiarity with basic turn-based and grid-based mechanics, 
such as turn alternation, card placement, and adjacent cell targeting.
- **Extensibility**: The codebase is structured to allow extensions, 
such as additional battle rules, new grid shapes, or varied winning conditions.
- **Prerequisites**: This model requires basic understanding of Java as well as filesystems.

### Main Features

1. **Game Initialization** - Sets up the game with decks, hands, and a grid.
2. **Card Placement and Battle Mechanics** - Manages the rules for placing and 
battling cards on the grid.
3. **Game States** - Tracks and manages the progression of the game.
4. **Player Turns** - Alternates between players and tracks the current player.
5. **Win Condition Checking** - Determines if the game has ended and which player won.

### Quick Start

Write two textfiles with the specified formation below, one for the grid and one for the deck. 
Configure a file in main with 2 words, either human, strategy1, or strategy2.
If both words are human, the game will launch a 1v1 game with two controllers.
If one of the words is a strategy, the game will launch one controller.
If both of the words are strategies, the game will play but will not be viewable.

### Source Organization


Project: ThreeTrios\
├── src/\
│   ├── controller/\
│   │   ├── ModelListener.java\
│   │   ├── IPlayerActions.java\
│   │   └── ThreeTriosController.java\
│   ├── model/\
│   │   ├── IThreeTriosModel.java\
│   │   ├── PlayerColor.java\
│   │   ├── ICard.java\
│   │   ├── Grid.java\
│   │   ├── Card.java\
│   │   ├── ThreeTriosModel.java\
│   │   ├── Deck.java\
│   │   ├── GridCell.java\
│   │   ├── GameState.java\
│   │   ├── Direction.java\
│   │   └── IReadonlyThreeTriosModel.java\
│   ├── view/\
│   │   ├── IThreeTriosJSwingView.java\
│   │   ├── ThreeTriosView.java\
│   │   ├── GridView.java\
│   │   ├── IView.java\
│   │   ├── HandView.java\
│   │   ├── ThreeTriosJSwingView.java\
│   │   ├── CardView.java\
│   │   └── IHandView.java\
│   ├── player/\
│   │   ├── AIPlayer.java\
│   │   ├── HumanPlayer.java\
│   │   └── IPlayer.java\
│   └── strategy/\
│       ├── IThreeTriosStrategy.java\
│       ├── Coord.java\
│       ├── FlipMaxCards.java\
│       └── Corners.java\
└── test/\
├── strategies/\
│   ├── TestFlipMaxCards.java\
│   └── TestCorners.java\
└── model/\
├── TestThreeTriosModel.java\
└── MockThreeTriosModel.java\


### Card & Grid Files

To set up a Three Trios game, the deck and grid take in file representations to use.

A single card would be formatted as such: CARD_NAME NORTH SOUTH EAST WEST.\
Each Direction should be represented by an integer 1-10 (in game 10 is represented by an A).\
To make a valid deck, you would need at least N+1 cards represented in this way, where N is the
number of grid cells.

A grid would be formatted as such:

5 7\
CCXXXXC\
CXCXXXC\
CXXCXXC\
CXXXCXC\
CXXXXCC

The first two integers represent the number of rows and columns, respectively.\
C is a valid card cell and X is a hole.

## Classes
#### MODEL
- **Card**: Represents a single card in the game.
- **Deck**: Represents a list of cards. 
- **Grid**: Represents the board where the game is played.
- **GridCell**: Represents a single cell in the grid.
- **ThreeTriosModel**: The model where the magic happens.
#### VIEW
- **CardView**: Represents the view of a single card.
- **GridView**: Represents the view of the grid.
- **HandView**: Represents the view of the hands on either side.
- **ThreeTriosJSwingView**: The interactable GUI view.
- **ThreeTriosView**: The text view version of the game.
#### CONTROLLER
- **ThreeTriosController**: The main controller for the game.

## Enums
- **Direction**: Represents each direction a card can attack or defend from.
- **GameState**: Represents each state of the game.
- **PlayerColor**: Represents the two player colors in the game. 

## Example Usage

Below is a quick code example illustrating how to set up and play a turn in the **ThreeTrios** 
game model.

```java
ThreeTriosModel model = new ThreeTrioModel(exampleDeckFile, exampleGridFile);
model.startGame();

// Retrieve the current player and make a move
PlayerColor currentPlayer = model.getCurrentPlayer();
model.placeCard(2, 3, 0, currentPlayer);  // place card at grid (2,3) from player's hand

// Engage in battle phase
model.battle(2, 3);

// Check if game ended and display the winner
if (model.isGameOver()) {
    PlayerColor winner = model.determineWinner();
    System.out.println("The winner is: " + winner);
}

// If game is not over, next player's turn starts
