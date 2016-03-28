(ns tic-tac-toe-clj.computer-test
   (:require [midje.sweet :refer :all]
             [tic-tac-toe-clj.format :refer [complementary-fields]]
             [tic-tac-toe-clj.computer :refer :all]))

(facts "closing move"
 (fact "works for last move"
   (first (closing-move (complementary-fields [1 2]))) => 3
   (first (closing-move (complementary-fields [3 5]))) => 7
   (first (closing-move (complementary-fields [2 8]))) => 5)
 (fact "has a selection of several closing moves"
   (closing-move (complementary-fields [1 3 5]))
   => (contains [2 9 7] :in-any-order :gaps-ok)
   (closing-move (complementary-fields [2 5 9]))
   => (contains [1 8] :in-any-order :gaps-ok)))
