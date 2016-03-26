(ns tic-tac-toe-clj.computer
  "Decision module for a computer player implementation"
  (:refer-clojure :exclude [==])
  (:require [clojure.core.logic :refer :all]))

(defn test-find
  [x]
  (run* [q]
    (== q x)))

