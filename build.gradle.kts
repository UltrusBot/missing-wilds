import java.text.SimpleDateFormat
import java.util.Date
plugins {
    id("io.github.p03w.machete") version "1.1.2"
}

val mod_name: String by project
val mod_author: String by project
val minecraft_version: String by project

subprojects {
    apply(plugin = "java")
    apply(plugin = "io.github.p03w.machete")

    extensions.getByType<JavaPluginExtension>().run {
        toolchain.languageVersion.set(JavaLanguageVersion.of(17))
        withSourcesJar()
        withJavadocJar()
    }


    tasks {
        named<Jar>("jar") {
            manifest {
                attributes(
                    mapOf(
                        "Specification-Title" to mod_name,
                        "Specification-Vendor" to mod_author,
                        "Specification-Version" to project.version,
                        "Implementation-Title" to project.name,
                        "Implementation-Version" to project.version,
                        "Implementation-Vendor" to mod_author,
                        "Implementation-Timestamp" to SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(Date()),
                        "Timestamp" to System.currentTimeMillis(),
                        "Built-On-Java" to "${System.getProperty("java.vm.version")} (${System.getProperty("java.vm.vendor")})",
                        "Build-On-Minecraft" to minecraft_version
                    )
                )
            }
        }
    }

    repositories {
        mavenCentral()
        maven {
            name = "Sponge / Mixin"
            url = uri("https://repo.spongepowered.org/repository/maven-public/")
        }
        maven {
            name = "BlameJared Maven (CrT / Bookshelf)"
            url = uri("https://maven.blamejared.com")
        }
        maven {
            name = "Modrinth"
            url = uri("https://api.modrinth.com/maven")
        }
    }

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.release.set(17)
    }

    // Disables Gradle's custom module metadata from being published to maven. The
    // metadata includes mapped dependencies which are not reasonably consumable by
    // other mod developers.
    tasks.withType<GenerateModuleMetadata> {
        enabled = false
    }
}