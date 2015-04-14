;; Empty project file created by Leiningen
(ns clojure-week.core)

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

;; Day 0: setup
;; JVM: should be preinstalled
;; Leiningen: put the lein script somewhere in your path, run it
;; Cider: install package from emacs package manager
;; Paredit: install package from emacs package manager
;; nRPEL: put this in ~/.lein/profiles.clj:
;; {:user {:plugins [[cider/cider-nrepl "CIDER_VERSION]]
;;        :dependencies [[org.clojure/tools.nrepl "NREPL_VERSION"]]}}

;; Day 1 tryouts

(def line [[0 0] [10 20]])

(defn slope [[[xa ya] [xb yb]]] (/ (- yb ya) (- xb xa)))

(slope line)

(def lineList '((0 0) (10 20)))

(slope lineList)

(defn eco [st st2] (str st st2))

;; Day 1 self study
(defn big
  "true if st is longer than n characters"
  [st n]
  (if (= (first st) nil) 
    false
    (if (= n 0) true
        (big (rest st) (- n 1))
        ))
  )

(defn collection-type
  "returns the type of col"
  [col]
  (def myMap {clojure.lang.PersistentList :list, clojure.lang.PersistentVector :vector, clojure.lang.PersistentArrayMap :map})
  (myMap (type col))
  )

;; Day 2 tryout



;; Day 2 self study

(defmacro unless
  [test body else]
  (list 'if (list 'not test) body (list 'do else))
)

(unless false (println "danger") (println "no danger"))
(unless true (println "danger") (println "no danger"))

(defprotocol CoffeeMachine
  makecoffee [c]
  clean [c]
  refill [c]
)

(def machinestate [:clean :empty :dirty])

(defrecord CoffeeMachine [state]
  American  
)
