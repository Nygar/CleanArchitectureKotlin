plugins {
    id("cleanarchitecturekotlin.android.application")
    id("kotlin-kapt")
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
        //testInstrumentationRunner = "com.rafaelroldan.technicaltestmango.CustomTestRunner"

        buildFeatures {
            compose = true
        }

        /*
        signingConfigs {
            debug {
                storeFile = file('../keystore/debug.jks')
                storePassword = 'aa123456'
                keyAlias = 'Cleancode'
                keyPassword = 'aa123456'
            }
        }

         */
    }

    /*
    flavorDimensions = "version"
    productFlavors {
        normal {
        }
        especial {
        }
    }

     */

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

    implementation(project(":network:repository"))

    implementation(libs.hilt)
    kapt(libs.hilt.compiler)

    androidTestImplementation(libs.hilt.android.testing)
    kaptAndroidTest(libs.hilt.compiler)

    implementation(libs.room.runtime)
}
