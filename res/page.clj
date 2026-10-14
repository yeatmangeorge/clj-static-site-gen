(ns page
  (:require
   [hiccup2.core :as h]
   [navbar]
   [routes]))

(defn page [url title & body]
  (str "<!doctype html>"
       (h/html
        [:html {:lang "en"}
         [:head
          [:meta {:charset "utf-8"}]
          [:meta {:name "viewport"
                  :content "width=device-width, initial-scale=1"}]
          [:title title]
          [:link {:rel "stylesheet"
                  :href "https://fonts.googleapis.com/css2?family=Cormorant+Garamond:wght@300..700&display=swap"}]
          [:link {:rel "stylesheet"
                  :href (str routes/base-path "/style/style.css")}]]
         (into [:body]
               (concat [(navbar/navbar url)] body))])))

(defn image [name description]
  [:img {:src (str routes/base-path "/image/" name ".webp") :alt description}])