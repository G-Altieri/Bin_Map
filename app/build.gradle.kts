plugins {
    id("com.android.application")
 //   id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin") version "2.0.1" apply false

}

android {
    namespace = "it.gteam.app.bin_map"
    compileSdk = 34

    defaultConfig {
        applicationId = "it.gteam.app.bin_map"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    // per abilitare il view e il data biding
    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {

    //Soluzione Trovata su internet per risolvere il problema della classe duplicata
    constraints {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.8.0") {
            because("kotlin-stdlib-jdk7 is now a part of kotlin-stdlib")
        }
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.0") {
            because("kotlin-stdlib-jdk8 is now a part of kotlin-stdlib")
        }
    }

    //Dipendenze
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.6.0") //downgrade per risolvere un errore
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    //navigation ui
    implementation("androidx.navigation:navigation-fragment:2.7.7")
    implementation("androidx.navigation:navigation-ui:2.7.7")

    // Cronet per richiesta
    implementation("com.google.android.gms:play-services-cronet:18.0.1")

    // Room Database
    implementation("androidx.room:room-runtime:2.6.1")
    annotationProcessor("androidx.room:room-compiler:2.6.1")

    //Database
    implementation("com.google.android.gms:play-services-maps:18.2.0")

    //Altro
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //PDF View
    implementation("com.github.barteksc:android-pdf-viewer:2.8.2")
}