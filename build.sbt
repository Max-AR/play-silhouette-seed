import com.typesafe.sbt.SbtScalariform._

import scalariform.formatter.preferences._

name := "play-silhouette-seed"

version := "6.0.0"

scalaVersion := "2.13.1"

val silhouetteVer = "7.0.2"


resolvers += "Sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"
resolvers += "Atlassian Releases" at "https://maven.atlassian.com/public/"
libraryDependencies ++= Seq(
  "io.github.honeycomb-cheesecake" %% "play-silhouette" % silhouetteVer,
  "io.github.honeycomb-cheesecake" %% "play-silhouette-password-bcrypt" % silhouetteVer,
  "io.github.honeycomb-cheesecake" %% "play-silhouette-persistence" % silhouetteVer,
  "io.github.honeycomb-cheesecake" %% "play-silhouette-crypto-jca" % silhouetteVer,
  "io.github.honeycomb-cheesecake" %% "play-silhouette-totp" % silhouetteVer,
  "org.webjars" %% "webjars-play" % "2.8.0",
  "org.webjars" % "bootstrap" % "4.4.1" exclude("org.webjars", "jquery"),
  "org.webjars" % "jquery" % "3.2.1",
  "net.codingwell" %% "scala-guice" % "4.2.6",
  "com.iheart" %% "ficus" % "1.4.7",
  "com.typesafe.play" %% "play-mailer" % "7.0.1",
  "com.typesafe.play" %% "play-mailer-guice" % "7.0.1",
  "com.enragedginger" %% "akka-quartz-scheduler" % "1.8.3-akka-2.6.x",
  "com.adrianhurt" %% "play-bootstrap" % "1.5.1-P27-B4",

  "io.github.honeycomb-cheesecake" %% "play-silhouette-testkit" % silhouetteVer % "test",
  specs2 % Test,
  ehcache,
  guice,
  filters
)

lazy val root = (project in file(".")).enablePlugins(PlayScala)

routesImport += "utils.route.Binders._"

// https://github.com/playframework/twirl/issues/105
TwirlKeys.templateImports := Seq()

scalacOptions ++= Seq(
  "-deprecation", // Emit warning and location for usages of deprecated APIs.
  "-feature", // Emit warning and location for usages of features that should be imported explicitly.
  "-unchecked", // Enable additional warnings where generated code depends on assumptions.
  "-Xfatal-warnings", // Fail the compilation if there are any warnings.
  //"-Xlint", // Enable recommended additional warnings.
  "-Ywarn-dead-code", // Warn when dead code is identified.
  "-Ywarn-numeric-widen", // Warn when numerics are widened.
  // Play has a lot of issues with unused imports and unsued params
  // https://github.com/playframework/playframework/issues/6690
  // https://github.com/playframework/twirl/issues/105
  "-Xlint:-unused,_"
)

//********************************************************
// Scalariform settings
//********************************************************

scalariformAutoformat := true

ScalariformKeys.preferences := ScalariformKeys.preferences.value
  .setPreference(FormatXml, false)
  .setPreference(DoubleIndentConstructorArguments, false)
  .setPreference(DanglingCloseParenthesis, Preserve)
