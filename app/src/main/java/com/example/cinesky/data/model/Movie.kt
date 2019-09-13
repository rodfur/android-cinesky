package com.example.cinesky.data.model

data class Movie(
        val title: String,
        val overview: String,
        val duration: String,
        val release_year: String,
        val cover_url: String,
        val backdrops_url: List<String>
)