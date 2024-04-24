plugins {
    id("cleanarchitecturekotlin.android.library")
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.nygar.repository"
}

dependencies {

    implementation(project(":dto"))
    api(project(":network:local"))
    api(project(":network:remote"))

    implementation(libs.androidx.core.ktx)

    implementation(libs.hilt)
    ksp(libs.hilt.compiler)
}
