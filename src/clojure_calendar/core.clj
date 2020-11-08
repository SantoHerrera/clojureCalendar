(ns clojure-calendar.core
  (:gen-class))

(refer-clojure :exclude [range iterate format max min])
(use 'java-time)

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

; make this a cond instead of (if
;ex.
;(cond (= input "e") (exitProgram)
;      :else do (updateCalendar) (recur))
;               (recur)
;
;

;put do on (= input "e")
;and println filtered aswell
;
;
;
;;
;



;(def booked-events
 ; (filter (fn [[dt {:strs [booked?]}]]
 ;           booked?)
 ;         nextMonth))

(defn getNext4WeeksDates
  []
  (loop [count 0
         day (local-date)
         other []]
    (if (= 28 count)
      other
      (recur (inc count) (plus day (days 1)) (conj other (str day))))))

(defn randomBoolean
  []
  (if (= 0 (rand-int 2))
   true
   false))

(defn createDataBase
  [dates]
  (->> dates
   (map (fn [x]
         {x {:booked? (randomBoolean)
             :nameOfEvent :nothingBookedYet}}))
   (into {})))

(def nextMonthV2 (createDataBase (getNext4WeeksDates)))


(defn nextMonthV3 [] (createDataBase (getNext4WeeksDates)))

(println (createDataBase (getNext4WeeksDates)))

;
;todo add onEnter function
;
;
;
;
;


(defn getInputV3
  []
  (loop [input "nothing"
         allInputs []]
    (cond
      (= input "e") (println "exitsg program")
      :else (do
              (filter (fn [[dt {:strs [booked?]}]]
                          booked?)
                      nextMonthV2)
              (recur (read-line) (conj allInputs input))))))



(defn getInputV4
  []
  (loop [input "nothing"
         allInputs []]
    (cond
      (= input "e")
      (do
        (mapcat println booked-events)
        (println "exits program"))
      :else (recur (read-line) (conj allInputs input)))))






(def booked-events
 (filter (fn [[dt f
               dt {:strs [booked?]}]]
           (println dt))
         nextMonthV2))


(println booked-events)


    ;
    ; (def twentyfour-hours (map #(merge (hash-map % false name "nothing")) (vectorXLong 4)))







(def booked-eventsV2
  (filter (fn [[dt {:strs [booked?]}]]
            booked? (println booked?))
          nextMonthV2))

(println booked-eventsV2)

(let [dt {:keys [booked?]} nextMonthV2]
  (println dt))

(def bookedV2
  (filter (fn [[dt {:strs [booked?]}]]
           (println "fasdf" booked?))
    nextMonthV2))

(println bookedV2)

(println nextMonthV2)




(filter (fn [[dt {:strs [booked?]} ]] booked?) nextMonthV2)





(into {} (filter (fn [[dt {:strs [booked?]} ]] dt) nextMonthV2))




(filter (fn [x] (let [obj x secondLayer obj] (println "whats this layer" (obj :booked?)))) nextMonthV2)



(filter (fn [x] (let [obj x] (println "whats this layer" ((into {} obj) :booked?)))) nextMonthV2)



(let [firstLayer nextMonthV2
      secondLayer (:booked? firstLayer)]
  (println "idk"  secondLayer))




(filter (fn [x] (let [firstLayer {:strs [booked?]}]
                 (println secondLayer "idk")))
   nextMonthV2)






(let [{{booked :booked?} :keys} nextMonthV2]
 (println "whats happening " booked))


(filter (fn [x] (let [{booked :booked?} x] (println booked "fuck") nextMonthV2)))
;this one works, kinda
(map
  (fn
    [dt]
    (let [[a b] dt] (println "fuck " b)))
 nextMonthV2)

(fn [[dt {:strs [booked]}]]
    sd)
; ;
;   (filter (fn [[dt {:strs [booked?]}]]
;             booked? (println booked?))
;           nextMonthV2)

(nextMonthV3)

(nextMonthV3 {:keys "true"?})


(let [{allKeys :keys booked} nextMonthV2]
  (println "idk" allKeys booked?))


(def eventsNotWithinTimeline (atom []))



(defn print-board
  [board]
  (doseq [a board]
   (println a)))





;change any number to true
;











(defn main
  []
  (loop [calendar (assignHoursToDates (getNext4WeeksDates))
         input "nothing"]
    (if (= "e" input)
      (print-board calendar)
      (recur calendar (read-line)))))
