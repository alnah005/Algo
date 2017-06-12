(def proc-type?
  (fn [type]
    (and
      (not (empty? type))
      (= (first (first type)) 'proc))))

(def simple-type? symbol?)

(def proc-parameters (comp first rest))

(def proc-result (comp first rest rest))

(def list-type?
  (fn [type]
    (and
      (not (empty? type))
      (not (list? (first type))))))

(def error
  (fn [message]
    (throw (Exception. message))))


(def error)
(def pairwise)
(def proc-parameters)
(def proc-result)
(def proc-subtype?)
(def proc-type?)
(def simple-subtype?)
(def simple-type?)
(def subtype?)
(def has-type?)
(def parameterresult-checker)
(def some)

;  SIMPLE-SUBTYPES. Assert that simple subtypes are subtypes of each other.
;
;    t ? complex   t ? real   t ? int
;    -----------   --------   --------
;     t ? real     t ? int    t ? bool

(def simple-subtypes
  (hash-map
    'bool  #{'int 'real 'complex}
    'int   #{'real 'complex}
    'real  #{'complex}))

;  SUBTYPE?. Test if LEFT-TYPE is a subtype of RIGHT-TYPE.
;
;     t ? t     t ? obj
;    -------    -------    
;     true       true

(def subtype?
  (fn [left-type right-type]
    (cond
      (= left-type right-type)
      true
      
      (= right-type 'obj)
      true
      
      (simple-type? left-type)
      (simple-subtype? left-type right-type)
      
      (proc-type? left-type)
      (proc-subtype? left-type right-type)
      
      true
      (error "Type expected."))))

;  SIMPLE-SUBTYPE?. Test if simple type LEFT-TYPE is a subtype of RIGHT-TYPE.

(def simple-subtype?
  (fn [left-type right-type]
    (and
      (simple-type? right-type)
      (contains? (get simple-subtypes left-type) right-type))))


;  PROC-SUBTYPE?. Test if PROCedure type LEFT-TYPE is a subtype of RIGHT-TYPE.
;
;    proc (t1 t2 ... t?) t ? proc (T1, T2 ..., T?) T
;    -----------------------------------------------
;       T1 ? t1,  T2 ? t2  ...,  T? ? t?,  t ? T

(def proc-subtype?
  (fn [left-type right-type]
    (and
      (proc-type? right-type)
      (subtype?
        (proc-result left-type)
        (proc-result right-type))
      (pairwise
        (fn [left-parameter right-parameter]
          (subtype? right-parameter left-parameter))
        (proc-parameters left-type)
        (proc-parameters right-type)))))

;  PAIRWISE. Test if each element of LEFTS, and each positionally corresponding
;  element of RIGHTS, satisfies the 2-ary predicate TEST?.

(def pairwise
  (fn [test? lefts rights]
    (if
      (or
        (empty? lefts)
        (empty? rights))
      (and
        (empty? lefts)
        (empty? rights))
      (and
        (test?
          (first lefts)
          (first rights))
        (recur test?
               (rest lefts)
               (rest rights))))))

(def some 
  (fn [etc form form-saved returntype map]    
    (if (empty? (first form-saved))
      false
      (if (etc form returntype (assoc (dissoc map (first form)) (first form) (list (first form-saved))))
        true
        (some etc form (rest form-saved) returntype map)))))

(def parameterresult-checker
  (fn [etc original shouldbe map]
    (if (empty? shouldbe)
      (if (empty? original)
        true
        (error "wrong airety"))
      (if (and (not (empty? shouldbe))(not (empty? original)))
        (and (etc (first original) (first shouldbe) map)
             (parameterresult-checker etc (rest original) (rest shouldbe) map))
        (error "wrong airety")))))

(def has-type?
  (fn [form returntype map]
    (if (symbol? form) 
      (let [form-saved (get map form)]          
        (cond 
          (simple-type? form-saved)
          (subtype? form-saved returntype)
          
          (list-type? form-saved)
          (subtype? (first form-saved) returntype)
          
          (> (count form-saved) 1)
          (some has-type? form form-saved returntype map)
          
          (proc-type? form-saved)
          (and 
            (parameterresult-checker has-type? '() (proc-parameters (first form-saved)) map)
            (subtype? (proc-result (first form-saved)) returntype))
          
        		true
        		(error "Type expected.")))
      
      (let [form-saved (get map (first form))]
        (cond
          
          (simple-type? form-saved)
          (subtype? form-saved returntype)
          
          (list-type? form-saved)
          (subtype? (first form-saved) returntype)
          
          
          (> (count form-saved) 1)
          (some has-type? form form-saved returntype map)  
          
          
          (proc-type? form-saved)
          (and 
            (parameterresult-checker has-type? (rest form) (proc-parameters (first form-saved)) map)
            (subtype? (proc-result (first form-saved)) returntype))
          
          		true
        		(error "Type expected."))))))

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

