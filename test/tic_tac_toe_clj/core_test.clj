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

(facts "The triples colection has"
  (let [empty-board [1 2 3
                     4 5 6
                     7 8 9]]
    (fact "the first horizontal"
      (triples empty-board) => (contains [[1 2 3]]))
    (fact "the second horizontal"
      (triples empty-board) => (contains [[4 5 6]]))
    (fact "the third horizontal"
      (triples empty-board) => (contains [[7 8 9]]))
    (fact "the first vertical"
      (triples empty-board) => (contains [[1 4 7]]))
    (fact "the second vertical"
      (triples empty-board) => (contains [[2 5 8]]))
    (fact "the third vertical"
      (triples empty-board) => (contains [[3 6 9]]))
    (fact "the top left botom right diagonal"
      (triples empty-board) => (contains [[1 5 9]]))
    (fact "the top right botom left diagonal"
      (triples empty-board) => (contains [[3 5 7]]))))

(facts "About winner"
  (fact "is X if x-player has a winning tripple first"
    (let [x-winner-board [:x :o  3
                          :o :x :o
                           7 :o :x]]
      (winner x-winner-board) => :x))
  (fact "is O if o-player has a winning tripple first"
    (let [o-winner-board [ 1 :x :o
                          :x :o :x
                          :o :x :x]]
      (winner o-winner-board) => :o)))
