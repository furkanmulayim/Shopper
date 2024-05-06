package com.furkanmulayim.modamula.ui.category

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.furkanmulayim.modamula.base.BaseFragment
import com.furkanmulayim.modamula.data.enums.CategoryName
import com.furkanmulayim.modamula.data.model.Categorie
import com.furkanmulayim.modamula.data.model.Product
import com.furkanmulayim.modamula.databinding.FragmentCategoryBinding
import com.furkanmulayim.modamula.ui.home.HomeProductAdapter
import com.furkanmulayim.modamula.utils.viewGone
import com.furkanmulayim.modamula.utils.viewMessage
import com.furkanmulayim.modamula.utils.viewVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : BaseFragment<FragmentCategoryBinding, CategoryViewModel>() {

    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var productAdapter: HomeProductAdapter
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCategoryBinding {
        return FragmentCategoryBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isFocusedBunleDataObserve(); observeDatas(); setProductAdapter(arrayListOf()); searchProduct()
    }

    private fun observeDatas() {
        viewModel.products.observe(viewLifecycleOwner) {
            it?.let {
                productAdapter.updateList(it as ArrayList<Product>)
            }
        }

        viewModel.categorie.observe(viewLifecycleOwner) {
            it?.let {
                setCategoryAdapter(it)
            }
        }
    }

    //Kategorileri Listelemek İçin Kullanılan adapter
    private fun setCategoryAdapter(categorie: List<Categorie>) {
        categoryAdapter =
            CategoryAdapter(categorie as ArrayList<Categorie>, ::categoryClickListener)
        binding.toolBar.categoryRcyc.layoutManager =
            LinearLayoutManager(mcontext, LinearLayoutManager.HORIZONTAL, false)
        binding.toolBar.categoryRcyc.adapter = categoryAdapter
    }

    //Ürün Listele
    private fun setProductAdapter(list: ArrayList<Product>) {
        val filteredList = list.filter { it.active == 1 }
        productAdapter = HomeProductAdapter(
            mcontext,
            filteredList as ArrayList<Product>,
            ::showProductDetails,
            ::showProductVariants
        )
        binding.productRcyc.adapter = productAdapter
        binding.productRcyc.layoutManager = GridLayoutManager(mcontext, 2)
    }

    //HIGHER ORDER FUNCTİON -> Tıklanan Kategoriye Göre filtered list
    private fun categoryClickListener(categoryName: String) {
        if (CategoryName.TUMU.id != categoryName)
            setProductAdapter(
                viewModel.products.value?.filter {
                    it.category == categoryName
                } as ArrayList<Product>
            )
        else setProductAdapter(viewModel.products.value as ArrayList<Product>)
    }

    private fun searchProduct() {
        binding.toolBar.searchbar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val searchText = s.toString()
                setProductAdapter(
                    viewModel.products.value?.filter { product ->
                        product.description?.contains(searchText, ignoreCase = true) ?: false ||
                                product.name?.contains(searchText, ignoreCase = true) ?: false
                    } as ArrayList<Product>)
                if (productAdapter.itemCount == 0) {
                    viewVisible(binding.emptSearchPanel)
                } else {
                    viewGone(binding.emptSearchPanel)
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    //HIGHER ORDER FUNCTİON -> Compatible Size
    private fun showProductVariants(item: Product) {
        viewMessage(mcontext, item.relatedProductId.toString())
        val act = CategoryFragmentDirections.actionCategoryFragmentToColorVariantFragment()
        navigateTo(act.actionId)
    }

    private fun showProductDetails(productItemName: Product) {
        val bundle = Bundle().apply {
            putParcelable("ProductItem", productItemName)
        }
        val action = CategoryFragmentDirections.actionCategoryFragmentToProductDetailFragment()
        navigateTo(action.actionId, bundle)
    }

    private fun isFocusedBunleDataObserve() {
        viewModel.isSearchFocused.observe(viewLifecycleOwner) {
            it.let { focus ->
                if (focus) binding.toolBar.searchbar.requestFocus()
                val inputMethodManager =
                    requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.showSoftInput(
                    binding.toolBar.searchbar,
                    InputMethodManager.SHOW_IMPLICIT
                )
            }
        }
    }
}