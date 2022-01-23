//package com.dinsfire.ponymotes
//
//import android.os.Environment
//import java.io.File
//import java.io.InputStream
//import java.io.OutputStream
//import java.lang.Exception
//import java.util.ArrayList
//
//class FileIO {
//    // folder does NOT need to have the preceding slash
//    // folder is the folder starting from /sdcard/<folder>
//    fun getListOfFiles(folder: String): ArrayList<File>? {
//        var folderListing: ArrayList<File>? = null
//        try {
//            if (isExternalStorageReadable) {
//                val f: File = File(
//                    Environment.getExternalStorageDirectory().toString().toString() + "/" + folder
//                )
//                folderListing = ArrayList<File>(Arrays.asList<File>(*f.listFiles()))
//            }
//        } catch (e: Exception) {
//            // TODO Error detection. Yeah...
//        }
//        return folderListing
//    }
//
//    // TODO Error detection. Yeah...
//    val listOfEmotes: ArrayList<File>?
//        get() {
//            var folderListing: ArrayList<File>? = null
//            try {
//                if (isExternalStorageReadable) {
//                    val f = File(FULL_EMOTE_PATH)
//                    folderListing = ArrayList<File>(Arrays.asList<File>(*f.listFiles()))
//                }
//            } catch (e: Exception) {
//                // TODO Error detection. Yeah...
//            }
//            return folderListing
//        }
//
//    companion object {
//        val FULL_EMOTE_PATH: String = Environment.getExternalStorageDirectory().toString()
//            .toString() + "/RedditEmotes/"
//
//        fun doesDirectoryExistIfNotMakeIt() {
//            val theDir = File(FULL_EMOTE_PATH)
//            val nomediaFile = File(FULL_EMOTE_PATH, ".nomedia")
//            if (!theDir.exists()) {
//                theDir.mkdir()
//            }
//            if (!nomediaFile.exists()) {
//                try {
//                    nomediaFile.createNewFile()
//                } catch (e: IOException) {
//                    Log.e("nomediafile", e.toString())
//                }
//            }
//        }
//
//        fun getEmotePath(emoteName: String?): String {
//            return FULL_EMOTE_PATH + emoteName + ".png"
//        }
//
//        fun getEmoteByName(emoteName: String?): File? {
//            try {
//                if (isExternalStorageReadable) {
//                    return File("$FULL_EMOTE_PATH$emoteName.png")
//                }
//            } catch (e: Exception) {
//                // TODO Error detection. Yeah...
//            }
//            return null
//        }
//
//        fun emoteExistsInStorage(emoteName: String?): Boolean {
//            try {
//                if (isExternalStorageReadable) {
//                    val f = File(FULL_EMOTE_PATH + emoteName + ".png")
//                    return f.exists()
//                }
//            } catch (e: Exception) {
//                // TODO Error detection. Yeah...
//            }
//            return false
//        }
//
//        fun deleteEmote(emoteName: String): Boolean {
//            try {
//                if (isExternalStorageReadable) {
//                    val f = File(FULL_EMOTE_PATH + emoteName + ".png")
//                    return f.delete()
//                }
//            } catch (e: Exception) {
//                // TODO Error detection. Yeah...
//            }
//            return false
//        }
//
//        fun copyTempEmote(emoteName: String?): String? {
//            var fullPath: String? = null
//            try {
//                fullPath = FULL_EMOTE_PATH + "temp.png"
//                val `in`: InputStream = FileInputStream(FULL_EMOTE_PATH + emoteName + ".png")
//                val out: OutputStream = FileOutputStream(fullPath)
//
//                // Transfer bytes from in to out
//                val buf = ByteArray(1024)
//                var len: Int
//                while (`in`.read(buf).also { len = it } > 0) {
//                    out.write(buf, 0, len)
//                }
//                `in`.close()
//                out.flush()
//                out.close()
//                Log.i("shareDebug", "fullPath: $fullPath")
//                Log.i("shareDebug", "Temp created!")
//            } catch (e: IOException) {
//                Log.e("tempEmpte", e.toString())
//            }
//            return fullPath
//        }
//
//        // Taken from Android Examples
//        // Checks if external storage is available for read and write
//        val isExternalStorageWritable: Boolean
//            get() {
//                val state: String = Environment.getExternalStorageState()
//                return if (Environment.MEDIA_MOUNTED.equals(state)) {
//                    true
//                } else false
//            }
//
//        // /Checks if external storage is available to at least read
//        val isExternalStorageReadable: Boolean
//            get() {
//                val state: String = Environment.getExternalStorageState()
//                return if (Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(
//                        state
//                    )
//                ) {
//                    true
//                } else false
//            }
//    }
//}