# risk-dop

Implementation of [Risk (the board game)](<https://en.wikipedia.org/wiki/Risk_(game)>) following the DOP programming paradigm as it is presented in [Data-oriented programming](https://www.manning.com/books/data-oriented-programming).

## Current iteration (#1)

### Requirements

- The game can be played with a minimum of 3 players and a maximum of 6.
- Each player randomly gets assigned an army.
- The turn order is randomly decided.
- The army units are distributed over territories following these steps:
  - The following Army units are distributed to each player.
    - 3 players (35 Army units)
    - 4 players (30 Army units)
    - 5 players (25 Army units)
    - 6 players (20 Army units)
  - According to the turn order, players place one Army units on unoccupied territories untill all territories are occupied.
  - According to the turn order, players continue placing infantries on the territories they've occupied until players run out of Army units.
- Turns are taken by players according to the turn order.
- When a its the turn of a player, go through the following steps:
  - Step 1 (Strengthening army):
    - The player receives an amount of armies that they can distribute among their territories any way they wish.
    - The amount they receive is calculated according to this formula:
      - `(amount of occupied territories % 3) with a minimum of 3`
    - The total amount of armies the player has on the board cannot exceed 180.
  - Step 2 (Attack):
    - The player can attack enemy territory if they own an adjacent territory that has more than one trooper. They can then choose to attack with 1-3 troopers, as long as at least 1 trooper stays behind.
    - The defender can choose to defend with 1, or if they have a second, 2 troopers.
    - Each player can roll one die for each trooper that engages in combat.
    - The casualties are determined according to the following algorithm:
    - Take the highest roll of each player. If the attacker rolled higher, the defender has one casualty, otherwise the attacker has a casualty. Remove the dice and repeat with the remaining dice until one player runs out.
    - If the defender is left with no troops left on their territory, the territory is captured by the attacker and the attacking troops that survived must move to the newly captured territory.
    - The attacker can keep attacking as many territories as they want.
  - Step 3 (Fortify positions):
    - A player can move any number of armies from one territory to any adjacent territory that they occupy as long as at least 1 army stays behind on any given territory.
- A player wins when they have taken all territories on the map.

### Data model

![image](https://user-images.githubusercontent.com/28148115/188442501-59145c7a-abdb-4b87-ba1d-c371838f1d2f.png)

### Modules

![image](https://user-images.githubusercontent.com/28148115/188442105-04095ded-acd1-4b7d-9896-932f75c30346.png)

###

## Future iterations

- [x] 1. Add army strengthening step to turns
- [x] 2. Add fortification step to turns
- [x] 3. Attack with multiple die
- [ ] 4. Gain more armies when having captured entire continents (depends on 1)
- [ ] 5. Add risk cards (at the end of a turn where a territory is captured)
- [ ] 6. Add more kinds of army units
- [ ] 7. Add mission cards
- [ ] 8. Add second player with special game rules
- [ ] 9. Add online play

## Notes

- If the highest guard is successful, no other guard should ever be unsuccessful.
