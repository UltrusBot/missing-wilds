plugins {
    // Required for NeoGradle
    id("org.jetbrains.gradle.plugin.idea-ext") version "1.1.7"
    alias(libs.plugins.fabric.loom) apply false
    alias(libs.plugins.neoforge.moddev) apply false
    alias(libs.plugins.publishing) apply false
}