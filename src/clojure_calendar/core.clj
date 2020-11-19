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
    (if (= 365 count)
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
(println (createDataBase (vectorXLong 28)))
(createDataBase (vectorXLong 28))


(createDataV2 (vectorXLong 5))

(def nextMonth5 (createDataBase (vectorXLong 28)))
(println nextMonth5)
(createDataBase (vectorXLong 20))



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



(defn vector-X-Long
  [x]
  (loop [itr 0
         arr []]
    (if (= itr x)
     arr
     (recur (inc itr) (conj arr itr)))))

(vectorXLong 28)




Hey guys, a little confused by this behavior.
(defn vector-x-long
  [x]
  (loop [itr 0
         arr []]
    (if (= itr x)
     arr
     (recur (inc itr) (conj arr itr)))))


(defn create-data
  [dates]
  (->> dates
    (map (fn [x]
            {x {:booked? false
                :nameOfEvent :nothingBookedYet}}))
    (into (sorted-map))))

(def lost (create-data (getNext4WeeksDates)))
Why doesnt this return the hashmap in order. ex. {0 {}, 1 {}, 2 {} ...}
Instead it returns what feels like randomness. When I println x inside create-date its in order.
(println lost)
How could I get it for def lost to be in order?
