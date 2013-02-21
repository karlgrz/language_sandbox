(ns rosalind.core)

(def cwd
  "Returns *this* (project root) directory, as a string"
  (apply str (remove #((set ".") %) 
    (-> (java.io.File. ".") .getAbsolutePath))))

(def new-line
  "Returns an appropriate newline string for your OS"
  (if (re-find #"Windows" (System/getProperty "os.name")) 
    "\r\n" "\n"))

(defn read-file [path-from-proj-root]
  "slurp file, given path is built from project root"
  (slurp (str cwd path-from-proj-root)))

(defn write-file [filename contents]
  "spit `contents` into file, given path is built from project root"
  (spit (str cwd filename) contents))

(defn join-file [files]
  (clojure.string/join (java.io.File/separator) files))

(defn drop-from [target, what]
  "Delete character(s) in `target` matching string `what`"
  (apply str (remove #((set what) %) target)))