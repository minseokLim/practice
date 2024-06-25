import org.springframework.boot.gradle.tasks.bundling.BootJar

val bootJar: BootJar by tasks
bootJar.enabled = false

dependencies {
    // spring
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")

    // jwt
    implementation("io.jsonwebtoken:jjwt-api:${property("jjwtVersion")}")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:${property("jjwtVersion")}")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:${property("jjwtVersion")}")

    // module
    implementation(project(":common-module"))

    testImplementation(project(":test-module"))
    testImplementation(testFixtures(project(":member-module")))

    testFixturesImplementation(project(":test-module"))
}
