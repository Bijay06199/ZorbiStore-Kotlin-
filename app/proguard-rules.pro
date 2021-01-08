# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile


-keep class com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory { *; }
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-verbose
#-ignorewarnings

-dontwarn retrofit.**
-keep class com.example.zorbistores.ui.main.MainActivity
-keepattributes Signature
-keepattributes Exceptions

-keep class com.example.zorbistores.*

-keepattributes *Annotation*
-dontwarn okhttp3.**
-dontnote okhttp3.**
-keep class com.example.zorbistores.base.BaseActivity
-keep class com.example.zorbistores.base.BaseFragment
-keep class com.example.zorbistores.base.BaseViewModel
-keep class * implements java.io.Serializable { *; }
-keep class com.example.zorbistores.ui.main.cart.model.*
-keep class com.example.zorbistores.ui.main.categories.response.*
-keep class com.example.zorbistores.ui.main.shop.response.*
-keep class com.example.zorbistores.ui.main.cart.checkout.response.*
-keep class com.example.zorbistores.ui.auth.login.response.*
-keep class com.example.zorbistores.ui.auth.register.response.*
-keep class com.example.zorbistores.ui.main.cart.checkout.response.*
-keep class com.example.zorbistores.ui.*
-keep class com.example.zorbistores.utils.*
-keep class com.example.zorbistores.ui.main.cart.ShoppingCart




#for gson
-keepattributes Signature
-dontwarn sun.misc.**
-keep class * extends com.google.gson.TypeAdapter
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

#for glide
# Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
