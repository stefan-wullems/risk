(ns risk.players-test
  (:require [clojure.test :refer :all]
            [risk.players :as players]))

(deftest current-player
  (testing "Returns current player"
    (let [player-state {:turn {:player :green}}]
      (is (= :green (players/current-player player-state))))))