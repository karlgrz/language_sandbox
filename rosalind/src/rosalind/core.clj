(ns rosalind.core)

(defn cwd []
  "Returns *this* (project root) directory, as a string"
  (apply str (remove #((set ".") %) 
    (-> (java.io.File. ".") .getAbsolutePath))))

(defn read-file [path-from-proj-root]
  "slurp file, given path is built from project root"
  (slurp (str (cwd) path-from-proj-root)))

(defn write-file [filename contents]
  "spit `contents` into file, given path is built from project root"
  (spit (str (cwd) filename) contents))

(defn join-file [files]
  (clojure.string/join (java.io.File/separator) files))