plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "my.id.jeremia.fokusteropong"
    compileSdk = 34

    defaultConfig {
        applicationId = "my.id.jeremia.fokusteropong"
        minSdk = 24
        targetSdk = 34
        versionCode = 2
        versionName = "2.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    }

    buildTypes {
        debug {
            buildConfigField("String", "SERVER_IPADDR", "\"192.168.50.5\"")
            buildConfigField("String", "SERVER_PORT", "\"5000\"")
            buildConfigField("String", "SERVER_MACADDR", "\"e4:5f:01:1c:78:67\"")
            buildConfigField("String", "SERVER_SSID", "\"RPiHotspot\"")
        }
        release {
            buildConfigField("String", "SERVER_IPADDR", "\"192.168.50.5\"")
            buildConfigField("String", "SERVER_PORT", "\"5000\"")
            buildConfigField("String", "SERVER_MACADDR", "\"e4:5f:01:1c:78:67\"")
            buildConfigField("String", "SERVER_SSID", "\"RPiHotspot\"")

            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures{
        viewBinding = true
        buildConfig = true
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
    implementation ("androidx.datastore:datastore-preferences:1.1.1")

    // Work
    val work = "2.9.0"
    implementation("androidx.work:work-runtime-ktx:$work")

    // Image Coil
    val coil = "2.6.0"
    implementation("io.coil-kt:coil:$coil")
    implementation("io.coil-kt:coil-compose:$coil")


    val hilt = "2.50"
    implementation("com.google.dagger:hilt-android:$hilt")
    ksp("com.google.dagger:hilt-android-compiler:$hilt")

    val hiltKtx = "1.1.0"
    implementation("androidx.hilt:hilt-navigation-compose:$hiltKtx")
    implementation("androidx.hilt:hilt-work:$hiltKtx")
    ksp("androidx.hilt:hilt-compiler:$hiltKtx")

    implementation("io.github.thanosfisherman.wifiutils:wifiutils:1.6.6")
    implementation ("com.guolindev.permissionx:permissionx:1.7.1")

    // Network
    val retrofit2 = "2.11.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofit2")
    implementation("com.squareup.retrofit2:converter-moshi:${retrofit2}")
    implementation("com.squareup.retrofit2:converter-scalars:${retrofit2}")

    // JSON
    val moshi = "1.15.0"
    implementation("com.squareup.moshi:moshi:$moshi")
    ksp("com.squareup.moshi:moshi-kotlin-codegen:$moshi")

    val okhttp3 = "4.12.0"
    implementation(platform("com.squareup.okhttp3:okhttp-bom:$okhttp3"))
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}