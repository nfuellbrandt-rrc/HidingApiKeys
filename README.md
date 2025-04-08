# Hiding API keys
Here are 2 ways to hide an api key.
## Method 1: apikeys.properties
Create a new file at the root of your project
I named mine **apikeys.properties** although it doesn't really matter what you call it
```properties
API_KEY = "Your API key here"
```
You will need to add this file into your .gitignore
```gitignore
apikeys.properties
```

Add this code to your app/build.gradle.kts (Module :app) within the defaultConfig section 
```kotlin
android.buildFeatures.buildConfig = true

//loads the file
val keystoreFile = project.rootProject.file("apikeys.properties")
val properties = Properties()
properties.load(keystoreFile.inputStream())

// API_KEY here needs to be the same as in the previous file
val apiKey = properties.getProperty("API_KEY") ?: "" // defaulting to an empty string
buildConfigField(
    type = "String",
    name = "API_KEY", // how you will reference it in code
    value = apiKey
)
```
If using a different file name replace **apikeys.properties** with that file name


## Method 2: gradle.properties

Add your API key to your gradle.properties file like so
```properties
API_KEY = "Your API key here"
```

Add this code to your app/build.gradle.kts (Module :app)
```kotlin
android.buildFeatures.buildConfig = true

val apiKey: String? = project.findProperty("API_KEY") as String?
buildConfigField(
    type = "String",
    name = "API_KEY",
    value = "$apiKey"
)
```

Finally you will need to run this in your console so that git won't try to upload changes to this file
```
git update-index --skip-worktree gradle.properties
```

