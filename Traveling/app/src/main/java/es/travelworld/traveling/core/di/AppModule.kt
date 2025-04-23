package es.travelworld.traveling.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.travelworld.traveling.domain.services.IHotelService
import es.travelworld.traveling.domain.services.IAccountService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL_BACKEND = "http://192.168.1.68/"
    private const val BASE_URL_MOCK = "https://01394d44-8918-4a1d-8059-629c50c25e87.mock.pstmn.io/"

    @Singleton
    @Provides
    @Named("BackendRetrofit")
    fun provideBackendRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_BACKEND)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    @Named("MockRetrofit")
    fun provideMockRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_MOCK)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesHotelService(@Named("MockRetrofit") retrofit: Retrofit): IHotelService {
        return retrofit.create(IHotelService::class.java)
    }

    @Singleton
    @Provides
    fun providesLoginService(@Named("BackendRetrofit") retrofit: Retrofit): IAccountService {
        return retrofit.create(IAccountService::class.java)
    }
}
