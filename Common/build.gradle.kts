import me.ultrusmods.missingwilds.gradle.Properties
import me.ultrusmods.missingwilds.gradle.Versions
import me.modmuss50.mpp.PublishModTask

plugins {
    id("conventions.common")
    id("net.neoforged.moddev")
    id("me.modmuss50.mod-publish-plugin")
}

sourceSets {
    create("generated") {
        resources {
            srcDir("src/generated/resources")
        }
    }
}
val neoform_version: String by project
val parchment_version: String by project
neoForge {
    neoFormVersion = neoform_version
    parchment {
        minecraftVersion = libs.minecraft.get().version
        mappingsVersion = parchment_version
    }
    addModdingDependenciesTo(sourceSets["test"])

    val at = file("src/main/resources/${Properties.MOD_ID}.cfg")
    if (at.exists())
        setAccessTransformers(at)
    validateAccessTransformers = true
}

dependencies {
    compileOnly(libs.mixin.extras)
    annotationProcessor(libs.mixin.extras)
    compileOnly(libs.fabric.mixin)

    compileOnly(libs.dynamicassetgenerator)
    compileOnly(libs.defaultresources)
}

configurations {
    register("commonJava") {
        isCanBeResolved = false
        isCanBeConsumed = true
    }
    register("commonResources") {
        isCanBeResolved = false
        isCanBeConsumed = true
    }
    register("commonTestResources") {
        isCanBeResolved = false
        isCanBeConsumed = true
    }
}

artifacts {
    add("commonJava", sourceSets["main"].java.sourceDirectories.singleFile)
    add("commonResources", sourceSets["main"].resources.sourceDirectories.singleFile)
    add("commonResources", sourceSets["generated"].resources.sourceDirectories.singleFile)
    add("commonTestResources", sourceSets["test"].resources.sourceDirectories.singleFile)
}

publishMods {
    changelog = rootProject.file("CHANGELOG.md").readText()
    version = "${Versions.MOD}+${libs.minecraft.get().version}"
    type = STABLE

    github {
        accessToken = providers.gradleProperty("GH_TOKEN")
        repository = Properties.GITHUB_REPO
        tagName = "${Versions.MOD}+${libs.minecraft.get().version}"
        commitish = Properties.GITHUB_COMMITISH

        allowEmptyFiles = true
    }
}