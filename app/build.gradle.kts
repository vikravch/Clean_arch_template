plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")
}

android {
    compileSdk = ProjectConfig.compileSdk
    namespace = ProjectConfig.appId

    defaultConfig {
        applicationId = ProjectConfig.appId
        minSdk = ProjectConfig.minSdk
        targetSdk = ProjectConfig.targetSdk
        versionCode = ProjectConfig.versionCode
        versionName = ProjectConfig.versionName

        testInstrumentationRunner =  "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Compose.composeVersion
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    kapt(DaggerHilt.hiltCompiler)
    implementation(DaggerHilt.hiltAndroid)

    implementation(project(Modules.coreData))
    implementation(project(Modules.coreDomain))
    implementation(project(Modules.corePresentation))

    implementation(project(Modules.calculateFactorialData))
    implementation(project(Modules.calculateFactorialDomain))
    implementation(project(Modules.calculateFactorialPresentation))

    // retrofit
    implementation(Retrofit.retrofit)
    implementation(Retrofit.converterGson)
    implementation(Retrofit.okHttp)
    implementation(Retrofit.loggingInterceptor)

    implementation(AndroidX.coreKtx)
    implementation(AndroidX.lifecycleRuntimeKtx)

    implementation(Compose.ui)
    implementation(Compose.material)
    implementation(Compose.uiToolingPreview)
    implementation(Compose.activityCompose)
    implementation(Compose.navigationCompose)
    implementation(Compose.hiltNavigationCompose)

    testImplementation(Testing.junit4)
    testImplementation(Testing.junitAndroidExt)
    testImplementation(Testing.truth)
    testImplementation(Testing.coroutines)
    testImplementation(Testing.turbine)
    testImplementation(Testing.composeUiTest)
    testImplementation(Testing.mockk)
    testImplementation(Testing.mockWebServer)

    androidTestImplementation(Testing.espresso)
    androidTestImplementation(Testing.junit4)
    androidTestImplementation(Testing.junitAndroidExt)
    androidTestImplementation(Testing.truth)
    androidTestImplementation(Testing.coroutines)
    androidTestImplementation(Testing.turbine)
    androidTestImplementation(Testing.composeUiTest)
    androidTestImplementation(Testing.mockk)
    androidTestImplementation(Testing.mockWebServer)
    androidTestImplementation(Testing.hiltTesting)
    androidTestImplementation(Testing.mockkAndroid)
    kaptAndroidTest(DaggerHilt.hiltCompiler)
    androidTestImplementation(Testing.testRunner)
    androidTestImplementation(Testing.testCore)

    debugImplementation(Testing.composeUITooling)
    debugImplementation(Testing.composeUITestManifest)
    debugImplementation(Testing.composeUITestManifest)
}