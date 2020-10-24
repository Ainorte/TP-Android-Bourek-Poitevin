package com.mbds.newsletter.adapters

import android.text.format.DateUtils
import android.text.format.DateUtils.FORMAT_ABBREV_RELATIVE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mbds.newsletter.R
import com.mbds.newsletter.databinding.ItemArticleBinding
import com.mbds.newsletter.model.Article
import com.mbds.newsletter.tools.setImageFromUrl
import java.util.*

class ArticleAdapter(val dataSet: MutableList<Article?>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val LOADING_ITEM = 0
        private const val ARTICLE_ITEM = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == LOADING_ITEM) {
            val rootView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_loading, parent, false)

            return LoadingViewHolder(rootView)
        } else {
            val rootView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_article, parent, false)

            val viewHolder = ArticleViewHolder(rootView)
            viewHolder.binding = ItemArticleBinding.bind(rootView)
            return viewHolder
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ArticleViewHolder)
            dataSet[position]?.let { holder.bind(it) }
    }

    override fun getItemCount() = dataSet.size

    override fun getItemViewType(position: Int) =
        if (dataSet[position] == null) LOADING_ITEM else ARTICLE_ITEM

    class ArticleViewHolder(private val root: View) : RecyclerView.ViewHolder(root) {

        internal lateinit var binding: ItemArticleBinding

        fun bind(item: Article) {
            binding.articleTitle.text = item.title
            binding.articleDescription.text = item.description
            binding.articleSource.text = item.source.name
            binding.articleDate.text = DateUtils.getRelativeTimeSpanString(
                item.publishedAt.time,
                Date().time,
                DateUtils.MINUTE_IN_MILLIS,
                FORMAT_ABBREV_RELATIVE
            )
            if (!item.urlToImage.isNullOrBlank())
                binding.articleImage.setImageFromUrl(
                    item.urlToImage,
                    R.drawable.placeholder_article,
                    root
                )
        }
    }

    class LoadingViewHolder(root: View) : RecyclerView.ViewHolder(root)

}