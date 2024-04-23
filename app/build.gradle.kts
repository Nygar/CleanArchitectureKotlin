plugins {
    id("cleanarchitecturekotlin.android.application")
    alias(libs.plugins.ksp)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.hiltPlugin)
    alias(libs.plugins.firebasePlugin)
}

android {
    defaultConfig {
        applicationId = "com.nygar.app"
        versionCode = 1

        vectorDrawables {
            useSupportLibrary = true
        }
        testInstrumentationRunner = "com.nygar.cleanarchitecturekotlin.CustomTestRunner"

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
        }
        debug {
            isMinifyEnabled = false
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
}
