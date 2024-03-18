package com.example.fakestoremvvm.di

import com.example.fakestoremvvm.core.utils.Constants
import com.example.fakestoremvvm.data.remote.api.FakeStoreApi
import com.example.fakestoremvvm.data.repository.RepositoryImpl
import com.example.fakestoremvvm.domain.model.Product
import com.example.fakestoremvvm.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun providesRetrofitInstance(): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constants.BASE_URL)
        .build()

    @Provides
    @Singleton
    fun providesFakeStoreApi(retrofit: Retrofit): FakeStoreApi =
        retrofit.create(FakeStoreApi::class.java)

    @Provides
    @Singleton
    fun providesProductRepository(fakeStoreApi: FakeStoreApi): ProductRepository{
        return  RepositoryImpl(fakeStoreApi)
    }

}