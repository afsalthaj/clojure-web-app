(ns webdev.core
  (:require [ring.adapter.jetty :as jetty])
  (:require [ring.middleware.reload :refer [wrap-reload]]))

(defn welcome
  [request]
  (if (= "/" (:uri request)) 
    {:status 200
     :body "<h1>Hello, how is things going? Now you go and change whatever you need without restarting the app</h1>
            <p> Welcome to your first Clojure app.</p>"
     :headers {}}
    {:status 404
     :body "<h1> This is not the page you are looking for</h1>"
     :headers {}}))

(defn -main
  "A very simple web server using Ring & Jetty"
  [port-number]
  (jetty/run-jetty welcome
                   {:port (Integer. port-number)}))

(defn -dev-main
  "A very simple web server using Ring & Jetty that reloads code changes via the development profile of Leiningen"
  [port-number]
  (jetty/run-jetty (wrap-reload #'welcome)
                   {:port (Integer. port-number)}))
