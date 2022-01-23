//package com.dinsfire.ponymotes
//
//import android.app.IntentService
//import java.util.ArrayList
//
//class EmoteDownloadService : IntentService("EmoteDownloadService") {
//    protected fun onHandleIntent(intent: Intent) {
//        val listOfEmotes: ArrayList<String> = intent.getStringArrayListExtra("listOfEmotes")
//        val receiver: ResultReceiver = intent.getParcelableExtra("receiver") as ResultReceiver
//        var error = false
//        var i = 0
//        while (!error && i < listOfEmotes.size) {
//            val emoteName = listOfEmotes[i]
//            val resultData = Bundle()
//            resultData.putInt("progress", i + 1)
//            resultData.putInt("size", listOfEmotes.size)
//            resultData.putString("emoteName", emoteName)
//            receiver.send(UPDATE_PROGRESS, resultData)
//            if (Download.from(BASE_URL + emoteName + ".png", BASE_LOCAL + emoteName + ".png")) {
//                receiver.send(ERROR, resultData)
//                error = true
//            }
//            i++
//        }
//    }
//
//    companion object {
//        const val BASE_URL = "http://www.dinsfire.com/emoteCache/"
//        const val SERVER_DB_URL = "http://www.dinsfire.com/emoteCache/mobileDatabase.db"
//        val BASE_LOCAL: String = FileIO.Companion.FULL_EMOTE_PATH
//        const val UPDATE_PROGRESS = 8344
//        const val ERROR = 1212
//    }
//}