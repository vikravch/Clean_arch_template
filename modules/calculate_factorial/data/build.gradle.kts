apply {
    from("$rootDir/buildSrc/general_gradle_build/data_build.gradle")
}

dependencies {
    "implementation"(project(Modules.calculateFactorialDomain))
}