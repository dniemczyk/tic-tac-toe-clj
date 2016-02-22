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
  (letfn [(horizontal-triples     [x] (partition-all 3 x))
          (first-vertical-triple  [x] (take-nth 3 x))
          (second-vertical-triple [x] (take-nth 3 (drop 1 x)))
          (third-vertical-triple  [x] (take-nth 3 (drop 2 x)))]
    (concat
     (horizontal-triples board)
     (list
      (first-vertical-triple  board)
      (second-vertical-triple board)
      (third-vertical-triple  board)))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
