package com.mbds.newsletter.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mbds.newsletter.R
import com.mbds.newsletter.adapters.CategoryAdapter
import com.mbds.newsletter.model.Category

class CategoriesFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
        val categories = listOf<Category>(
            Category("Politique","https://picsum.photos/300/200"),
            Category("Faits Divers", "https://picsum.photos/300/200"),
            Category("Culture", "https://picsum.photos/300/200"),
            Category("Politique","https://picsum.photos/300/200"),
            Category("Faits Divers", "https://picsum.photos/300/200"),
            Category("Culture", "https://picsum.photos/300/200"),
            Category("Politique","https://picsum.photos/300/200"),
            Category("Faits Divers", "https://picsum.photos/300/200"),
            Category("Culture", "https://picsum.photos/300/200"),
            Category("Politique","https://picsum.photos/300/200"),
            Category("Faits Divers", "https://picsum.photos/300/200"),
            Category("Culture", "https://picsum.photos/300/200"))

        val categoriesAdapter = CategoryAdapter(categories)
        recyclerView.layoutManager = GridLayoutManager(view.context, 2)
        recyclerView.hasFixedSize()
        recyclerView.adapter = categoriesAdapter
    }
}