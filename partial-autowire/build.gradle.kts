dependencies {
    val springVersion: String by project

    implementation(project(":util"))

    implementation("org.springframework", "spring-context", springVersion)
    implementation("org.springframework", "spring-web", springVersion)
    implementation("org.springframework.boot", "spring-boot", "2.3.1.RELEASE")
}