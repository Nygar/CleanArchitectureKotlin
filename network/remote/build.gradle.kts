plugins {
    id("cleanarchitecturekotlin.android.library")
}

android {
    namespace = "com.nygar.repository"
}

dependencies {

    implementation(project(":dto"))

    implementation(libs.androidx.core.ktx)

    api(libs.retrofit)
    implementation(libs.retrofit.json)
    implementation(libs.retrofit.kotlin.serialization)
}
