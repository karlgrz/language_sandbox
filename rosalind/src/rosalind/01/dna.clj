(ns rosalind.01.dna
  (:require [rosalind.core :refer :all]))

(defn dna [sequence]
  "Returns the count of each member in a DNA sequence, (A C G T)"
  (vals (sort-by key (frequencies (seq sequence)))))

(def dna-loc 
  (join-file ["src" "rosalind" "01" "rosalind_dna.txt"]))

(def dna-source
  (join-file ["src" "rosalind" "01" "dna.txt"]))

(defn -main []
  "Write solution to problem 1 in this directory"
  (write-file dna-source (clojure.string/join #" " (dna (read-file dna-loc)))))