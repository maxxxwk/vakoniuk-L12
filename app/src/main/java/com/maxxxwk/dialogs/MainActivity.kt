package com.maxxxwk.dialogs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.maxxxwk.dialogs.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout> by lazy {
        BottomSheetBehavior.from(
            binding.bottomSheet.bottomSheetParentContainer
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupBottomSheet()
        setupListeners()
    }

    private fun setupBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupListeners() {
        binding.buttonShowAlertDialog.setOnClickListener {
            showAlertDialog()
        }
        binding.buttonShowDialogFragment.setOnClickListener {
            showDialogFragment()
        }
        binding.buttonShowBottomSheet.setOnClickListener {
            showBottomSheet()
        }
        binding.buttonShowBottomSheetFragment.setOnClickListener {
            showBottomSheetFragment()
        }
    }

    private fun showAlertDialog() {
        AlertDialog.Builder(this)
            .setTitle(R.string.alert_dialog_title)
            .setMessage(R.string.alert_dialog_message)
            .setIcon(R.drawable.ic_launcher_foreground)
            .setPositiveButton(R.string.alert_dialog_positive_button_text) { dialog, which ->
                val text = "${getString(R.string.alert_dialog_positive_button_text)} clicked"
                Toast.makeText(this, text, Toast.LENGTH_LONG).show()
            }
            .setNegativeButton(R.string.alert_dialog_negative_button_text) { dialog, which ->
                val text = "${getString(R.string.alert_dialog_negative_button_text)} clicked"
                Toast.makeText(this, text, Toast.LENGTH_LONG).show()
            }
            .show()
    }

    private fun showDialogFragment() {
        val dialog = DialogFragmentExample.newInstance { text ->
            Toast.makeText(this, text, Toast.LENGTH_LONG).show()
        }
        supportFragmentManager.beginTransaction()
            .add(dialog, "TAG")
            .commitAllowingStateLoss()
    }

    private fun setupBottomSheet() {
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                var text = "Bottom sheet state - "
                when (newState) {
                    BottomSheetBehavior.STATE_HIDDEN -> text += "HIDDEN"
                    BottomSheetBehavior.STATE_COLLAPSED -> text += "COLLAPSED"
                    BottomSheetBehavior.STATE_DRAGGING -> text += "DRAGGING"
                    BottomSheetBehavior.STATE_EXPANDED -> text += "EXPANDED"
                    BottomSheetBehavior.STATE_SETTLING -> text += "SETTLING"
                }
                Toast.makeText(this@MainActivity, text, Toast.LENGTH_SHORT).show()
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                Log.d("LOG_TAG", "BottomSheet onSlide: $slideOffset")
            }

        })
    }

    private fun showBottomSheet() {
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    private fun showBottomSheetFragment() {
        supportFragmentManager.beginTransaction()
            .add(BottomSheetFragment.newInstance(), "TAG")
            .commitAllowingStateLoss()
    }
}