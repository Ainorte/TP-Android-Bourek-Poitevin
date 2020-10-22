package com.mbds.newsletter.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.mbds.newsletter.adapters.CategoryAdapter
import com.mbds.newsletter.data.Data
import com.mbds.newsletter.databinding.FragmentCategoriesBinding
import com.mbds.newsletter.R

class CategoriesFragment: Fragment() {

    lateinit var binding: FragmentCategoriesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //On creer la liste des categories et on l'affiche dans le fragment

        val recyclerView = binding.recyclerView
        val categories = Data.getCategoryList()
        val categoriesAdapter = CategoryAdapter(categories)

        recyclerView.layoutManager = GridLayoutManager(view.context, 2)
        recyclerView.hasFixedSize()
        recyclerView.adapter = categoriesAdapter

        activity?.setTitle(R.string.categories)
    }
}