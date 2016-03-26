(ns tic-tac-toe-clj.core-test
   (:require [midje.sweet :refer :all]
             [colorize.core :refer [color]]
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

(facts "About board"
  (fact "initial board is compromised of numbers from 1 to 9"
    initial-board => [1 2 3
                      4 5 6
                      7 8 9])
  (fact "is full when all fields have an X or an O"
    (board-full? [:x :o :x
                  :x :o :x
                  :o :x :o]) => true)
  (fact "is not full when at least one field empty"
    (board-full? [:x  2 :x
                  :x :o :x
                  :o :x :o]) => false))

(facts "About capitalize keyword"
  (fact "changes keyword to a capital string"
    (capitalize-keyword :x) => "X"
    (capitalize-keyword :o) => "O"
    (capitalize-keyword :a) => "A")
  (fact "does nothing with numbers"
    (capitalize-keyword 1)   => 1
    (capitalize-keyword 502) => 502))

(facts "About printable board"
  (fact "returs a board of numbers for the initial board"
    (let [initial-board [1 2 3
                         4 5 6
                         7 8 9]]
      (board->printable initial-board) => (str "-------------\n"
                                               "| 1 | 2 | 3 |\n"
                                               "-------------\n"
                                               "| 4 | 5 | 6 |\n"
                                               "-------------\n"
                                               "| 7 | 8 | 9 |\n"
                                               "-------------")))
  (fact "returs a board properly formated for printing"
    (let [some-board [1  2  :x
                      4  :o 6
                      :x 8  9]
          blue-x (color :blue "X")
          red-o  (color :red  "O")]
      (board->printable some-board) => (str "--"        "---"      "-----"      "---\n"
                                            "| "   "1"  " | "  "2"  " | " blue-x " |\n"
                                            "--"        "---"      "-----"      "---\n"
                                            "| "   "4"  " | " red-o " | "   "6"  " |\n"
                                            "--"        "---"      "-----"      "---\n"
                                            "| " blue-x " | "  "8"  " | "   "9"  " |\n"
                                            "--"        "---"      "-----"      "---"))))
