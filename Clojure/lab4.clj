(defn numOrchar? ; check if its a symbol or a number
	([n]
		(if (or (number? n) (symbol? n))
			true
			false)))
(defn operator	; getting operator
	([list]
		(first list)))
(defn left	; getting first argument
	([list]	
		(first (rest list))))
(defn right	; getting second argument
	([list]	
		(first (rest (rest list)))))
(defn stackifying	
	([n]
		(cond
			(numOrchar? n) ; if argument is a number
				(list (list 'push n)) ; return number or symbol  
			(and (list? n) (= (count n) 2)) ; if operation is a negation
				(if (= (operator n) '-) 
					(concat (stackifying (left n)) '(neg))) ;get the number and write negation	
			 (= (operator n) '+) 							; if operation is an addition
				(concat (stackifying (left n)) (stackifying (right n)) '((add)))	; get the first and second numbers then add
			 (= (operator n) '-)							; if operation is a subtraction
		      		(concat (stackifying (left n)) (stackifying (right n)) '((sub)))	; get the first and second numbers then subtract
			 (= (operator n) '*)							; if operation is a multiplication
				(concat (stackifying (left n)) (stackifying (right n)) '((mul)))	; get the first and second numbers then multiply
			 (= (operator n) '/)							; if operation is a division
				(concat (stackifying (left n)) (stackifying (right n)) '((div)))))) ; get the first and second numbers then divide
(defn stackify ;function that starts the program
	([n] (concat (stackifying n) '((pop))))) ; adds pop to the list

