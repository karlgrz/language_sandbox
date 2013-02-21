(ns rosalind.04.gc
  (:require [rosalind.core :refer :all]))

(defn format-rosalind [raw-rosalind]
  "Prepare Rosalind_ID's for manipulation later
   '>Rosalind_1161
   CACGATACCGCTTAAA
   TCAGGTGGCATAATTC
   GCGATAGATCTTGATA
   GCATAACAATCTA
   >Rosalind_1495
   GTGCAGTGGGAGGCCT
   CTGCCGCATAATGCAA
   CATTCATAGAGCTTCA
   TACAGTCGTT'
  becomes ->
   {'Rosalind_1161'
    'CACGATACCGCTTAAATCAGGTGGCATAATTCGCGATAGATCTTGATAGCATAACAA'
    'Rosalind_1495'
    'GTGCAGTGGGAGGCCTCTGCCGCATAATGCAACATTCATAGAGCTTCATACAGT'}"
  ; first element is always ""; use (rest ())
  (def newline-sequences 
    (rest (clojure.string/split raw-rosalind #">Rosalind_\d{4}")))
  (def sequences (map #(drop-from % new-line) newline-sequences))
  (zipmap (re-seq #"Rosalind_\d{4}" raw-rosalind) sequences))

(defn gc-content [freqs]
  "{Rosalind_ID {frequencies}, ...} -> {Rosalind_ID GC-content%, ...}"
  (def gc_size (map #(+ (% \G) (% \C))  (vals freqs)))
  (def size    (map #(apply + (vals %)) (vals freqs)))
  (def zipped  (map vector gc_size size))
  ; Rosalind wants percents as 99.01, instead of .9901
  (zipmap (keys freqs) (map #(* 100 (float (apply / %))) zipped)))

(defn gc [fastas]
  "Rosalind FASTA(s) -> {ID percentage} with max GC content"
  (def freqs (zipmap (keys fastas) (map frequencies (vals fastas))))
  (gc-content freqs))

(def gc-loc 
  (join-file ["src" "rosalind" "04" "gc.txt"]))

(def gc-source 
  (join-file ["src" "rosalind" "04" "rosalind_gc.txt"]))

(defn -main []
  (def to-write 
    (last (sort-by val (gc (format-rosalind (read-file gc-source))))))
  (write-file gc-loc (clojure.string/join "\n" to-write)))