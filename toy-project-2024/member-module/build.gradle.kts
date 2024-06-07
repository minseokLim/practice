import org.springframework.boot.gradle.tasks.bundling.BootJar

val bootJar: BootJar by tasks
bootJar.enabled = false

dependencies {
    // module
    implementation(project(":common-module"))
    testImplementation(project(":test-module"))
}
