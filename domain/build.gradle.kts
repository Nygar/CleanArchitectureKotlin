import java.util.Properties

plugins {
    id("cleanarchitecturekotlin.android.library")
    alias(libs.plugins.ksp)
    `maven-publish`
}

android {
    namespace = "com.nygar.domain"
}

publishing {

    /**Create github.properties in root project folder file with gpr.usr=GITHUB_USER_ID  & gpr.key=PERSONAL_ACCESS_TOKEN**/
    val githubProperties: Properties =
        Properties().apply {
            // This is when you load your credentials for Github
            // load(FileInputStream(File(rootProject.rootDir, "github.properties")))
        }

    fun getVersionName(): String {
        return "0.0.1" // Replace with version Name
    }

    fun getArtificatId(): String {
        return "cleanarchitecturekotlinlib" // Replace with library name ID
    }

    publications {
        create<MavenPublication>("gpr") {
            run {
                groupId = "com.nygar"
                artifactId = getArtificatId()
                version = getVersionName()
                artifact(layout.buildDirectory.dir("/outputs/aar/domain-debug.aar").get())
            }
        }
    }

    repositories {
        maven {
            name = "GitHubPackages"
            /** Configure path of your package repository on Github
             *  Replace GITHUB_USERID with your/organisation Github userID and REPOSITORY with the repository name on GitHub
             */
            url = uri("https://maven.pkg.github.com/Nygar/CleanArchitectureKotlin")
            credentials {
                /**Create github.properties in root project folder file with gpr.usr=GITHUB_USER_ID  & gpr.key=PERSONAL_ACCESS_TOKEN
                 * OR
                 * Set environment variables
                 */
                username = githubProperties["gpr.usr"] as String? ?: System.getenv("GPR_USER")
                password = githubProperties["gpr.key"] as String? ?: System.getenv("GPR_API_KEY")
            }
        }
    }
}

dependencies {

    implementation(project(":dto"))
    implementation(project(":network:repository"))
    implementation(project(":core:common"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.runtime.viewmodel)

    implementation(libs.hilt)
    ksp(libs.hilt.compiler)
}
