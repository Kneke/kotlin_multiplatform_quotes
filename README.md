# Kotlin Multiplatform Quotes

This app is a example project to show how to build apps with the kotlin multiplatform project. 
It includes an android app, an iOS app, a web app and a backend witch can deliver the data for the apps.

The purpose of the app is to show some famous quotes from the field of computer science.

## Structure

The project consist of a:
- common module
- android module
- ios module
- web module
- backend module

## Initial setup

- run `./gradle :common:build`
- run `cd web && yarn install && npm run updateCommon && npm run buildAndCopy`
- run `cd ios && carthage update`
- run `cd flutterapp && flutter package get`

## Common

Include the all code that can be shared between the modules.
It includes:
- api 
- repositories
- presenters
- database
- utils

```
./gradlew :common:build
```

## Android

Include a simple activity that shows quotes from a presenter which is present in the common module.

```
 ./gradlew :android:build
```

To run android app just run the android directory as android app

## iOS

Include a simple viewcontroller that shows quotes from a presenter which is present in the common module.
 
To build the iOS app just start xcode and open the .xcodeproj from the ios directory. Also do a `carthage update` to load all dependencies for the project

If common module changes since last start it will be rebuild on app start.
Just run the app on an emulator as any other ios app. 


## Flutter

Include a flutter app which can be compiled to an android, iOS and web app. 
Use flutter channels to communicate with native code. The native code parts of the flutter module then calls the common library

To build flutter app just load all necessary flutter dependencies with `flutter package get`
To run app just plugin your device or start emulators. Call `flutter devices` to find id of your device.
After that call `flutter run -d DEVICE_ID`

NEVER OPEN PROJECT WITH ANDROID STUDIO! `This will result in error kotlinmultiapp/flutterapp/android/.idea/sqldelight/../../common/.sqldelight (No such file or directory)`
To solve this you need to clone a clean version of the repo again. 

## Web

Includes a web app based on React. Common module will be included as npm package.

To build js common module and update the dependency in the web package use
```
 npm run updateCommon
```

To start the webpage just run
```
 npm start
```

## Backend

A simple ktor webservice that delivers the same quote data as the public endpoint. 

```
 ./gradlew :backend:build
```

To run the backend webservice call

```
 ./gradlew :backend:run
```

To show content without the need of a local webservice currently the api endpoint of http://quotes.stormconsultancy.co.uk is used.


### TO-DO

- Refactor repo interface structure
- Add descriptions and icons for release
- Check gradle builds with Jokes app
- Clean architecture use cases
- Write some unit tests
