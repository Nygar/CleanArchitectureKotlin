gradle.startParameter.excludedTaskNames.addAll(listOf(":build-logic:convention:testClasses"))
pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
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
