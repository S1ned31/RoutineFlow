plugins {
    id("routinetracker.android.library")
    id("routinetracker.android.koin")
    id("routinetracker.lint")
}

android {
    namespace = "com.busol.habittracker.core.testcommon"

    flavorDimensions += "minSdk"

    productFlavors {
        create("minSdk26") {
            dimension = "minSdk"
            minSdk = 26
            compileOptions {
                isCoreLibraryDesugaringEnabled = false
            }
        }
        create("minSdk21") {
            dimension = "minSdk"
            minSdk = 21
            compileOptions {
                isCoreLibraryDesugaringEnabled = true
            }
        }
    }
}

dependencies {
    implementation(libs.androidx.runner)
    implementation(libs.jetbrains.kotlinx.datetime)

    implementation(project(":core:database"))
    implementation(project(":core:data"))
    implementation(project(":core:logic"))
    implementation(project(":core:model"))
}