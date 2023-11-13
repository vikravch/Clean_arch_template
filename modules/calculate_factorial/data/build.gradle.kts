plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = ProjectConfig.appId+".calculate_factorial.data"
}

apply {
    from("$rootDir/buildSrc/general_gradle_build/data_build.gradle")
}

dependencies {
    "implementation"(project(Modules.calculateFactorialDomain))
}