plugins {
    id(Plugins.AGP.library)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.kapt)
}

android {
    namespace = "com.example.rickmortytestapp"
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility =  JavaVersion.VERSION_11
        targetCompatibility =  JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(project(":domain"))

    //UI
    implementation(Deps.UI.androidCore)
    implementation(Deps.UI.appCompat)
    implementation(Deps.UI.material)

    // room persistence library
    implementation(Deps.Room.runtime)
    kapt(Deps.Room.compiler)
    implementation(Deps.Room.room)
    implementation(Deps.Room.paging)

    //paging 3
    api(Deps.Paging.runtime)

    //Coroutines
    implementation(Deps.Coroutines.core)

    // Retrofit2
    implementation(Deps.Retrofit2.retrofit)

    //test
    testImplementation(Deps.UI.jUnit)
    androidTestImplementation(Deps.UI.extJUnit)
    androidTestImplementation(Deps.UI.espresso)
}