plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    namespace = ProjectConfig.appId+".calculate_factorial.domain"
}

apply {
    from("$rootDir/buildSrc/general_gradle_build/domain_build.gradle")
}