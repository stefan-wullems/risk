(ns risk.rules.fortification (:require [risk.rules.board :as board]
                                       [clojure.set :as set]))

(defn transitive-neighbors [territories territory]
  (let [neighbors (set/intersection (board/neighbors-of territory) territories)]
    (conj (reduce set/union neighbors (map #(transitive-neighbors (set/difference territories neighbors) %) neighbors)) territory)))

(comment
  (transitive-neighbors (into (board/board :africa) (board/board :australia)) :indonesia)
  (transitive-neighbors #{:alaska :china :india} :india))


(defn fortifiable-clusters [territories]
  (loop [clusters #{}
         territories (set territories)]
    (let [cluster (transitive-neighbors territories (first territories))
          leftover-territories (set/difference territories cluster)]
      (if (empty? leftover-territories)
        (conj clusters cluster)
        (recur (conj clusters cluster) leftover-territories)))))

(comment
  (fortifiable-clusters (set/union (board/board :africa) (board/board :australia)))
  (fortifiable-clusters '(:venezuela :china)))


(defn within-cluster? [cluster fortification]
  (set/subset? #{(fortification :from) (fortification :to)} cluster))

(defn within-clusters? [clusters fortification]
  (some #(within-cluster? % fortification) clusters))

(defn every-within-clusters? [clusters fortifications]
  (every? #(within-clusters? clusters %) fortifications))

(defn territories-non-empty? [territories] (every? #(> % 0) (vals territories)))

(defn accumulate-fortifications [fortifications]
  (reduce (fn [acc fortification]
            (-> acc
                (update (fortification :from) #(- (or % 0) (fortification :armies)))
                (update (fortification :to) #(+ (or % 0) (fortification :armies)))))
          {}
          fortifications))


(defn fortify [territories fortifications]
  {:pre [(every-within-clusters? (fortifiable-clusters (keys territories)) fortifications)]
   :post [(territories-non-empty? %)]}
  (merge-with + territories (accumulate-fortifications fortifications)))

(comment
  (fortify {:argentina 1 :brazil 2 :peru 2 :venezuela 2 :china 1} [{:from :brazil :to :peru :armies 1}]))
