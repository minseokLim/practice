import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")

    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
    kotlin("kapt")

    id("jacoco")
}

repositories {
    mavenCentral()
}

subprojects {
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.jetbrains.kotlin.plugin.jpa")
    apply(plugin = "org.jetbrains.kotlin.kapt")

    apply(plugin = "jacoco")

    group = "com.minseoklim"
    version = "0.0.1-SNAPSHOT"

    java {
        sourceCompatibility = JavaVersion.VERSION_17
    }

    repositories {
        mavenCentral()
    }

    dependencies {
        // spring
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter-security")
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("org.springframework.boot:spring-boot-starter-data-redis")

        // kotlin
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")

        // swagger
        implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")

        // log
        implementation("io.github.microutils:kotlin-logging-jvm:3.0.5")

        // querydsl
        implementation("com.querydsl:querydsl-jpa:5.1.0:jakarta")
        kapt("com.querydsl:querydsl-apt:5.1.0:jakarta")

        // db
        runtimeOnly("com.h2database:h2")

        // test
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("io.rest-assured:rest-assured")
        testImplementation("io.mockk:mockk:1.13.11")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    }

    allOpen {
        annotation("jakarta.persistence.Entity")
        annotation("jakarta.persistence.Embeddable")
        annotation("jakarta.persistence.MappedSuperclass")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs += "-Xjsr305=strict"
            jvmTarget = "17"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
        finalizedBy("jacocoTestReport")
    }

    tasks.jacocoTestReport {
        reports {
            html.required.set(true)
        }

        classDirectories.setFrom(
            fileTree(
                mapOf(
                    "dir" to "${layout.buildDirectory.get()}/classes/kotlin/main", // QClass들을 제외시키기 위함
                    "exclude" to listOf(
                        "**/config/*",
                        "**/support/*",
                        "**/dto/*",
                        "**/ToyProject2024ApplicationKt.class"
                    )
                )
            )
        )
    }
}
