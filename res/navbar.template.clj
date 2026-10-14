
(ns navbar
  (:require
   [routes]))

(defn navbar-item [url title selected?]
  [:a.navbar-item
   {:href url
    :class (when selected? "selected")}
   title])

(defn navbar-items [current-page-url]
  (map (fn [[url title]]
         (navbar-item url title (= url current-page-url)))
       [(routes/home "Home")]))

(defn navbar [current-page-url]
  [:nav.navbar
   [:a.navbar-brand
    {:href routes/home
     :aria-label "Home"}
    ";;PROJECT-NAME;;"]

   [:div.navbar-links
    (navbar-items current-page-url)]

   [:details.navbar-mobile
    [:summary.navbar-toggle
     {:aria-label "Toggle navigation"}
     [:span]
     [:span]
     [:span]]

    [:div.navbar-mobile-links
     (navbar-items current-page-url)]]])
