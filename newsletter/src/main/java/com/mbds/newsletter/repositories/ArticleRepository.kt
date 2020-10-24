package com.mbds.newsletter.repositories

import com.mbds.newsletter.BuildConfig
import com.mbds.newsletter.model.Article
import com.mbds.newsletter.model.Category
import com.mbds.newsletter.services.ArticleService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ArticleRepository {
    private val service: ArticleService
    private var page = 1
    private var maxpage = -1

    init {

        val clientBulider = OkHttpClient.Builder()
            .addInterceptor(AuthenticationInterceptor())

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            clientBulider.addInterceptor(logging)
        }
        val client = clientBulider.build()

        val retrofit = Retrofit.Builder().apply {
            baseUrl("https://newsapi.org/v2/")
            client(client)
            addConverterFactory(GsonConverterFactory.create())
        }.build()

        service = retrofit.create(ArticleService::class.java)
    }

    class AuthenticationInterceptor: Interceptor{
        override fun intercept(chain: Interceptor.Chain): Response {
            var request = chain.request()
            val headers = request
                .headers()
                .newBuilder()
                .add("Authorization", "Bearer 5a7700db7c2f43e3bbc789c5a2a655c8")
                .build()
            request = request
                .newBuilder()
                .headers(headers)
                .build()
            return chain.proceed(request)
        }

    }

    fun list(category: Category): List<Article>? {
        if (maxpage < 0 || page < maxpage) {
            val response = service.list(category.key, page).execute()
            if (response.isSuccessful) {
                maxpage = response.body()?.totalResults?.div(20) ?: maxpage
                page++
                return response.body()?.articles?.toList() ?: emptyList()
            }
            return null
        }
        return listOf()
    }
}