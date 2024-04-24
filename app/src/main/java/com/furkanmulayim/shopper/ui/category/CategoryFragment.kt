package com.furkanmulayim.shopper.ui.category

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.furkanmulayim.shopper.base.BaseFragment
import com.furkanmulayim.shopper.data.enums.CategoryName
import com.furkanmulayim.shopper.data.model.Product
import com.furkanmulayim.shopper.databinding.FragmentCategoryBinding
import com.furkanmulayim.shopper.ui.home.HomeProductAdapter
import com.furkanmulayim.shopper.utils.viewMessage
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
        isFocusedBunleDataObserve(); observeData()
    }

    private fun observeData() {
        viewModel.products.observe(viewLifecycleOwner) {
            it?.let {
                setCategoryAdapter()
                setProductAdapter(it as ArrayList<Product>)
            }
        }
    }

    //Kategorileri Listelemek İçin Kullanılan adapter
    private fun setCategoryAdapter() {
        categoryAdapter = CategoryAdapter(viewModel.categories, ::categoryClickListener)
        binding.toolBar.categoryRcyc.layoutManager =
            LinearLayoutManager(mcontext, LinearLayoutManager.HORIZONTAL, false)
        binding.toolBar.categoryRcyc.adapter = categoryAdapter
    }

    //Ürünleri Listelemek İçin Kullanılan adapter
    private fun setProductAdapter(list: ArrayList<Product>) {
        productAdapter = HomeProductAdapter(
            mcontext,
            list,
            ::showProductDetails,
            ::showProductVariants
        )
        binding.productRcyc.adapter = productAdapter
        binding.productRcyc.layoutManager = GridLayoutManager(mcontext, 2)
    }

    //Kategorilerde tıklanan itemler için kullanılan higher order fonks.
    private fun categoryClickListener(categoryName: String) {
        when (categoryName) {
            CategoryName.TUMU.id -> {
                //productAdapter.updateList(viewModel.products)
            }

            CategoryName.JIK.id -> {
                //productAdapter.updateList(viewModel.products2)
            }

            CategoryName.BEBEK.id -> {
                //productAdapter.updateList(viewModel.products1)
            }
        }
    }


    private fun showProductVariants(productItemVariants: String) {
        viewMessage(mcontext, productItemVariants)
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