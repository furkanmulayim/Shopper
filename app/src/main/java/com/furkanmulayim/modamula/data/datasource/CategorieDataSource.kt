package com.furkanmulayim.modamula.data.datasource

import androidx.lifecycle.MutableLiveData
import com.furkanmulayim.modamula.data.model.Categorie
import com.google.firebase.firestore.CollectionReference

class CategorieDataSource(
    private val collectionProduct: CollectionReference,
) {
    private var categorieList = MutableLiveData<List<Categorie>>()

    fun getData(): MutableLiveData<List<Categorie>> {
        collectionProduct.addSnapshotListener { value, error ->
            if (value != null) {
                val list = ArrayList<Categorie>()
                for (i in value.documents) {
                    val categ = i.toObject(Categorie::class.java)
                    if (categ != null) {
                        list.add(categ)
                    }
                }
                categorieList.value = list
            }
        }
        return categorieList
    }
}