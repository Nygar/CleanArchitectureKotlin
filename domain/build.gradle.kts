plugins {
    id("cleanarchitecturekotlin.android.library")
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.nygar.domain"
}

dependencies {

    implementation(project(":dto"))
    implementation(project(":network:repository"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.runtime.viewmodel)

    implementation(libs.hilt)
    ksp(libs.hilt.compiler)
}
