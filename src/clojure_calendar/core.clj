(ns clojure-calendar.core
  (:gen-class)
  (:refer-clojure :exclude [range iterate format max min])
  (:require [cheshire.core :refer :all]
            [java-time :refer :all]))


; (ns clojure-calendar.clojure
;   (:gen-class)
;   (:require [cheshire.core :refer :all]
;             [java-time :as jt]))


(defn bookedEvents
  [calendar]
  (into (sorted-map)
   (filter
     (fn [x]
       (get-in x [1 :booked?])) calendar)))


(defn updateCalendar
  [calendar date nameOfEvent]
  (if (= nil (get-in calendar [date]))
   calendar
   (update-in calendar [date] assoc :booked? true :nameOfEvent nameOfEvent)))


(defn getNext4WeeksDates
  []
  (loop [count 0
         day (local-date)
         other []]
    (if (= 365 count)
      other
      (recur (inc count) (plus day (days 1)) (conj other (str day))))))


(defn create-data
   [dates]
   (->> dates
     (map (fn [x]
             {x {:booked? false
                 :nameOfEvent :nothingBookedYet}}))
     (into (sorted-map))))


(defn getInput
  []
  (loop [calendar (create-data (getNext4WeeksDates))
         dateToChange ""
         nameOfEvent ""]
    (println "Enter date, followed by the name of Event")
    (if (or (= dateToChange "e") (= nameOfEvent "e"))
     (println (generate-string (bookedEvents calendar) {:pretty true}))
     (recur (updateCalendar calendar dateToChange nameOfEvent) (read-line) (read-line)))))


(defn -main
   "I don't do a whole lot ... yet."
   [& args]
   (getInput))

;format is yyyy-mm-dd
;ex. 2021-04-28
;
