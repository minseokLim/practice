import org.springframework.boot.gradle.tasks.bundling.BootJar

val bootJar: BootJar by tasks
bootJar.enabled = false

dependencies {
    // spring
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // swagger
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:${property("springdocOpenapiStarterWebmvcUiVersion")}")

    // log
    implementation("io.github.microutils:kotlin-logging-jvm:${property("kotlinLoggingJvmVersion")}")

    // firebase
    implementation("com.google.firebase:firebase-admin:${property("firebaseAdminVersion")}")

    // test
    testImplementation("org.springframework.boot:spring-boot-starter-security")
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

    testImplementation(project(":test-module"))
    testImplementation(testFixtures(project(":auth-module")))
    testImplementation(testFixtures(project(":member-module")))

    testFixturesImplementation(project(":test-module"))
}
