package com.furkanmulayim.shopper.ui.product_detail

import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.furkanmulayim.shopper.base.BaseFragment
import com.furkanmulayim.shopper.data.model.Product
import com.furkanmulayim.shopper.databinding.FragmentProductDetailBinding
import com.furkanmulayim.shopper.ui.product_detail.adapters.ColorVariantAdapter
import com.furkanmulayim.shopper.ui.product_detail.adapters.CompatibleSizeAdapter
import com.furkanmulayim.shopper.ui.product_detail.adapters.SimilarProductAdapter
import com.furkanmulayim.shopper.ui.product_detail.slider.ProductImageAdapter
import com.furkanmulayim.shopper.utils.onSingleClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
        binding.buyToolBar.productOldPrice.paintFlags =
            binding.buyToolBar.productOldPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        observeData(); initAdapters()
    }

    private fun observeData() {
        viewModel.productItem.observe(viewLifecycleOwner) { item ->
            with(binding) {
                productName.text = item.isim
                productDetail.text = item.aciklama
                buyToolBar.productOldPrice.text = item.oncekiFiyat
                buyToolBar.productCurrentPrice.text = item.gecerliFiyat
            }
            item.isim?.let { buyButtoClickListener(it) }
            initClickListeners()
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

    private fun buyButtoClickListener(description: String) {
        binding.buyToolBar.productBuyButton.onSingleClickListener {
            sendWhatsAppMessage("+905344533008", description)
        }
    }

    private fun initAdapters() {
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

    private fun deneme(item: Product) {

    }


    private fun deneme2(stri: String) {

    }

    fun sendWhatsAppMessage(phoneNumber: String, message: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("https://api.whatsapp.com/send?phone=$phoneNumber&text=$message")
        startActivity(intent)
    }


}