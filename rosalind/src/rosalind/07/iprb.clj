(ns rosalind.07.iprb
  (:require [rosalind.core :refer :all]))

(defn inherit-probability
  "Homozygous dominant, heterozygous, and homozygous recessive members.
   Return the odds (as a decimal) that two random members mating
   will create an offspring who displays at least on dominant factor."
  [dominant hetero recessive]
  (let [d dominant
        h hetero
        r recessive
        input (concat (repeat r 2) (repeat h 1) (repeat r 0))
        combs (combinations input 2)
        possible (* 4 (count combs))
        d-prob (* 4 (count (filter #(some #{2} %) combs)))
        h-prob (* 3 (count (filter #(= [1 1] %) combs)))
        r-prob (* 2 (count (filter #(= [1 0] %) combs)))]
    (print d-prob h-prob r-prob)
    (float (/ (+ d-prob h-prob r-prob) possible))))

(def iprb-loc
  (join-file ["src" "rosalind" "07" "rosalind_iprb.txt"]))

(def iprb-source
  (join-file ["src" "rosalind" "07" "iprb.txt"]))

(defn -main []
  (let [input (clojure.string/split (read-file iprb-source) #" ")
        args (map #(read-string %) input)
        dom (first args) 
        het (second args)
        rec (nth args 2)]
   (write-file iprb-loc (inherit-probability dom het rec))))