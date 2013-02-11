(ns rosalind.03.revc
  (:require [rosalind.core :refer :all]))

(defn revc [sequence]
  "Reverse a DNA sequence, replacing ACGT with complement TGCA"
  (reverse (clojure.string/replace
            sequence #"A|C|G|T"
            {"A" "T"
             "C" "G"
             "G" "C"
             "T" "A"})))

(def revc-loc
  (join-file ["src" "rosalind" "03" "revc.txt"]))

(def revc-source
  (join-file ["src" "rosalind" "03" "rosalind_revc.txt"]))

(defn -main []
  (write-file revc-loc (apply str (revc (read-file revc-source)))))