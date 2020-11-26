(ns clojure-calendar.core
  (:gen-class)
  (:refer-clojure :exclude [range iterate format max min])
  (:require [cheshire.core :refer :all]
            [java-time :refer [local-date plus days]]))
(def dates-not-in-calendar (atom {}))

(defn booked-events
  [calendar]
  (into (sorted-map)
        (filter
         (fn [x]
          (get-in x [1 :booked?]))
         calendar)))

(defn conj-atom
  [map]
  (swap! dates-not-in-calendar conj map))

(defn update-calendar
  [calendar date name-of-event]
  (if (= nil (get-in calendar [date]))
   (do
     (conj-atom (hash-map date name-of-event))
     calendar)
   (update-in calendar [date] assoc :booked? true :name-of-event name-of-event)))

(defn get-next-x-dates
  [x]
  (loop [count 0
         day (local-date)
         all-dates []]
    (if (= x count)
      all-dates
      (recur (inc count) (plus day (days 1)) (conj all-dates (str day))))))

(defn init-calendar
   [dates]
   (->> dates
     (map (fn [x]
             {x {:booked? false
                 :name-of-event :nothing-booked-yet}}))
     (into (sorted-map))))

(defn get-input
  []
  (read-line))

(defn main
  []
  (loop [calendar (init-calendar (get-next-x-dates 365))
         date-to-change ""
         name-of-event ""]
    (println "Enter date, format yyyy-mm-dd, followed by the name of Event")
    (if (or (= date-to-change "e") (= name-of-event "e"))
     (println (generate-string (booked-events calendar) {:pretty true}))
     (recur (update-calendar calendar date-to-change name-of-event) (get-input) (get-input)))))

(defn -main
   "I don't do a whole lot ... yet."
   [& args]
   (main))
