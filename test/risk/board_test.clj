(ns risk.board-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.clojure-test :refer [defspec]]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.generators :as gen]
            [risk.board :as board]))

;; GENERATORS

(def army-colors-generator
  (gen/set (gen/elements board/army-colors) {:min-elements 3, :max-elements 6}))

;; TODO can be made more accurate by constraining 
;; the amount of total armies per player to max 180.
(defn territory-generator [army-colors]
  (gen/let [color (gen/elements army-colors)
            armies gen/nat]
    {:color color
     :armies armies}))


(defn board-generator [army-colors]
  (gen/let [territories (gen/vector
                         (territory-generator army-colors)
                         (count board/territories))]
    (let [territories-2 (zipmap board/territories territories)]
      (gen/return {:armies-by-territory
                   (into (hash-map) (map (fn [[k v]] [k (v :armies)]) territories-2))
                   :territories-by-color (into (hash-map) (map (fn [[k territories-3]] [k (map first territories-3)]) (group-by (fn [[k v]] (v :color)) territories-2)))}))))

(defn board-state-generator [army-colors]
  (gen/let [board (board-generator army-colors)]
    {:board board}))


(comment
  (gen/sample (board-state-generator #{:blue :yellow :pink})))

;; TESTS

(deftest occupied-territories
  (let [board-state
        {:board
         {:japan {:color :green}
          :india {:color :green}
          :alaska {:color :red}
          :quebec {:color :red}}}]
    (is (= (board/occupied-territories board-state :green) #{:japan, :india}))))

(defn implies [a b] (if a b true))

;; (defspec armies-to-receive
;;   (testing "Should return at least 3 armies"
;;     (prop/for-all [army-colors army-colors-generator
;;                    color (gen/elements army-colors)
;;                    board-state (board-state-generator army-colors)]
;;                   (>= (board/armies-to-receive board-state color) 3)))
;;   (testing "Should be monotonic in relation to the amount of territories"
;;     (prop/for-all [army-colors army-colors-generator
;;                    color (gen/elements army-colors)
;;                    board-state1 (board-state-generator army-colors)
;;                    board-state2 (board-state-generator army-colors)]
;;                   (implies (>=
;;                             (board/occupied-territories board-state1 color)
;;                             (board/occupied-territories board-state2 color))
;;                            (>= (board/armies-to-receive board-state1 color)
;;                                (board/armies-to-receive board-state2 color))))))

