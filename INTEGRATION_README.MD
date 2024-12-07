
## Integration Notes
### What we achieved
We managed to implement the following:
- Getting the model and controller functionality integrated.
- Retrieving information from the model.
- Controlling the model based on player actions such as clicking on cells or cards.
- Signaling game states, for example when a card is placed.
- Getting the score.
- Determining the winner.

We could not get the following to work
- We could not get tile reading to work
    - This is because their MapReader takes a type parameter <C>, which is fundamental to their implementation.
    - We were not able to change it.
    - Our code did not rely on that parameterization, leading to a critical design incompatibility.