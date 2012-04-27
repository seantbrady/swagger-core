import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "java-play2"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
      "org.codehaus.jackson" % "jackson-jaxrs" % "1.9.5",
         "org.codehaus.jackson" % "jackson-xc" % "1.9.5",
         "org.codehaus.jackson" % "jackson-mapper-asl" % "1.9.5",
         "org.codehaus.jackson" % "jackson-core-asl" % "1.9.5",
         "swagger-play2" %% "swagger-play2" % "1.1-SNAPSHOT"
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = JAVA).settings(
      resolvers += "java-net" at "http://download.java.net/maven/2",
      resolvers += Resolver.url("local-ivy", new URL("file://" + Path.userHome.absolutePath + "/.ivy2/local"))(Resolver.ivyStylePatterns),
      resolvers += Resolver.url("local-ivy-cache", new URL("file://" + Path.userHome.absolutePath + "/.ivy2/cache"))(Resolver.ivyStylePatterns),
      resolvers += Resolver.url("local-ivy-cache-mvn", new URL("file://" + Path.userHome.absolutePath + "/.ivy2/cache"))(Resolver.mavenStylePatterns),
      resolvers += "local-maven-repo" at "file://" + Path.userHome.absolutePath + "/.m2/repository/"
    )

}
