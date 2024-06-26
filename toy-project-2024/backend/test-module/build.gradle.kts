import org.springframework.boot.gradle.tasks.bundling.BootJar

val bootJar: BootJar by tasks
bootJar.enabled = false

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-test")
    implementation("io.rest-assured:rest-assured")
    implementation("io.mockk:mockk:${property("mockkVersion")}")
    implementation("com.github.codemonstur:embedded-redis:${property("embeddedRedisVersion")}")
}
