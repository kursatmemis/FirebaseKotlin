package com.kursatmemis.firebasekotlin.di.modules

import com.kursatmemis.firebasekotlin.repository.FirebaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {

    @ViewModelScoped
    @Provides
    fun provideFirebaseRepository() : FirebaseRepository {
        return FirebaseRepository()
    }

}