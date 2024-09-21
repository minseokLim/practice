import org.springframework.boot.gradle.tasks.bundling.BootJar

val bootJar: BootJar by tasks
bootJar.enabled = false

dependencies {
    // spring
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
    implementation("org.springframework.boot:spring-boot-starter-websocket")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")

    // swagger
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:${property("springdocOpenapiStarterWebmvcUiVersion")}")

    // log
    implementation("io.github.microutils:kotlin-logging-jvm:${property("kotlinLoggingJvmVersion")}")

    // jwt
    implementation("io.jsonwebtoken:jjwt-api:${property("jjwtVersion")}")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:${property("jjwtVersion")}")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:${property("jjwtVersion")}")

    // test
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "com.vaadin.external.google", module = "android-json")
    }
    testImplementation("io.rest-assured:rest-assured")
    testImplementation("io.mockk:mockk:${property("mockkVersion")}")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testFixturesImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "com.vaadin.external.google", module = "android-json")
    }
    testFixturesImplementation("io.rest-assured:rest-assured")

    // module
    implementation(project(":common-module"))
    implementation(project(":member-module"))
    runtimeOnly(project(":websocket-module"))

    testImplementation(project(":test-module"))
    testImplementation(testFixtures(project(":member-module")))

    testFixturesImplementation(project(":test-module"))
}
