# NooroWeather
A weather application built with modern Android development practices, using MVVM and Clean Architecture to ensure maintainability and scalability.

## Projet setup
- Clone the repository
- Perform Gradle Sync
- Run the project directly on Android Studio or run this command within the root folder `./gradlew installDebug` to install it on your device
- Run the Unit tests and UI tests using this command `./gradlew test connectedAndroidTest`

## Architecture
This app uses MVVM (Model-View-ViewModel) combined with Clean Architecture, divided into four main layers:

- **Data:** Responsible for handling data retrieval, storage, and persistence. It includes API interactions, data models, and repositories.
- **Domain:** Contains business logic and use cases, separate from the data layer to ensure testability and reusability.
- **UI:** The UI layer uses Jetpack Compose for building the user interface in a declarative style. It communicates with the ViewModel to display the state of the app.
- **Dependency Injection (DI):** Dagger Hilt is used to manage the app’s dependencies, ensuring loose coupling and easy testing.

## Tools and Libraries Used
- **Jetpack Compose:** For building modern, declarative UIs.
- **MVVM + Clean Architecture:** For separating concerns and improving testability.
- **Dagger Hilt:** For dependency injection to manage app dependencies.
- **Retrofit:** For networking and API calls.
- **Kotlin Flow & Coroutines:** For managing background tasks and asynchronous operations.
- **Mockito & JUnit:** For unit and UI testing.
- **Compose Test:** For UI testing with Compose.
- **Kotlin:** For all app logic.

## Video Demo
[untitled.webm](https://github.com/user-attachments/assets/ae34544b-810f-41b2-9d5b-7337c367e4ab)
