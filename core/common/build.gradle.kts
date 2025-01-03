import java.io.FileInputStream
import java.util.Properties

plugins {
    id("cleanarchitecturekotlin.android.library")
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.nygar.common"

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        val localProperties: Properties =
            Properties().apply {
                load(FileInputStream(File(rootProject.rootDir, "gradle.properties")))
            }
        buildConfigField(
            type = "String",
            name = "URL_BASE",
            value = localProperties.getProperty("URL_BASE") ?: "",
        )
        buildConfigField(
            type = "String",
            name = "GOOGLE_TOKEN",
            value = localProperties.getProperty("GOOGLE_TOKEN") ?: "",
        )
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
}
