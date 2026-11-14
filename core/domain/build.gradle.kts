plugins {
    id("routinetracker.android.library")
    id("routinetracker.android.koin")
    id("routinetracker.android.library.instrumentationtestrunner")
    id("routinetracker.lint")
}

android {
    namespace = "com.busol.habittracker.domain"

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
    implementation(project(":core:data"))
    implementation(project(":core:database"))
    implementation(project(":core:model"))
    implementation(project(":core:logic"))
    implementation(project(":core:ui"))
    implementation(libs.app.cash.sqldelight.sqlite.driver)
    implementation(libs.jetbrains.kotlinx.datetime)

    testImplementation(libs.jetbrains.kotlinx.coroutines.test)
}