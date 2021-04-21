package com.example.habitnote.di

import com.example.data.Habit
import com.example.data.HabitApi
import com.example.data.HabitNetworkRepository
import com.example.data.HabitNetworkRepositoryImpl
import com.example.habitnote.Adapters.HabitJsonDeserializer
import com.example.habitnote.Adapters.HabitJsonSerializer
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class HabitNetworkRepositoryModule {
    companion object {
        private const val AUTHORIZATION = "Authorization"
        private const val KEY_TOKEN = "1334dc12-2507-4a3f-8a88-5459d63a3d30"
        private const val URL = "https://droid-test-server.doubletapp.ru/api/"
    }

    @Provides
    fun providesOkHttpClient(): OkHttpClient {
         return OkHttpClient.Builder()
            .addInterceptor {
                val request = it.request()
                it.proceed(
                    request.newBuilder()
                        .addHeader(AUTHORIZATION, KEY_TOKEN)
                        .build()
                )
            }.build()
    }

    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {

        val gson:Gson = GsonBuilder()
            .registerTypeAdapter(Habit::class.java, HabitJsonSerializer())
            .registerTypeAdapter(Habit::class.java, HabitJsonDeserializer())
            .create()

        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit
    }

    @Provides
    fun providesHabitApi(retrofit: Retrofit): HabitApi {
        return retrofit.create(HabitApi::class.java)
    }

    @Provides
    fun providesHabitNetworkRepository(habitApi: HabitApi): HabitNetworkRepository {
        return HabitNetworkRepositoryImpl(habitApi)
    }
}









