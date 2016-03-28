(ns tic-tac-toe-clj.format
  (:require [clojure.set :as set]))

(def initial-board (vec (range 1 10)))

(defn complementary-fields
  ([] (complementary-fields []))
  ([fields] (complementary-fields fields initial-board))
  ([fields board]
   (sort (vec (set/difference (set board) (set fields))))))
