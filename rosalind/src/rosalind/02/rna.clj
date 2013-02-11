(ns rosalind.02.rna
  (:require [rosalind.core :refer :all]))

(defn rna [sequence]
  "Transform a DNA sequence into an RNA sequence"
  (clojure.string/replace sequence "T" "U"))

(def rna-loc 
  (join-file ["src" "rosalind" "02" "rosalind_rna.txt"]))

(def rna-source
  (join-file ["src""rosalind" "02" "rna.txt"]))

(defn -main []
  "Write solution to problem 2 in this directory"
  (write-file rna-source (rna (read-file rna-loc))))