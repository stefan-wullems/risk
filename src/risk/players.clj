(ns risk.players)

(defn current-player [players-state]
  (get-in players-state [:turn :player]))

(def allowed-actions-by-previous-action
  {nil #{:distribute-armies}
   :distribute-armies #{:attack :fortify-positions :end-turn}
   :attack #{:attack :fortify-positions :end-turn}})

(defn allowed-actions [player-state]
  (let [previous-action (get-in player-state [:turn :previous-action])]
    (get allowed-actions-by-previous-action previous-action)))
