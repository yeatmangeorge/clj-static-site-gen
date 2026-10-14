(ns routes)

(def base-path (or (System/getenv "BASE_PATH") ""))

(defmacro defurl [name path]
  `(def ~name (str base-path ~path)))

(defn relative-path [url]
  (subs url (count base-path)))

(defurl home "/")
(defurl not-found "/404")