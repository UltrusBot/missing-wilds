plugins {
    id("java")
    id("org.spongepowered.gradle.vanilla") version "0.2.1-SNAPSHOT"
    id("maven-publish")
}

val mod_name: String by project
val mod_id: String by project
val minecraft_version: String by project

val archivesBaseName: String = "${mod_name}-common-${minecraft_version}"


minecraft {
    version(minecraft_version)
    runs {
        val commonRunsEnabled = project.hasProperty("common_runs_enabled") && project.property("common_runs_enabled").toString().toBoolean()
        if (commonRunsEnabled) {
            server(project.findProperty("common_server_run_name")?.toString() ?: "vanilla_server") {
                workingDirectory.set(file("run"))
            }
            client(project.findProperty("common_client_run_name")?.toString() ?: "vanilla_client") {
                workingDirectory.set(file("run"))
            }
        }
    }
    accessWideners(file("src/main/resources/missingwildscommon.accesswidener"))
}

dependencies {
    compileOnly("org.spongepowered:mixin:0.8.5")
}

sourceSets {
    named("main") {
        resources.srcDir("src/generated/resources")
    }
}

tasks.processResources {
    val buildProps = project.properties // TODO: clone()?

    filesMatching("pack.mcmeta") {
        expand(buildProps)
    }
    exclude(".cache")
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
