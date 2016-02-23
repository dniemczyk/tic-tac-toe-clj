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
  (letfn [(rows [x] (partition-all 3 x))
          (first-column  [x] (take-nth 3 x))
          (second-column [x] (take-nth 3 (drop 1 x)))
          (third-column  [x] (take-nth 3 (drop 2 x)))
          (top-left-diagonal  [x] (take-nth 4 x))
          (top-right-diagonal [x] (take-nth 2 (drop-last 2 (drop 2 x))))]
    (concat (rows board)
            (list (first-column  board)
                  (second-column board)
                  (third-column  board)
                  (top-left-diagonal  board)
                  (top-right-diagonal board)))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
