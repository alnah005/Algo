(def merging
	(fn [less? left right]
		(if (empty? left) 	;left is empty
				right	;return right
				(if (empty? right)	;if left is not empty check if right is empty
					left		; if it is then return left
					(if (less? (first left)(first right)) ; if both of them are not empty check the condition
						(cons (first  left) (merging less? (rest left) right))	;if the condition is true add first left to the list
						(cons (first right) (merging less? left (rest right)))	;if false add first right to the list
					)		
				)
			)
		)
	)


(declare mergesort)
(def splitting
	(fn [less? unsorted left right]
		(if (> (count unsorted) 1) ; our list has more than one element
			(splitting less? ; call splitting with our original and two lists
				(rest (rest unsorted))	; our original list minus two elements
				(cons (first unsorted) left) ; the first element is added to our left list
			 	(cons (first (rest unsorted)) right) ;the second element is added to our right lest
			)
			(if (= (count unsorted) 1) ; if our list is only one element
				(merging less? ; then call merging with what you have and add that element to the left list
					(mergesort less? (cons (first unsorted) left))
					(mergesort less? right)
				)
				(merging less? ;if our list is empty call merging with the lists we have
					(mergesort less? left)
					(mergesort less? right)
				)
			)
		)
	)
)
			
(def mergesort
	(fn[ less? unsorted]
		(if (or (empty? unsorted) (= (count unsorted) 1)) ;if our list is empty or is just one element
			unsorted ;return our list
			(splitting less? unsorted '() '())))) ; else call splitting
