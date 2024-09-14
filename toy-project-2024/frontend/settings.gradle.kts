rootProject.name = "toy-project-2024-frontend"

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }

    plugins {
        id("org.springframework.boot") version extra["springBootVersion"] as String
        id("io.spring.dependency-management") version extra["springDependencyManagementVersion"] as String

        val kotlinVersion = extra["kotlinVersion"] as String
        kotlin("jvm") version kotlinVersion
        kotlin("plugin.spring") version kotlinVersion
        kotlin("plugin.jpa") version kotlinVersion
        kotlin("kapt") version kotlinVersion

        id("org.jlleitschuh.gradle.ktlint") version extra["ktlintVersion"] as String
    }
}
