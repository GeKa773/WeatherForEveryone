package com.gekaradchenko.weatherforeveryone

import android.Manifest
import android.app.Application
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity

import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import java.text.SimpleDateFormat
import java.util.*




object UnitSome {
    fun showDialog(context: Context) {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.custom_dialog)
        dialog.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.setCancelable(true)
        val dialogPrivateButton = dialog.findViewById<Button>(R.id.dialogPrivateButton)
        val dialogTermButton = dialog.findViewById<Button>(R.id.dialogTermButton)
        dialogPrivateButton.setOnClickListener {
            Toast.makeText(context, "Private policy", Toast.LENGTH_SHORT).show()
            dialog.cancel()
        }

        dialogTermButton.setOnClickListener {
            Toast.makeText(context, "Term of using", Toast.LENGTH_SHORT).show()
            dialog.cancel()
        }
        dialog.show()
    }








}
