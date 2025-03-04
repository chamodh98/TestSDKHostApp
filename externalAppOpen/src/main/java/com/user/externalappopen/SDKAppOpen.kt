
package com.user.externalappopen

import android.content.Context
import android.content.Intent

/**
 * Created by Chamod Hettiarachchi on 2025-03-04
 */

class SDKAppOpen {

    companion object {
        fun lunchWebView(context: Context, param1: String, param2: String) {
            val intent = Intent(context, ActWebView::class.java).apply {
                putExtra("param1", param1)
                putExtra("param2", param2)
            }
            context.startActivity(intent)
        }
    }
}