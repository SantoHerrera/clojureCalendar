(ns clojure-calendar.core
  (:gen-class))

(refer-clojure :exclude [range iterate format max min])
(use 'java-time)

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))


(def twentyfour-hours (list 0 1 2 3 4 5 6 7 8 9 10 11 12
                            13 14 15 16 17 18 19 20 21 22 23))



(defn getNext4WeeksDates
  []
  (loop [count 0
         day (local-date)
         other []]
    (if (= 28 count)
      other
      (recur (inc count) (plus day (days 1)) (conj other (str day))))))



(map #(hash-map % twentyfour-hours) (getNext4WeeksDates))
