(ns risk.rules.army-return (:require [risk.rules.board :as board]))

(def min-received-armies 3)

(defn territory-army-return [territories] (int (/ (count territories) 3)))

(def army-return-per-continent {:north-america 5
                                :europe 5
                                :asia 7
                                :south-america 2
                                :africa 3
                                :australia 2})

(defn continent-army-return [territories]
  (reduce + (vals (select-keys army-return-per-continent (board/fully-occupied-continents territories)))))

(defn army-return [territories]
  (max min-received-armies (+ (territory-army-return territories)
                              (continent-army-return territories))))

