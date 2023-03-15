plugins {
    id(Plugins.Java.library)
    id(Plugins.Kotlin.jvm)
}

/*before updating it from 1.7(and 1.8 in app & data gradle) to 11
paging library with common version didn't work*/
java {
    sourceCompatibility =  JavaVersion.VERSION_11
    targetCompatibility =  JavaVersion.VERSION_11
}

dependencies {
    //Coroutines Core
    implementation(Deps.Coroutines.core)
    //paging 3
    implementation(Deps.Paging.common)
}