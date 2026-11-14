plugins {
    id("routinetracker.android.application")
    id("routinetracker.android.application.compose")
    id("routinetracker.android.koin")
    id("routinetracker.lint")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.busol.habittracker.app"

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

    defaultConfig {
        applicationId = "com.busol.habittracker.app"
        versionCode = 1
        versionName = "0.1.0"

        vectorDrawables {
            useSupportLibrary = true
        }

        testInstrumentationRunner =
            "com.busol.habittracker.core.testcommon.InstrumentationTestRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    packaging {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

composeCompiler {
    reportsDestination = layout.buildDirectory.dir("compose_compiler")
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:database"))
    implementation(project(":core:model"))
    implementation(project(":core:ui"))
    implementation(project(":core:domain"))
    implementation(project(":core:logic"))
    implementation(project(":feature:addeditroutine"))

    implementation(project(":feature:agenda"))
    implementation(project(":feature:routinedetails"))
    implementation(project(":feature:auth"))
    implementation(project(":feature:profile"))
    implementation(project(":feature:articles"))
    testImplementation(project(":core:testcommon"))

    implementation(platform("com.google.firebase:firebase-bom:33.5.1"))
    implementation("com.google.firebase:firebase-analytics")
    implementation(libs.jetbrains.kotlinx.datetime)
    implementation(libs.androidx.core.splashscreen)
}