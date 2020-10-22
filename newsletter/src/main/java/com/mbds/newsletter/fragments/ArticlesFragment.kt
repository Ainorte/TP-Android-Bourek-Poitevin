package com.mbds.newsletter.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mbds.newsletter.R
import com.mbds.newsletter.databinding.FragmentArticlesBinding
import com.mbds.newsletter.model.Category
import com.mbds.newsletter.tools.getName

class ArticlesFragment : Fragment() {

    lateinit var binding: FragmentArticlesBinding

    private var category: Category? = null

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
        return binding.root
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