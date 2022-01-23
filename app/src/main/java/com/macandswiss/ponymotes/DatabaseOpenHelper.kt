//package com.dinsfire.ponymotes
//
//import android.content.Context
//
//class DatabaseOpenHelper : SQLiteOpenHelper {
//    constructor(context: Context?, name: String?, factory: CursorFactory?, version: Int) : super(
//        context,
//        name,
//        factory,
//        version
//    ) {    // TODO Auto-generated constructor stub
//    }
//
//    constructor(context: Context?, name: String?, version: Int) : super(
//        context,
//        name,
//        null,
//        version
//    ) {    // TODO Auto-generated constructor stub
//    }
//
//    constructor(context: Context?) : super(context, DATABASE_NAME, null, DATABASE_VERSION) {}
//
//    fun onCreate(db: SQLiteDatabase) {
//        db.execSQL(DATABASE_CREATE)
//        db.execSQL(INDEX_EMOTE)
//        db.execSQL(INDEX_TAGS)
//        db.execSQL(INDEX_SOURCE)
//    }
//
//    fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
//        Log.w(
//            DatabaseOpenHelper::class.java.name,
//            "Upgrading database from version " + oldVersion + " to " + newVersion
//                    + ", which will destroy all old data"
//        )
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMOTES)
//        onCreate(db)
//    }
//
//    companion object {
//        const val TABLE_EMOTES = "mobileEmotes"
//        const val COLUMN_ID = "_id"
//        const val COLUMN_EMOTE_NAME = "emoteName"
//        const val COLUMN_TAGS = "tags"
//        const val COLUMN_NSFW = "isNSFW"
//        const val COLUMN_DATE_MODIFIED = "dateModified"
//        const val COLUMN_SOURCE = "source"
//        const val EMOTE_INDEX = "emoteIndex"
//        const val TAGS_INDEX = "tagsIndex"
//        const val SOURCE_INDEX = "sourceIndex"
//        private const val DATABASE_NAME = "clientEmotes.db"
//        private const val DATABASE_VERSION = 2
//        const val SERVER_DATABASE_NAME = "mobileDatabase.db"
//        private const val DATABASE_CREATE =
//            ("create table " + TABLE_EMOTES + "(" + COLUMN_EMOTE_NAME
//                    + " text primary key not null," + COLUMN_TAGS + " text not null," + COLUMN_NSFW + " integer not null,"
//                    + COLUMN_DATE_MODIFIED + " integer not null," + COLUMN_SOURCE + " text not null" + ");")
//        private const val INDEX_EMOTE =
//            ("CREATE INDEX " + EMOTE_INDEX + " ON " + TABLE_EMOTES + " ("
//                    + COLUMN_EMOTE_NAME + ");")
//        private const val INDEX_TAGS =
//            ("CREATE INDEX " + TAGS_INDEX + " ON " + TABLE_EMOTES + " (" + COLUMN_TAGS
//                    + "," + COLUMN_EMOTE_NAME + ");")
//        private const val INDEX_SOURCE =
//            ("CREATE INDEX " + SOURCE_INDEX + " ON " + TABLE_EMOTES + " ("
//                    + COLUMN_SOURCE + "," + COLUMN_EMOTE_NAME + ");")
//    }
//}