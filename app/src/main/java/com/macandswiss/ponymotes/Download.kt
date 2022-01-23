//package com.dinsfire.ponymotes
//
//import android.content.Context
//import java.lang.Exception
//import java.net.HttpURLConnection
//import java.net.URL
//
//object Download {
//    const val TIMEOUT = 5000
//    fun from(url: String?, local: String?): Boolean {
//        var error = false
//        try {
//            Log.i("downloading", url)
//            val u = URL(url)
//            val c = u.openConnection() as HttpURLConnection
//            // c.setConnectTimeout(TIMEOUT);
//            // c.setReadTimeout(TIMEOUT);
//            // c.setRequestMethod("GET");
//            // c.setDoOutput(true);
//            c.connect()
//            val f = FileOutputStream(local)
//            val `in` = c.inputStream
//            val buffer = ByteArray(1024)
//            var len1 = 0
//            while (`in`.read(buffer).also { len1 = it } > 0) {
//                f.write(buffer, 0, len1)
//            }
//            f.close()
//        } catch (e: FileNotFoundException) {
//            if (e.message != null) Log.d("downloaderFileNotFoundException", e.message)
//        } catch (e: IOException) {
//            if (e.message != null) Log.d("downloaderIOException", e.message)
//            error = true
//        } catch (e: Exception) {
//            if (e.message != null) Log.d("downoaderOtherException", e.message)
//            error = true
//        }
//        return error
//    }
//
//    fun isConnectedToMobile(context: Context): Boolean {
//        val cm: ConnectivityManager =
//            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        val activeNetwork: NetworkInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
//        return activeNetwork != null && activeNetwork.isConnected()
//    }
//
//    fun setUserAgent(p: PackageManager) {
//        val pInfo: PackageInfo
//        try {
//            pInfo = p.getPackageInfo("com.dinsfire.ponymotes", 0)
//            System.setProperty("http.agent", pInfo.packageName.toString() + " " + pInfo.versionName)
//        } catch (e: NameNotFoundException) {
//            Log.e("NameNotFoundException", e.toString())
//            System.setProperty("http.agent", "?????")
//        }
//    }
//}