(ns tic-tac-toe-clj.core-test
   (:require [midje.sweet :refer :all]
             [tic-tac-toe-clj.core :refer :all]))

(facts "A triple winer"
 (fact "is x if all fields in the tripple are x"
   (triple-winner [:x :x :x]) => :x)
 (fact "is o if all fields in the tripple are o"
   (triple-winner [:o :o :o]) => :o)
 (fact "does not exist if nither has all fields"
   (triple-winner [:o :x :o]) => nil
   (triple-winner [:x :x :o]) => nil))

(facts "About the triples colection"
  (let [empty-board [1 2 3
                     4 5 6
                     7 8 9]]
    (fact "the first horizontal"
      (triples empty-board) => (contains [[1 2 3]]))
    (fact "the second horizontal"
      (triples empty-board) => (contains [[4 5 6]]))
    (fact "the third horizontal"
      (triples empty-board) => (contains [[7 8 9]]))))
