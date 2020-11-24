(ns clojure-calendar.core
  (:gen-class)
  (:refer-clojure :exclude [range iterate format max min])
  (:require [cheshire.core :refer :all]
            [java-time :refer [local-date plus days]]))

(defn booked-events
  [calendar]
  (into (sorted-map)
   (filter
     (fn [x]
       (get-in x [1 :booked?])) calendar)))

(defn update-calendar
  [calendar date name-of-event]
  (if (= nil (get-in calendar [date]))
   calendar
   (update-in calendar [date] assoc :booked? true :name-of-event name-of-event)))

(defn get-next-x-dates
  [x]
  (loop [count 0
         day (local-date)
         other []]
    (if (= x count)
      other
      (recur (inc count) (plus day (days 1)) (conj other (str day))))))

(defn create-data
   [dates]
   (->> dates
     (map (fn [x]
             {x {:booked? false
                 :name-of-event :nothing-booked-yet}}))
     (into (sorted-map))))

(defn get-input
  []
  (loop [calendar (create-data (get-next-x-dates 365))
         date-to-change ""
         name-of-event ""]
    (println "Enter date, format yyyy-mm-dd, followed by the name of Event")
    (if (or (= date-to-change "e") (= name-of-event "e"))
     (println (generate-string (booked-events calendar) {:pretty true}))
     (recur (update-calendar calendar date-to-change name-of-event) (read-line) (read-line)))))

(defn -main
   "I don't do a whole lot ... yet."
   [& args]
   (get-input))
