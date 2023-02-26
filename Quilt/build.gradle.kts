plugins {
    id("org.quiltmc.loom") version "0.12.+"
    id("maven-publish")
}

val mod_name: String by project
val mod_id: String by project
val minecraft_version: String by project

val archivesBaseName: String = "${mod_name}-fabric-${minecraft_version}"

repositories {
    maven {
        name = "Quilt"
        url = uri("https://maven.quiltmc.org/repository/release")
    }
    maven {
        name = "TerraformersMC"
        url = uri("https://maven.terraformersmc.com/")
    }
    exclusiveContent {
        forRepository {
            maven {
                name = "Modrinth"
                url = uri("https://api.modrinth.com/maven")
            }
        }
        filter {
            includeGroup("maven.modrinth")
        }
    }
}

dependencies {

    minecraft(libs.minecraft)
    mappings(loom.officialMojangMappings())
    modImplementation(libs.quilt.loader)

    modImplementation(libs.qfapi)
    modImplementation(libs.emi)
    modImplementation(libs.tablesaw)

    implementation(project(":Common"))
}
loom {
    accessWidenerPath.set(file("src/main/resources/missingwilds.accesswidener"))
    mixin {
        defaultRefmapName.set("${mod_id}.refmap.json")
    }
}
tasks {
    processResources {
        from(project(":Common").sourceSets["main"].resources)
        inputs.property("version", project.version)

        filesMatching("fabric.mod.json") {
            expand(mapOf("version" to version))
        }
        exclude(".cache")
    }
    jar {
        from("LICENSE") {
            rename { "LICENSE_${mod_name}" }
        }
    }
}



tasks.withType<JavaCompile> {
    source(project(":Common").sourceSets.getByName("main").allSource)
}


publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            groupId = project.group.toString()
            artifactId = archivesBaseName
            version = project.version.toString()
            from(components["java"])
        }
    }

    repositories {
        maven {
            setUrl("file://" + System.getenv("local_maven"))
        }
    }
}
