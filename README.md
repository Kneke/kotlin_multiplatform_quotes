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

## iOS

Include a simple viewcontroller that shows quotes from a presenter which is present in the common module.
 
To build the iOS app just start xcode and open the .xcodeproj from the ios directory


## Web

Includes a web app consisting of a index.html, style.css, a Kotlin Page file which implements a view and all the javascripts that were compiled in the common module.

## Backend

A simple ktor webservice that delivers the same quote data as the public endpoint. 

To show content without the need of a local webservice currently the api endpoint of http://quotes.stormconsultancy.co.uk is used.

### TO-DO

- Write some unit tests
- Clean up architecture
- Couroutines provider
- Flow integration