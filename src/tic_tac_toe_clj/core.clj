;;;; Tic-Tac-Toe in Clojure
;;;; ----------------------
(ns tic-tac-toe-clj.core
  (:gen-class))

(defn triple-winner [triple]
  (cond
    (every? #{:x} triple) :x
    (every? #{:o} triple) :o))

(defn triples
  "Splits the board into a collection of winnig triples"
  [board]
  (concat
   (partition-all 3 board)
   (list
    (take-nth 3 board)
    (take-nth 3 (drop 1 board))
    (take-nth 3 (drop 2 board)))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
