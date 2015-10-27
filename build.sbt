name := """si1-lab3"""


version := "1.0-SNAPSHOT"


lazy val root = (project in file(".")).enablePlugins(PlayJava)


scalaVersion := "2.11.6"


libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs, javaCore, javaJpa, 
  "org.apache.directory.api" % "api-all" % "1.0.0-M14",
  "postgresql" % "postgresql" % "9.1-901-1.jdbc4",
  "org.hibernate" % "hibernate-core" % "4.2.3.Final",
  "org.hibernate" % "hibernate-entitymanager" % "4.2.3.Final"
)



// Play provides two styles of routers, one expects its actions to be injected, the

// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator
