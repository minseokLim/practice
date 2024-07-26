dependencies {
    // test
    testImplementation("com.tngtech.archunit:archunit-junit5:${property("archunitJunit5Version")}")

    // module
    implementation(project(":auth-module"))
    implementation(project(":card-module"))
    implementation(project(":common-module"))
    implementation(project(":member-module"))
}
