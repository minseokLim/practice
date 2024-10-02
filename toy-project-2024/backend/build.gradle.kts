import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType
import org.jlleitschuh.gradle.ktlint.tasks.GenerateReportsTask

plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")

    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
    kotlin("kapt")

    id("jacoco")
    id("jacoco-report-aggregation")

    id("java-test-fixtures")

    id("org.jlleitschuh.gradle.ktlint")
}

repositories {
    mavenCentral()
}

allprojects {
    apply {
        plugin("org.jlleitschuh.gradle.ktlint")
    }

    ktlint {
        reporters {
            reporter(ReporterType.JSON)
        }
    }

    tasks.withType<GenerateReportsTask> {
        reportsOutputDirectory.set(
            rootProject.layout.buildDirectory.dir("reports/ktlint/${project.name}")
        )
    }
}

subprojects {
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.jetbrains.kotlin.plugin.jpa")
    apply(plugin = "org.jetbrains.kotlin.kapt")

    apply(plugin = "jacoco")

    apply(plugin = "java-test-fixtures")

    group = "com.minseoklim"
    version = "0.0.1-SNAPSHOT"

    java {
        sourceCompatibility = JavaVersion.VERSION_17
    }

    repositories {
        mavenCentral()
    }

    dependencyManagement {
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
        }
    }

    dependencies {
        // kotlin
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")

        // db
        runtimeOnly("com.mysql:mysql-connector-j")
        testRuntimeOnly("com.h2database:h2")
    }

    allOpen {
        annotation("jakarta.persistence.Entity")
        annotation("jakarta.persistence.Embeddable")
        annotation("jakarta.persistence.MappedSuperclass")
    }

    kotlin {
        compilerOptions {
            freeCompilerArgs.addAll("-Xjsr305=strict")
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

dependencies {
    getJacocoTargetProjects().forEach { jacocoAggregation(it) }
}

tasks.withType<Test> {
    useJUnitPlatform()
    finalizedBy("testCodeCoverageReport")
}

tasks.testCodeCoverageReport {
    classDirectories.setFrom(
        files(
            *getJacocoTargetProjects().map {
                it.fileTree(
                    mapOf(
                        // QClass들을 제외시키기 위함
                        "dir" to "${it.layout.buildDirectory.get()}/classes/kotlin/main",
                        "exclude" to listOf(
                            "**/config/*",
                            "**/support/*",
                            "**/dto/*",
                            "**/ApplicationKt.class"
                        )
                    )
                )
            }.toTypedArray()
        )
    )
}

private fun getJacocoTargetProjects() = rootProject.subprojects.filter { it.name != "test-module" }
