package me.brisson.apitest.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.brisson.apitest.data.ITestApi
import me.brisson.apitest.data.repository.CustomerRepositoryImpl
import me.brisson.apitest.domain.repository.ICustomerRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "http://192.168.0.108:8080"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesApi() : ITestApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ITestApi::class.java)

    @Singleton
    @Provides
    fun providesCustomerRepository(api: ITestApi) : ICustomerRepository {
        return CustomerRepositoryImpl(api)
    }
}