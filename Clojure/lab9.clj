(def euler
	(fn ([x]
		(euler 1 x 1))
		([term x n] (list term (fn [] (euler (/ (* x term) n) x (+ n 1)))))))
		
(def next-term 
	(fn [terms]
		(first terms)))
(def remaining-terms
	(fn [terms]
		((first (rest terms)))))
		
(def sum
	(fn [terms epsilon]
		(if (> (next-term terms) epsilon)
			(+ (next-term terms) (sum (remaining-terms terms) epsilon))
			0)))
			
