plugins {
    id("cleanarchitecturekotlin.android.feature")
}

android {
    namespace = "com.nygar.designsystem"

    buildFeatures {
        compose = true
    }
}

dependencies {

    api(project(":core:common"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.material3)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.splash.screen)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.constraintlayout)

    implementation(libs.coil)
    implementation(libs.lottie)
}
