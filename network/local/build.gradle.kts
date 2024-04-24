plugins {
    id("cleanarchitecturekotlin.android.library")
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.nygar.local"
}

dependencies {

    implementation(project(":dto"))

    implementation(libs.androidx.core.ktx)

    implementation(libs.hilt)
    ksp(libs.hilt.compiler)

    api(libs.room.runtime)
    ksp(libs.room.compiler)
}
