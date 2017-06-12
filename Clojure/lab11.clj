(declare satisfy)
(def partial-ordering  
(hash-map  
	'A  #{'C}  
	'B  #{'H}  
	'C  #{'G}  
	'G  #{'D 'E}  
	'D  #{'F}  
	'E  #{'H}  
	'H  #{'F}  
	'I  #{'B 'E}))
(def delete
	(fn [objects object]
		(if (= (first objects) object)
			(rest objects)
			(concat (list (first objects)) (delete (rest objects) object)))))
		
(def precedes?
	(fn [left right]
		(contains? (get partial-ordering left) right))) 

(def unpreceded?
	(fn [precedes? left letters]
		(if (empty? letters)
			true 
			(if (precedes? (first letters) left) 
			false
			(recur precedes? left (rest letters))))))
		
(def helper
	(fn [object objects]
		(cons object (satisfy precedes? (delete objects (first objects))))))
		
(def unpreceded
	(fn [etc precedes? objects]
		(cond 
			(empty? objects)
			'()

			(unpreceded? precedes? (first objects) (rest objects))
			(etc (first objects) objects)
			
			true
			(recur etc precedes? (concat (rest objects) (list (first objects)))))))
		
(def satisfy
	(fn [precedes? objects]
		(if (not (empty? objects))
			(unpreceded helper precedes? objects)
			objects)))
