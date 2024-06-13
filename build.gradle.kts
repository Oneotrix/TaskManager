buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.4.2")
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(core.plugins.android.application) apply false
    alias(core.plugins.kotlin.android) apply false
    alias(core.plugins.kotlin.jvm)
    alias(core.plugins.kotlin.serialization)
    alias(core.plugins.kotlin.kapt)
}