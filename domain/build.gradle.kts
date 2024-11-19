plugins {
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
dependencies {

    implementation(libs.javax.inject)

    testImplementation(libs.junit)
    testImplementation(libs.mockk)
}
