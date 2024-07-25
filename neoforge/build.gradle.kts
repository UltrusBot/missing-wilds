import me.ultrusmods.missingwilds.gradle.Properties
import me.ultrusmods.missingwilds.gradle.Versions
import org.apache.tools.ant.filters.LineContains
import org.gradle.jvm.tasks.Jar

plugins {
    id("conventions.loader")
    alias(libs.plugins.neoforge.moddev)
    alias(libs.plugins.publishing)
}
base {
    archivesName = archivesName.get() + "-neoforge"
}
val parchment_version: String by project
neoForge {
    version = libs.neoforge.get().version
    parchment {
        minecraftVersion = libs.minecraft.get().version
        mappingsVersion = parchment_version
    }
    addModdingDependenciesTo(sourceSets["test"])

    val at = project(":common").file("src/main/resources/${Properties.MOD_ID}.cfg")
    if (at.exists())
        setAccessTransformers(at)
    validateAccessTransformers = true

    runs {
        configureEach {
            systemProperty("forge.logging.markers", "REGISTRIES")
            systemProperty("forge.logging.console.level", "debug")
            systemProperty("neoforge.enabledGameTestNamespaces", Properties.MOD_ID)
        }
        create("client") {
            client()
            gameDirectory.set(file("runs/client"))
            sourceSet = sourceSets["test"]
            jvmArguments.set(setOf("-Dmixin.debug.verbose=true", "-Dmixin.debug.export=true"))
        }
        create("server") {
            server()
            gameDirectory.set(file("runs/server"))
            programArgument("--nogui")
            sourceSet = sourceSets["test"]
            jvmArguments.set(setOf("-Dmixin.debug.verbose=true", "-Dmixin.debug.export=true"))
        }
    }

    mods {
        register(Properties.MOD_ID) {
            sourceSet(sourceSets["main"])
            sourceSet(sourceSets["test"])
        }
    }
}
repositories {
    mavenCentral()
}
dependencies {
    implementation(libs.dynamicassetgenerator) {
        capabilities {
            requireCapability("dev.lukebemish:dynamicassetgenerator-neoforge")
        }
    }
    implementation(libs.defaultresources) {
        capabilities {
            requireCapability("dev.lukebemish:defaultresources-neoforge")
        }
    }
    implementation(libs.midnightlib.forge)
    jarJar(libs.midnightlib.forge)
    jarJar(libs.defaultresources) {
        capabilities {
            requireCapability("dev.lukebemish:defaultresources-neoforge")
        }
    }
}

tasks {
    named<ProcessResources>("processResources").configure {
        filesMatching("*.mixins.json") {
            filter<LineContains>("negate" to true, "contains" to setOf("refmap"))
        }
    }
}

publishMods {
    file.set(tasks.named<Jar>("jar").get().archiveFile)
    modLoaders.add("neoforge")
    changelog = rootProject.file("CHANGELOG.md").readText()
    version = "Missing Wilds Neoforge ${Versions.MOD}+${libs.minecraft.get().version}"
    type = STABLE

    curseforge {
        projectId = Properties.CURSEFORGE_PROJECT_ID
        accessToken = providers.gradleProperty("CF_API_KEY")

        minecraftVersions.add(libs.minecraft.get().version!!)
        javaVersions.add(JavaVersion.VERSION_21)

        clientRequired = true
        serverRequired = true
    }

    modrinth {
        projectId = Properties.MODRINTH_PROJECT_ID
        accessToken = providers.gradleProperty("MODRINTH_TOKEN")

        minecraftVersions.add(libs.minecraft.get().version!!)
    }

//    github {
//        accessToken = providers.gradleProperty("GH_TOKEN")
//        parent(project(":common").tasks.named("publishGithub"))
//    }
}