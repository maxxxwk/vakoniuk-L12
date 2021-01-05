package com.maxxxwk.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.maxxxwk.dialogs.databinding.BottomSheetFragmentBinding

class BottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: BottomSheetFragmentBinding

    companion object {
        fun newInstance() = BottomSheetFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomSheetFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
}