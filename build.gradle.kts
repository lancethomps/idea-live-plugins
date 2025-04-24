import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
  java
  groovy
  id("com.github.ben-manes.versions") version "0.52.0"
  id("org.jetbrains.intellij.platform") version "2.0.1"
  kotlin("jvm") version "2.0.10"
}

repositories {
  mavenLocal()
  mavenCentral()
  google()
  gradlePluginPortal()

  intellijPlatform {
    defaultRepositories()
  }
}

java {
  sourceCompatibility = JavaVersion.VERSION_17
  targetCompatibility = JavaVersion.VERSION_17
}

val kotlinVersion = "2.0.10"
val groovyVersion = "3.0.19"
//243.21155.17-EAP-SNAPSHOT
val ideVersion = System.getenv().getOrDefault("LIVEPLUGIN_IDEA_VERSION", "2024.3")
println("Using IntelliJ version: ${ideVersion}")

dependencies {
  implementation("org.jetbrains.kotlin:kotlin-compiler-embeddable:${kotlinVersion}")
  implementation("org.jetbrains.kotlin:kotlin-stdlib:${kotlinVersion}")
  implementation("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")
  implementation("org.jetbrains.kotlin:kotlin-scripting-jvm:${kotlinVersion}")
  implementation("org.jetbrains.kotlin:kotlin-scripting-common:${kotlinVersion}")
  implementation("org.jetbrains.kotlin:kotlin-scripting-compiler-embeddable:${kotlinVersion}")
  implementation("org.jetbrains.kotlin:kotlin-scripting-compiler-impl-embeddable:${kotlinVersion}")
  compileOnly("org.codehaus.groovy:groovy-all:${groovyVersion}")
  compileOnly("liveplugin:live-plugin:0.9.5")
  intellijPlatform {
    intellijIdeaCommunity(ideVersion, false)
    bundledPlugin("com.intellij.java")
    bundledPlugin("org.intellij.groovy")
    bundledPlugin("org.jetbrains.kotlin")
    bundledPlugin("Git4Idea")
    bundledPlugin("org.jetbrains.plugins.github")
    bundledPlugin("JUnit")
  }
}

sourceSets {
  main {
    java { srcDir("src/main/java") }
    groovy { srcDir("src/main/groovy") }
    resources { srcDir("src/main/resources") }
  }
}

intellijPlatform {
  projectName = "idea-live-plugins"
  instrumentCode = false
  buildSearchableOptions = false
  pluginConfiguration {
    id = "idea-live-plugins"
    name = "idea-live-plugins"
    ideaVersion {
      untilBuild.set(provider { null })
    }
  }
}

kotlin {
  compilerOptions {
    jvmTarget.set(JvmTarget.JVM_17)
  }
}

tasks {
  buildSearchableOptions { enabled = false }
  wrapper { distributionType = Wrapper.DistributionType.ALL }
}
