;;;; Tic-Tac-Toe in Clojure
;;;; ----------------------
(ns tic-tac-toe-clj.core
  (:gen-class))

(defn triple-winner? [triple]
  (cond
    (every? #{:x} triple) :x
    (every? #{:o} triple) :o))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
