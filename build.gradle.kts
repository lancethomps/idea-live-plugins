plugins {
  java
  groovy
  id("com.github.ben-manes.versions") version "0.42.0"
  id("org.jetbrains.intellij") version "1.9.0"
  kotlin("jvm") version "1.6.21"
}

repositories {
  mavenLocal()
  mavenCentral()
  google()
  gradlePluginPortal()
}

java {
  sourceCompatibility = JavaVersion.VERSION_17
  targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
  implementation("org.jetbrains.kotlin:kotlin-compiler-embeddable:1.6.21")
  implementation("org.jetbrains.kotlin:kotlin-stdlib:1.6.21")
  implementation("org.jetbrains.kotlin:kotlin-reflect:1.6.21")
  implementation("org.jetbrains.kotlin:kotlin-scripting-jvm:1.6.21")
  implementation("org.jetbrains.kotlin:kotlin-scripting-common:1.6.21")
  implementation("org.jetbrains.kotlin:kotlin-scripting-compiler-embeddable:1.6.21")
  implementation("org.jetbrains.kotlin:kotlin-scripting-compiler-impl-embeddable:1.6.21")
  compileOnly("org.codehaus.groovy:groovy-all:3.0.9")
  compileOnly("liveplugin:live-plugin:0.8.2")
}

sourceSets {
  main {
    java { srcDir("src/main/java") }
    groovy { srcDir("src/main/groovy") }
    resources { srcDir("src/main/resources") }
  }
}

intellij {
  val ideVersion = System.getenv().getOrDefault("LIVEPLUGIN_IDEA_VERSION", "2022.3")
  println("Using IntelliJ version: ${ideVersion}")
  version.set(ideVersion)

  pluginName.set("idea-live-plugins")
  downloadSources.set(true)
  sameSinceUntilBuild.set(false)
  updateSinceUntilBuild.set(false)

  plugins.set(
    listOf("Groovy", "Kotlin", "Git4Idea", "org.jetbrains.plugins.github", "junit", "java")
  )
}

tasks {
  buildSearchableOptions { enabled = false }
  wrapper { distributionType = Wrapper.DistributionType.ALL }

  withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions { jvmTarget = "17" }
  }
}
