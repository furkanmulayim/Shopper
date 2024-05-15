package com.furkanmulayim.modamula.ui.home.modal_bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.furkanmulayim.modamula.R
import com.furkanmulayim.modamula.databinding.FragmentColorVariantBinding
import com.furkanmulayim.modamula.ui.detail.adapters.CompatibleSizeAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ColorVariantFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentColorVariantBinding
    private val viewModel: ColorVariantViewModel by viewModels()
    private lateinit var sizeAdapter: CompatibleSizeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_color_variant, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.stringList.observe(viewLifecycleOwner) {
            setCompatibleSizeList(it)
        }
    }

    private fun setCompatibleSizeList(compSizeList: List<String>?) {
        compSizeList?.let {
            sizeAdapter = CompatibleSizeAdapter(it)
            binding.sizeRcyc.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            binding.sizeRcyc.adapter = sizeAdapter
        }
    }

}