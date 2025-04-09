buildscript {
    repositories {
        google()
        mavenCentral()
    }
}

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.library") version "8.1.4" apply false
    alias(libs.plugins.kotlin.android) apply false
    id("com.google.dagger.hilt.android") version "2.51.1" apply false
    id("com.android.application") version "8.4.0" apply false
    id("com.google.devtools.ksp") version ("2.0.0-1.0.22") apply false

}