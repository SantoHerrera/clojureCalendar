(ns clojure-calendar.core
  (:gen-class))

(refer-clojure :exclude [range iterate format max min])
(use 'java-time)

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!")


 (def twentyfour-hours (list 12 3 6 9)))

; (def twentyfour-hours (list 0))



(defn getNext4WeeksDates
  []
  (loop [count 0
         day (local-date)
         other []]
    (if (= 28 count)
      other
      (recur (inc count) (plus day (days 1)) (conj other (str day))))))


(defn assignHoursToDates
  [dates]
  (map #(hash-map % twentyfour-hours) dates))

(assignHoursToDates (getNext4WeeksDates))

(def calendarV2 (assignHoursToDates (getNext4WeeksDates)))

(def calendarV3 (partition 7 calendarV2))

(println calendarV3)

(print-board calendarV3)



(defn print-board
  [board]
  (doseq [a board]
   (println a)))

(partition 7 calendarV2)








































(defn main
  []
  (loop [calendar (assignHoursToDates (getNext4WeeksDates))
         input "nothing"]
    (if (= "e" input)
      (print-board calendar)
      (recur calendar (read-line)))))










(partition 3 (assignHoursToDates (getNext4WeeksDates)))


(map #(hash-map % twentyfour-hours) (getNext4WeeksDates))


; TODO
; INSTEAD OF LIST OF 0 - 23
; make it hashmap of 0 - 23 being keys, and all being false
;
;
;
;
