(ns ewen.cle-usb.test-server.test-server
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]
            [io.pedestal.http.body-params :as body-params]
            [io.pedestal.http.route.definition :refer [defroutes]]
            [ring.util.response :as ring-resp]
            [immutant.web :as web]
            [immutant.util]

            [ring.middleware.resource]))





(defn home-page [request] (ring-resp/response "Hello World!"))

(defroutes routes
           [[["/" {:get home-page} ^:interceptors [http/html-body]]]])

(def service {::http/routes        #(deref #'routes)
              ::http/resource-path "/public/"})

(comment
  (web/run (::http/servlet (http/create-servlet service)) {:path "/"})
  (web/stop {:path "/"})


  ;Hugly hack !

  (require '[ring.util.codec :as codec]
           '[ring.util.response :as response]
           '[ring.util.request :as request]
           '[ring.middleware.head :as head])


  (defn resource-request
    "If request matches a static resource, returns it in a response map.
    Otherwise returns nil."
    {:added "1.2"}
    [request root-path]
    (if (#{:head :get} (:request-method request))
      (let [path (codec/url-decode (request/path-info request))]
        (-> (response/resource-response path {:root root-path})
            (head/head-response request)))))

  (alter-var-root #'ring.middleware.resource/resource-request
                  (fn [_] (fn [request root-path]
                            (resource-request request root-path))))



  )