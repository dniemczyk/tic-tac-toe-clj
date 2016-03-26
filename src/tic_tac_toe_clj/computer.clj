(ns tic-tac-toe-clj.computer
  "Decision module for a computer player implementation"
  (:refer-clojure :exclude [==])
  (:require [clojure.core.logic :refer :all]
            [clojure.set :as set]))

(def winning-tripples
  [[1 2 3] [4 5 6] [7 8 9]
   [1 4 7] [2 5 8] [3 6 9]
   [1 5 9] [3 5 7]])

(defn closing-move
  [occupied-spaces]
  (let [board (set (range 1 10))
        free-spaces (vec (set/difference board
                                         (set occupied-spaces)))]
    (run 6 [next-move]
      (fresh [winning-tripple first-in-row second-in-row]
        (membero first-in-row    occupied-spaces)
        (membero second-in-row   occupied-spaces)
        (membero next-move       free-spaces)
        (membero winning-tripple winning-tripples)
        (membero next-move       winning-tripple)
        (membero first-in-row    winning-tripple)
        (membero second-in-row   winning-tripple)
        (!=      first-in-row    second-in-row)))))
