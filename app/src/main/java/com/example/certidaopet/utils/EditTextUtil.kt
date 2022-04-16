package com.example.certidaopet.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

class EditTextUtil {
    companion object {
        fun disableEditText(editText: EditText) {
            editText.isFocusable = false
            editText.isCursorVisible = false
        }

        fun enableEditText(editText: EditText) {
            editText.isFocusable = true
            editText.isFocusableInTouchMode = true
            editText.isCursorVisible = true
        }

        fun hideKeyboard(activity: Activity) {
            val currentFocus = activity.currentFocus
            currentFocus?.let { view ->
                val imm =
                    activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm?.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }
    }
}