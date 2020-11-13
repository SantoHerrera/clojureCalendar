(ns clojure-calendar.core
  (:gen-class)
  (:require [cheshire.core :refer :all]))



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
         {x {:booked? false
             :nameOfEvent :nothingBookedYet}}))
   (into {})))

(def nextMonth5 (createDataBase (getNext4WeeksDates)))


(defn nextMonthV3 [] (createDataBase (getNext4WeeksDates)))

;
;todo add
;instead of random boolean, make all default to false
;update with utser input
;


(defn bookedEvents
  [calendar]
  (into {}
   (filter
     (fn [x]
       (get-in x [1 :booked?])) calendar)))

(generate-string (bookedEvents nextMonth5))

(defn updateCalendar
  [calendar date nameOfEvent]
  (update-in calendar [date] assoc :booked? true :nameOfEvent nameOfEvent))

(updateCalendar nextMonth5 "2020-12-01" "fuck it acutally worked")

(println nextMonth5 "2020-12-01")

(defn getInput
  []
  (loop [calendar (createDataBase (getNext4WeeksDates))
         dateToChange ""
         nameOfEvent ""]
    (cond
      (or (= dateToChange "e") (= nameOfEvent "e"))
      (doall
        (println "enter date to be changed followed by the name of the event")
        (println (generate-string (bookedEvents calendar)))
        (println "exits  new"))
      :else (recur (updateCalendar calendar dateToChange nameOfEvent) (read-line) (read-line)))))





;what todo
;
;
;
;
;
;



















(apply println (into {} (filter (fn [x] (get-in x [1 :booked?])) nextMonth4)))

(println nextMonth4)

(update-in nextMonth4 ["2020-12-01"] assoc :booked? true)

(println nextMonth4)

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



















(def fuck {:foo "bar" :baz 5})


(generate-string fuck)
