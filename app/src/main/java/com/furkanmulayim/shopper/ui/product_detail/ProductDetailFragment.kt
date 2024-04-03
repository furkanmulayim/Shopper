package com.furkanmulayim.shopper.ui.product_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.furkanmulayim.shopper.base.BaseFragment
import com.furkanmulayim.shopper.data.model.ProductItem
import com.furkanmulayim.shopper.databinding.FragmentProductDetailBinding
import com.furkanmulayim.shopper.ui.product_detail.adapters.ColorVariantAdapter
import com.furkanmulayim.shopper.ui.product_detail.adapters.CompatibleSizeAdapter
import com.furkanmulayim.shopper.ui.product_detail.adapters.SimilarProductAdapter
import com.furkanmulayim.shopper.ui.product_detail.slider.ProductImageAdapter
import com.furkanmulayim.shopper.utils.onSingleClickListener
import com.furkanmulayim.shopper.utils.viewMessage

class ProductDetailFragment : BaseFragment<FragmentProductDetailBinding, ProductDetailViewModel>() {

    private lateinit var productVP: ViewPager2
    private lateinit var sliderAdapter: ProductImageAdapter
    private lateinit var colorVariantAdapter: ColorVariantAdapter
    private lateinit var sizeAdapter: CompatibleSizeAdapter
    private lateinit var similarAdapter: SimilarProductAdapter

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentProductDetailBinding {
        return FragmentProductDetailBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData(); initClickListeners(); initAdapters()
    }

    private fun observeData() {
        viewModel.productItem.observe(viewLifecycleOwner) { item ->
            viewMessage(mcontext, item.isim)
            setSliderImages(viewModel.imagesList)
        }
    }

    private fun setSliderImages(imageList: Array<Int>) {
        productVP = binding.productViewPager
        sliderAdapter = ProductImageAdapter(mcontext, imageList)
        binding.productViewPager.adapter = sliderAdapter
        binding.indicator.setViewPager(productVP)
    }

    private fun initClickListeners() {
        binding.likeButton.onSingleClickListener {

        }

        binding.shareButton.onSingleClickListener {

        }

        binding.backButton.onSingleClickListener {
            onBackPressed()
        }
    }

    private fun initAdapters() {
        //Renk Seçenekleri için Recycler View
        colorVariantAdapter = ColorVariantAdapter(viewModel.colorVariantList)
        binding.colorVariantRcyc.layoutManager =
            LinearLayoutManager(mcontext, LinearLayoutManager.HORIZONTAL, false)
        binding.colorVariantRcyc.adapter = colorVariantAdapter


        //Uyumlu Olduğu Bedenler için Recycler View
        sizeAdapter = CompatibleSizeAdapter(viewModel.sizeList)
        binding.compatibleSizeRcyc.layoutManager =
            LinearLayoutManager(mcontext, LinearLayoutManager.HORIZONTAL, false)
        binding.compatibleSizeRcyc.adapter = sizeAdapter


        //Benzer ürünler için Adapter
        similarAdapter = SimilarProductAdapter(viewModel.similarList)
        binding.similarRcyc.layoutManager =
            LinearLayoutManager(mcontext, LinearLayoutManager.HORIZONTAL, false)
        binding.similarRcyc.adapter = similarAdapter

    }


    private fun deneme(item: ProductItem) {

    }


    private fun deneme2(stri: String) {

    }


}