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
        dataBinding = true
        viewBinding = true
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
    implementation(libs.play.services.auth)

    implementation(libs.coil)

    implementation(libs.hilt)
    ksp(libs.hilt.compiler)

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.fragment:fragment-ktx:1.6.2")
    implementation("androidx.activity:activity-ktx:1.8.2")
    implementation("androidx.compose.compiler:compiler:1.5.12")
    implementation("androidx.compose.runtime:runtime:1.6.6")
    implementation("androidx.compose.runtime:runtime-livedata:1.6.6")
    implementation("androidx.annotation:annotation:1.7.1")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("com.google.android.material:material:1.11.0")
}
