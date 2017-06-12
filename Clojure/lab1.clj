(def epsilon 0.000001)

(def exp-h
  (fn [ x S T F]
	(if (<= T epsilon)
	S
	(recur x (+ S T) (* T (/ x F)) (+ 1.0 F)))))

(def exponential
 (fn[x] 
	(exp-h x 0.0 1.0 1.0)))

(def absulote
 (fn[x]
	(if (> x 0) x 
	(* x -1))))
(def square-rootH
 (fn[x G H]
	(if (< (absulote (- G H))  epsilon)
	G
	(recur x (/ (+ G H) 2.0) (/ x (/ (+ G H) 2.0))))))	

(def square-root
 (fn[x]
	(if (< x 0) "number can't be negative" 
	 (square-rootH x x 1.0)))
)

