
organizationName := "Opalia Systems"

organization := "systems.opalia"

name := "interfaces"

homepage := Some(url("https://github.com/OpaliaSystems/opalia-interfaces"))

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.12.4"

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-library" % scalaVersion.value,
  "org.scala-lang" % "scala-reflect" % scalaVersion.value,
  "org.scala-lang" % "scala-compiler" % scalaVersion.value,
  "org.osgi" % "org.osgi.core" % "6.0.0" % "provided",
  "com.typesafe" % "config" % "1.3.3",
  "org.scalatest" %% "scalatest" % "3.0.5" % "test"
)
