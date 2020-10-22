package com.mbds.newsletter.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mbds.newsletter.R
import com.mbds.newsletter.databinding.ItemArticleBinding
import com.mbds.newsletter.model.Article
import com.mbds.newsletter.tools.setImageFromUrl

class ArticleAdapter (private val dataSet: List<Article>)
    : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    class ViewHolder(private val root: View) : RecyclerView.ViewHolder(root) {

        internal lateinit var binding: ItemArticleBinding

        fun bind(item: Article) {
            /*
            binding.categoryName.text = item.getName(root.context)
            binding.categoryItem.setOnClickListener {
                val mainActivity = (root.context as MainActivity)
                mainActivity.changeFragment(ArticlesFragment.newInstance(item))
            }*/

            binding.articleTitle.text = item.title
            binding.articleImage.setImageFromUrl(item.urlToImage, root)
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