/**
 * Application
 */
object Application {
    const val mainClass = "com.github.funczz.hello_world.app.MainClass"
}

/**
 * plugins
 */
plugins {
    kotlin("jvm") version "1.4.20"
    java
    application
    jacoco
}

/**
 * build script
 */
buildscript {
    /**
     * repositories
     */
    repositories {
        mavenLocal()
        mavenCentral()
        jcenter()
        maven { setUrl(CommonRepos.Sonatype.snapshots) }
        maven { setUrl(CommonRepos.Jitpack.releases) }
        maven { setUrl(CommonRepos.FunczzGithub.releases) }
        maven { setUrl(CommonRepos.FunczzGithub.snapshots) }
    }
}

/**
 * repositories
 */
repositories {
    mavenLocal()
    mavenCentral()
    jcenter()
    maven { setUrl(CommonRepos.Sonatype.snapshots) }
    maven { setUrl(CommonRepos.Jitpack.releases) }
    maven { setUrl(CommonRepos.FunczzGithub.releases) }
    maven { setUrl(CommonRepos.FunczzGithub.snapshots) }
    maven { setUrl("https://funczz.github.io/hello-world/stable") }
}

/**
 * dependencies: kotlin
 */
kotlinProjectDependencies()

/**
 * dependencies
 */
dependencies {
    implementation("com.github.funczz:hello-world-lib:1.0.0")
}

/**
 * task: JavaCompile
 */
org.gradle.api.Action<org.gradle.api.plugins.JavaPluginExtension> {
    sourceCompatibility = CommonDeps.Java.version
    targetCompatibility = CommonDeps.Java.version
}
tasks.withType(JavaCompile::class) {
    options.encoding = CommonDeps.Java.encoding
}

/**
 * task: KotlinCompile
 */
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = CommonDeps.Kotlin.jvmTarget
        freeCompilerArgs = CommonDeps.Kotlin.freeCompilerArgs
    }
}

/**
 * task: Test
 */
tasks.withType(Test::class.java) {
    useJUnitPlatform() //task: kotlintest-runner-junit5
    testLogging {
        events(*CommonTasks.Test.loggingEvent)
    }
}

/**
 * plugin: application
 */
application {
    mainClassName = Application.mainClass
}

fun Manifest.setApplicationAttributes() {
    this.apply {
        attributes["Main-Class"] = Application.mainClass
    }
}

val run by tasks.getting(JavaExec::class) {
    if (project.hasProperty("args")) {
        args = (project.property("args") as String).split("""\s+""".toRegex())
    }
}

/**
 * task: jar
 */
val jar by tasks.getting(Jar::class) {
    manifest.setApplicationAttributes()
}

/**
 * task: fatJar
 */
val fatJar = task("fatJar", type = Jar::class) {
    group = "Build"
    description = "Assembles a fat jar archive."
    archiveBaseName.set("${archiveBaseName.get()}-fat")
    manifest.setApplicationAttributes()
    with(tasks["jar"] as CopySpec)
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    exclude("META-INF/*.RSA", "META-INF/*.SF", "META-INF/*.DSA")
}
