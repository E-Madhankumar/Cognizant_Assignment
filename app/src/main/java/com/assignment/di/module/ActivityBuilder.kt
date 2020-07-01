package com.assignment.di.module

import com.assignment.view.MainActivity
import com.assignment.di.scope.Activityscope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @Activityscope
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}