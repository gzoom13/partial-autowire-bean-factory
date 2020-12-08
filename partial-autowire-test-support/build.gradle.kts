dependencies {
    val springVersion: String by project

    implementation(project(":partial-autowire"))

    compileOnly("org.springframework", "spring-beans", springVersion)
    compileOnly("org.springframework", "spring-test", springVersion)
    compileOnly("org.springframework", "spring-context", springVersion)
    compileOnly("org.springframework", "spring-web", springVersion)
    compileOnly("org.springframework.boot", "spring-boot", "2.3.1.RELEASE")

    testImplementation("org.springframework", "spring-test", springVersion)
    testImplementation("org.springframework", "spring-context", springVersion)
    testImplementation("org.springframework", "spring-web", springVersion)
    testImplementation("org.springframework.boot", "spring-boot", "2.3.1.RELEASE")
    testImplementation("junit", "junit", "4.12")
    testImplementation("org.assertj:assertj-core:3.15.0")
    testImplementation("org.springframework", "spring-test", springVersion)
}
