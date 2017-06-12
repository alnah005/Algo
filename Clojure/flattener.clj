(def flattener
	(fn [lissst]
		(if (list? lissst)
			(if (empty? lissst)
				lissst
				(if (list? (first lissst))
					(concat (flattener (first lissst)) (flattener (rest lissst)))
					(concat (list (first lissst)) (flattener (rest lissst)))))
					lissst))) 
					
(def howmany
	(fn [lissst mapped]
		(if (list? lissst)
			(if (empty? lissst)
				mapped
				(if (list? (first lissst))
					(howmany (rest lissst) (howmany (first lissst) mapped))
					(if (contains? mapped (first lissst))
						(howmany (rest lissst) (assoc mapped (first lissst) (+ (get mapped  (first lissst)) 1)))
						(howmany (rest lissst) (assoc mapped (first lissst) 1))))))))
