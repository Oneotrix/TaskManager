plugins {
    alias(core.plugins.android.application)
    alias(core.plugins.kotlin.android)
    alias(core.plugins.kotlin.serialization)
}

android {
    namespace = "com.dirion.walltechtodo"
    compileSdk = core.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.dirion.walltechtodo"
        minSdk = core.versions.minSdk.get().toInt()
        targetSdk = core.versions.targetSdk.get().toInt()
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(network.okhttp)
    implementation(network.okhttp.loggingInterceptor)
    implementation(network.retrofit)
    implementation(network.retrofit.kotlinx.serialization)

    implementation(core.androidx.ktx)
    implementation(core.androidx.appcompat)
    implementation(core.material)
    implementation(core.constraintlayout)

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}