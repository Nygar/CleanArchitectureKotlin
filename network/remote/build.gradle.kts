plugins {
    id("cleanarchitecturekotlin.android.library")
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.nygar.remote"
}

dependencies {

    implementation(project(":dtolib"))
    implementation(project(":core:common"))

    implementation(libs.hilt)
    ksp(libs.hilt.compiler)

    implementation(libs.androidx.core.ktx)

    implementation(libs.retrofit)
    implementation(libs.retrofit.json)
    implementation(libs.retrofit.kotlin.serialization)
}
