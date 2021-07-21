package com.gekaradchenko.weatherforeveryone.viewpager.pagefragments

import android.app.Application
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import com.gekaradchenko.weatherforeveryone.MainActivity
import com.gekaradchenko.weatherforeveryone.R
import com.gekaradchenko.weatherforeveryone.lifecycle.SingleLiveEvent

class PageViewModel(application: Application) : AndroidViewModel(application) {
    val app = application

    private val _navigationEvent = MutableLiveData<Boolean>()
    val navigationEvent: LiveData<Boolean> = _navigationEvent

    fun onNavigateEvent() {
        _navigationEvent.value = true
    }

    fun onNavigateEventComplete() {
        _navigationEvent.value = false
    }

    private val _toastShow = SingleLiveEvent<String>()
    val toastShow: LiveData<String>
        get() = _toastShow

    private val _dialogShow = SingleLiveEvent<Boolean>()
    val dialogShow: LiveData<Boolean>
        get() = _dialogShow


    fun onShowDialog() {
        _dialogShow.postValue(true)
    }

     fun showToastPrivetPolicy() {
        _toastShow.postValue(app.getString(R.string.privacy_policy))
    }

     fun showToastTermOfUsing() {
        _toastShow.postValue(app.getString(R.string.term_of_using))
    }



    // !!! ??? !!!
//    fun showCustomDialog(context: Context) {
//
//        val dialog = Dialog(context)
//        dialog.setContentView(R.layout.custom_dialog)
//        dialog.window!!.setLayout(
//            ViewGroup.LayoutParams.MATCH_PARENT,
//            ViewGroup.LayoutParams.WRAP_CONTENT
//        )
//        dialog.setCancelable(true)
//        val dialogPrivateButton = dialog.findViewById<Button>(R.id.dialogPrivateButton)
//        val dialogTermButton = dialog.findViewById<Button>(R.id.dialogTermButton)
//        dialogPrivateButton.setOnClickListener {
//            showToastPrivetPolicy()
//            dialog.cancel()
//        }
//
//        dialogTermButton.setOnClickListener {
//            showToastTermOfUsing()
//            dialog.cancel()
//        }
//        dialog.show()
//    }


}
