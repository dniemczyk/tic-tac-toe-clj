;;;; Tic-Tac-Toe in Clojure
;;;; ----------------------
(ns tic-tac-toe-clj.core
  (:gen-class))

(defn triple-winner? [triple]
  (cond
    (every? #{:x} triple) :x
    (every? #{:o} triple) :o))

(defn triples
  "Splits the board into a collection of winnig triples"
  [board]
  (subvec board 0 3))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
