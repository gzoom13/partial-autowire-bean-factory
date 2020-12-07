buildscript {
    extra.apply {
        set("springVersion", "5.2.8.RELEASE")
    }
}

allprojects {
    group = "net.golikov"
    version = "0.4"

    repositories {
        mavenLocal()
        mavenCentral()
    }
}

subprojects {
    apply<JavaPlugin>()
    apply<JavaLibraryPlugin>()
    apply<MavenPublishPlugin>()

    configure<JavaPluginExtension> {
        sourceCompatibility = JavaVersion.VERSION_1_8
        withSourcesJar()
    }

    configure<PublishingExtension> {
        publications {
            create<MavenPublication>("library") {
                from(components["java"])
            }
        }
    }
}
