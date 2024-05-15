package com.furkanmulayim.modamula.ui.detail

import android.content.Intent
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.furkanmulayim.modamula.R
import com.furkanmulayim.modamula.base.BaseFragment
import com.furkanmulayim.modamula.data.model.Product
import com.furkanmulayim.modamula.databinding.FragmentProductDetailBinding
import com.furkanmulayim.modamula.ui.detail.adapters.ColorVariantAdapter
import com.furkanmulayim.modamula.ui.detail.adapters.CompatibleSizeAdapter
import com.furkanmulayim.modamula.ui.detail.slider.ProductImageAdapter
import com.furkanmulayim.modamula.utils.discountCalculate
import com.furkanmulayim.modamula.utils.onSingleClickListener
import com.furkanmulayim.modamula.utils.stringToList
import com.furkanmulayim.modamula.utils.viewGone
import com.furkanmulayim.modamula.utils.viewMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentProductDetailBinding, DetailViewModel>() {

    private lateinit var productVP: ViewPager2
    private lateinit var sliderAdapter: ProductImageAdapter
    private lateinit var sizeAdapter: CompatibleSizeAdapter
    private lateinit var colorsAdapter: ColorVariantAdapter
    private var isProductFav: Boolean = false

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
        observeData(); savedObserver()
    }

    private fun savedObserver() {
        viewModel.favItem.observe(viewLifecycleOwner) {
            it?.let {
                if (it.id != null) {
                    val foreground =
                        AppCompatResources.getDrawable(mcontext, R.drawable.favorie_red_heart)
                    binding.likeButton.foreground = foreground
                    isProductFav = true
                }
            }
        }
    }

    private fun observeData() {
        viewModel.productItem.observe(viewLifecycleOwner) { item ->
            applyProductDetails(item)
            viewModel.productImages.value = item.image

            item.name?.let { buyButtoClickListener(it) }
            initClickListeners()

            val imageList = item.image?.let { stringToList(it) }
            if (imageList != null) {
                setSliderImages(imageList)
            }

            val compatibleSizeList = item.compatibleSize?.let { stringToList(it) }
            if (compatibleSizeList != null) {
                setCompatibleSizeList(compatibleSizeList)
            }

            val colors = item.color?.let { stringToList(it) }
            if (colors != null) {
                setColors(colors)
            }

        }
    }

    private fun setSliderImages(imageList: List<String>?) {
        imageList?.let {
            productVP = binding.productViewPager
            sliderAdapter =
                ProductImageAdapter(requireContext(), it)
            binding.productViewPager.adapter = sliderAdapter
            binding.indicator.setViewPager(productVP)
        }
    }

    private fun setCompatibleSizeList(compSizeList: List<String>?) {
        compSizeList?.let {
            sizeAdapter = CompatibleSizeAdapter(it)
            binding.compatibleSizeRcyc.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            binding.compatibleSizeRcyc.adapter = sizeAdapter
        }
    }

    private fun setColors(colors: List<String>) {
        colors.let {
            colorsAdapter = ColorVariantAdapter(it)
            binding.colorsRcyc.layoutManager =
                LinearLayoutManager(mcontext, LinearLayoutManager.HORIZONTAL, false)
            binding.colorsRcyc.adapter = colorsAdapter
        }
    }

    private fun applyProductDetails(item: Product) {
        with(binding) {
            apply {
                if (item.favorite == 1) {
                    viewGone(likeButton)
                }
                productName.text = item.name
                productDetail.text = item.description
                productDiscountDescription.text = item.discDesc
                if (item.cargoPrice == 0) {
                    cargoPrice.text = mcontext.getString(R.string.cargo_not_free)
                } else {
                    cargoPrice.text = mcontext.getString(R.string.cargo_free)
                }
                cargoPrice
                val oldPrice = "${item.beforePrice} TL"
                val newPrice = "${item.currentPrice} TL"
                buyToolBar.productOldPrice.text = oldPrice
                buyToolBar.productCurrentPrice.text = newPrice
                indirimYuzde.text = item.beforePrice?.toDoubleOrNull()?.let { beforePrice ->
                    item.currentPrice?.toDoubleOrNull()?.let { currentPrice ->
                        discountCalculate(beforePrice, currentPrice)
                    }
                }
            }
        }
    }


    private fun initClickListeners() {
        binding.likeButton.onSingleClickListener {
            viewModel.productItem.value?.let { prod ->
                if (prod.id != null) {
                    val f: Drawable?
                    if (isProductFav) {
                        isProductFav = false
                        f = AppCompatResources.getDrawable(
                            mcontext, R.drawable.favorie_line_heart
                        )
                        viewModel.deleteSingleProducct(prod.id!!)
                    } else {
                        isProductFav = true
                        f = AppCompatResources.getDrawable(
                            mcontext, R.drawable.favorie_red_heart
                        )
                        viewModel.saveFavoriProduct(prod)
                    }
                    if (f != null) binding.likeButton.foreground = f
                } else {
                    viewMessage(mcontext, getString(R.string.saved_hata))
                }
            }
        }
        binding.shareButton.onSingleClickListener {}
        binding.backButton.onSingleClickListener { onBackPressed() }
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