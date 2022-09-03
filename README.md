# risk-dop

Implementation of [Risk (the board game)](<https://en.wikipedia.org/wiki/Risk_(game)>) following the DOP programming paradigm as it is presented in [Data-oriented programming](https://www.manning.com/books/data-oriented-programming).

## Current iteration (#1)

### Requirements

- The game can be played with a minimum of 3 players and a maximum of 6.
- Each player chooses an army and depending on the amount of players.
- Each army has a maximum of 180 infantry.
- The turn order is decided by each rolling a die. The turn order is from highest to lowest roll. Resolve conflicts by repeating.
- The territories are distributed following these steps:
  - The following infantry are distributed to each player.
    - 3 players (35 infantry)
    - 4 players (30 infantry)
    - 5 players (25 infantry)
    - 6 players (20 infantry)
  - According to the turn order, players place one infantry on unoccupied territories untill all territories are occupied.
  - According to the turn order, players continue placing infantries on the territories they've occupied until players run out of infantry.
- Turns are taken by players according to the turn order.
- When a its the turn of a player, they can either choose to attack or they can pass
  - A player can attack any enemy territory that's adjacent to a territory they own that has at least 2 infantry. A player can keep repeating this action as long as these requirements are met.
    - When a player attacks a territory, both the player and the owner of the attacked territory throw a dice. The player who throws the lowest number loses an infantry. If it's a tie, only the attacker loses an infantry.
    - When the defender loses its last infantry on a territory, the attacker has captured the territory an must move at least one infantry onto the territory. At least one infantry must stay behind on the attacking territory.
- A player wins when they have taken all territories on the map.

## Future iterations

- [ ] 1. Add army strengthening step to turns
- [ ] 2. Add fortification step to turns
- [ ] 3. Attack with multiple die
- [ ] 4. Gain more armies when having captured entire continents (depends on 1)
- [ ] 5. Add risk cards (at the end of a turn where a territory is captured)
- [ ] 6. Add more kinds of army units
- [ ] 7. Add mission cards
- [ ] 8. Add second player with special game rules
- [ ] 9. Add online play
