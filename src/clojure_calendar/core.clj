(ns clojure-calendar.core
  (:gen-class))

(refer-clojure :exclude [range iterate format max min])
(use 'java-time)

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))


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

(def nextMonth4 (createDataBase (getNext4WeeksDates)))


(defn nextMonthV3 [] (createDataBase (getNext4WeeksDates)))

;
;todo add onEnter function
;
;
;
;
;

(defn getInput
  []
  (loop [input "nothing"
         allInputs []]
    (cond
      (= input "e")
      (doall
        (apply println (into {} (filter (fn [x] (get-in x [1 :booked?])) nextMonth4)))
        (println "exits  new"))
      :else (recur (read-line) (conj allInputs input)))))


(mapcat println nextMonth4)

(apply println (into {} (filter (fn [x] (get-in x [1 :booked?])) nextMonth4)))


;this works
;why doesnt it return things already in order, wouldnt it do it firsst comee first serve??

(map println (into {} (filter (fn [x] (get-in x [1 :booked?])) nextMonth4)))


(defn print-board
  [board]
  (doseq [a board]
   (println a)))









(defn main
  []
  (loop [calendar (assignHoursToDates (getNext4WeeksDates))
         input "nothing"]
    (if (= "e" input)
      (print-board calendar)
      (recur calendar (read-line)))))
