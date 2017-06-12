;this program tells you whether your propositional exression is either a tautology, contradiction, or neither.
;to use it, simply put (tautology? '(insert proposition here)) then it will tell you what it thinks.

(def emptyornotlist
  (fn [l]
    (if (or (not (list? l))(empty? l))
      true
      false)))

(def ifify 
  (fn [q]
    (if (emptyornotlist q)
      q
      (let [oper (first q) a (first (rest q)) b (first (rest (rest q)))]
        (cond   (= oper 'not)  (list 'if (ifify a) false     true)
                (= oper 'and)  (list 'if (ifify a) (ifify b) false)
                (= oper 'or)   (list 'if (ifify a) true      (ifify b))				
                (= oper 'imply)(list 'if (ifify a) (ifify b) true)
                (= oper 'equiv)(list 'if (ifify a) (ifify b) (list 'if (ifify b) false true)))))))

(def iftester
  (fn [q]
    (if (not (emptyornotlist q))
      (if (= (first q) 'if)
        true
        false)
      false)
    ))

(def secarg
  (fn [q]
    (if (emptyornotlist q)
      q
      (second q))))

(def thirarg
  (fn [q]
    (if (emptyornotlist q)
      q
      (first (rest (rest q))))))

(def fourarg
  (fn [q] 
    (if (emptyornotlist q)
      q
      (last q))))

(def make-if
  (fn [x w m]
    (list 'if x w m)))

(declare normalize)

(def normalizer
  (fn [q]
    (make-if (secarg (secarg q))
             (normalize (make-if (normalize (thirarg (secarg q))) (normalize (thirarg q)) (normalize (fourarg q))))
             (normalize (make-if (normalize (fourarg (secarg q))) (normalize (thirarg q)) (normalize (fourarg q)))))))  

(def normalize
  (fn [q]
    (if (emptyornotlist q)
      q
      (if (iftester q)
        (if (iftester (secarg q))
          (normalize (normalizer  q))
          q)
        q))))

(declare simplify)
(def substitute
  (fn [L V N]
    (if (not (list? L))
      (if (= L V) N L) 
      (simplify (make-if(substitute (secarg L) V N)
                        (substitute (thirarg L) V N)
                        (substitute (fourarg L) V N))))))

(def simplify
  (fn [q]
    (if (emptyornotlist q)
      q
      (cond	(true? (secarg q))(simplify (thirarg q))
            (false? (secarg q)) (simplify (fourarg q))
            (and (true? (thirarg q)) (false? (fourarg q))) (simplify (secarg q))
            (= (thirarg q) (fourarg q)) (simplify (thirarg q))
            :else 	(make-if (secarg q)    
                            (simplify (substitute (simplify (thirarg q)) (secarg q) true))
                            (simplify (substitute (simplify (fourarg q))(secarg q) false)))))))
(def tautology?
  (fn [q]
    (if (emptyornotlist q)
      q
      (let [proposition (simplify (simplify (normalize (ifify q))))]
        (cond 
          (true? proposition) (do (println q) (println "Is a tautology!"))
          (false? proposition)(do (println q) (println "Is a contradiction!"))
          :else (do (println q) (println "simplifies to")(println proposition)(println "So, it is neither a tautology nor a contradiction!"))
          )
        ))))
