name := "Scala_2"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies +=
  "com.typesafe.akka" %% "akka-actor" % "2.3.6"

resolvers += "Akka Snapshots" at "http://repo.akka.io/snapshots/"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test"

libraryDependencies += "junit" % "junit" % "4.10" % "test"