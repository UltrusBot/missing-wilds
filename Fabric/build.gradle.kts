plugins {
    id("fabric-loom") version "0.11-SNAPSHOT"
    id("maven-publish")
    id("idea")
}
val mod_name: String by project
val mod_id: String by project
val minecraft_version: String by project


val archivesBaseName: String = "${mod_name}-fabric-${minecraft_version}"
repositories {
    maven(url = "https://jitpack.io")
    maven("https://storage.googleapis.com/devan-maven/")
    maven {
        name = "TerraformersMC"
        url = uri("https://maven.terraformersmc.com/")
    }
}

dependencies {
    minecraft(libs.minecraft)
    mappings(loom.officialMojangMappings())
    modImplementation(libs.fabric.loader)
    modImplementation(libs.fabric.api)
    implementation(project(":Common"))
    modImplementation(libs.arrp)
    modImplementation(libs.emi)
}

loom {
    accessWidenerPath.set(file("src/main/resources/missingwilds.accesswidener"))
    runs {
        named("client") {
            client()
            setConfigName("Fabric Client")
            ideConfigGenerated(true)
            runDir("run")
        }
        named("server") {
            server()
            setConfigName("Fabric Server")
            ideConfigGenerated(true)
            runDir("run")
        }
        register("DatagenCommon") {
            client()
            setConfigName("Fabric Datagen Common")
            vmArg("-Dmw.common=true")
            vmArg("-Dfabric-api.datagen")
            vmArg("-Dfabric-api.datagen.output-dir=${file("../Common/src/generated/resources")}")
            vmArg("-Dfabric-api.datagen.modid=${mod_id}")
            runDir = "build/datagen"
        }
    }
    mixin {
        defaultRefmapName.set("${mod_id}.refmap.json")
    }
}

tasks.withType<JavaCompile> {
    source(project(":Common").sourceSets.getByName("main").allSource)
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

sourceSets {
    getByName("main").resources {
        srcDirs("src/main/generated")
    }
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