plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.0"
}

android {
    namespace = "com.example.seamlesstranslation"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.seamlesstranslation"
        // MediaRecorderが31以上
        minSdk = 31
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

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
        // glanceのため変更 11 to 1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        // glanceのため変更 11 to 1.8
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    val glance_version = "1.2.0-alpha01"
    // For Glance support
    implementation("androidx.glance:glance:$glance_version")
    // For AppWidgets support
    implementation("androidx.glance:glance-appwidget:$glance_version")
    // For Wear-Tiles support
    implementation("androidx.glance:glance-wear-tiles:1.0.0-alpha05")
    val material3_version = "1.3.2"
    // material3
    implementation("androidx.compose.material3:material3:$material3_version")
    implementation("androidx.compose.material3:material3-window-size-class:$material3_version")
    implementation("androidx.compose.material3:material3-adaptive-navigation-suite:1.4.0-alpha15")
    // androidx.activity
    val activity_version = "1.10.1"
    implementation("androidx.activity:activity:$activity_version")
    implementation("androidx.activity:activity-ktx:$activity_version")
    // activity-compose
    implementation("androidx.activity:activity-compose:$activity_version")
    // ServiceCompat, NotificationCompatなど
    implementation("androidx.core:core-ktx:1.16.0")
    //Vosk:Speech recognition (is placed in MavenCentral)
    implementation("com.alphacephei:vosk-android:0.3.32+")
    //ffmpegのaudio部分だけ kitが開発終了で使えない
    // implementation ("com.arthenica:ffmpeg-kit-audio:6.0-2")
}