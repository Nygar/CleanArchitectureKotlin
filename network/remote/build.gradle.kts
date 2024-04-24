plugins {
    id("cleanarchitecturekotlin.android.library")
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.nygar.remote"
}

dependencies {

    implementation(project(":dto"))
    implementation(project(":core:common"))

    implementation(libs.androidx.core.ktx)

    api(libs.retrofit)
    implementation(libs.retrofit.json)
    implementation(libs.retrofit.kotlin.serialization)
}
