//package com.dinsfire.ponymotes
//
//import android.app.NotificationManager
//
//class Notifications(
//    context: Context, mNotifyManager: NotificationManager, mBuilder: NotificationCompat.Builder,
//    pendingIntent: PendingIntent
//) {
//    val DOWNLOAD_NOTIFICAITON_ID = 123
//    private val mNotifyManager: NotificationManager
//    private val mBuilder: NotificationCompat.Builder
//    private val context: Context
//    private val pendingIntent: PendingIntent
//    fun disabledOnMobile() {
//        mBuilder.setContentTitle(context.getString(R.string.notificationTitle))
//            .setContentText(context.getString(R.string.syncingDisabled))
//            .setSmallIcon(R.drawable.error)
//            .setProgress(0, 0, false).setOngoing(false)
//        publish()
//    }
//
//    fun downloadingDatabase() {
//        mBuilder.setContentTitle(context.getString(R.string.notificationTitle))
//            .setContentText(context.getString(R.string.downloadingNewDatabase))
//            .setSmallIcon(android.R.drawable.ic_popup_sync).setProgress(0, 0, true).setOngoing(true)
//        publish()
//    }
//
//    fun comparingDatabases() {
//        mBuilder.setContentTitle(context.getString(R.string.notificationTitle))
//            .setContentText(context.getString(R.string.comparingDatabases))
//            .setSmallIcon(android.R.drawable.ic_popup_sync).setProgress(0, 0, true).setOngoing(true)
//        publish()
//    }
//
//    fun connectionError() {
//        mBuilder.setContentTitle(context.getString(R.string.notificationTitle))
//            .setProgress(0, 0, false)
//            .setContentText(context.getString(R.string.connectionError))
//            .setSmallIcon(R.drawable.error)
//            .setOngoing(false)
//        publish()
//    }
//
//    fun upToDate() {
//        mBuilder.setContentTitle(context.getString(R.string.notificationTitle))
//            .setProgress(0, 0, false)
//            .setContentText(context.getString(R.string.upToDate)).setSmallIcon(R.drawable.checkmark)
//            .setOngoing(false)
//        publish()
//    }
//
//    fun downloadingUpdate(size: Int, progress: Int, emoteName: String) {
//        mBuilder.setProgress(size - 1, progress, false)
//        mBuilder.setContentText("($progress/$size) $emoteName")
//            .setContentInfo("$progress/$size").setOngoing(true)
//        publish()
//    }
//
//    private fun publish() {
//        mNotifyManager.notify(DOWNLOAD_NOTIFICAITON_ID, mBuilder.build())
//    }
//
//    init {
//        this.context = context
//        this.mNotifyManager = mNotifyManager
//        this.mBuilder = mBuilder
//        this.pendingIntent = pendingIntent
//        mBuilder.setContentIntent(pendingIntent)
//    }
//}