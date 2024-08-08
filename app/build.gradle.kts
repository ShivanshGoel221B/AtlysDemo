import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.0"
    kotlin("plugin.serialization").version("1.7.10")
    id("kotlinx-serialization")
}

val localProperties = project.rootProject.file("local.properties")
val properties = Properties()
properties.load(localProperties.inputStream())

android {
    namespace = "com.shivansh.atlysdemo"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.shivansh.atlysdemo"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField(
            type = "String",
            name = "APPLICATION_ID",
            value = "\"${project.name}\""
        )
        buildConfigField(
            type = "String",
            name = "MOVIES_URL",
            value = properties.getProperty("moviesApiUrl")
        )
        buildConfigField(
            type = "String",
            name = "MOVIES_ACCESS_TOKEN",
            value = properties.getProperty("apiAccessToken")
        )
        buildConfigField(
            type = "String",
            name = "POSTER_URL_PREFIX",
            value = properties.getProperty("posterPrefix")
        )

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}


dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose)
    implementation(libs.kotlin.coroutines)
    implementation(libs.bundles.ktor)
    implementation(libs.bundles.koin)
    implementation(libs.glide)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}