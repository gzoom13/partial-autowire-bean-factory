plugins {
    java
}

group = "net.golikov"
version = "0.1-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
}

val springVersion = "5.2.5.RELEASE"
//val springVersion = "5.2.6.PARTIAL-AUTOWIRE-SNAPSHOT"

dependencies {
    testCompile("junit", "junit", "4.12")
    testImplementation("org.assertj:assertj-core:3.15.0")
    testCompile("org.springframework", "spring-test", springVersion)
    testCompile("org.springframework", "spring-context", springVersion)
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}