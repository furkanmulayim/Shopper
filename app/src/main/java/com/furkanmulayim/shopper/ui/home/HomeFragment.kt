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
import com.furkanmulayim.shopper.utils.viewMessage

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
        setSlider()
        initClicks()
        setProductAdapter()
    }

    private fun setSlider() {
        adapter = ImageAdapter(requireContext(), binding.viewPager)
        binding.viewPager.adapter = adapter
        binding.viewPager.setPageTransformer(ZoomOutPageTransformer(5))
    }

    private fun setProductAdapter() {

        productAdapter = HomeProductAdapter(mcontext, sil(), ::selectedProductItem)
        binding.productRcyc.adapter = productAdapter
        val layoutManager = GridLayoutManager(mcontext,2)
        binding.productRcyc.layoutManager = layoutManager
    }

    private fun selectedProductItem(productItemName: String) {
        viewMessage(mcontext, productItemName)
    }

    private fun initClicks() {
        binding.searchBar.notificationsButton.onSingleClickListener {
            val act = HomeFragmentDirections.actionHomeFragmentToNotificationFragment()
            navigateTo(act.actionId)
        }
    }

    private fun sil ():ArrayList<ProductItem>{
           val deneme = arrayListOf<ProductItem>(
            ProductItem(
                yeniMi = true,
                indirimAciklama = "Ramazana Özel %50",
                renkleri = 5,
                adi = "Moda Ürünü",
                eskiFiyat = "150₺",
                yeniFiyat = "75₺",
                indirimOrani = "%50",
                isKargo = true
            ), ProductItem(
                yeniMi = true,
                indirimAciklama = "Ramazana Deneme Özel %50",
                renkleri = 5,
                adi = "Moda Ürünü",
                eskiFiyat = "150₺",
                yeniFiyat = "75₺",
                indirimOrani = "%50",
                isKargo = true
            ), ProductItem(
                yeniMi = true,
                indirimAciklama = "Ramazana Özel %50",
                renkleri = 5,
                adi = "Moda Ürünü",
                eskiFiyat = "150₺",
                yeniFiyat = "75₺",
                indirimOrani = "%50",
                isKargo = true
            ), ProductItem(
                yeniMi = true,
                indirimAciklama = "Ramazana Özel %50",
                renkleri = 5,
                adi = "Moda Ürünü",
                eskiFiyat = "150₺",
                yeniFiyat = "75₺",
                indirimOrani = "%50",
                isKargo = true
            ), ProductItem(
                yeniMi = true,
                indirimAciklama = "Ramazana Özel %50",
                renkleri = 5,
                adi = "Moda Ürünü",
                eskiFiyat = "150₺",
                yeniFiyat = "75₺",
                indirimOrani = "%50",
                isKargo = true
            ), ProductItem(
                yeniMi = true,
                indirimAciklama = "Ramazana Özel %50",
                renkleri = 5,
                adi = "Moda Ürünü",
                eskiFiyat = "150₺",
                yeniFiyat = "75₺",
                indirimOrani = "%50",
                isKargo = true
            )
        )
        return deneme
    }
}