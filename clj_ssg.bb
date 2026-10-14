(ns clj-ssg
  (:require
   [clojure.string :as s]
   [babashka.fs :as fs]
   [babashka.cli :as cli]))

(def project-name "demo")

;; Paths relative to the source script
(def source-dir
  (fs/parent (fs/absolutize *file*)))

;; Output paths relative to the current working directory
(def working-dir
  (fs/cwd))

(def root-dir
  (fs/path working-dir project-name))

(fs/create-dirs root-dir)

(defn res [res-file]
  (slurp (str (fs/path source-dir "res" res-file))))

(defn save [rel-path contents]
  (let [path (fs/path root-dir rel-path)]
    (fs/create-dirs (fs/parent path))
    (spit (str path) contents)))

(save ".gitignore" (res "template.gitignore"))
(save "bb.edn" (res "bb.edn"))
(save "build.clj" (res "build.clj"))
(save "static/style/style.css" (res "style.css"))
(save "src/routes.clj" (res "routes.clj"))
(save "src/page.clj" (res "page.clj"))
(save "src/not_found.clj" (res "not_found.clj"))

(defn process-navbar [navbar]
  (s/replace navbar ";;PROJECT-NAME;;" project-name))

(save "src/navbar.clj"
      (process-navbar (res "navbar.template.clj")))