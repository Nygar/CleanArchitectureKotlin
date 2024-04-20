plugins {
    id("cleanarchitecturekotlin.android.library")
    id("kotlin-kapt")
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.nygar.feature"
}

dependencies {

    //implementation(project(":data:network"))

    implementation(libs.androidx.core.ktx)

    implementation(libs.hilt)
    kapt(libs.hilt.compiler)
}
