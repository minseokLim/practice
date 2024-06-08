import org.springframework.boot.gradle.tasks.bundling.BootJar

val bootJar: BootJar by tasks
bootJar.enabled = false

dependencies {
    // jwt
    implementation("io.jsonwebtoken:jjwt-api:0.12.5")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.5")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.5")

    // module
    implementation(project(":common-module"))
    implementation(project(":member-module"))

    testImplementation(project(":test-module"))
    testImplementation(testFixtures(project(":member-module")))

    testFixturesImplementation(project(":test-module"))
}
