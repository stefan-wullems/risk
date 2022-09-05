(ns risk.game-test
  (:require [clojure.test :refer :all]
            [risk.game :as game]))

(deftest current-player
  (testing "Returns current player"
    (let [game-state {:players {:turn {:player :green}}}]
      (is (= :green (game/current-player game-state))))))