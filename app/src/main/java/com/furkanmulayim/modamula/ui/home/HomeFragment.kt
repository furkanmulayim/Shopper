package com.furkanmulayim.modamula.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.furkanmulayim.modamula.R
import com.furkanmulayim.modamula.base.BaseFragment
import com.furkanmulayim.modamula.data.model.Product
import com.furkanmulayim.modamula.data.model.Slider
import com.furkanmulayim.modamula.databinding.FragmentHomeBinding
import com.furkanmulayim.modamula.ui.home.slider.ImageAdapter
import com.furkanmulayim.modamula.ui.home.slider.ZoomOutPageTransformer
import com.furkanmulayim.modamula.utils.animSearch
import com.furkanmulayim.modamula.utils.animSlider
import com.furkanmulayim.modamula.utils.onSingleClickListener
import com.furkanmulayim.modamula.utils.viewGone
import com.furkanmulayim.modamula.utils.viewVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
        viewModel.getDatas(); initClicks(); observeDatas(); setScrollSettings();setAnim()
    }

    private fun setAnim() {
        binding.apply {
            animSlider(viewPager)
            animSearch(searchBar.searchbar)
        }
    }

    private fun initClicks() {
        with(binding) {
            searchBar.notificationsButton.onSingleClickListener {
                val action =
                    HomeFragmentDirections.actionHomeFragmentToNotificationFragment().actionId
                navigateTo(action)
            }

            searchBar.searchbar.onSingleClickListener {
                val navController: NavController =
                    findNavController(requireActivity(), R.id.fragmentContainerView)
                val bundle = Bundle().apply {
                    putBoolean("search", true)
                }
                navController.navigate(R.id.categoryFragment, bundle)
            }

            upToButton.onSingleClickListener {
                binding.nestedScrollView.smoothScrollTo(0, 0, 1500)
            }

            headerToolBar.setOnLongClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToPanelFragment().actionId
                navigateTo(action)
                return@setOnLongClickListener true
            }
        }
    }

    private fun observeDatas() {
        viewModel.productList.observe(viewLifecycleOwner) { plist ->
            plist?.let { list ->
                val filteredList = list.filter { it.active == 1 && it.producerSelect == 1 }
                setProductAdapter(filteredList as ArrayList<Product>)
            }
        }

        viewModel.sliderList.observe(viewLifecycleOwner) { sliderList ->
            setSlider(sliderList)
        }

    }

    private fun showProductVariants(item: Product) {
        // HIGHER ORDER FUNCTİON -> Ürün Varyant Bottom Nav Bar Açar
        val bundle = Bundle().apply {
            putString("variant_ids", item.compatibleSize.toString())
        }
        val action = HomeFragmentDirections.actionHomeFragmentToColorVariantFragment().actionId
        navigateTo(action, bundle)
    }

    private fun showProductDetails(productItemName: Product) {
        // HIGHER ORDER FUNCTİON -> Ürün Detayı Açar
        val bundle = Bundle().apply {
            putParcelable("ProductItem", productItemName)
        }
        val action = HomeFragmentDirections.actionHomeFragmentToProductDetailFragment().actionId
        navigateTo(action, bundle)
    }

    private fun setSlider(list: List<Slider>) {
        // VIEW PAGER -> Set Slider List
        adapter = ImageAdapter(requireContext(), binding.viewPager, list)
        binding.viewPager.adapter = adapter
        binding.viewPager.setPageTransformer(ZoomOutPageTransformer(5))
    }

    private fun setProductAdapter(arrayList: ArrayList<Product>) {
        productAdapter =
            HomeProductAdapter(mcontext, arrayList, ::showProductDetails, ::showProductVariants)
        binding.productRcyc.adapter = productAdapter
        binding.productRcyc.layoutManager = GridLayoutManager(mcontext, 2)
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

