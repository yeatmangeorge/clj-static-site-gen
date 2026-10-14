(ns not-found
  (:require [page :as p]
            [routes]))

(def page
  (p/page routes/not-found "Not Found" [:h1 "Not Found"]))