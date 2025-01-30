import com.android.build.gradle.internal.api.BaseVariantOutputImpl

plugins {
    id("cleanarchitecturekotlin.android.library")
    alias(libs.plugins.ksp)
    id("com.kezong.fat-aar")
}

android {
    namespace = "com.nygar.repository"

    libraryVariants.all {
        outputs.map { it as BaseVariantOutputImpl }.forEach { output ->
            // val outputName = "${project.rootDir}/outputLib/${this.baseName}.aar"
            // output.outputFileName = outputName

            val fileName = "${project.name}.aar"
            output.outputFileName = "/outputLib/$fileName"
        }
    }
}

/*
fataar {
    /**
 * If transitive is true, local jar module and remote library's dependencies will be embed.
 * If transitive is false, just embed first level dependency
 * Local aar project does not support transitive, always embed first level
 * Default value is false
 * @since 1.3.0
 */
    transitive = true
}

tasks.withType<GenerateModuleMetadata> {
    enabled = false
}
 */

dependencies {

    implementation(project(":dtolib"))
    implementation(libs.androidx.core.ktx)

    implementation(libs.hilt)
    embed(project(path = ":network:local"))
    embed(project(path = ":network:remote"))
    ksp(libs.hilt.compiler)

    // Dependencies for Remote FatArr
    embed(libs.retrofit)
    embed(libs.retrofit.json)

    // Dependencies for Local FatAar
    embed(libs.room.runtime)
}

afterEvaluate {
    tasks.named("extractDeepLinksDebug") {
        dependsOn(":network:repository:explodeAndroidx.roomRoom-runtimeDebug")
    }
}
