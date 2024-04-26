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
        initClicks(); observeData(); observSlider(); setScrollSettings();deneme()
    }

    private fun setSlider(list: List<Slider>) {
        adapter = ImageAdapter(requireContext(), binding.viewPager, list)
        binding.viewPager.adapter = adapter
        binding.viewPager.setPageTransformer(ZoomOutPageTransformer(5))
    }

    private fun setProductAdapter(arrayList: ArrayList<Product>) {
        productAdapter =
            HomeProductAdapter(
                mcontext,
                arrayList,
                ::showProductDetails,
                ::showProductVariants
            )
        binding.productRcyc.adapter = productAdapter
        binding.productRcyc.layoutManager = GridLayoutManager(mcontext, 2)
    }

    private fun observSlider() {
        viewModel.sliderList.observe(viewLifecycleOwner) {
            it?.let {
                setSlider(it)
            }
        }
    }

    private fun observeData() {
        viewModel.productList.observe(viewLifecycleOwner) { plist ->
            plist?.let { list ->
                setProductAdapter(list.filter { it.isAktif == 1 } as ArrayList<Product>)
            }
        }
    }

    private fun showProductVariants(productItemVariants: String) {
        val bundle = Bundle().apply {
            putString("variant_ids", productItemVariants)
        }
        val action = HomeFragmentDirections.actionHomeFragmentToColorVariantFragment().actionId
        navigateTo(actionId = action, bundle = bundle)
    }

    private fun showProductDetails(productItemName: Product) {
        val bundle = Bundle().apply {
            putParcelable("ProductItem", productItemName)
        }
        val action = HomeFragmentDirections.actionHomeFragmentToProductDetailFragment().actionId
        navigateTo(actionId = action, bundle = bundle)
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

    private fun setScrollSettings() {
        binding.nestedScrollView.setOnScrollChangeListener { _, _, scrollY, _, _ ->
            if (scrollY == 0) {
                viewGone(binding.upToButton)
            } else {
                viewVisible(binding.upToButton)
            }
        }
    }

    private fun deneme() {
        viewModel.denek.observe(viewLifecycleOwner) {
            it?.let { x ->
                println("LOGDF" + x.size)
            }
        }
    }
}

