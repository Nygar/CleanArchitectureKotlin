plugins {
    id("cleanarchitecturekotlin.android.library")
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlinSerialization)
}

android {
    namespace = "com.nygar.dto"
}

dependencies {

    implementation(libs.androidx.core.ktx)

    implementation(libs.room.runtime)
    ksp(libs.room.compiler)

    implementation(libs.kotlin.serialization)
}
