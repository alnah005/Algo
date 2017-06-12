(def matching
  (fn [table pattern subject]
    (cond (keyword? pattern) 				(if	 (= (get table pattern) nil) 	(assoc table pattern subject)	 (if (= (get table pattern) subject) table nil)		)
          (and (seq? pattern) (seq? subject) (not (empty? pattern)) (not (empty? subject)))		(let [t (matching table (first pattern) (first subject))]	(if (= t nil) t (matching t (rest pattern) (rest subject))))
          (= pattern subject) table
          :else nil)
   )
)  
  (def match
    (fn [pattern subject]
      (matching (hash-map) pattern subject)))
