
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

```kotlin
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
```

### 2. **WhetherRepo (Repository)**

Responsible for interacting with `WhetherService` and handling data-related operations.

```kotlin
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
```

### 3. **FullWeekWeatherUseCase (Use Case)**

Executes the business logic for fetching the weekly weather data from the repository.

```kotlin
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
```


### 4. **UI Model Conversion**

Converts the API response to a UI-friendly model to be displayed in the app.

```kotlin
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
```

---
## Caching Implementation in NetworkModule
Caching is crucial for improving the performance and efficiency of network requests in mobile applications. In this implementation, we utilize OkHttp's caching capabilities to store responses and reduce the number of network calls, thereby enhancing user experience, especially in scenarios with limited connectivity.

## Steps to Implement Caching

1. **Create a Cache Directory:**
   A cache directory is provided where cached HTTP responses will be stored. This directory is created in the application's cache directory.

   ```kotlin
   @Provides
   @Singleton
   fun provideCacheDir(application: Application): File {
       return File(application.cacheDir, "http_cache")
   }
   ```

2. **Define Cache Size:**
   We define a cache size of 10 MB to limit the amount of data stored in the cache.

   ```kotlin
   @Provides
   @Singleton
   fun provideCache(cacheDir: File): Cache {
       val cacheSize = 10 * 1024 * 1024L // 10 MB
       return Cache(cacheDir, cacheSize)
   }
   ```

3. **Configure OkHttpClient with Cache:**
   The `OkHttpClient` is configured to utilize the cache. A cache interceptor is added to manage caching rules based on the HTTP request methods.

   ```kotlin
   @Provides
   @Singleton
   fun provideOkHttpClient(cache: Cache): OkHttpClient {
       val cacheInterceptor = Interceptor { chain ->
           var request = chain.request()
           val response: Response = chain.proceed(request)

           // Check if request is a GET request
           if (request.method().equals("GET", ignoreCase = true)) {
               val maxAge = 60 * 60 // Cache responses for 1 hour
               response.newBuilder()
                   .header("Cache-Control", "public, max-age=$maxAge")
                   .build()
           } else {
               response
           }
       }

       return OkHttpClient.Builder()
           .cache(cache)
           .addInterceptor(cacheInterceptor)
           .build()
   }
   ```

4. **Integrate Retrofit with OkHttpClient:**
   The Retrofit instance is created using the configured `OkHttpClient` which now supports caching.

   ```kotlin
   @Provides
   @Singleton
   fun provideWhetherService(okHttpClient: OkHttpClient): WhetherService {
       return Retrofit.Builder()
           .baseUrl(Constants.BASE_URL)
           .client(okHttpClient) // Attach OkHttpClient with caching to Retrofit
           .addConverterFactory(GsonConverterFactory.create())
           .build()
           .create(WhetherService::class.java)
   }
   ```

---

## Features
- **Current Day Weather**: Displays weather information like temperature, sunrise/sunset times, and conditions.
- **Weekly Weather Forecast**: Provides a 7-day forecast for any city.
- **Loading Animations**: Shimmer effect during data fetching.
- **Responsive UI**: Optimized for various screen sizes and orientations.
- **Offline Mode ()Caching**: Saved Network calls for one hour for the same data.
---

## Screenshots

<div style="display: flex; overflow-x: auto;">
    <img src="https://github.com/user-attachments/assets/1fcb06a1-8216-4970-87d5-1b346276ec3c" alt="Home Screen" width="250" style="margin-right: 10px;">
    <img src="https://github.com/user-attachments/assets/55941ccd-f90b-4cd4-8c32-3eefe4809670" alt="Weekly Forecast" width="250" style="margin-right: 10px;">
    <img src="https://github.com/user-attachments/assets/d90c33db-9944-4e9e-9dc9-69354b164098" alt="Shimmer Loading Effect" width="250" style="margin-right: 10px;">
</div>

   

---

## Installation & Setup

1. **Clone the repository**:

   ```bash
   git clone https://github.com/intsab/Weather.git
   ```

3. **Open in Android Studio**.

4. **Setup API key**:
   - Add your weather API key in the `Constants` file.
 ```kotlin
   object Constants {
       const val API_TOKEN = "your_api_key_here"
   }
  ```

5. **Build & Run** the project.

---

## APK File
[Download APK](https://github.com/intsab/Weather/blob/master/IntsabWether.apk) and Run. Ensure you change settings to install from an unknown source in Android settings.

---

## Future Enhancements
- **Unit Testing**: Implement test cases for the ViewModels and Use Cases.
- **Localization**: Add support for multiple languages.

---

