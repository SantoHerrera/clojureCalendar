(ns clojure-calendar.core
  (:gen-class))

(refer-clojure :exclude [range iterate format max min])
(use 'java-time)

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(defn getInput
  []
  (loop [input "nothing"
         allInputs []]
   (if (= "e" input)
     (println "exits program" allInputs)
     (recur (read-line) (conj allInputs input)))))



(println (count nextMonth))




(defn getNext4WeeksDates
  []
  (loop [count 0
         day (local-date)
         other []]
    (if (= 28 count)
      other
      (recur (inc count) (plus day (days 1)) (conj other (str day))))))


(println (getNext4WeeksDates))


(def nextMonth
 (into {} (map (fn [x] (hash-map x (hash-map "booked?" (randomBoolean) "nameOfEvent" "nothingBookedYet"))) (getNext4WeeksDates))))



(def booked-events
 (filter (fn [[dt {:strs [booked?]}]]
           booked?)
         nextMonth))

(defn createDataBase
  [dates]
  (->> dates
   (map (fn [x]
         {x {:booked? (randomBoolean)
             :nameOfEvent :nothingBookedYet}}))
   (into {})))

(createDataBase (getNext4WeeksDates))

(defn randomBoolean
  []
  (if (= 0 (rand-int 2))
   true
   false))














    ;
    ; (def twentyfour-hours (map #(merge (hash-map % false name "nothing")) (vectorXLong 4)))




































































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
