name := "arroch"
version := "1.0"

lazy val `arroch` = (project in file(".")).enablePlugins(PlayJava)
scalaVersion := "2.11.7"

libraryDependencies ++= Seq(javaJdbc, ehcache, javaWs)
libraryDependencies ++= Seq(
  javaJpa,
  "org.postgresql" % "postgresql" % "42.0.0",
  "com.h2database" % "h2" % "1.4.193",
  "dom4j" % "dom4j" % "1.6.1",
  "org.hibernate" % "hibernate-core" % "5.2.10.Final",
  "org.hibernate" % "hibernate-ehcache" % "5.2.10.Final"
) //Hibernate ORM
libraryDependencies += "org.fluentlenium" % "fluentlenium-core" % "3.2.0"
libraryDependencies += "org.assertj" % "assertj-core" % "3.6.2"
libraryDependencies += "org.seleniumhq.selenium" % "selenium-server" % "3.4.0"
libraryDependencies += "org.seleniumhq.selenium" % "selenium-java" % "3.4.0"
libraryDependencies += "org.seleniumhq.selenium" % "selenium-htmlunit-driver" % "2.52.0"
libraryDependencies += "com.google.apis" % "google-api-services-people" % "v1-rev110-1.22.0"

PlayKeys.externalizeResources := false

unmanagedResourceDirectories in Test += baseDirectory ( _ /"target/web/public/test" ).value

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
