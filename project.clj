(defproject ewen/test-immutant "0.1.0-SNAPSHOT"
            :description "test-immutant"
            :min-lein-version "2.0.0"
            :source-paths ["src"]
            :dependencies [[org.clojure/clojure "1.6.0"]
                           [io.pedestal/pedestal.service "0.3.0"]
                           [io.pedestal/pedestal.service-tools "0.3.0"]
                           [javax.servlet/javax.servlet-api "3.1.0" :scope "provided"]
                           [org.immutant/web "2.0.0-alpha1"]])
