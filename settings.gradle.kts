gradle.startParameter.excludedTaskNames.addAll(listOf(":build-logic:convention:testClasses"))
pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven {
            setUrl("https://jitpack.io")
            content {
                includeGroup("com.github.aasitnikov")
            }
        }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "CleanArchitectureKotlin"
include(":app")
include(":feature")
include(":domain")
include(":dto")

include(":core:common")
include(":core:designsystem")

include(":network:repository")
include(":network:local")
include(":network:remote")
include(":dtolib")
include(":networklib")
