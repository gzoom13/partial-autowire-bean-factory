import com.jfrog.bintray.gradle.BintrayExtension.PackageConfig
import com.jfrog.bintray.gradle.BintrayExtension.VersionConfig
import java.util.Date

plugins {
    `maven-publish`
    id("com.jfrog.bintray") version "1.8.5"
}

dependencies {
    val springVersion: String by project

    compileOnly("org.springframework", "spring-beans", springVersion)
    compileOnly("org.springframework", "spring-context", springVersion)
}

configure<PublishingExtension> {
    publications {
        create<MavenPublication>("library") {
            from(components["java"])
            pom {
                name.set("Partial autowire bean factory")
                licenses {
                    license {
                        name.set("The Apache Software License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("gzoom13")
                        name.set("Andrey Golikov")
                        email.set("andrey@golikov.net")
                    }
                }
                scm {
                    url.set("https://github.com/gzoom13/partial-autowire-bean-factory")
                }
            }
        }
    }
}

bintray {
    val bintrayUser = System.getProperty("bintray.user")
    val bintrayKey = System.getProperty("bintray.key")
    val buildNumber = System.getProperty("build.number")
    user = bintrayUser
    key = bintrayKey
    setPublications("library")

    pkg(closureOf<PackageConfig> {
        repo = "maven-releases"
        name = "partial-autowire-bean-factory"
        setLicenses("Apache-2.0")
        vcsUrl = "https://github.com/gzoom13/partial-autowire-bean-factory"
        version(closureOf<VersionConfig>{
            name = project.version.toString()
            desc = "build ${buildNumber}"
            released  = Date().toString()
        })
    })

    publish = true
}
