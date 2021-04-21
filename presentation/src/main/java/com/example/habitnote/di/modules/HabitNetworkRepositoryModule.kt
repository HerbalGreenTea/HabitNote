package com.example.habitnote.di.modules

import com.example.data.entities.Habit
import com.example.data.network.HabitApi
import com.example.data.useCases.HabitNetworkRepository
import com.example.data.network.HabitNetworkRepositoryImpl
import com.example.data.typeConverters.HabitJsonDeserializer
import com.example.data.typeConverters.HabitJsonSerializer
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class HabitNetworkRepositoryModule(
    private val AUTHORIZATION: String,
    private val KEY_TOKEN: String,
    private val URL: String) {

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









