//package com.dinsfire.ponymotes
//
//import com.dinsfire.ponymotes.DatabaseOpenHelper
//import com.dinsfire.ponymotes.DatabaseFromFileHelper
//import kotlin.Throws
//import com.dinsfire.ponymotes.Emote
//import com.dinsfire.ponymotes.DatabaseController
//import com.dinsfire.ponymotes.FileIO
//import java.io.IOException
//import java.lang.RuntimeException
//import java.io.FileInputStream
//import java.io.FileOutputStream
//import java.io.FileNotFoundException
//import com.dinsfire.ponymotes.EmoteAdapter.ViewHolder
//import com.dinsfire.ponymotes.EmoteAdapter.ThumbnailTask
//import com.dinsfire.ponymotes.EmoteDownloadService
//import com.dinsfire.ponymotes.Download
//import com.dinsfire.ponymotes.SearchFragment
//import java.util.Arrays
//import com.dinsfire.ponymotes.FragmentPreferences.PrefsFragment
//import com.dinsfire.ponymotes.Notifications
//import com.dinsfire.ponymotes.MainActivity
//import com.dinsfire.ponymotes.Prefrences
//import com.dinsfire.ponymotes.WebActivity
//import com.dinsfire.ponymotes.FragmentPreferences
//import com.dinsfire.ponymotes.RegularPreferences
//import com.dinsfire.ponymotes.MainActivity.DownloadCompareDatabases
//import java.lang.Void
//import com.dinsfire.ponymotes.MainActivity.EmoteDownloadReciever
//import com.dinsfire.ponymotes.SearchFragment.SearchMode
//import com.dinsfire.ponymotes.EmoteAdapter
//import com.dinsfire.ponymotes.EmoteDetailsDialog
//import com.dinsfire.ponymotes.SearchFragment.SearchASyncTask
//
//class Emote {
//    var emoteName: String
//        private set
//    var tags: String? = null
//        private set
//    var dateModified = 0
//        private set
//    var source: String? = null
//        private set
//    var isNSFW = false
//        private set
//
//    internal constructor(
//        emoteName: String,
//        tags: String?,
//        isNSFW: Int,
//        dateModified: Int,
//        source: String?
//    ) {
//        this.emoteName = emoteName
//        this.tags = tags
//        this.dateModified = dateModified
//        this.source = source
//        if (isNSFW == 1) this.isNSFW = true else this.isNSFW = false
//    }
//
//    internal constructor(emoteName: String) {
//        this.emoteName = emoteName
//    }
//
//    val isIntNSFW: Int
//        get() = if (isNSFW == true) 1 else 0
//
//    override fun toString(): String {
//        return emoteName
//    }
//}