(ns rosalind.05.hamm
  (:require [rosalind.core :refer :all]))

(defn hamm [seq1 seq2]
  "Return the Hamming disance between `seq1` and `seq2`"
  (def zseq (map vector seq1 seq2))
  (count (filter #(not= (count (set %)) 1) zseq)))

(def hamm-loc
  (join-file ["src" "rosalind" "05" "hamm.txt"]))

(def hamm-source
  (join-file ["src" "rosalind" "05" "rosalind_hamm.txt"]))

(defn -main []
  (def sequences (clojure.string/split 
                   (read-file hamm-source) (re-pattern new-line)))
  (write-file hamm-loc (hamm (first sequences) (last sequences))))