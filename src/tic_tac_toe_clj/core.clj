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
    [(str "---"   "-------"     "---")
     (str "| " (string-of-row 0) " |")
     (str "---"   "-------"     "---")
     (str "| " (string-of-row 1) " |")
     (str "---"   "-------"     "---")
     (str "| " (string-of-row 2) " |")
     (str "---"   "-------"     "---")]))

(defn print-board [board]
  (map println (board->printable board)))

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

(defn -main
  "Prints the initial board"
  [& args]
  (doall
   (print-board initial-board)))
