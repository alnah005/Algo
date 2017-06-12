(def error
	(fn [message]
		(throw (Exception. message))))
(def adder
	(fn [top object] 
		(if (empty? top)	
			(list object) 
		(cons (first top) (adder (rest top) object)))))
(declare queue)
(def pushes
	(fn [queue objects]
		(if 
			(empty? objects)
				queue
			(pushes 
				(queue :enqueue (first objects))
				(rest objects)))))
(def queue
	(fn []
		(letfn
			[(make-queue [top]
				(fn [method & arguments]
					(letfn
						[(empty? []
							(= top '()))
						(enqueue [object]
							(make-queue (adder top object)))
						(front []
							(if (empty?)
								(error "stack is empty")
								(first top)))
						(dequeue [] (if (empty?) (error "stack is empty") (make-queue (rest top))))]
							(cond 
								(= method :empty?)
								(empty?)
								(= method :enqueue)
								(enqueue (first arguments))
								(= method :dequeue)
								(dequeue)
								(= method :front)
								(front)
								true
								(error "no such method")))))]
								(make-queue '()))))
