package com.assignment.di.module

import androidx.lifecycle.ViewModel
import com.assignment.di.factory.ViewModelKey
import com.assignment.view.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun provideHomeViewModel(baseViewModel: MainViewModel): ViewModel
}