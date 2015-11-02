(ns csv-emailer.core
  (:require
     [clojure-csv.core :as csv]
     [postal.core :as email]))

;; enter your SMTP server info here
;; if you need to use a nonstandard port, add ":port ####" to this map

(def outgoing {:host ""                       ;; your SMTP server
               :user ""                       ;; SMTP server's user
               :pass ""})                     ;; SMTP password

;; enter what you want the email's 'from' and 'subject' lines to read here
(def mail-info {:from "you@example.com"
                :subject "Here's your game download code!"})


(defn get-csv [filename]
  "Returns CSV data as a vector"
  (csv/parse-csv (slurp filename)))


;; your email template should be a file named "template.example.html"
;; in the resources directory. The file should have the token "[CODE]"
;; in the location you want your download code to show up.
(defn mail-code [[addr code]]
  "Send out an email for each code."
  (let [mail-info-full (assoc mail-info
                         :to addr
                         :body [{:type "text/html"
                                :content
                                 (clojure.string/replace
                                 (slurp "resources/template.html")
                                 #"\[CODE\]"
                                 code)}])]
    (try
      (println (str "Sending message to: " addr))
      (email/send-message outgoing mail-info-full))
    (catch Exception e (println "Error sending message: " (.getMessage e)))))

;; entry point for 'lein run'
(defn -main
  "Parse CSVs and send emails."
  [& args]
  (let [csv-data (get-csv "resources/names.csv")]
    (doall (map mail-code csv-data))
    (println "Mailing finished!")))