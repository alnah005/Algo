(declare depth)
(def looper (fn [destinations visited number]
              (if (> number 0)
                (do (depth (rest destinations) (cons (first destinations) visited))
                  (looper (concat (rest destinations) (list (first destinations))) visited (- number 1)))
                )))
(def depth (fn [destinations visited]
               (if (empty? destinations)
			visited
			(looper destinations visited (count destinations)))))