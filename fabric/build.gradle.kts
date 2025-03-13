import me.ultrusmods.missingwilds.gradle.Properties
import net.fabricmc.loom.task.RemapJarTask
import org.gradle.jvm.tasks.Jar

plugins {
    id("conventions.loader")
    id("fabric-loom")
    id("me.modmuss50.mod-publish-plugin")
}

repositories {
    maven {
        name = "TerraformersMC"
        url = uri("https://maven.terraformersmc.com/")
    }
}

base {
    archivesName = archivesName.get() + "-fabric"
}

dependencies {
    minecraft(libs.minecraft);
    mappings(loom.officialMojangMappings())

    modImplementation(libs.fabric.loader)
    modImplementation(libs.fabric.api)
    modLocalRuntime(libs.modmenu)
    modLocalRuntime(libs.emi.fabric)

    modImplementation(libs.dynamicassetgenerator) {
        capabilities {
            requireCapability("dev.lukebemish:dynamicassetgenerator-fabric")
        }
    }
    modImplementation(libs.defaultresources) {
        capabilities {
            requireCapability("dev.lukebemish:defaultresources-fabric")
        }
    }
    modImplementation(libs.midnightlib.fabric)
    modImplementation(libs.templates)

    include(libs.midnightlib.fabric)
    include(libs.defaultresources) {
        capabilities {
            requireCapability("dev.lukebemish:defaultresources-fabric")
        }
    }
    modImplementation(libs.bovinesandbuttercups.fabric)
    modRuntimeOnly(libs.wthit.fabric)
    modCompileOnly(libs.wthit.api.fabric)
    
    modRuntimeOnly(libs.badpackets.fabric)

}
loom {
    val aw = file("src/main/resources/${Properties.MOD_ID}.accesswidener");
    if (aw.exists())
        accessWidenerPath.set(aw)
    mixin {
        defaultRefmapName.set("${Properties.MOD_ID}.refmap.json")
    }
    mods {
        register(Properties.MOD_ID) {
            sourceSet(sourceSets["main"])
            sourceSet(sourceSets["test"])
        }
    }
    runs {
        named("client") {
            client()
            configName = "Fabric Client"
            setSource(sourceSets["test"])
            ideConfigGenerated(true)
//            vmArgs("-Dmixin.debug.verbose=${Properties.DEBUG_MIXIN}", "-Dmixin.debug.export=${Properties.DEBUG_MIXIN}")
        }
        named("server") {
            server()
            configName = "Fabric Server"
            setSource(sourceSets["test"])
            ideConfigGenerated(true)
//            vmArgs("-Dmixin.debug.verbose=${Properties.DEBUG_MIXIN}", "-Dmixin.debug.export=${Properties.DEBUG_MIXIN}")
        }
        register("datagen") {
            server()
            configName = "Fabric Datagen"
            setSource(sourceSets["test"])
            ideConfigGenerated(true)
            vmArg("-Dfabric-api.datagen")
            vmArg("-Dfabric-api.datagen.output-dir=${file("../common/src/generated/resources")}")
            vmArg("-Dfabric-api.datagen.modid=${Properties.MOD_ID}")
            runDir("build/datagen")
        }
    }
}

tasks {
    named<ProcessResources>("processResources").configure {
        exclude("${Properties.MOD_ID}.cfg")
    }
}

publishMods {
    file.set(tasks.named<Jar>("remapJar").get().archiveFile)
    modLoaders.add("fabric")
    changelog = rootProject.file("CHANGELOG.md").readText()
    displayName = "Missing Wilds Fabric ${Properties.MOD}+${libs.minecraft.get().version}"
    version = "${Properties.MOD}+${libs.minecraft.get().version}-fabric"
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