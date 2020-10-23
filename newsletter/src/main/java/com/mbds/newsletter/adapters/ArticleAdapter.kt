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

class ArticleAdapter (private val dataSet: List<Article>)
    : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    class ViewHolder(private val root: View) : RecyclerView.ViewHolder(root) {

        internal lateinit var binding: ItemArticleBinding

        fun bind(item: Article) {
            binding.articleTitle.text = item.title
            binding.articleDescription.text = item.description
            binding.articleSource.text = item.source.name
            binding.articleDate.text = DateUtils.getRelativeTimeSpanString(item.publishedAt.time, Date().time, DateUtils.MINUTE_IN_MILLIS,FORMAT_ABBREV_RELATIVE)
            if(!item.urlToImage.isNullOrBlank())
                binding.articleImage.setImageFromUrl(item.urlToImage, R.drawable.placeholder_article, root)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rootView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_article, parent, false)
        val viewHolder = ViewHolder(rootView)
        viewHolder.binding = ItemArticleBinding.bind(rootView)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount(): Int = dataSet.size

}