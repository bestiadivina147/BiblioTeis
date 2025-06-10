plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.biblioteis"
    compileSdk = 35
    defaultConfig {
        applicationId = "com.example.biblioteis"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // ZXing dependencies for QR code scanning
    implementation(libs.camera.core)
    implementation(libs.camera.camera2)
    implementation(libs.camera.lifecycle)
    implementation(libs.camera.view)
    implementation(libs.barcode.scanning)
    implementation(libs.core)
    implementation(libs.journeyapps.zxing.android.embedded)
    implementation(libs.security.crypto)
    implementation(libs.journeyapps.zxing.android.embedded)

}