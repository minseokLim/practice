import org.springframework.boot.gradle.tasks.bundling.BootJar

val bootJar: BootJar by tasks
bootJar.enabled = false

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "com.vaadin.external.google", module = "android-json")
    }
    implementation("io.rest-assured:rest-assured")
    implementation("io.mockk:mockk:${property("mockkVersion")}")
    implementation("com.github.codemonstur:embedded-redis:${property("embeddedRedisVersion")}")
}
