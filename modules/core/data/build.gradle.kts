plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    namespace = ProjectConfig.appId+".core.data"
}

apply {
    from("$rootDir/buildSrc/general_gradle_build/basic.gradle")
}