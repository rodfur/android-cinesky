package com.example.cinesky.data

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiService {

    private fun initRetrofit(): Retrofit {

        return Retrofit.Builder()
            .baseUrl("https://sky-exercise.herokuapp.com/api/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    val service: SkyServices = initRetrofit().create(SkyServices::class.java)
}