(ns risk.game
  (:gen-class)
  (:require [clojure.set :refer :all]
            [risk.players :as players]
            [clojure.set :as set]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; STATE ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def initial-state {:current-turn :yellow
                    :board
                    {:armies-by-territory
                     {:china 5,
                      :south-africa 4,
                      :siberia 1,
                      :quebec 8,
                      :congo 7,
                      :middle-east 1,
                      :yakutsk 9,
                      :southern-europe 8,
                      :argentina 2,
                      :western-europe 9,
                      :ural 1,
                      :central-america 5,
                      :ukraine 4,
                      :madagascar 9,
                      :eastern-us 2,
                      :irkutsk 7,
                      :north-west-territory 7,
                      :great-britain 2,
                      :afghanistan 1,
                      :peru 5,
                      :venezuela 2,
                      :mongolia 3,
                      :new-guinea 1,
                      :japan 4,
                      :northern-europe 6,
                      :iceland 2,
                      :north-africa 3,
                      :siam 2,
                      :ontario 6,
                      :egypt 1,
                      :east-africa 2,
                      :western-australia 1,
                      :india 3,
                      :western-us 2,
                      :brazil 2,
                      :eastern-australia 3,
                      :kamchatka 2,
                      :greenland 2,
                      :scandinavia 8,
                      :indonesia 3,
                      :alaska 3,
                      :alberta 2},
                     :territories-by-color
                     {:blue
                      #{:china
                        :south-africa
                        :ukraine
                        :japan
                        :north-africa
                        :egypt
                        :east-africa
                        :western-australia
                        :western-us
                        :brazil
                        :kamchatka
                        :greenland},
                      :pink
                      #{:siberia
                        :quebec
                        :middle-east
                        :argentina
                        :ural
                        :central-america
                        :madagascar
                        :north-west-territory
                        :great-britain
                        :peru
                        :mongolia
                        :northern-europe
                        :ontario
                        :india
                        :eastern-australia
                        :scandinavia
                        :alberta},
                      :yellow
                      #{:congo
                        :yakutsk
                        :southern-europe
                        :western-europe
                        :eastern-us
                        :irkutsk
                        :afghanistan
                        :venezuela
                        :new-guinea
                        :iceland
                        :siam
                        :indonesia
                        :alaska}}}})
(defn state.owned-territories [game color] (get-in game [:board :territories-by-color color]))
(defn state.current-player [game] (game :current-turn))





;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; GAME ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; (defn armies-to-receive [game color]
;;   (let [territories (owned-territories game color)]
;;     (max 3 (int (/ (count territories) 3)))))

;; (defn valid-distribution? [game distribution]
;;   (let [color (current-player game)
;;         territories (owned-territories game color)
;;         armies (armies-to-receive game color)
;;         territories-in-distribution (keys distribution)
;;         total-distributed-armies (apply + (vals distribution))]
;;     (and  (every? (set territories) territories-in-distribution)
;;           (= total-distributed-armies armies))))

(defn distribute-armies [game distribution] nil)

(defn battle [game attack] nil)

(defn fortify-positions [game fortifications] nil)

(defn end-turn [game] nil)

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
