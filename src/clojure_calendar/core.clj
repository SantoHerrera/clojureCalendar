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


(defn updateCalendarV2
  [calendar date nameOfEvent]
  (if (= nil (get-in calendar [date]))
   calendar
   (update-in calendar [date] assoc :booked? true :nameOfEvent nameOfEvent)))
;make updateCalendar
;
;
;
;
;
;

(defn getInput
  []
  (loop [calendar (createDataBase (getNext4WeeksDates))
         dateToChange ""
         nameOfEvent ""]
    (cond
      (or (= dateToChange "e") (= nameOfEvent "e"))
      (doAll
        (println "enter date to be changed followed by the name of the event")
        (println (generate-string (bookedEvents calendar)))
        (println "exits  new"))
      :else (recur (updateCalendar calendar dateToChange nameOfEvent) (read-line) (read-line)))))



(defn getInputV2
  []
  (loop [calendar (createDataBase (getNext4WeeksDates))
         dateToChange ""
         nameOfEvent ""]
     (if (or (= dateToChange "e") (= nameOfEvent "e"))
      (println (generate-string (bookedEvents calendar)))
      (cond-> nextMonth5
        (get-in calendar [dateToChange]) (updateCalendar calendar dateToChange nameOfEvent)))
    (recur calendar (read-line) (read-line))))


(defn getInputV3
  []
  (loop [calendar (createDataBase (getNext4WeeksDates))
         dateToChange ""
         nameOfEvent ""]
    (if (or (= dateToChange "e") (= nameOfEvent "e"))
     (println (generate-string (bookedEvents calendar) {:pretty true}))
     (recur (updateCalendarV2 calendar dateToChange nameOfEvent) (read-line) (read-line)))))

(println (createDataBase (getNext4WeeksDates)))



(defn testV2
  [input]
  (if (= true input) "returns json of bookedEvents"
   (cond-> nextMonth5
     (get-in nextMonth5 [input]) (updateCalendar nextMonth5 input "yee"))))

(get-in nextMonth5 ["2020-12-01"])


  ; if input does not exit in hashmap (println "please do not ")
  ;
  ;
  ;

(cond-> 2
  nil inc
  true inc)


(cond nextMonth5 "yes" false "false")


;what toDo
;1. move recur outside cond
;
;2.add another cond where if dateToChange is not in calendar dont updateCalendar
;
;3.
;
;
;
;


(defn has-value
  [key value]
  (fn [m]
    (= m (m key))))

;"2002-12-01"
(println nextMonth5)

(contains? nextMonth5 :2020-12-01)




;this works
;why doesnt it return things already in order, wouldnt it do it firsst comee first serve??


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
