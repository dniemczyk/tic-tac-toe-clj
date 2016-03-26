(ns tic-tac-toe-clj.computer-test
   (:require [midje.sweet :refer :all]
             [tic-tac-toe-clj.core :refer [complementary-fields]]
             [tic-tac-toe-clj.computer :refer :all]))

(facts "closing move"
 (fact "works for last move"
   (first (closing-move (complementary-fields [1 2]))) => 3
   (first (closing-move (complementary-fields [3 5]))) => 7
   (first (closing-move (complementary-fields [2 8]))) => 5))
