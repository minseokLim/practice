dependencies {
    // spring
    implementation("org.springframework.boot:spring-boot-starter")

    // test
    testImplementation("org.springframework.boot:spring-boot-starter-jdbc")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "com.vaadin.external.google", module = "android-json")
    }
    testImplementation("com.tngtech.archunit:archunit-junit5:${property("archunitJunit5Version")}")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // module
    implementation(project(":auth-module"))
    implementation(project(":card-module"))
    implementation(project(":chat-module"))
    implementation(project(":common-module"))
    implementation(project(":location-module"))
    implementation(project(":member-module"))
    implementation(project(":notification-module"))
    implementation(project(":order-module"))
    implementation(project(":payment-module"))
    implementation(project(":product-module"))
    implementation(project(":websocket-module"))
}
