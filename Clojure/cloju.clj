(def implying
	(fn [form remaining]
		(if (= (count remaining) 0)
			(list form)
			(concat (list (list 'not form)) (implying (first remaining) (rest remaining))))))

(defmacro imply [form & remaining]
	(if (empty? remaining)
		form
		(cons 'or (implying form remaining))))


;If I only print (first (rest form)) then this program will only work with symbols! That's why I decided to only print the form
;Technically quote a in clojure is equivalent to 'a
;user=>(quote a)
;a
;user=> (macroexpand-1 '(imply 'a 1 2 'x 2 4 5 22 1 1412 ))
;(or (not (quote a)) (not 1) (not 2) (not (quote x)) (not 2) (not 4) (not 5) (not 22) (not 1) 1412)

