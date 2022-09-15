(ns risk.rules.attack)

(defn- roll-winner [attacker defender]
  (cond
    (> attacker defender) :attacker
    (> defender attacker) :defender
    :else :defender))

(defn- battle [attacker-rolls defender-rolls]
  (merge-with + {:attacker 0 :defender 0} (frequencies (map roll-winner (sort > attacker-rolls) (sort > defender-rolls)))))

(defn valid-rolls? [party rolls {max-rolls :max-rolls}]
  (<= (count rolls) (min (party :armies) max-rolls)))

(defn attack [attacker defender attacker-rolls defender-rolls]
  {:pre [(valid-rolls? attacker attacker-rolls {:max-rolls 3})
         (valid-rolls? defender defender-rolls {:max-rolls 2})]}
  (let [{attacker-wins :attacker defender-wins :defender} (battle attacker-rolls defender-rolls)]
    (if (>= attacker-wins (defender :armies))
      [(update attacker :armies #(- % (count attacker-rolls)))
       {:armies (-
                 (count attacker-rolls)
                 defender-wins)
        :player (attacker :player)}]
      [(update attacker :armies #(- % defender-wins)), (update defender :armies #(- % attacker-wins))])))

(comment
  (attack {:armies 5 :player :red} {:armies 2 :player :green} [6 5 6] [6 2]))