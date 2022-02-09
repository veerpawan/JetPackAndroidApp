package com.pawan.jetpackandroidapp.ui.fragments

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.pawan.jetpackandroidapp.R
import com.pawan.jetpackandroidapp.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

abstract class BaseFragment : Fragment() {

    val job = Job()
    val coroutineScope = CoroutineScope(Dispatchers.Main + job)

    @LayoutRes
    abstract fun getLayoutId(): Int

    protected fun openUrl(url: String) {
        try {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        } catch (e: ActivityNotFoundException) {
        }
    }


    fun refer() {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(Intent.EXTRA_TITLE, R.string.app_name)
        sendIntent.putExtra(Intent.EXTRA_TEXT,
            "Stylish Fonts express your LOVE with Stylish Fonts @ Whatsapp Facebook any chat app. I have been using it in a while, give it a try  : https://play.google.com/store/apps/details?id=${activity?.packageName}&h=en")
        sendIntent.type = "text/plain"
        startActivity(sendIntent)
    }

    fun rateUs() {
        activity?.let {
            Toast.makeText(it, resources.getString(R.string.thanks), Toast.LENGTH_SHORT).show()
            val intent = Intent(Intent.ACTION_VIEW)
            // Try Google play
            intent.data = Uri
                .parse("https://play.google.com/store/apps/details?id=${it.packageName}&h=en")
            startActivity(intent)
        }

    }

    fun SendEmail() {
        val send = Intent(Intent.ACTION_SENDTO)
        var pInfo: PackageInfo? = null
        try {
            pInfo = activity?.packageName?.let { activity?. packageManager?.getPackageInfo(it, 0) }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        val Appversion = pInfo!!.versionName
        val androidOS = Build.VERSION.RELEASE
        val uriText = ("mailto:"
                + Uri.encode(resources.getString(R.string.mail))
                + "?subject="
                + resources.getString(R.string.email_subject) + "" + resources.getString(R.string.app_name) + "&body=" + "\n\n\n"
                + "App name : " + resources.getString(R.string.app_name) + "\n"
                + "Mobile model : " + Constants.getDeviceName() + "\n"
                + "App version : " + Appversion + "\n"
                + "Android version : " + androidOS)
        val uri = Uri.parse(uriText)
        send.data = uri
        startActivity(Intent.createChooser(send, "Send mail..."))
    }

    fun moreApps() {
        val more = Intent(Intent.ACTION_VIEW)
        more.data = Uri.parse("market://search?q=pub:" + resources.getString(R.string.enter_your_email))
        startActivity(more)
    }

}