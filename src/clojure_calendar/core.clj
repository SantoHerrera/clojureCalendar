(ns clojure-calendar.core
  (:gen-class))


(ns my
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
         {x {:booked? (randomBoolean)
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



;what todo
;make a macro given nextMonth4
;filters all trues and rreturns
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
