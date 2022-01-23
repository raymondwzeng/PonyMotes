package com.macandswiss.ponymotes

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }
}

//class MainActivity : ActionBarActivity() {
//    var dBController: DatabaseController? = null
//        private set
//    private var mWakeLock: PowerManager.WakeLock? = null
//    var notifications: Notifications? = null
//    protected fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction().add(R.id.container, SearchFragment())
//                .commit()
//        }
//        val intent = Intent(this, MainActivity::class.java)
//        val pendingIntent: PendingIntent =
//            PendingIntent.getActivity(getApplicationContext(), 0, intent, 0)
//        notifications = Notifications(
//            this,
//            this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager,
//            Builder(this), pendingIntent
//        )
//        val pm: PowerManager = this.getSystemService(Context.POWER_SERVICE) as PowerManager
//        mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, javaClass.name)
//        FileIO.Companion.doesDirectoryExistIfNotMakeIt()
//        dBController = DatabaseController(this)
//        prefs = Prefrences(PreferenceManager.getDefaultSharedPreferences(this))
//        PreferenceManager.setDefaultValues(this, R.xml.settings, false)
//        onboardingSession()
//    }
//
//    fun onCreateOptionsMenu(menu: Menu?): Boolean {
//
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu)
//        return true
//    }
//
//    fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        val itemId: Int = item.getItemId()
//        return if (itemId == R.id.action_updateDatabase) {
//            updateDatabase()
//            true
//        } else if (itemId == R.id.action_settings) {
//            openSettings()
//            true
//        } else if (itemId == R.id.action_help) {
//            openHelp()
//            true
//        } else if (itemId == R.id.action_about) {
//            openAbout()
//            true
//        } else {
//            super.onOptionsItemSelected(item)
//        }
//    }
//
//    fun openHelp() {
//        val intent = Intent(this, WebActivity::class.java)
//        intent.putExtra("url", "http://dinsfire.com/projects/reddit-emotes/docs/help.htm")
//        intent.putExtra("title", "Help")
//        startActivity(intent)
//    }
//
//    fun openAbout() {
//        val intent = Intent(this, WebActivity::class.java)
//        intent.putExtra("url", "http://dinsfire.com/projects/reddit-emotes/docs/about.htm")
//        intent.putExtra("title", "About")
//        startActivity(intent)
//    }
//
//    fun openSettings() {
//        val currentapiVersion: Int = android.os.Build.VERSION.SDK_INT
//        if (currentapiVersion >= android.os.Build.VERSION_CODES.HONEYCOMB) {
//            startActivity(Intent(this, FragmentPreferences::class.java))
//        } else {
//            val i = Intent(getApplicationContext(), RegularPreferences::class.java)
//            startActivityForResult(i, 1)
//        }
//    }
//
//    fun updateDatabase() {
//        if (!Download.isConnectedToMobile(this.getApplicationContext()) || !prefs!!.disableOnMobile()) {
//            DownloadCompareDatabases(this@MainActivity).execute(dBController)
//        } else {
//            notifications!!.disabledOnMobile()
//        }
//    }
//
//    private inner class DownloadCompareDatabases(context: Context) :
//        AsyncTask<Any?, Void?, ArrayList<String?>?>() {
//        private val context: Context
//        private var serverDBController: DatabaseController? = null
//        var emotesToDownload: ArrayList<String?>? = null
//        protected fun onPreExecute() {
//            Download.setUserAgent(getPackageManager())
//            mWakeLock.acquire()
//            notifications!!.downloadingDatabase()
//        }
//
//        protected fun doInBackground(vararg params: Any?): ArrayList<String?>? {
//            dBController = params[0] as DatabaseController?
//            if (!Download.from(
//                    EmoteDownloadService.Companion.SERVER_DB_URL,
//                    context.getCacheDir().toString().toString() + "/"
//                            + DatabaseOpenHelper.Companion.SERVER_DATABASE_NAME
//                )
//            ) {
//                serverDBController = DatabaseController(
//                    context, context.getCacheDir().toString().toString() + "/"
//                            + DatabaseOpenHelper.Companion.SERVER_DATABASE_NAME
//                )
//                // System.out.println(serverDBController.getNumberOfRows());
//
//                // Get prefs for syncing before sycning
//                notifications!!.comparingDatabases()
//                dBController!!.open()
//                emotesToDownload = serverDBController!!.getDifferences(
//                    prefs!!.wantToDownloadEmotes(),
//                    prefs!!.wantToDownloadMissingEmotes(), prefs!!.includeNSFW(), dBController
//                )
//                dBController!!.close()
//                serverDBController!!.close()
//            }
//            return emotesToDownload
//        }
//
//        protected fun onPostExecute(result: ArrayList<String?>?) {
//            mWakeLock.release()
//            downloadNewEmotes(result)
//        }
//
//        protected fun onProgressUpdate(vararg values: Void?) {}
//
//        init {
//            this.context = context
//        }
//    }
//
//    fun downloadNewEmotes(emotesToDownload: ArrayList<String?>?) {
//        if (emotesToDownload == null) {
//            notifications!!.connectionError()
//        } else if (emotesToDownload.size == 0) {
//
//            // Toast.makeText(this, "There are no new emotes to download.",
//            // Toast.LENGTH_LONG).show();
//            notifications!!.upToDate()
//        } else {
//            mWakeLock.acquire()
//            val intent = Intent(this, EmoteDownloadService::class.java)
//            // intent.putExtra("listOfEmotes", new
//            // EmoteWrapper(emotesToDownload));
//            intent.putExtra("listOfEmotes", emotesToDownload)
//            intent.putExtra("receiver", EmoteDownloadReciever(Handler()))
//            this.startService(intent)
//
//            // Toast.makeText(this, "Starting download",
//            // Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    fun onboardingSession() {
//        if (prefs!!.onboardingSession()) {
//            val builder: AlertDialog.Builder = Builder(this)
//            builder.setMessage(R.string.onboardingMessage).setTitle(R.string.onboardingTitle)
//            builder.setPositiveButton(R.string.onboardingOk, object : OnClickListener() {
//                fun onClick(dialog: DialogInterface?, id: Int) {
//                    updateDatabase()
//                    openHelp()
//                }
//            })
//            builder.setNegativeButton(R.string.onboardingNo, object : OnClickListener() {
//                fun onClick(dialog: DialogInterface?, id: Int) {
//                    // updateDatabase();
//                }
//            })
//            val dialog: AlertDialog = builder.create()
//            dialog.show()
//
//            // new
//            // AlertDialog.Builder(this.getActivity()).setTitle("First Run").setMessage("This only pops up once").setNeutralButton("OK",
//            // null).show();
//            prefs!!.turnOnboardingSessionOff()
//        }
//    }
//
//    private inner class EmoteDownloadReciever(handler: Handler?) : ResultReceiver(handler) {
//        protected fun onReceiveResult(resultCode: Int, resultData: Bundle) {
//            super.onReceiveResult(resultCode, resultData)
//            if (resultCode == EmoteDownloadService.Companion.ERROR) {
//                notifications!!.connectionError()
//                if (mWakeLock.isHeld()) mWakeLock.release()
//            } else if (resultCode == EmoteDownloadService.Companion.UPDATE_PROGRESS) {
//
//                // Pull the data of what just got downloaded
//                val progress: Int = resultData.getInt("progress")
//                val size: Int = resultData.getInt("size")
//                val emoteName: String = resultData.getString("emoteName")
//
//                // Update the progress bar
//                if (progress == size) {
//                    notifications!!.upToDate()
//                    mWakeLock.release()
//                } else {
//                    notifications!!.downloadingUpdate(size, progress, emoteName)
//                }
//            }
//        }
//    }
//
//    companion object {
//        var prefs: Prefrences? = null
//    }
//}