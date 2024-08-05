import org.springframework.boot.gradle.tasks.bundling.BootJar

val bootJar: BootJar by tasks
bootJar.enabled = false

dependencies {
    // spring
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.session:spring-session-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-websocket")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")

    // log
    implementation("io.github.microutils:kotlin-logging-jvm:${property("kotlinLoggingJvmVersion")}")

    // querydsl
    implementation("com.querydsl:querydsl-jpa:${property("querydslVersion")}:jakarta")
    kapt("com.querydsl:querydsl-apt:${property("querydslVersion")}:jakarta")

    // test
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "com.vaadin.external.google", module = "android-json")
    }
    testImplementation("io.mockk:mockk:${property("mockkVersion")}")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // module
    testImplementation(project(":test-module"))
}
