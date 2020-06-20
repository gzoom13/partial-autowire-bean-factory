dependencies {
    val springVersion: String by project

    implementation(project(":util"))

    implementation("org.springframework", "spring-context", springVersion)
}