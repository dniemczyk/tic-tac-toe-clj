;;;; Tic-Tac-Toe in Clojure
;;;; ----------------------
(ns tic-tac-toe-clj.core
  (:require [clojure.string :as string :refer [upper-case lower-case]]
            [colorize.core :refer [color]]
            [tic-tac-toe-clj.format :refer [free-fields]]
            [tic-tac-toe-clj.computer :as computer :refer [play-move]])
  (:gen-class))

(defn triple-winner [triple]
  (cond
    (every? #{:x} triple) :x
    (every? #{:o} triple) :o))

(defn triples
  "Splits the board into a collection of winnig triples"
  [board]
  (let [rows (partition-all 3 board)
        first-column  (take-nth 3 board)
        second-column (take-nth 3 (drop 1 board))
        third-column  (take-nth 3 (drop 2 board))
        top-left-diagonal  (take-nth 4 board)
        top-right-diagonal (take-nth 2 (drop-last 2 (drop 2 board)))]
    (concat rows (list first-column
                       second-column
                       third-column
                       top-left-diagonal
                       top-right-diagonal))))

(defn winner [board]
  (first (filter #{:o :x} (map triple-winner (triples board)))))

(defn board-full? [board]
  (every? #{:x :o} board))

(defn capitalize-keyword [k]
  (if (keyword? k)
    (upper-case (subs (str k) 1))
    k))

(defn colorize-player [s]
  (cond
    (= "X" s) (color :blue s)
    (= "O" s) (color :red s)
    :else s))

(defn board->printable [board]
  (let [rows (partition-all 3 board)
        string-of-row (fn [x] (->> (nth rows x)
                                   (map capitalize-keyword)
                                   (map colorize-player)
                                   (string/join " | ")))]
    (str "---"   "-------"     "---\n"
         "| " (string-of-row 0) " |\n"
         "---"   "-------"     "---\n"
         "| " (string-of-row 1) " |\n"
         "---"   "-------"     "---\n"
         "| " (string-of-row 2) " |\n"
         "---"   "-------"     "---")))

(defn print-board [board]
  (println (board->printable board)))

(def initial-board (vec (range 1 10)))

(defn player-name [x]
  (#{"X" "O"} (capitalize-keyword x)))

(defn color-player-name [player]
  (colorize-player (player-name player)))

(defn get-move
  "Queries the user for field input. If the field is already
  taken or if the user selected an invalid input returns nil."
  [board]
  (let [input (try
                (Integer/parseInt (read-line))
                (catch Exception e nil))]
    (if (some #{input} board)
      input
      nil)))

(def player-sequence (cycle [:x :o]))

(defn new-game? []
  (println "Do you want to play one more game [Y/n]")
  (let [response (read-line)]
    (if (#{"Y" "y"} response) true)))

(defn string->key [s]
  (keyword (lower-case s)))

(def player-type (atom {:x nil
                        :o nil}))

(defn reset-player-types [] (reset! player-type {:x nil :o nil}))

(defn decide-player-type [player]
  (loop []
    (println "Choose type for player" (color-player-name player) ": (H)uman / (C)omputer")
    (let [response (read-line)
          response-key (string->key response)]
      (if (#{"H" "h" "C" "c"} response)
        (swap! player-type assoc player response-key)
        (do
          (println "Wrong input, please type H or C.")
          (recur))))))

(defn decide-all-player-types []
  (reset-player-types)
  (decide-player-type :x)
  (decide-player-type :o))

(defn make-computer-move [player board]
  (let [free-fields (free-fields board)
        move (computer/play-move free-fields)]
    (assoc board (dec move) player)))

(defn make-human-move [player board]
  (println "Select your move Player"
           (color-player-name player)
           "(press 1-9 and hit ENTER):")
  (loop [move (get-move board)]
    (if move
      (assoc board (dec move) player)
      (do
        (println "Move was invalid. Select proper move Player"
                 (color-player-name player) ":")
        (recur (get-move board))))))

(defn take-turn [player board]
  (let [computer-player? (= :c (get @player-type player))]
    (if computer-player?
      (make-computer-move player board)
      (make-human-move    player board))))

(defn play-game []
  (loop [board initial-board player-sequence player-sequence]
    (let [winning-player (winner board)]
      (println "Current board")
      (print-board board)
      (cond
        winning-player (println "Player" (color-player-name winning-player) "wins!")
        (board-full? board) (println "Game is a draw.")
        :else (recur
               (take-turn (first player-sequence) board)
               (rest player-sequence))))))

(defn -main
  "The main game loop"
  [& args]
  (loop []
    ;; ask the user to decide what type of player player X and O are
    (decide-all-player-types)
    (play-game)
    (if (new-game?) (recur))))
