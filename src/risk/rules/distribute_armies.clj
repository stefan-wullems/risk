(ns risk.rules.distribute-armies (:require [risk.rules.army-return :as army-return]
                                           [clojure.set :as set]))

(defn valid-territories? [occupied-territories distribution]
  (set/subset? (set (keys distribution)) (set (keys occupied-territories))))

(defn- valid-army-amount? [occupied-territories distribution]
  (= (reduce + (vals distribution)) (army-return/army-return (keys occupied-territories))))


(defn distribute-armies [occupied-territories distribution]
  {:pre [(valid-territories? occupied-territories distribution) (valid-army-amount? occupied-territories distribution)]}
  (merge-with + occupied-territories distribution))