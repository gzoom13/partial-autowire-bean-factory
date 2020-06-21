dependencies {
    val springVersion: String by project

    implementation(project(":test-util"))
    implementation(project(":partial-autowire"))

    implementation("org.springframework", "spring-test", springVersion)
    implementation("org.springframework", "spring-context", springVersion)

    testImplementation("junit", "junit", "4.12")
    testImplementation("org.assertj:assertj-core:3.15.0")
    testImplementation("org.springframework", "spring-test", springVersion)
}
