pluginManagement {
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
    versionCatalogs {
        create("core") {
            from(files("version-catalog/core.toml"))
        }
        create("network") {
            from(files("version-catalog/network.toml"))
        }
    }
}

rootProject.name = "WalltechTodo"
include(":app")
 