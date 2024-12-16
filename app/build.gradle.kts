plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "com.dianascode.nooroweather"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.dianascode.nooroweather"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "BASE_URL", "\"http://api.weatherapi.com/v1/\"")
        buildConfigField("String", "API_KEY", "\"d5a409c3b4dd4aaead4114012241412\"")

    }

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            applicationIdSuffix = ".debug"
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // dependency injection with koin
    implementation(project.dependencies.platform(libs.koin.bom))
    implementation(libs.koin.core)
    implementation(libs.koin.test)
    implementation(libs.koin.test.junit4)
    implementation(libs.koin.android)
    implementation(libs.koin.android.test)
    implementation(libs.koin.android.compose)
    implementation(libs.koin.android.compose.navigation)
    implementation(libs.koin.core.coroutines)

    // networking with retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp3.logging)

    // navigation with compose
    implementation(libs.androidx.navigation.compose)

    // persistence with room
    implementation(libs.androidx.room)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.coroutines)

    // Coil
    implementation(libs.coil.compose)

    // unit tests
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)

    // ui tests
    androidTestImplementation(libs.dexmaker.mockito.inline)
    androidTestImplementation(libs.mockito.kotlin)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}