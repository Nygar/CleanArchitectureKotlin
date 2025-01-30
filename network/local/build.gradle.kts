plugins {
    id("cleanarchitecturekotlin.android.library")
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.nygar.local"
}

dependencies {

    implementation(project(":dtolib"))

    implementation(libs.androidx.core.ktx)

    implementation(libs.hilt)
    ksp(libs.hilt.compiler)

    implementation(libs.room.runtime)
    ksp(libs.room.compiler)
}
