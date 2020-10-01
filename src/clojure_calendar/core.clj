(ns clojure-calendar.core
  (:gen-class))

(refer-clojure :exclude [range iterate format max min])
(use 'java-time)

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(local-date 1999 04 2)


(def now (local-date))

(plus now (days 1))




(defn idk
  []
  (loop [day (local-date)
         count 0
         allDays []]
    (if (= count 28)
      allDays
      (recur (plus day (days 1) (inc count) (conj allDays day))))))





(defn test
  []
  (loop [count 0
         day (local-date)
         other []]
    (if (= 28 count)
      other
      (recur (inc count) (plus day (days 1)) (conj other day)))))

(test)
