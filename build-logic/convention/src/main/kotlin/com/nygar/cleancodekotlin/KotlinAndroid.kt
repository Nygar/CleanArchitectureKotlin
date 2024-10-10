package com.nygar.cleancodekotlin

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.jetbrains.kotlin.gradle.dsl.kotlinExtension

/**
 * Configure base Kotlin with Android options
 */
internal fun Project.configureKotlinAndroid(commonExtension: CommonExtension<*, *, *, *, *>) {
    configureKotlin()
    commonExtension.apply {
        packaging {
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
        }
        composeOptions {
            kotlinCompilerExtensionVersion = libs.findVersion("kotlinCompile").get().toString()
        }
        baseConfig(libs)
    }
}

internal fun Project.configureKotlinLibraryAndroid(commonExtension: CommonExtension<*, *, *, *, *>) {
    configureKotlin()
    commonExtension.apply {
        baseConfig(libs)
    }
}

internal fun CommonExtension<*, *, *, *, *>.baseConfig(libs: VersionCatalog) {
    compileSdk = libs.findVersion("compileSdk").get().toString().toInt()

    defaultConfig {
        minSdk = libs.findVersion("minSdk").get().toString().toInt()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

private fun Project.configureKotlin() {
    kotlinExtension.jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}
