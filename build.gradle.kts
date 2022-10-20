import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.gradle.api.tasks.testing.logging.TestLogEvent.*

plugins {
  java
  application  
  id("com.github.johnrengelman.shadow") version "7.0.0"
}

group = "org.lonpe.lonvx"
version = "1.0.0-SNAPSHOT"

repositories {
  mavenCentral()
}


val vertxVersion = "4.3.3"//"4.1.2"

val junitJupiterVersion = "5.7.0"

val mainVerticleName = "org.lonpe.lonvx.MainVerticle"
val launcherClassName = "io.vertx.core.Launcher"

val watchForChange = "src/**/*"
val doOnChange = "${projectDir}/gradlew classes"

application {
  mainClassName = launcherClassName
}

dependencies {
  implementation(platform("io.vertx:vertx-stack-depchain:$vertxVersion"))
  implementation("io.vertx:vertx-web-client")
  implementation("io.vertx:vertx-web-validation")
  implementation("io.vertx:vertx-config")
  implementation("io.vertx:vertx-auth-jwt")
  implementation("io.vertx:vertx-web")
  implementation("io.vertx:vertx-pg-client")

  implementation("io.vertx:vertx-hazelcast")
  implementation("io.vertx:vertx-rx-java3") 
  testImplementation("io.vertx:vertx-junit5")
  testImplementation("org.junit.jupiter:junit-jupiter:$junitJupiterVersion")

  implementation("org.springframework.security:spring-security-crypto:5.2.4.RELEASE")
  implementation("commons-logging:commons-logging:1.1.1")

  implementation("org.apache.logging.log4j:log4j-core:2.16.0")
  implementation("org.apache.poi:poi:5.2.2")
  implementation("org.apache.poi:poi-ooxml:5.2.2")

}

java {
  sourceCompatibility = JavaVersion.VERSION_11
  targetCompatibility = JavaVersion.VERSION_11
  //sourceCompatibility = JavaVersion.VERSION_17
  //targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<ShadowJar> {
  archiveClassifier.set("fat")
  manifest {
    attributes(mapOf("Main-Verticle" to mainVerticleName))
  }
  mergeServiceFiles()
}

tasks.withType<Test> {
  useJUnitPlatform()
  testLogging {
    events = setOf(PASSED, SKIPPED, FAILED)
  }
}


val  hazelcastArgs = "--add-modules java.se --add-exports java.base/jdk.internal.ref=ALL-UNNAMED --add-opens java.base/java.lang=ALL-UNNAMED --add-opens java.base/java.nio=ALL-UNNAMED --add-opens java.base/sun.nio.ch=ALL-UNNAMED --add-opens java.management/sun.management=ALL-UNNAMED --add-opens jdk.management/com.sun.management.internal=ALL-UNNAMED"

tasks.withType<JavaExec> {
    //args = listOf("run", mainVerticleName, "--redeploy=$watchForChange", "--launcher-class=$launcherClassName", "--on-redeploy=$doOnChange","--java-opts","-agentlib:jdwp=transport=dt_socket,server=y,address=5007")
    args = listOf("run", mainVerticleName,  "--launcher-class=$launcherClassName",hazelcastArgs)
}


