import com.android.build.gradle.internal.api.BaseVariantOutputImpl

plugins {
    id("cleanarchitecturekotlin.android.library")
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.nygar.dto"

    libraryVariants.all {
        outputs.map { it as BaseVariantOutputImpl }.forEach { output ->
            // val outputName = "${project.rootDir}/outputLib/${this.baseName}.aar"
            // output.outputFileName = outputName

            val fileName = "${project.name}.aar"
            output.outputFileName = "$buildOutputs/outputLib/$fileName"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)

    implementation(libs.room.runtime)
    ksp(libs.room.compiler)
}
