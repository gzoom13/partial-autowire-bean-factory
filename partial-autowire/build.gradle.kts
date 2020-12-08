dependencies {
    val springVersion: String by project

    compileOnly("org.springframework", "spring-beans", springVersion)
    compileOnly("org.springframework", "spring-context", springVersion)
}