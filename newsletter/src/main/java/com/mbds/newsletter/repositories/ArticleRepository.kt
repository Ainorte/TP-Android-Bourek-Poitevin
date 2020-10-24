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
                .add("Authorization", "Bearer 63f085dd32274d2daaa83d357bfb74a4")
                .build()
            request = request
                .newBuilder()
                .headers(headers)
                .build()
            return chain.proceed(request)
        }

    }

    fun list(category: Category): List<Article> {
        val response =  service.list(category.key).execute()
        return response.body()?.articles?.toList() ?: emptyList()
    }
}