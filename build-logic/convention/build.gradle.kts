plugins {
    `kotlin-dsl`
}

group = "com.nygar.cleanarchitecturekotlin.buildlogic"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "cleanarchitecturekotlin.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "cleanarchitecturekotlin.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidFeature") {
            id = "cleanarchitecturekotlin.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
    }
}
