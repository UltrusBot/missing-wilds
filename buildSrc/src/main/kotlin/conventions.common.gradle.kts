import me.ultrusmods.missingwilds.gradle.Properties
import me.ultrusmods.missingwilds.gradle.Versions

plugins {
    base
    `java-library`
    idea
    `maven-publish`
}
fun libs(lib: String) =
    project.extensions.getByType(VersionCatalogsExtension::class).named("libs").findLibrary(lib).get()

base.archivesName.set(Properties.MOD_NAME)
group = Properties.GROUP
version = "${Versions.MOD}+${libs("minecraft").get().version}"

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(Versions.JAVA))
    withSourcesJar()
    withJavadocJar()
}

repositories {
    mavenCentral()
    // https://docs.gradle.org/current/userguide/declaring_repositories.html#declaring_content_exclusively_found_in_one_repository
    exclusiveContent {
        forRepository {
            maven("https://repo.spongepowered.org/repository/maven-public") {
                name = "Sponge"
            }
        }
        filter { includeGroupAndSubgroups("org.spongepowered") }
    }
    exclusiveContent {
        forRepositories(
            maven("https://maven.parchmentmc.org/") {
                name = "ParchmentMC"
            },
            maven("https://maven.neoforged.net/releases") {
                name = "NeoForge"
            }
        )
        filter { includeGroup("org.parchmentmc.data") }
    }
    maven("https://maven.fabricmc.net/") {
        name = "Fabric"
    }
    exclusiveContent {
        forRepository {
            maven("https://api.modrinth.com/maven") {
                name = "Modrinth"
            }
        }
        filter { includeGroup("maven.modrinth") }
    }
}


dependencies {
    implementation("org.jetbrains:annotations:24.1.0")

}

// Declare capabilities on the outgoing configurations.
// Read more about capabilities here: https://docs.gradle.org/current/userguide/component_capabilities.html#sec:declaring-additional-capabilities-for-a-local-component
setOf("apiElements", "runtimeElements", "sourcesElements", "javadocElements").forEach { variant ->
    configurations.getByName(variant).outgoing {
        capability("$group:${Properties.MOD_ID}-${project.name}:$version")
        capability("$group:${Properties.MOD_ID}:$version")
    }
    publishing.publications.forEach { publication ->
        if (publication is MavenPublication) {
            publication.suppressPomMetadataWarningsFor(variant);
        }
    }
}

tasks {
    named<Jar>("sourcesJar").configure {
        from(rootProject.file("LICENSE")) {
            rename { "${it}_${Properties.MOD_NAME}" }
        }
    }
    named<Jar>("jar").configure {
        from(rootProject.file("LICENSE")) {
            rename { "${it}_${Properties.MOD_NAME}" }
        }

        manifest {
            attributes["Specification-Title"] = Properties.MOD_NAME
            attributes["Specification-Vendor"] = Properties.MOD_AUTHOR
            attributes["Specification-Version"] = archiveVersion
            attributes["Implementation-Title"] = project.name
            attributes["Implementation-Version"] = archiveVersion
            attributes["Implementation-Vendor"] = Properties.MOD_AUTHOR
            attributes["Built-On-Minecraft"] = libs("minecraft").get().version
        }
    }
    val neoforge_loader_range: String by project
    val neoforge_minecraft_range: String by project
    val fabric_loader_range: String by project
    val fabric_minecraft_range: String by project
    val expandProps = mapOf(
        "mod_version" to Versions.MOD,
        "group" to project.group, //Else we target the task's group.
        "minecraft_version" to libs("minecraft").get().version,
        "fabric_api_version" to libs("fabric_api").get().version,
        "fabric_loader_version" to libs("fabric_loader").get().version,
        "fabric_minecraft_version_range" to fabric_minecraft_range,
        "fabric_loader_range" to fabric_loader_range,
        "mod_name" to Properties.MOD_NAME,
        "mod_author" to Properties.MOD_AUTHOR,
        "mod_contributors" to Properties.MOD_CONTRIBUTORS,
        "fabric_mod_contributors" to createFabricContributors(),
        "mod_id" to Properties.MOD_ID,
        "mod_license" to Properties.LICENSE,
        "mod_description" to Properties.DESCRIPTION,
        "neoforge_version" to libs("neoforge").get().version,
        "neoforge_minecraft_version_range" to neoforge_minecraft_range,
        "neoforge_loader_version_range" to neoforge_loader_range,
        "java_version" to Versions.JAVA,
        "homepage" to Properties.HOMEPAGE,
        "sources" to Properties.GITHUB_REPO
    )

    val processResourcesTasks = listOf("processResources", "processTestResources", "processDatagenResources")

    withType<ProcessResources>().matching { processResourcesTasks.contains(it.name) }.configureEach {
        inputs.properties(expandProps)
        filesMatching(setOf("fabric.mod.json", "META-INF/neoforge.mods.toml", "*.mixins.json")) {
            expand(expandProps)
        }
        exclude("\\.cache")
    }
}

fun createFabricContributors() : String {
    val string = StringBuilder();
    val contributors = Properties.MOD_CONTRIBUTORS.split(", ")
    var count = 0
    for (contributor in contributors) {
        if (count > 0)
            string.append("\t\t")
        ++count
        string.append("\"${contributor}\"")
        if (count < contributors.size)
            string.append(",\n")
    }
    return string.toString()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = base.archivesName.get()
            from(components["java"])
        }
    }
    repositories {
        maven {
            name = "Greenhouse"
            url = uri("https://maven.greenhouseteam.dev/releases")
            credentials {
                username = System.getenv("MAVEN_USERNAME")
                password = System.getenv("MAVEN_PASSWORD")
            }
            authentication {
                create<BasicAuthentication>("basic")
            }
        }
    }
}