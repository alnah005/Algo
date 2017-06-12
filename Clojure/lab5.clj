(declare choosing) ; choosing is called before getting defined
(def thru	;lecture code
	(fn [etc start end]
		(loop [index start]
		(if
			(< index end)
			(do
			(etc index)
			(recur (+ index 1)))))))
(def choose ;function that calls choosing
	(fn [etc n k]
		(choosing etc '() n k 0)))
(def choosing
	(fn [etc l n k e]
		(if (= k 0) ;if you want a number of arguments at a time
			(etc l) ; print the new list
			(thru (fn [w]
				(choosing etc (cons w l) n (- k 1) (+ 1 w))) e n)
			)))
