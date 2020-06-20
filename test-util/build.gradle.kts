dependencies {
    val springVersion: String by project

    implementation(project(":partial-autowire"))

    implementation("org.springframework", "spring-beans", springVersion)
    implementation("org.springframework", "spring-context", springVersion)
    implementation("org.springframework", "spring-test", springVersion)
}