(ns webdev.core
  (:require [ring.adapter.jetty :as jetty]))

(defn welcome
  [request]
  (if (= "/" (:uri request)) 
    {:status 200
     :body "<h1>Hello, Clojure World</h1>
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
