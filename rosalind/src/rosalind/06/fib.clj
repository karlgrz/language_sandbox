(ns rosalind.06.fib
  (:require [rosalind.core :refer :all]))

(defn breed [month months last-month this-month litter-size]
  (if (= month months) this-month
    (let [last this-month
          this (+ (* litter-size last-month) this-month)
          m (+ month 1)]
      (breed m months last this litter-size))))

(defn fib [months litter-size]
  (if (= months 2) 1)
  (if (= months 3) litter-size)
  (let [month 3
        last-month 1
        rabbits (+ 1 litter-size)]
    (breed month months last-month rabbits litter-size)))

(def fib-loc
  (join-file ["src" "rosalind" "06" "fib.txt"]))

(def fib-source
  (join-file ["src" "rosalind" "06" "rosalind_fib.txt"]))

(defn -main [] 
  (let [input (clojure.string/split (read-file fib-source) #" ")
        args (map #(read-string %) input)
        months (first args) 
        litter-size (second args)]
   (write-file fib-loc (fib months litter-size))))
  