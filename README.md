# Keasy

[![Build status](https://travis-ci.org/hivian/keasy.svg?branch=master)](https://travis-ci.org/hivian/keasy)
[![Download](https://api.bintray.com/packages/hivian/maven/keasy/images/download.svg?version=1.2.0)](https://bintray.com/hivian/maven/keasy/1.2.0/link)
[![License](https://img.shields.io/badge/License-MIT-blue)](https://github.com/hivian/keasy/edit/master/LICENSE)

Kotlin extensions and utility functions for Android

## Installing

### Gradle

```
repositories {
    ...
    jcenter()
}
```

Add the following dependency to your `app/build.gradle`:
```
dependencies {
    implementation 'com.github.hivian:keasy:1.2.0'
}
```

## Extensions

### Context

Extensions linked to Context or Activity, such as starting custom or system activities.

### Conversions

Extensions for usefull conversions between types, dp to pixels & and vice versa, drawable to bitmap etc.

### Design

Android UI extensions for TextView, ImageView, Snackbar ... 

### Observer

Extensions for optimizing LiveData update & validate text input.

### Phone

Extensions for validating and formatting phone numbers easily, when using [libphonenumber](https://github.com/google/libphonenumber).

### Standard

General purpose extensions unrelated to any specific domain.

### Web

Extensions for url validation and format.

## Utilities

Bitmap caching, Location handler ...

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details
