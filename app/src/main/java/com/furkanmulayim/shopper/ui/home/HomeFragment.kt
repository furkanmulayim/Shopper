package com.furkanmulayim.shopper.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.furkanmulayim.shopper.base.BaseFragment
import com.furkanmulayim.shopper.data.model.ProductItem
import com.furkanmulayim.shopper.databinding.FragmentHomeBinding
import com.furkanmulayim.shopper.ui.home.slider.ImageAdapter
import com.furkanmulayim.shopper.ui.home.slider.ZoomOutPageTransformer
import com.furkanmulayim.shopper.utils.onSingleClickListener
import com.furkanmulayim.shopper.utils.viewGone
import com.furkanmulayim.shopper.utils.viewMessage
import com.furkanmulayim.shopper.utils.viewVisible

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    private lateinit var adapter: ImageAdapter
    private lateinit var productAdapter: HomeProductAdapter

    override fun getFragmentBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSlider(); initClicks(); setProductAdapter(); setScrollSettings()
    }

    private fun setSlider() {
        adapter = ImageAdapter(requireContext(), binding.viewPager)
        binding.viewPager.adapter = adapter
        binding.viewPager.setPageTransformer(ZoomOutPageTransformer(5))
    }

    private fun setProductAdapter() {
        productAdapter =
            HomeProductAdapter(
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
        val act = HomeFragmentDirections.actionHomeFragmentToColorVariantFragment()
        navigateTo(act.actionId)
    }

    private fun showProductDetails(productItemName: ProductItem) {
        val bundle = Bundle().apply {
            putParcelable("ProductItem", productItemName)
        }
        val action = HomeFragmentDirections.actionHomeFragmentToProductDetailFragment()
        navigateTo(action.actionId, bundle)
    }

    private fun initClicks() {
        binding.searchBar.notificationsButton.onSingleClickListener {
            val act = HomeFragmentDirections.actionHomeFragmentToNotificationFragment()
            navigateTo(act.actionId)
        }

        binding.upToButton.onSingleClickListener {
            binding.nestedScrollView.smoothScrollTo(0, 0, 1500)
        }
    }

    private fun setScrollSettings() {
        binding.nestedScrollView.setOnScrollChangeListener { _, _, scrollY, _, _ ->
            if (scrollY == 0) {
                viewGone(binding.upToButton)
            } else {
                viewVisible(binding.upToButton)
            }
        }
    }
}