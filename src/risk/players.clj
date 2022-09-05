(ns risk.players)

(defn current-player [players-state]
  (get-in players-state [:turn :player]))