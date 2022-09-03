(ns risk.core
  (:gen-class)
  (:require [clojure.set :refer :all]))

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