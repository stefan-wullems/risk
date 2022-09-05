(ns risk.game
  (:gen-class)
  (:require [clojure.set :refer :all]
            [risk.players :as players]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(def armies #{:red :blue :green :yellow :pink})

(def max-army {:infantry 40
               :cavalry 12
               :artillery 8})


(def pieces {:infantry 1
             :cavalry 5
             :artillery 10})

(defn current-player [game-state]
  (let [players-state (get-in game-state [:players])]
    (players/current-player players-state)))
