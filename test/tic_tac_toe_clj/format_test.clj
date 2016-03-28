(ns tic-tac-toe-clj.format-test
   (:require [midje.sweet :refer :all]
             [tic-tac-toe-clj.format :refer :all]))

(facts "About board filters"
  (fact "free-fields returns only free field numbers"
    (free-fields [ 1   2  :x
                   4  :o   6
                  :x   8   9]) => [1 2 4 6 8 9]
    (free-fields [:o   2   3
                  :x  :o  :x
                  :x   8   9]) => [2 3 8 9])
  (fact "complementary-fields returns the other fields (free <-> occupied)"
    (complementary-fields [1 2 4 6 8 9]) => [3 5 7]
    (complementary-fields [2 3 8 9]) => [1 4 5 6 7])
  (fact "complementary-fields return full board if nothing provided"
    (complementary-fields) => [1 2 3 4 5 6 7 8 9]))
