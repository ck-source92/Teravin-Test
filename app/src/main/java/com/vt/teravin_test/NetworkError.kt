package com.vt.teravin_test

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class NetworkError(private val context: Context) {
    @SuppressLint("ObsoleteSdkInt")
    fun showNoConnectionDialog() {
        val builder: AlertDialog.Builder
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            builder = AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert)
        } else {
            builder = AlertDialog.Builder(context)
        }

        builder.setTitle("No Internet Connection")
            .setMessage("Please enable internet connectivity.")
            .setPositiveButton("Retry") { _, _ ->
                if (!isNetworkAvailable()) {
                    showNoConnectionDialog()
                } else {
                    // Internet is available, perform the retry action
                    Toast.makeText(context, "Retry action...", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
            .setNeutralButton("Settings") { _, _ ->
                val intent = Intent(Settings.ACTION_SETTINGS)
                context.startActivity(intent)
            }
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}