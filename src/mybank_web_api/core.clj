(ns mybank-web-api.core
  (:require [com.stuartsierra.component :as component]
            [com.walmartlabs.system-viz :refer [visualize-system]]
            [mybank-web-api.database :as db]
            [mybank-web-api.server :as web-server]
            [mybank-web-api.config :as config]
            [mybank-web-api.routing :as routes])
  (:gen-class))


(def new-sys
  (component/system-map
    :config (config/new-config)
    :routes (routes/new-routes)
    :database (component/using
                (db/new-database)
                [:config])
    :web-server (component/using
                  (web-server/new-servidor)
                  [:database :routes :config])))

(def sys (atom nil))
(defn main [] (reset! sys (component/start new-sys)))

(def routes
  (route/expand-routes
    #{["/saldo/:id" :get get-saldo :route-name :saldo]
      ["/deposito/:id" :post make-deposit :route-name :deposito]}))

(defn create-server []
  (http/create-server
    {::http/routes routes
     ::http/type   :jetty
     ::http/port   8890
     ::http/join?  false}))

(defonce server (atom nil))

(defn start []
  (reset! server (http/start (create-server))))

(defn test-request [server verb url]
  (test-http/response-for (::http/service-fn @server) verb url))
(defn test-post [server verb url body]
  (test-http/response-for (::http/service-fn @server) verb url :body body))
(comment
  (start)
  (http/stop @server)

  (test-request server :get "/saldo/1")
  (test-request server :get "/saldo/2")
  (test-request server :get "/saldo/3")
  (test-request server :get "/saldo/4")

  (test-post server :post "/deposito/2" "863.99")
)
