package com.maxxxwk.dialogs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.maxxxwk.dialogs.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
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
}