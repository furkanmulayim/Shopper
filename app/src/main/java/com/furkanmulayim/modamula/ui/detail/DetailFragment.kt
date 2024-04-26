package com.furkanmulayim.modamula.ui.detail

import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.furkanmulayim.modamula.R
import com.furkanmulayim.modamula.base.BaseFragment
import com.furkanmulayim.modamula.data.model.Product
import com.furkanmulayim.modamula.databinding.FragmentProductDetailBinding
import com.furkanmulayim.modamula.ui.detail.adapters.CompatibleSizeAdapter
import com.furkanmulayim.modamula.ui.detail.adapters.SimilarProductAdapter
import com.furkanmulayim.modamula.ui.detail.slider.ProductImageAdapter
import com.furkanmulayim.modamula.utils.onSingleClickListener
import com.furkanmulayim.modamula.utils.stringToList
import com.furkanmulayim.modamula.utils.viewVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentProductDetailBinding, DetailViewModel>() {

    private lateinit var productVP: ViewPager2
    private lateinit var sliderAdapter: ProductImageAdapter
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
        observeData(); initAdaptersAsObserver()
    }

    private fun observeData() {
        viewModel.productItem.observe(viewLifecycleOwner) { item ->
            with(binding) {
                productName.text = item.isim
                productDetail.text = item.aciklama
                productDiscountDescription.text = item.indirimAciklama
                buyToolBar.productOldPrice.text = item.oncekiFiyat + getString(R.string.tl)
                buyToolBar.productCurrentPrice.text = item.gecerliFiyat + getString(R.string.tl)
            }
            item.isim?.let { buyButtoClickListener(it) }
            initClickListeners()
            item.image?.let { stringToList(it) }?.let { setSliderImages(it) }
        }
    }

    private fun initAdaptersAsObserver() {
        viewModel.sizedList.observe(viewLifecycleOwner) {
            it?.let { sized ->
                sizeAdapter = CompatibleSizeAdapter(sized as ArrayList<String>)
                binding.compatibleSizeRcyc.layoutManager =
                    LinearLayoutManager(mcontext, LinearLayoutManager.HORIZONTAL, false)
                binding.compatibleSizeRcyc.adapter = sizeAdapter
            }
        }

        viewModel.similarList.observe(viewLifecycleOwner) {
            val similarList: ArrayList<Product> = arrayListOf()
            it?.let { similar ->
                similar.forEach { s ->
                    s
                    if (s.isNotEmpty()) {
                        if (viewModel.searchById(s.toInt()) != null) {
                            similarList.add(viewModel.searchById(s.toInt())!!)
                        }
                    }
                }
            }
            similarData(similarList)
        }
    }

    private fun similarData(list: ArrayList<Product>) {
        if (list.isNotEmpty()) {
            with(binding) {
                similarAdapter = SimilarProductAdapter(list)
                similarRcyc.layoutManager =
                    LinearLayoutManager(mcontext, LinearLayoutManager.HORIZONTAL, false)
                similarRcyc.adapter = similarAdapter
                if (similarAdapter.itemCount > 0) viewVisible(colorVariantPanel)
            }

        }
    }


    private fun setSliderImages(imageList: List<String>) {
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


    private fun sendWhatsAppMessage(phoneNumber: String, message: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("https://api.whatsapp.com/send?phone=$phoneNumber&text=$message")
        startActivity(intent)
    }


}