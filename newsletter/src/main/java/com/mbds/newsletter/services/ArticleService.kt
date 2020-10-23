package com.mbds.newsletter.services

import com.mbds.newsletter.model.NewsApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleService {
    @GET("everything?sortBy=publishedAt")
    fun list(@Query("q") category: String): Call<NewsApiResponse>
}