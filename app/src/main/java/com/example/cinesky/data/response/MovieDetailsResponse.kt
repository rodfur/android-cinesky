package com.example.cinesky.data.response

import com.example.cinesky.data.model.Movie
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDetailsResponse(
        @Json(name = "title")
        val title: String,
        @Json(name = "overview")
        val overview: String,
        @Json(name = "duration")
        val duration: String,
        @Json(name = "release_year")
        val release_year: String,
        @Json(name = "cover_url")
        val cover_url: String,
        @Json(name = "backdrops_url")
        val backdrops_url: List<String>
) {
    fun getMovieModel() = Movie(
        title = this.title,
        overview = this.overview,
        duration = this.duration,
        release_year = this.release_year,
        cover_url = this.cover_url,
        backdrops_url = this.backdrops_url
    )
}