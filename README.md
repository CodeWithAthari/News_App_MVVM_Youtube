# Daily News MVVM

Daily news is an android app which is using wordpress rest api under the hood. I made this project for youtube video
You can watch here:
[![IMAGE ALT TEXT HERE](https://img.youtube.com/vi/Cy3zhPwPNPo/maxresdefault.jpg)](https://youtu.be/Cy3zhPwPNPo)


## Dependencies


Build.gradle (module level)
```gradle
    implementation 'com.github.bumptech.glide:glide:4.15.1'
    implementation 'com.android.volley:volley:1.2.1'
    def lifecycle_version = "2.4.0"
    implementation "androidx.lifecycle:lifecycle-viewmodel:$lifecycle_version"
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4'
    implementation 'com.zzhoujay.richtext:richtext:3.0.0'
    implementation "androidx.browser:browser:1.4.0"
    implementation 'com.facebook.shimmer:shimmer:0.5.0'
    implementation 'androidx.swiperefreshlayout:swiperefreshlayout:1.1.0'
```

Settings.gradle

```gradle
.....
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        jcenter()
    }
}
.....
```
Gradle.properties
```gradle
.....
android.enableJetifier = true
.....
```
## My Socials

You can give my socials a try

[Instagram 📷](https://www.instagram.com/athar_android/)
[Github 👨‍💻](https://github.com/CodeWithAthari) 
[Twitter 💬](https://twitter.com/ZamanAthari)


