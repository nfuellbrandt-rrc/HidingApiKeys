import java.util.Properties

//val localPropertiesFile = rootProject.file("local.properties")
//val localProperties = Properties()

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

val api_key_from_gradle_properties: String? = project.findProperty("api_key") as String?
android {
    namespace = "com.example.hidingapikeys"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.hidingapikeys"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // required for both ways
        android.buildFeatures.buildConfig = true

        // load the values from apikeys.properties file
        val keystoreFile = project.rootProject.file("apikeys.properties")
        val properties = Properties()
        properties.load(keystoreFile.inputStream())

        // return empty key in case something goes wrong
        val apiKey = properties.getProperty("API_KEY") ?: ""
        buildConfigField(
            type = "String",
            name = "API_KEY",
            value = apiKey
        )

        buildConfigField(
            type = "String",
            name = "API_KEY_FROM_GRADLE_PROPERTIES",
            value = "$api_key_from_gradle_properties")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}