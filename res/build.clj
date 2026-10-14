(ns build
  (:require
   [babashka.fs :as fs]
   [home]
   [not-found]
   [routes]))

(fs/delete-tree "public")
(fs/create-dirs "public")

(def out-path "public/")

(fs/copy-tree "static" out-path)

(defn write!
  "Use this below to create each page."
  [url html]
  (let [relative-path (routes/relative-path url)
        out (str out-path
                 (case relative-path
                   "/404" "404.html"
                   (str relative-path "/index.html")))]
    (fs/create-dirs (fs/parent out))
    (spit out html)))

(write! routes/home home/page)
(write! routes/not-found not-found/page)