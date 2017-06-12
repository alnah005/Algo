(def emptyornotlist?
  (fn [S]
    (if (or (not (list? S)) (empty? S))
      true
      false
      )
    )
  )

(def some 
  (fn [P S element]
    (if (empty? S)
      false
      (if (P (first S) element)
        true
        (some P (rest S) element))
      )
    )
  )


(def unique
  (fn [list]
    (if (not (empty? list))
      (if (some = (rest list) (first list))
        (unique (rest list))				
        (cons (first list) (unique (rest list)))
        )
      )
    )
  )

(def fnorseq
  (fn [S]
    (if (list? (first S))
      (first S)
      S
      )
    )
  )

(def lambda-parameters? 
  (fn [P]
    (if (and (vector? P) (= (count P) (count (unique P))))
      true
      false
      )
    )
  )

(declare lambda?)

(def lambda-fn?
  (fn [L S]
    (if (lambda-parameters? (second L))
      (lambda? (fnorseq (rest (rest L))) (concat S (second L)))
      (lambda? (fnorseq (rest (rest L))) S)
      )
    )
  )

(declare lambda-symbol?)							  

(def lambda-call? 
  (fn [L S]
    (if (empty? (rest L))
      (lambda-symbol? (first L) S)
      (if (lambda-symbol? (first L) S)
        (lambda? (rest L) S)
        false)
      )
    )
  )

(def lambda-symbol?
  (fn [N S]
    (some = S N)
    )
  )

(def lambda?
  (fn 	([L](lambda? L '()))
    ([L S]
      (if (not (emptyornotlist? L))
        (if (list? L) 
          (if (list? (first L))
            (if (empty? (rest L))
              (lambda? (first L) S)
              (do (lambda? (first L) S)
                (lambda? (rest L) S)
                )
              )  		
            (if  (= (first L) 'fn)
              (lambda-fn? L S)
              (lambda-call? L S)
              )
            )
          )
        (lambda-symbol? L S)
        )
      )
    )
  )
