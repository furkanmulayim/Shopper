package com.furkanmulayim.shopper.ui.home.modal_bottom_sheet

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.furkanmulayim.shopper.R
import com.furkanmulayim.shopper.databinding.FragmentColorVariantBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ColorVariantFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentColorVariantBinding
    private val viewModel: ColorVariantViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_color_variant, container, false)
        return binding.root
    }
}