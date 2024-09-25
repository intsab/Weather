
# WeatherApp

## Overview
**WeatherApp** is a weather forecast application built using **MVVM Clean Architecture**. It provides the current day's weather and a full week forecast for various cities. The architecture is modular, with clear separation between layers: UI, domain, and data. The application relies on several third-party libraries for dependency injection, network operations, and UI enhancements.

---

## Architecture
The project follows **MVVM Clean Architecture** to ensure separation of concerns, scalability, and maintainability. The architecture layers are as follows:

- **Presentation Layer**: Contains UI components and ViewModels.
- **Domain Layer**: Handles business logic through Use Cases.
- **Data Layer**: Manages repositories and API communication.
- **Common Module**: A separate module used for common utilities shared across different layers.

---

## Libraries Used

1. **Dagger2**: For dependency injection to provide clean management of dependencies across the app.  
   Helps in injecting dependencies into classes, such as repositories and ViewModels, making the app modular and testable.

2. **Retrofit**: A powerful HTTP client for Android used for making network calls.  
   Used to fetch weather data from a remote API.

3. **Glide**: Image loading and caching library.  
   Utilized for loading weather condition icons from URLs efficiently.

4. **Gson**: A library to convert Java Objects into JSON and vice versa.  
   Used for parsing JSON responses from the weather API.

5. **Facebook Shimmer**: Provides a shimmering effect for UI elements while data is being loaded.  
   Adds a modern loading animation while weather data is being fetched.

---

## Key Components

### 1. **WhetherService (API Interface)**

Defines the API endpoints used to fetch weather data.

\```kotlin
interface WhetherService {
    @GET
    suspend fun getCurrentDayWeather(
        @Url url: String
    ): CurrentDayWeatherResponse

    @GET
    suspend fun getFullWeekWeatherList(
        @Url url: String
    ): FullWeekDaysResponse
}
\```

### 2. **WhetherRepo (Repository)**

Responsible for interacting with `WhetherService` and handling data-related operations.

\```kotlin
class WhetherRepo @Inject constructor(
    private val services: WhetherService,
) {
    suspend fun getCurrentDayWeather(url: String): CurrentDayWeatherResponse =
        withContext(Dispatchers.IO) {
            try {
                services.getCurrentDayWeather(url)
            } catch (exp: Exception) {
                CurrentDayWeatherResponse(null, null, false)
            }
        }

    suspend fun getFullWeekWeatherList(url: String): FullWeekDaysResponse =
        withContext(Dispatchers.IO) {
            try {
                services.getFullWeekWeatherList(url)
            } catch (exp: Exception) {
                FullWeekDaysResponse(null, null, null, false)
            }
        }
}
\```

### 3. **FullWeekWeatherUseCase (Use Case)**

Executes the business logic for fetching the weekly weather data from the repository.

\```kotlin
fun FullWeekDaysResponse.toUiModel(): List<FullWeekWeatherB> {
    val list = arrayListOf<FullWeekWeatherB>()
    this.forecast?.forecastday?.forEach {
        list.add(
            FullWeekWeatherB(
                date = it.date.toNullEmpty(),
                temperature = it.day?.avgtempC.toNullEmpty() + " \u2103",
                sunRise = it.astro?.sunrise.toNullEmpty(),
                sunSet = it.astro?.sunset.toNullEmpty(),
                condition = it.day?.condition?.text.toNullEmpty(),
                icon = "https:" + it.day?.condition?.icon.toNullEmpty()
            )
        )
    }
    return list
}
\```


### 4. **UI Model Conversion**

Converts the API response to a UI-friendly model to be displayed in the app.

\```kotlin
fun FullWeekDaysResponse.toUiModel(): List<FullWeekWeatherB> {
val list = arrayListOf<FullWeekWeatherB>()
             this.forecast?.forecastday?.forEach {
                 list.add(
                     FullWeekWeatherB(
                         date = it.date.toNullEmpty(),
                         temperature = it.day?.avgtempC.toNullEmpty() + " ℃",
                         sunRise = it.astro?.sunrise.toNullEmpty(),
                         sunSet = it.astro?.sunset.toNullEmpty(),
                         condition = it.day?.condition?.text.toNullEmpty(),
                         icon = "https:" + it.day?.condition?.icon.toNullEmpty()
                     )
                 )
             }
             return list
         }
\```

---

## Features
- **Current Day Weather**: Displays weather information like temperature, sunrise/sunset times, and conditions.
- **Weekly Weather Forecast**: Provides a 7-day forecast for any city.
- **Loading Animations**: Shimmer effect during data fetching.
- **Responsive UI**: Optimized for various screen sizes and orientations.

---

## Screenshots
Add screenshots here showing the app's main screens:
1. **Home Screen**
2. **Weekly Forecast**
3. **Shimmer Loading Effect**

---

## Installation & Setup

1. **Clone the repository**:
   \```bash
   git clone https://github.com/intsab/WeatherApp.git
   \```

2. **Open in Android Studio**.

3. **Setup API key**:
   - Add your weather API key in the `Constants` file.
\```kotlin
   object Constants {
       const val API_TOKEN = "your_api_key_here"
   }
\```

4. **Build & Run** the project.

---

## Future Enhancements
- **Unit Testing**: Implement test cases for the ViewModels and Use Cases.
- **Offline Mode**: Add caching for offline weather viewing.
- **Localization**: Add support for multiple languages.

---

