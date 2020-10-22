package com.mbds.newsletter.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.mbds.newsletter.R
import com.mbds.newsletter.adapters.ArticleAdapter
import com.mbds.newsletter.databinding.FragmentArticlesBinding
import com.mbds.newsletter.model.Article
import com.mbds.newsletter.model.Category
import com.mbds.newsletter.repositories.ArticleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArticlesFragment : Fragment() {

    lateinit var binding: FragmentArticlesBinding
    private var category: Category? = null
    private val articleRepository = ArticleRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            category = it.getSerializable(ARG_CATEGORY) as Category
        }

        val categoryId = category?.nameId ?: R.string.all
        activity?.setTitle(categoryId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArticlesBinding.inflate(inflater,container,false)
        lifecycleScope.launch {
            getData()
        }

        return binding.root
    }

    private suspend fun getData() {
        withContext(Dispatchers.IO)
        {
            val result = articleRepository.list(category ?: Category(0, "", ""))
            bindData(result)
        }
    }

    private suspend fun bindData(result: List<Article>) {
        withContext(Dispatchers.Main)
        {
            Log.println(Log.DEBUG,"bind data", result.toString())
            val recyclerView = binding.recyclerView
            val articleAdapter = ArticleAdapter(result)

            recyclerView.layoutManager = LinearLayoutManager(view?.context)
            recyclerView.hasFixedSize()
            recyclerView.adapter = articleAdapter
        }
    }

    companion object {
        private const val ARG_CATEGORY = "category"

        @JvmStatic
        fun newInstance(category: Category) =
            ArticlesFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_CATEGORY, category)
                }
            }
    }
}