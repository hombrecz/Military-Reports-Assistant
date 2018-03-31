# Military Reports Assistant

Military Reports Assistant is application, that helps it's user to prepare standard military reports on his Android smartphone. It enables user to fill in reports in form of preformatted formswith filled in basic information. Application can prepare the MGRS location, DTG date and some static user data, that he prefilled in.

## Reports

The current available reports are:
* SALTR
* SALUTE
* Sitrep
* Medevac 9-liner
* UXO/IED 9-liner

## Purpose

This application was created for my diploma thesis on Faculty of Mechatronics, Informatics and Interdisciplinary Studies on Technical University of Liberec.

The main purpose of this application is to simplify the training of Czech republic Army reserves and to help new recruits get faster prepared in communication skills. Some of the reports and processes could thus differ from another branches or armies processes.

## Installing

Application is intended to be run on smartphones with Android 4.4 (Kitkat) and newer.

For now it is available only from it's original author (see section Author for contact). Mabe it will be available on Google Play in future, if I had time to arrange it.

## Technologies used

* [Kotlin](https://kotlinlang.org/) - The programming language - licensed under [Apache License 2.0](https://github.com/JetBrains/kotlin-web-site/blob/master/LICENSE)
* [Gradle](https://gradle.org/) - Dependency Management - licensed under [Apache License 2.0](https://github.com/gradle/gradle/blob/master/LICENSE), (© Gradle Inc. 2018), (Copyright © 2004-2005 United States Government as represented by the Administrator of the National Aeronautics and Space Administration. All Rights Reserved. Copyright © 2004-2005 Contributors. All Rights Reserved.)
* [KOIN](https://github.com/Ekito/koin) - Used for dependency injection - licensed under [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0)
* [Anko](https://github.com/Kotlin/anko) - Used for dynamically created layout - licensed under [Apache License 2.0](https://github.com/Kotlin/anko/blob/master/LICENSE), (Copyright 2016 JetBrains s.r.o.)
* [SingleDateAndTimePicker](https://github.com/florent37/SingleDateAndTimePicker) - Date time picker - licensed under [Apache License 2.0](https://github.com/florent37/SingleDateAndTimePicker/blob/master/LICENSE), (Copyright 2016 florent37, Inc.)
* [Geo-Coordinate-Conversion-Java](https://github.com/Berico-Technologies/Geo-Coordinate-Conversion-Java) - Latitude/longitude to MGRS  - licensed under [NASA Open Source Agreement Version 1.3](https://github.com/Berico-Technologies/Geo-Coordinate-Conversion-Java/blob/master/NASA_Open_Source_Agreement_1.3.txt), (Copyright © 2004-2005 United States Government as represented by the Administrator of the National Aeronautics and Space Administration. All Rights Reserved. Copyright © 2004-2005 Contributors. All Rights Reserved.)
* [Google Places API for Android](https://developers.google.com/places/android-api/) - Place picker for Android  - terms applied [Google Maps APIs Terms of Service](https://developers.google.com/maps/terms)

## Author

* **Ondřej Dlabola** - *[e-mail](mailto:ondrej.dlabola@gmail.com)*, [Github](https://github.com/hombrecz)

## Licences of 3rd party

This software uses some third party libraries, technologies etc., that are aunder following licenses:

* Apache License 2.0 - Kotlin, Gradle, KOIN, Anko - see Apache_License_2.0.txt or [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0)
* NASA Open Source Agreement Version 1.3 - Geo-Coordinate-Conversion-Java - see NASA_Open_Source_Agreement_1.3.txt or [NASA Open Source Agreement Version 1.3](https://github.com/Berico-Technologies/Geo-Coordinate-Conversion-Java/blob/master/NASA_Open_Source_Agreement_1.3.txt)
* Google Maps APIs Terms of Service - Google Places API for Android - see [Google Maps APIs Terms of Service](https://developers.google.com/maps/terms)