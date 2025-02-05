(defproject mybank-web-api "0.1.0-SNAPSHOT"
  :description "Projeto aprendizado Clojure para vida real."
  :url "https://github.com/VictorInacio/curso-clojure-mybank"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url  "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [org.clojure/test.check "1.1.1"]
                 [io.pedestal/pedestal.service "0.5.10"]
                 [io.pedestal/pedestal.route "0.5.10"]
                 [io.pedestal/pedestal.jetty "0.5.10"]
                 [org.slf4j/slf4j-simple "1.7.28"]
                 [clj-http "3.12.1"]
                 [com.datomic/dev-local "1.0.243"]
                 [com.stuartsierra/component "1.0.0"]
                 [walmartlabs/system-viz "0.4.0"]
                 [prismatic/schema "0.4.3"]
                 [hiccup "1.0.5"]]
  :main ^:skip-aot mybank-web-api.core
  :target-path "target/%s"
  :repositories [["cognitect-dev-tools" {:url      "https://dev-tools.cognitect.com/maven/releases/"
                                         :username "ramonp.rios@gmail.com"
                                         :password "5D2A78EAEF55B18129E64F3C2086B620F92B53D6"}]]
  :profiles {:uberjar {:aot      :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
