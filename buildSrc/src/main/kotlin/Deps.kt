object Versions {
    const val AGP = "7.3.1"
    const val kotlin = "1.8.0"
    const val androidCore = "1.9.0"
    const val appCompat = "1.6.1"
    const val material = "1.8.0"
    const val constraint = "2.1.4"
    const val fragment = "1.5.5"
    const val jUnit = "4.13.2"
    const val extJUnit = "1.1.5"
    const val espresso = "3.5.1"
    const val room = "2.5.0"
    const val lifecycle = "2.6.0"
    const val lifecycleExtensions = "2.2.0"
    const val coroutines = "1.6.4"
    const val navigation = "2.5.3"
    const val coil = "2.2.2"
    const val paging = "3.1.1"
    const val retrofit = "2.9.0"
    const val loggingInterceptor = "5.0.0-alpha.2"
    const val koin = "2.2.2"
}

object Deps {

    object UI {
        const val androidCore = "androidx.core:core-ktx:${Versions.androidCore}"
        const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
        const val material = "com.google.android.material:material:${Versions.material}"
        const val constraint = "androidx.constraintlayout:constraintlayout:${Versions.constraint}"
        const val fragment = "androidx.fragment:fragment-ktx:${Versions.fragment}"
        const val jUnit = "junit:junit:${Versions.jUnit}"
        const val extJUnit = "androidx.test.ext:junit:${Versions.extJUnit}"
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    }

    object Room {
        const val runtime = "androidx.room:room-runtime:${Versions.room}"
        const val compiler = "androidx.room:room-compiler:${Versions.room}"
        const val room = "androidx.room:room-ktx:${Versions.room}"
        const val paging = "androidx.room:room-paging:${Versions.room}"
    }

    object Lifecycle {
        const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
        const val extensions =
            "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleExtensions}"
        const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    }

    object Coroutines {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val android =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    }

    object Navigation {
        const val ui = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
        const val fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    }

    object Koin {
        const val android = "io.insert-koin:koin-android:${Versions.koin}"
        const val scope = "io.insert-koin:koin-androidx-scope:${Versions.koin}"
        const val viewModel = "io.insert-koin:koin-androidx-viewmodel:${Versions.koin}"
        const val fragment = "io.insert-koin:koin-androidx-fragment:${Versions.koin}"
    }

    object Retrofit2 {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val converterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
        const val loggingInterceptor =
            "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"
    }

    object Paging {
        const val runtime = "androidx.paging:paging-runtime-ktx:${Versions.paging}"
        const val common = "androidx.paging:paging-common:${Versions.paging}"
    }

    object Coil {
        const val coil = "io.coil-kt:coil:${Versions.coil}"
    }
}

object Plugins {
    object AGP {
        const val application = "com.android.application"
        const val library = "com.android.library"
    }

    object Kotlin {
        const val android = "org.jetbrains.kotlin.android"
        const val kapt = "kotlin-kapt"
        const val jvm = "org.jetbrains.kotlin.jvm"
    }

    object Java {
        const val library = "java-library"
    }
}