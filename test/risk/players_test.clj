(ns risk.players-test
  (:require [clojure.test :refer :all]
            [risk.players :as players]))

(deftest current-player
  (testing "Returns current player"
    (let [player-state {:turn {:player :green}}]
      (is (= (players/current-player player-state) :green)))))

(deftest allowed-actions
  (testing "Allow only distributing armies when a player has not acted yet"
    (let [player-state {:turn {:player :green}}]
      (is (= (get (players/allowed-actions player-state) :actions) #{:distribute-armies}))))
  (testing "Allow attacking, fortifying positions or ending ones turn when a player has distributed armies"
    (let [player-state {:turn {:player :green :previous-action :distribute-armies}}]
      (is (= (get (players/allowed-actions player-state) :actions) (#{:attack, :fortify-positions, :end-turn})))))
  (testing "Allow attacking, fortifying positions or ending ones turn when a player has attacked"
    (let [player-state {:turn {:player :green :previous-action :attack}}]
      (is (= (get (players/allowed-actions player-state) :actions) #{:attack, :fortify-positions, :end-turn})))))
