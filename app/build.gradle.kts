plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.smartrecycle"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.smartrecycle"
        minSdk = 24
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    aaptOptions {
        noCompress("tflite")
    }

    kotlinOptions {
        jvmTarget = "11"
    }
}
dependencies {
    // Core Android libraries
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // Room
    implementation(libs.androidx.room.external.antlr)

    // TensorFlow Lite - using specific implementation to avoid conflicts
    implementation("org.tensorflow:tensorflow-lite:2.12.0") {
        exclude(group = "org.tensorflow", module = "tensorflow-lite-api")
    }
    // We'll use only one of the support libraries to avoid conflicts
    // implementation("org.tensorflow:tensorflow-lite-support:0.4.2") // Removed to avoid conflict
    implementation("org.tensorflow:tensorflow-lite-gpu:2.12.0") {
        exclude(group = "org.tensorflow", module = "tensorflow-lite-api")
    }

    // Google LiteRT - keep these but make sure they don't overlap with TensorFlow
    implementation(libs.litert.support.api)

    // Retrofit for backend communication
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // CameraX for real-time capture
    implementation("androidx.camera:camera-camera2:1.2.3")
    implementation("androidx.camera:camera-lifecycle:1.2.3")
    implementation("androidx.camera:camera-view:1.2.3")

    // Tests
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    // Retrofit for backend communication
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")

}