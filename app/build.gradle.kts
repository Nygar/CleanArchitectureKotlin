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

    //implementation(project(":feature"))

    implementation(libs.hilt)
    kapt(libs.hilt.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    androidTestImplementation(libs.androidx.navigation.testing)

    androidTestImplementation(libs.hilt.android.testing)
    kaptAndroidTest(libs.hilt.compiler)
}
