package com.maxxxwk.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.maxxxwk.dialogs.databinding.DialogFragmentExampleBinding

class DialogFragmentExample : DialogFragment() {

    private lateinit var binding: DialogFragmentExampleBinding
    private lateinit var resultCallback: (String) -> (Unit)

    companion object {
        fun newInstance(resultCallback: (String) -> Unit): DialogFragmentExample {
            val dialog = DialogFragmentExample()
            dialog.resultCallback = resultCallback
            return dialog
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogFragmentExampleBinding.inflate(inflater, container, false)
        setupListeners()
        return binding.root
    }

    private fun setupListeners() {
        binding.buttonPositive.setOnClickListener {
            resultCallback.invoke(binding.editText.text.toString())
            dismiss()
        }
    }

}