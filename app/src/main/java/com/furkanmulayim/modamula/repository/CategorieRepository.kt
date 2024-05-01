package com.furkanmulayim.modamula.repository

import androidx.lifecycle.MutableLiveData
import com.furkanmulayim.modamula.data.datasource.CategorieDataSource
import com.furkanmulayim.modamula.data.model.Categorie

class CategorieRepository(private var categorieDataSource: CategorieDataSource) {
    fun getData(): MutableLiveData<List<Categorie>> = categorieDataSource.getData()
}