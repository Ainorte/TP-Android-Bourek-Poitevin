package com.mbds.newsletter.data

import com.mbds.newsletter.R
import com.mbds.newsletter.model.Category

class Data {
    companion object {
        fun getCategoryList() = listOf<Category>(
                Category(R.string.politic,"https://live.staticflickr.com/6053/7003137491_6fdea157f6_b.jpg"),
                Category(R.string.economy, "https://cdn.pixabay.com/photo/2017/08/16/15/28/stock-exchange-2648118_1280.jpg"),
                Category(R.string.education, "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcS-yXHazNZII-J47VvcZxYZyl8ees-SoQcFrw&usqp=CAU"),
                Category(R.string.pandemic,"https://etaples-sur-mer.fr/wp-content/uploads/2020/03/coronavirus-4914026_960_720.jpg"),
                Category(R.string.sciences, "https://cdn.pixabay.com/photo/2016/02/06/09/56/science-1182713_1280.jpg"),
                Category(R.string.ecology, "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTo3G8ojfWSwUpfbOD5iTgVbTvAoNFtQosyUA&usqp=CAU"))
    }
}