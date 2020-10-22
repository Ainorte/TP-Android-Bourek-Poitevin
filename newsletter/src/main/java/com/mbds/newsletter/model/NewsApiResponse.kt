package com.mbds.newsletter.model

import java.io.Serializable

data class NewsApiResponse(val status: String, val totalResults: Int, val articles: Array<Article>): Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as NewsApiResponse

        if (status != other.status) return false
        if (totalResults != other.totalResults) return false
        if (!articles.contentEquals(other.articles)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = status.hashCode()
        result = 31 * result + totalResults
        result = 31 * result + articles.contentHashCode()
        return result
    }
}