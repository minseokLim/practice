import nu.studer.gradle.jooq.JooqEdition
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jooq.meta.jaxb.Logging
import org.jooq.meta.jaxb.OnError.LOG
import org.jooq.meta.jaxb.Property

plugins {
    // spring
    id("org.springframework.boot") version "2.6.8"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"

    // kotlin
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"

    // jooq
    id("nu.studer.jooq") version "7.1.1"

    // ktlint
    id("org.jlleitschuh.gradle.ktlint") version "10.2.1"
}

group = "com.minseoklim"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    // spring
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-jooq")

    // kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // db
    runtimeOnly("com.h2database:h2")

    // jooq
    jooqGenerator("com.h2database:h2")
    jooqGenerator("org.jooq:jooq-meta-extensions:" + dependencyManagement.importedProperties["jooq.version"])
    // TODO : ktlint exclude 잘 안됨. 우회하는 방법을 찾아봐야 하나... 일단 아쉬운대로 -> ktlint | grep -v generated
    // https://github.com/JLLeitschuh/ktlint-gradle/issues/184

    // test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

jooq {
    version.set(dependencyManagement.importedProperties["jooq.version"])
    edition.set(JooqEdition.OSS)

    configurations {
        create("main") {
            jooqConfiguration.apply {
                logging = Logging.WARN
                onError = LOG
                generator.apply {
                    name = "org.jooq.codegen.KotlinGenerator"
                    database.apply {
                        name = "org.jooq.meta.extensions.ddl.DDLDatabase"
                        properties = listOf(
                            Property().apply {
                                key = "scripts"
                                value = "src/main/resources/schema.sql"
                            },
                            Property().apply {
                                key = "sort"
                                value = "semantic"
                            }
                        )
                    }
                    generate.apply {
                        isDeprecated = false
                        isRecords = false
                        isImmutablePojos = false
                        isFluentSetters = false
                    }
                    target.apply {
                        packageName = "com.minseoklim.jooqpractice"
                        directory = "src/generated/jooq"
                    }
                    strategy.name = "org.jooq.codegen.DefaultGeneratorStrategy"
                }
            }
        }
    }
}
