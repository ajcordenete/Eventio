[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io/)

# Eventio

**Language**: Kotlin

A native Android application that saves button events and displays all record of said events.
<br/><br/>
<img src="https://github.com/ajcordenete/Eventio/blob/master/img/screenshot_home.png" width="250">&nbsp; 
<img src="https://github.com/ajcordenete/Eventio/blob/master/img/screenshot_list_page.png" width="250">&nbsp;
<img src="https://github.com/ajcordenete/Eventio/blob/master/img/screenshot_action_page.png" width="250">&nbsp;

### [Basekit]((https://developer.android.com/topic/libraries/architecture/room))
The app is generated from my own android-basekit which is my starter template for creating new Android projects and contains necessary boilerplate codes. More information about it in the repo:
- [android-basekit](https://github.com/ajcordenete/android-basekit)


### Persistence
  - [Room Library](https://developer.android.com/topic/libraries/architecture/room) is used as the persistence mechanism for saving/retrieving button events.
   
 ### Architecture and Modularization
  The app is divided into separate modules according to the layers from [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html).
   - **domain module** - contains models/entities that are being used within the app.
   - **persistence module** - contains classes that handle local storage such database, daos and db entities.
   - **data module** - contains repository classes
   - **app module** - contains the presentation layer (Activities, Fragments, ViewModels, UI logic)
   
   Other modules
   - **core** - contains the base classes
   - **buildSrc** - contains the Config file where all libraries and their versions are declared. Uses kotlin-dsl to handle library resources.
 
 ### Project Folder Structure
  
  - **PBF or [Package By Feature](https://medium.com/mindorks/pbf-package-by-feature-no-more-pbl-package-by-layer-50b8a9d54ae8)**. This folder structure enables us to maintain the structure properly and also increases the readability of the code. PBF also reduces time to find the code for a specific feature.

## Design
 - Material Design principles and components
 - Minimalist design approach
 - With support for Dark theme
 <br/><br/>
 <img src="https://github.com/ajcordenete/Eventio/blob/master/img/screenshot_home_dark.png" width="250">&nbsp; 

## Tests
- Unit test are implemented for view models and repository
 
 ## Other libraries used:
- Dagger Hilt - dependency injection
- Architecture Components
- Material design components
- JUnit
- Mockito
