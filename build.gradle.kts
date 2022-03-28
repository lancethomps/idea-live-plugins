plugins {
  java
  groovy
  id("com.github.ben-manes.versions") version "0.42.0"
  id("org.jetbrains.intellij") version "1.4.0"
  kotlin("jvm") version "1.6.10"
}

repositories {
  mavenLocal()
  mavenCentral()
  google()
  gradlePluginPortal()
}

java {
  sourceCompatibility = JavaVersion.VERSION_11
  targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
  implementation("org.jetbrains.kotlin:kotlin-compiler-embeddable:1.6.10")
  implementation("org.jetbrains.kotlin:kotlin-stdlib:1.6.10")
  implementation("org.jetbrains.kotlin:kotlin-reflect:1.6.10")
  implementation("org.jetbrains.kotlin:kotlin-scripting-jvm:1.6.10")
  implementation("org.jetbrains.kotlin:kotlin-scripting-common:1.6.10")
  implementation("org.jetbrains.kotlin:kotlin-scripting-compiler-embeddable:1.6.10")
  implementation("org.jetbrains.kotlin:kotlin-scripting-compiler-impl-embeddable:1.6.10")
  compileOnly("org.codehaus.groovy:groovy-all:2.5.14")
  compileOnly("liveplugin:live-plugin:0.7.5")
}

sourceSets {
  main {
    java { srcDir("src/main/java") }
    groovy { srcDir("src/main/groovy") }
    resources { srcDir("src/main/resources") }
  }
}

intellij {
  val ideVersion = System.getenv().getOrDefault("LIVEPLUGIN_IDEA_VERSION", "2021.3")
  println("Using IntelliJ version: ${ideVersion}")
  version.set(ideVersion)

  pluginName.set("idea-live-plugins")
  downloadSources.set(true)
  sameSinceUntilBuild.set(false)
  updateSinceUntilBuild.set(false)

  plugins.set(listOf("Groovy", "Kotlin", "git4idea", "github", "junit", "java"))
}

tasks {
  buildSearchableOptions { enabled = false }
  wrapper { distributionType = Wrapper.DistributionType.ALL }

  withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions { jvmTarget = "11" }
  }
}
