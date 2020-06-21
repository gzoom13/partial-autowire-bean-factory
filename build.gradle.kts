buildscript {
    extra.apply {
        set("springVersion", "5.2.5.RELEASE")
    }
}

allprojects {
    group = "net.golikov"
    version = "0.3"

    repositories {
        mavenLocal()
        mavenCentral()
    }
}

subprojects {
    apply<JavaPlugin>()
    apply<JavaLibraryPlugin>()
    apply<MavenPublishPlugin>()

    configure<JavaPluginConvention> {
        sourceCompatibility = JavaVersion.VERSION_1_8
    }

    configure<PublishingExtension> {
        publications {
            create<MavenPublication>("myLibrary") {
                from(components["java"])
            }
        }
    }
}
