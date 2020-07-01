package com.assignment.di.component

import android.app.Application
import com.assignment.BaseApplication
import com.assignment.di.module.ActivityBuilder
import com.assignment.di.module.AppModule
import com.assignment.di.module.ViewModelFactoryModule
import com.assignment.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component( modules=[AndroidInjectionModule::class,
    ViewModelFactoryModule::class,
    AppModule::class,
    ActivityBuilder::class,
    ViewModelModule::class])
interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}