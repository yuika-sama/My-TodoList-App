// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    id("com.google.devtools.ksp") version "2.1.0-1.0.29" apply false
    id ("org.jetbrains.kotlin.jvm") version "1.9.0"
    alias(libs.plugins.org.jetbrains.kotlin.kapt) apply false
}
buildscript {
    dependencies {
//        classpath(libs.androidx.navigation.safe.args.gradle.plugin)
        val nav_version = "2.8.9"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
    }
}