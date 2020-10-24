
package com.mbds.newsletter.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.mbds.newsletter.R
import com.mbds.newsletter.adapters.ArticleAdapter
import com.mbds.newsletter.databinding.FragmentArticlesBinding
import com.mbds.newsletter.model.Article
import com.mbds.newsletter.model.Category
import com.mbds.newsletter.repositories.ArticleRepository
import com.mbds.newsletter.tools.isOnline
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArticlesFragment : Fragment() {

    lateinit var binding: FragmentArticlesBinding
    private var category: Category? = null
    private val articleRepository = ArticleRepository()
    private var error_snackbar: Snackbar? = null

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
        binding = FragmentArticlesBinding.inflate(inflater, container, false)

        launchQuery()

        return binding.root
    }

    private fun launchQuery() {
        lifecycleScope.launch {
            binding.recyclerView.visibility = View.INVISIBLE
            binding.progressBar.show()
            getData()
        }
    }

    private suspend fun getData() {
        withContext(Dispatchers.IO)
        {
            if (context?.isOnline() == true) {
                val result = articleRepository.list(category ?: Category(0, "", ""))
                if (result.isNotEmpty()) {
                    bindData(result)
                } else {
                    displayError(R.string.request_error)
                }
            } else {
                displayError(R.string.no_internet_error)
            }
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

            binding.progressBar.hide()
            binding.recyclerView.visibility = View.VISIBLE
        }
    }

    private suspend fun displayError(@StringRes errorId: Int) {
        withContext(Dispatchers.Main)
        {
            error_snackbar = Snackbar.make(
                binding.progressBar,
                errorId,
                BaseTransientBottomBar.LENGTH_INDEFINITE
            )
            error_snackbar?.setAction(R.string.retry) {
                launchQuery()
            }
            error_snackbar?.show()

            binding.recyclerView.visibility = View.INVISIBLE
            binding.progressBar.hide()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        error_snackbar?.dismiss()
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