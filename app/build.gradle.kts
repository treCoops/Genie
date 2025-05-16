plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    //KSP and KAPT
    alias(libs.plugins.org.jetbrains.kotlin.kapt)
    alias(libs.plugins.kotlin.ksp)

    //Firebase
    id("com.google.gms.google-services")
}

android {
    namespace = "com.advagroup.genie"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.advagroup.genie"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0.3"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //Compose Navigation
    implementation(libs.androidx.navigation.compose)

    //Compose Constraint Layout
    implementation(libs.androidx.constraintlayout.compose)

    //Android Splash
    implementation(libs.androidx.core.splashscreen)

    //Kotlin Coroutines
    implementation(libs.kotlinx.coroutines.android)

    //Life Cycle and ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    kapt(libs.androidx.lifecycle.compiler)

    //Live Data
    implementation(libs.androidx.lifecycle.livedata.ktx)

    //Saved State
    implementation(libs.androidx.lifecycle.viewmodel.savedstate)

    //System UI Control
    implementation(libs.accompanist.systemuicontroller)

    //Firebase
    implementation(libs.firebase.bom)

    //Material Icon Extended
    implementation(libs.androidx.material.icons.extended)

    //Lottie
    implementation(libs.lottie.compose)

    //Pager
    implementation(libs.androidx.foundation)


}