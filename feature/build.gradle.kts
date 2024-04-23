plugins {
    id("cleanarchitecturekotlin.android.library")
    alias(libs.plugins.ksp)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.firebasePlugin)
    alias(libs.plugins.hiltPlugin)
}

android {
    namespace = "com.nygar.feature"

    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(project(":domain"))
    implementation(project(":core:designsystem"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.material3)
    implementation(libs.splash.screen)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.ui.tooling.preview)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)
    implementation(libs.firebase.analytics)

    implementation(libs.hilt)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.navigation)
}
