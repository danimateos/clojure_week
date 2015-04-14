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
  (makecoffee [c])
  (clean [c])
  (refill [c])
  (status [c])
)

(def machinestate [:empty :dirty :full])

(defrecord Filtered [state]
  CoffeeMachine
  (makecoffee [_] (if (= state :full) (Filtered. :dirty) (println "not ready")))
  (clean [_] (if (= state :dirty) (Filtered. :empty) (println "not dirty")))
  (refill [_] (if (= state :empty) (Filtered. :full) (println "not empty")))
  (status [_] state)

  Object
  (toString [this] (str "[" (status this) "]"))
)

(def m (Filtered. :empty))
;>  #'clojure-week.core/m
(makecoffee m)
;> "not ready"
(refill m)
;> #clojure_week.core.Filtered{:state :full}
(makecoffee (refill m))
;> #clojure_week.core.Filtered{:state :dirty}

;; Day 3 exploration



;; Day 3 self study

; Use refs to create a vector of accounts in memory. Create debit and
; credit functions to change the balance of an account.

(def a (ref 0))
(def b (ref 0))
(def c (ref 0))
(def accounts [a b c])

(defn credit 
  [amount account]
  (dosync (alter account + amount))
  )

(defn debit
  [amount account]
  (dosync (alter account - amount))
  )

(credit a 100)
(debit a 40)
(credit b 20)
accounts
;> [#<Ref@7e54be29: 60> #<Ref@6d90b68a: 20> #<Ref@37c05638: 0>]
(map deref accounts)
(60 20 0)
(map (partial credit 20) accounts)
(map deref accounts)
;> (80 40 20)

; Sleeping Barber by Edsger Dijkstra, 1965.

(def timetocut 20)
(def mininterval 10)
(def maxinterval 30)
(def chairs 3)

; Try implementing (size x) that computes the size of a sequence x with
; recursion and with loop/recur.
