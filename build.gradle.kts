// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false

    //KSP and KAPT
    alias(libs.plugins.org.jetbrains.kotlin.kapt) apply false
    alias(libs.plugins.kotlin.ksp) apply false
}

tasks.register<Delete>("clean") {
    delete(layout.buildDirectory)
}