package com.assignment.di.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

open class ViewModelProviderFactory @Inject constructor(private val creators:Map<Class<out ViewModel>,
        @JvmSuppressWildcards Provider<ViewModel>>) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        var creator = creators[modelClass] ?: throw IllegalArgumentException("unknown model class $modelClass") as Throwable
        for (entry in creators.entries) {

            // if it's allowed, set the Provider<EditItemViewModel>
            if (modelClass.isAssignableFrom(entry.key)) {
                creator = entry.value
                break
            }
        }
        // return the Provider
        try {
            return creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }
}