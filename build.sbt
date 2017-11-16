name := "toy-scala"

version := "0.1"

scalaVersion := "2.12.4"

scalaSource in Compile := baseDirectory.value / "src"

resourceDirectory in Compile := baseDirectory.value / "conf"

scalaSource in Test := baseDirectory.value / "test"

mainClass in (Compile, packageBin) := Some("main.scala.Application")


libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.1" % "test"
)
