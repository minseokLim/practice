rootProject.name = "toy-project-2024-backend"
include("app-module")
include("auth-module")
include("common-module")
include("member-module")
include("test-module")

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
    }
}
