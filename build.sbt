
organizationName := "Opalia Systems"

organizationHomepage := Some(url("https://opalia.systems"))

organization := "systems.opalia"

name := "interfaces"

description := "The project contains patterns and utilities used in Opalia stack."

homepage := Some(url("https://github.com/OpaliaSystems/opalia-interfaces"))

version := "1.0.0"

scalaVersion := "2.12.13"

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-library" % scalaVersion.value,
  "org.scala-lang" % "scala-reflect" % scalaVersion.value,
  "org.scala-lang" % "scala-compiler" % scalaVersion.value,
  "org.osgi" % "osgi.core" % "8.0.0" % "provided",
  "com.typesafe" % "config" % "1.4.1",
  "org.scalatest" %% "scalatest" % "3.2.5" % "test"
)
