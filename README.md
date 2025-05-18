# Six for Android
Six for Android

#Build and Run
$ANDROID_SDK_ROOT/emulator/emulator -avd Medium_Phone_API_34 &

clear &&
gradle build &&
adb install ./app/build/outputs/apk/debug/app-debug.apk &&
adb shell monkey -p eu.veldsoft.six -c android.intent.category.LAUNCHER 1

