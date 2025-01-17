package buildsrc.convention

import buildsrc.config.Deps
import org.jetbrains.kotlin.gradle.targets.jvm.KotlinJvmTarget

plugins {
    kotlin("multiplatform")

    id("org.jetbrains.dokka")

    id("buildsrc.convention.base")
    id("buildsrc.convention.toolchain-jvm")
}

kotlin {
    targets.configureEach {
        compilations.configureEach {
            kotlinOptions {
                apiVersion = "1.5"
                languageVersion = "1.7"
            }
        }
    }
    targets.withType<KotlinJvmTarget>().configureEach {
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
}

val javadocJar by tasks.registering(Jar::class) {
    from(tasks.dokkaHtml)
    archiveClassifier.set("javadoc")
}
