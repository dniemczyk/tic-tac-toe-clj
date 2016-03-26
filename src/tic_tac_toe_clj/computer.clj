(ns tic-tac-toe-clj.computer
  "Decision module for a computer player implementation"
  (:refer-clojure :exclude [==])
  (:require [clojure.core.logic :refer :all]
            [clojure.set :as set]))

(defn closing-move
  [occupied-spaces]
  (let [board (set (range 1 10))
        free-spaces (vec (set/difference board
                                         (set occupied-spaces)))]
    (run* [next-move]
      (membero next-move free-spaces))))
