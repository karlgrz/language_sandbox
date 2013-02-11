(defn gc [fastas]
  "Rosalind FASTA(s) -> 'ID\npercentage' with max GC content"
  (def sfastas (apply str (remove #((set ">") %) fastas)))
  (def hfastas (apply array-map (clojure.string/split sfastas #"\s+")))
  
  )