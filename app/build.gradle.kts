plugins {
    id(Plugins.AGP.application)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.kapt)
}

android {
    namespace = "com.example.rickmortytestapp"
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        applicationId = "com.example.rickmortytestapp"
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = 1
        versionName = "1.0"
        buildConfigField("String", "BASE_URL", "\"https://rickandmortyapi.com/\"")
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(project(":data"))
    implementation(project(":domain"))

    implementation(Deps.UI.androidCore)
    implementation(Deps.UI.appCompat)
    implementation(Deps.UI.material)
    implementation(Deps.UI.constraint)
    implementation(Deps.UI.fragment)

    // KoinDI
    implementation(Deps.Koin.android)
    implementation(Deps.Koin.scope)
    implementation(Deps.Koin.viewModel)
    implementation(Deps.Koin.fragment)

    // Coroutines
    implementation(Deps.Coroutines.android)
    implementation(Deps.Coroutines.core)

    // Lifecycle
    implementation(Deps.Lifecycle.extensions)
    implementation(Deps.Lifecycle.viewModel)
    implementation(Deps.Lifecycle.liveData)
    implementation(Deps.Lifecycle.runtime)

    //navigation
    implementation(Deps.Navigation.fragment)
    implementation(Deps.Navigation.ui)

    // Retrofit2
    implementation(Deps.Retrofit2.retrofit)
    implementation(Deps.Retrofit2.converterGson)
    implementation(Deps.Retrofit2.loggingInterceptor)

    // room persistence library
    implementation(Deps.Room.runtime)
    kapt(Deps.Room.compiler)
    implementation(Deps.Room.room)
    implementation(Deps.Room.paging)

    //coil
    implementation(Deps.Coil.coil)

    //paging 3
    implementation(Deps.Paging.runtime)
    implementation(Deps.Paging.common)

    //test
    testImplementation(Deps.UI.jUnit)
    androidTestImplementation(Deps.UI.extJUnit)
    androidTestImplementation(Deps.UI.espresso)
}