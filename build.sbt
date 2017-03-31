val macros = project.in(file("."))
  .settings(
    scalaVersion := "2.12.1",
    libraryDependencies += "org.scalameta" %% "scalameta" % "1.7.0",
    addCompilerPlugin("org.scalameta" % "paradise" % "3.0.0-M8" cross(CrossVersion.full))
  )

val usage = project.in(file("usage"))
  .dependsOn(macros)
  .settings(
    scalaVersion := "2.12.1",
    libraryDependencies += "org.scalameta" %% "scalameta" % "1.7.0" % Provided,
    addCompilerPlugin("org.scalameta" % "paradise" % "3.0.0-M8" cross(CrossVersion.full))
  )
