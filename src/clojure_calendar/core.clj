(ns clojure-calendar.core
  (:gen-class)
  (:require [cheshire.core :refer :all]))


(refer-clojure :exclude [range iterate format max min])
(use 'java-time)


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))



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


(defn bookedEvents
  [calendar]
  (into {}
   (filter
     (fn [x]
       (get-in x [1 :booked?])) calendar)))


(defn updateCalendar
  [calendar date nameOfEvent]
  (if (= nil (get-in calendar [date]))
   calendar
   (update-in calendar [date] assoc :booked? true :nameOfEvent nameOfEvent)))


(defn getInput
  []
  (loop [calendar (createDataBase (getNext4WeeksDates))
         dateToChange ""
         nameOfEvent ""]
    (if (or (= dateToChange "e") (= nameOfEvent "e"))
     (println (generate-string (bookedEvents calendar) {:pretty true}))
     (recur (updateCalendarV2 calendar dateToChange nameOfEvent) (read-line) (read-line)))))
