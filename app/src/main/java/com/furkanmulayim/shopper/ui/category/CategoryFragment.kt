package com.furkanmulayim.shopper.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.furkanmulayim.shopper.base.BaseFragment
import com.furkanmulayim.shopper.data.enums.CategoryName
import com.furkanmulayim.shopper.data.model.ProductItem
import com.furkanmulayim.shopper.databinding.FragmentCategoryBinding
import com.furkanmulayim.shopper.ui.category.adapters.CategoryAdapter
import com.furkanmulayim.shopper.ui.category.adapters.CategoryProductAdapter
import com.furkanmulayim.shopper.utils.viewMessage

class CategoryFragment : BaseFragment<FragmentCategoryBinding, CategoryViewModel>() {

    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var productAdapter: CategoryProductAdapter
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCategoryBinding {
        return FragmentCategoryBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setCategoryAdapter(); setProductAdapter()
    }

    private fun setCategoryAdapter() {
        categoryAdapter = CategoryAdapter(viewModel.categories, ::categoryClickListener)
        binding.toolBar.categoryRcyc.layoutManager =
            LinearLayoutManager(mcontext, LinearLayoutManager.HORIZONTAL, false)
        binding.toolBar.categoryRcyc.adapter = categoryAdapter
    }

    private fun categoryClickListener(categoryName: String) {
        when (categoryName) {
            CategoryName.TUMU.id -> {
                productAdapter.updateList(viewModel.products)
            }

            CategoryName.JIK.id -> {
                productAdapter.updateList(viewModel.products2)
            }

            CategoryName.BEBEK.id -> {
                productAdapter.updateList(viewModel.products1)
            }
        }
    }

    private fun setProductAdapter() {
        productAdapter = CategoryProductAdapter(
            mcontext,
            viewModel.products,
            ::showProductDetails,
            ::showProductVariants
        )
        binding.productRcyc.adapter = productAdapter
        binding.productRcyc.layoutManager = GridLayoutManager(mcontext, 2)
    }

    private fun showProductVariants(productItemVariants: String) {
        viewMessage(mcontext, productItemVariants)
        val act = CategoryFragmentDirections.actionCategoryFragmentToColorVariantFragment()
        navigateTo(act.actionId)
    }

    private fun showProductDetails(productItemName: ProductItem) {
        val bundle = Bundle().apply {
            putParcelable("ProductItem", productItemName)
        }
        val action = CategoryFragmentDirections.actionCategoryFragmentToProductDetailFragment()
        navigateTo(action.actionId, bundle)
    }
}