plugins {
    id("cleanarchitecturekotlin.android.application")
    alias(libs.plugins.ksp)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.hiltPlugin)
}

android {
    defaultConfig {
        applicationId = "com.nygar.cleanarchitecturekotlin"
        versionCode = 1

        vectorDrawables {
            useSupportLibrary = true
        }
        testInstrumentationRunner = "com.nygar.cleanarchitecturekotlin.CustomTestRunner"

        buildFeatures {
            compose = true
        }

        signingConfigs {
            getByName("debug") {
                keyAlias = "Cleancode"
                keyPassword = "aa123456"
                storeFile = file("../keystore/debug.jks")
                storePassword = "aa123456"
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            buildConfigField("boolean", "IS_DONATE", false.toString())
        }
        debug {
            isMinifyEnabled = false
            buildConfigField("boolean", "IS_DONATE", false.toString())
            //applicationIdSuffix '.dev'
        }
    }
    namespace = "com.nygar.app"
}

dependencies {

    implementation(project(":feature"))

    implementation(libs.hilt)
    ksp(libs.hilt.compiler)

    androidTestImplementation(libs.hilt.android.testing)
    //kaptAndroidTest(libs.hilt.compiler)

    implementation("androidx.compose.compiler:compiler:1.5.12")
    implementation("androidx.compose.runtime:runtime:1.6.6")
}
