//package com.dinsfire.ponymotes
//
//import android.content.Context
//import java.io.File
//import java.io.OutputStream
//
//class DatabaseFromFileHelper(context: Context, dbName: String) {
//    private val dbName: String
//    private val context: Context
//    fun openDatabase(): SQLiteDatabase {
//        val dbFile: File = context.getDatabasePath(dbName)
//        if (!dbFile.exists()) {
//            try {
//                copyDatabase(dbFile)
//            } catch (e: IOException) {
//                throw RuntimeException("Error creating source database", e)
//            }
//        }
//        return SQLiteDatabase.openDatabase(dbFile.path, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS)
//    }
//
//    @Throws(IOException::class)
//    private fun copyDatabase(dbFile: File) {
//        val `is` = FileInputStream(context.getCacheDir().toString().toString() + "/" + dbName)
//        val os: OutputStream = FileOutputStream(dbFile)
//        val buffer = ByteArray(1024)
//        while (`is`.read(buffer) > 0) {
//            os.write(buffer)
//        }
//        os.flush()
//        os.close()
//        `is`.close()
//    }
//
//    init {
//        this.context = context
//        this.dbName = dbName
//    }
//}