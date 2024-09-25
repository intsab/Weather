package com.intsab.intsabwether.di

import android.app.Application
import com.intsab.code_data.di.NetworkModule
import com.intsab.intsabwether.MainActivity
import com.intsab.intsabwether.WeatherApp
import com.intsab.intsabwether.fragments.dashboard.WeatherDashboardFragment
import com.intsab.intsabwether.fragments.weeklylist.WeatherWeeklyListFragment
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import javax.inject.Singleton


/**
 * Created by intsabhaider
 * on 24,September,2024
 */


@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, ActivitiesModuleBind::class, NetworkModule::class, FragmentBuilderModule::class])
interface AppComponent : AndroidInjector<WeatherApp> {
    fun inject(activity: MainActivity)
    fun inject(app: AppComponent)
    fun inject(dashboardFragment: WeatherDashboardFragment)
    fun inject(weeklyList: WeatherWeeklyListFragment)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: WeatherApp): AppComponent
    }
}

@Module(includes = [AppModuleBinds::class])
class AppModule {
    @Provides
    fun provideContext(app: WeatherApp) = app.applicationContext
}

@Module
abstract class AppModuleBinds {

    @Binds
    abstract fun provideApplication(bind: WeatherApp): Application

}

// Register Activities
@Module(
    subcomponents = [MainActivitySubComponent::class]
)
abstract class ActivitiesModuleBind {
    @Binds
    @IntoMap
    @ClassKey(MainActivity::class)
    abstract fun bindMainActivity(factory: MainActivitySubComponent.Factory): AndroidInjector.Factory<*>
}

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun bindWeatherDashboardFragment(): WeatherDashboardFragment
    @ContributesAndroidInjector
    abstract fun bindWeatherListFragment(): WeatherWeeklyListFragment
}

@Subcomponent
interface MainActivitySubComponent : AndroidInjector<MainActivity> {
    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<MainActivity>
}