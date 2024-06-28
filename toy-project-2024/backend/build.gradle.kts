import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

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

    apply(plugin = "java-test-fixtures")

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
        implementation("org.springframework.boot:spring-boot-starter-validation")
        implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
        implementation("org.springframework.session:spring-session-data-redis")

        // kotlin
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")

        // swagger
        implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:${property("springdocOpenapiStarterWebmvcUiVersion")}")

        // log
        implementation("io.github.microutils:kotlin-logging-jvm:${property("kotlinLoggingJvmVersion")}")

        // querydsl
        implementation("com.querydsl:querydsl-jpa:${property("querydslVersion")}:jakarta")
        kapt("com.querydsl:querydsl-apt:${property("querydslVersion")}:jakarta")

        // db
        runtimeOnly("com.h2database:h2")

        // test
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("io.rest-assured:rest-assured")
        testImplementation("io.mockk:mockk:${property("mockkVersion")}")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
        testFixturesImplementation("org.springframework.boot:spring-boot-starter-test")
        testFixturesImplementation("io.rest-assured:rest-assured")
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
    }
}

dependencies {
    jacocoAggregation(project(":app-module"))
    jacocoAggregation(project(":auth-module"))
    jacocoAggregation(project(":common-module"))
    jacocoAggregation(project(":member-module"))
}

tasks.withType<Test> {
    useJUnitPlatform()
    finalizedBy("testCodeCoverageReport")
}

tasks.testCodeCoverageReport {
    classDirectories.setFrom(
        files(
            *listOf(
                project(":app-module"),
                project(":auth-module"),
                project(":common-module"),
                project(":member-module")
            ).map {
                it.fileTree(
                    mapOf(
                        "dir" to "${it.layout.buildDirectory.get()}/classes/kotlin/main", // QClass들을 제외시키기 위함
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
