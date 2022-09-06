(ns risk.board-test
  (:require [clojure.test :refer :all]
            [clojure.test.check :as tc]
            [clojure.test.check.clojure-test :refer [defspec]]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.generators :as gen]
            [risk.board :as board]
            [clojure.set :as set]))

(deftest occupied-territories
  (let [board-state
        {:territories-by-player-color
         {:green #{:japan :india}
          :red #{:alaska :quebec}}}]
    (is (= (board/occupied-territories board-state :green) #{:japan, :india}))))

(def max-armies-per-player 180)
(defn max-amount-of-armies [players]
  (mod (* max-armies-per-player players) (count board/territories)))

(def army-colors #{:red, :blue, :green, :yellow, :purple, :pink})

(def army-colors-generator (gen/set (gen/elements army-colors) {:min-elements 3, :max-elements 6}))

;; TODO can be made more accurate by onstrainin 
;; the amount of total armies per player to max 180.
(defn territory-generator [army-colors]
  (gen/fmap (fn [[color, armies]] {:color color, :armies armies})
            (gen/tuple (gen/elements army-colors) gen/nat)))


(defn board-generator [army-colors]
  (gen/fmap #(zipmap board/territories %)
            (gen/vector (territory-generator army-colors) (count board/territories))))

(defn board-state-generator [army-colors]
  (gen/fmap (fn [board] {:board board}) (board-generator army-colors)))

(defspec armies-to-receive
  (testing "Should return at least 3 armies"
    (prop/for-all [army-colors army-colors-generator
                   color (gen/elements army-colors)
                   board-state (board-state-generator army-colors)]
                  (>= (board/armies-to-receive board-state color) 3))))