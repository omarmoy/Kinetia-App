
//build.gradle.kts (:app)
	plugins{
		id("org.jetbrains.kotlin.plugin.serialization") version "1.9.10"	
	}

	dependencies {
		// Retrofit
		implementation("com.squareup.retrofit2:retrofit:2.9.0")
		// Retrofit with Kotlin serialization Converter
		implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")
		implementation("com.squareup.okhttp3:okhttp:4.11.0")
		// Kotlin serialization
		implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
	}
	
//AndroidManifest.xml
	<uses-permission android:name="android.permission.INTERNET" />