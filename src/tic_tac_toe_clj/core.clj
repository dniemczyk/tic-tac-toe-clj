;;;; Tic-Tac-Toe in Clojure
;;;; ----------------------
(ns tic-tac-toe-clj.core
  (:require [clojure.string :as string :refer [upper-case]])
  (:gen-class))

(defn triple-winner [triple]
  (cond
    (every? #{:x} triple) :x
    (every? #{:o} triple) :o))

(defn triples
  "Splits the board into a collection of winnig triples"
  [board]
  (letfn [(rows [x] (partition-all 3 x))
          (first-column  [x] (take-nth 3 x))
          (second-column [x] (take-nth 3 (drop 1 x)))
          (third-column  [x] (take-nth 3 (drop 2 x)))
          (top-left-diagonal  [x] (take-nth 4 x))
          (top-right-diagonal [x] (take-nth 2 (drop-last 2 (drop 2 x))))]
    (concat (rows board)
            (list (first-column  board)
                  (second-column board)
                  (third-column  board)
                  (top-left-diagonal  board)
                  (top-right-diagonal board)))))

(defn winner [board]
  (first (filter #{:o :x} (map triple-winner (triples board)))))

(defn board-full? [board]
  (every? #{:x :o} board))

(defn capitalize-keyword [k]
  (if (keyword? k)
    (upper-case (subs (str k) 1))
    k))

(defn board->printable [board]
  (let [rows (partition-all 3 board)
        string-of-row (fn [x] (->> (nth rows x)
                                   (map capitalize-keyword)
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

(defn take-turn [player board]
  (println "Select your move Player"
           (player-name player)
           "(press 1-9 and hit ENTER):")
  (loop [move (get-move board)]
    (if move
      (assoc board (dec move) player)
      (do
        (println "Move was invalid. Select proper move Player"
                 (player-name player) ":")
        (recur (get-move board))))))

(defn play-game []
  (loop [board initial-board player-sequence player-sequence]
    (let [winning-player (winner board)]
      (println "Current board")
      (print-board board)
      (cond
        winning-player (println "Player" (player-name winning-player) "wins!")
        (board-full? board) (println "Game is a draw.")
        :else (recur
               (take-turn (first player-sequence) board)
               (rest player-sequence))))))

(defn new-game? []
  (println "Do you want to play one more game [Y/n]")
  (let [response (read-line)]
    (if (#{"Y" "y"} response) true)))

(defn -main
  "Prints the initial board"
  [& args]
  (loop []
   (play-game)
   (if (new-game?) (recur))))
