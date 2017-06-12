(def some 
	(fn [P S element]
		(if (empty? S)
			false
		(if (P (first S) element)
			true
			(some P (rest S) element)))))
			
(def unique
	(fn [F list]
		(if (not (empty? list))
			(if (some = (rest list) (first list))
				(unique F (rest list))				
				(do (F (first list)) (unique F (rest list)))		
			)
		)
	)
)
(def combiner
	(fn [F element list]
		(if (not (empty? list))
		(cons (F element (first list)) (combiner F element (rest list)))	
		'()		
		)		
	)
)
(def cartesian
	(fn [F list1 list2]
		(if (not (empty? list1))
			(if (not (empty? list2))
				(concat (combiner F (first list1) list2) (cartesian F (rest list1) list2))
			'()			
			)
		'()
		)
	)
)
(def nest
	(fn [F N]
		(if (= N 0)
			(fn[n] n)
			(comp F (nest F (- N 1)))		
		)
	)
)
