(defproject tic-tac-toe-clj "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/core.logic "1.0.0"]
                 [colorize "0.1.1" :exclusions [org.clojure/clojure]]]
  :main ^:skip-aot tic-tac-toe-clj.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}
             :dev {:dependencies [[midje "1.9.9"]]
                   :plugins [[lein-midje "3.2.2"]]}})
