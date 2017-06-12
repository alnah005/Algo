(def whiching
	(fn [S e]
		 (if (empty? e)
			nil
		(concat (list (= S (first e)) (second e)) (whiching S (rest (rest e)))))))  
(defmacro which [S & e]
	(concat '(cond) (whiching S e)))


(def mosting
	(fn [a]
	 (if (or (empty? a)(nil? (first a)))
			'()
		(concat (list (list 'if (first a) 1 0)) (mosting (rest a))))))

(defmacro most [ & a]	
	(concat (list '> (merge (mosting a) '+) (list '/ (count a) 2))))


(def compsing
	(fn( [F k]
		(if (= k 0)
			k
		(compsing F k (list F k))))
	   ( [F k G]
		(if  (> k 1)
		(compsing F (- k 1) (list F G))
		 G))))
(defmacro comps [G k]
	 (compsing G k))  


(defmacro qrat [s a b c]
	`(/ (~s (- ~b)(Math/sqrt (- (* ~b ~b) (* ~4 ~a ~c))))(* ~2 ~a)))




























	
