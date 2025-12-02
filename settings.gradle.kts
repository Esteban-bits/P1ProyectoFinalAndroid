pluginManagement {
    repositories {
        gradlePluginPortal()
        // CORRECCIÓN: Quitamos el bloque 'content' restrictivo
        // para permitir que Gradle encuentre todos los plugins de Google y Firebase.
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        // Esta parte ya estaba correcta para las librerías
        google()
        mavenCentral()
    }
}

rootProject.name = "P1ProyectoFinalAndroid"
include(":app")