buildscript {
    extra.apply {
        set("springVersion", "5.2.8.RELEASE")
    }
}

allprojects {
    group = "net.golikov"
    version = "0.5-SNAPSHOT"

    repositories {
        mavenLocal()
        jcenter()
        mavenCentral()
    }
}

subprojects {
    apply<JavaPlugin>()
    apply<JavaLibraryPlugin>()

    configure<JavaPluginExtension> {
        sourceCompatibility = JavaVersion.VERSION_1_8
        withJavadocJar()
        withSourcesJar()
    }
}