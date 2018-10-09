ThisBuild / scalaVersion := "2.12.6"
ThisBuild / organization := "polytech.ig5"

lazy val battleship = (project in file("."))
  .settings(
    name := "Project",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test,
  )