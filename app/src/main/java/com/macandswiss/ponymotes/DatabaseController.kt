//package com.dinsfire.ponymotes
//
//import android.content.ContentValues
//import java.lang.Exception
//import java.util.ArrayList
//
//class DatabaseController {
//    private var emoteDatabase: SQLiteDatabase? = null
//    private var dbHelper: DatabaseOpenHelper? = null
//    private var dbFromFileHelper: DatabaseFromFileHelper? = null
//
//    constructor(context: Context?) {
//        dbHelper = DatabaseOpenHelper(context)
//    }
//
//    constructor(context: Context?, name: String?, version: Int) {
//        dbHelper = DatabaseOpenHelper(context, name, version)
//    }
//
//    constructor(context: Context, fileName: String) {
//        dbFromFileHelper = DatabaseFromFileHelper(context, fileName)
//        emoteDatabase = dbFromFileHelper!!.openDatabase()
//    }
//
//    @Throws(SQLException::class)
//    fun open() {
//        emoteDatabase = dbHelper.getWritableDatabase()
//    }
//
//    fun close() {
//        emoteDatabase.close()
//    }
//
//    val sQL: SQLiteDatabase?
//        get() = emoteDatabase
//
//    fun addEmote(
//        emoteName: String?,
//        tags: String?,
//        isNSFW: Int,
//        dateModified: String?,
//        source: String?
//    ) {
//        val values = ContentValues()
//        values.put(DatabaseOpenHelper.Companion.COLUMN_EMOTE_NAME, emoteName)
//        values.put(DatabaseOpenHelper.Companion.COLUMN_TAGS, tags)
//        values.put(DatabaseOpenHelper.Companion.COLUMN_NSFW, isNSFW)
//        values.put(DatabaseOpenHelper.Companion.COLUMN_DATE_MODIFIED, dateModified)
//        values.put(DatabaseOpenHelper.Companion.COLUMN_SOURCE, source)
//        emoteDatabase.insert(DatabaseOpenHelper.Companion.TABLE_EMOTES, null, values)
//        // System.out.println("Added: " + String.valueOf(insertId));
//    }
//
//    fun batchAddEmotes(toBeAdded: ArrayList<Emote>) {
//        if (toBeAdded.size > 0) {
//            emoteDatabase.beginTransaction()
//            val sql =
//                "INSERT INTO " + DatabaseOpenHelper.Companion.TABLE_EMOTES + "  VALUES(?,?,?,?,?)"
//            val insert: SQLiteStatement = emoteDatabase.compileStatement(sql)
//            try {
//                for (emote in toBeAdded) {
//                    insert.bindString(1, emote.emoteName)
//                    insert.bindString(2, emote.tags)
//                    insert.bindLong(3, emote.isIntNSFW)
//                    insert.bindLong(4, emote.dateModified)
//                    insert.bindString(5, emote.source)
//                    // System.out.println(insert.toString());
//                    insert.execute()
//                }
//                emoteDatabase.setTransactionSuccessful()
//            } catch (e: Exception) {
//                Log.e("batchAdd", e.toString())
//            } finally {
//                emoteDatabase.endTransaction()
//            }
//        }
//    }
//
//    fun batchDeleteEmotes(toBeDeleted: ArrayList<*>) {
//        if (toBeDeleted.size > 0) {
//            emoteDatabase.beginTransaction()
//            val sql =
//                "DELETE FROM " + DatabaseOpenHelper.Companion.TABLE_EMOTES + " WHERE emoteName = (?)"
//            val insert: SQLiteStatement = emoteDatabase.compileStatement(sql)
//            try {
//                for (i in toBeDeleted.indices) {
//                    insert.bindString(1, toBeDeleted[i].toString())
//                    insert.execute()
//                }
//                emoteDatabase.setTransactionSuccessful()
//            } catch (e: Exception) {
//                Log.e("batchDelete", e.toString())
//            } finally {
//                emoteDatabase.endTransaction()
//            }
//        }
//    }
//
//    fun searchFor(searchTerm: String, limit: String?): ArrayList<String> {
//        var limit = limit
//        val result = ArrayList<String>()
//        val splited = searchTerm.split("\\s+".toRegex()).toTypedArray()
//        var query = ""
//
//        // TODO This is ugly, find a better way
//        for (i in splited.indices) {
//            if (splited[i].startsWith("+")) {
//                query += DatabaseOpenHelper.Companion.COLUMN_TAGS + " LIKE ?"
//                splited[i] = splited[i].replace("+", "")
//            } else if (splited[i].startsWith("r/")) {
//                query += DatabaseOpenHelper.Companion.COLUMN_SOURCE + " LIKE ?"
//                splited[i] = splited[i].replace("r/", "")
//            } else if (splited[i].startsWith("sr:")) {
//                query += DatabaseOpenHelper.Companion.COLUMN_SOURCE + " LIKE ?"
//                splited[i] = splited[i].replace("sr:", "")
//            } else if (splited[i].startsWith("-")) {
//                if (splited[i].startsWith("-+")) {
//                    query += DatabaseOpenHelper.Companion.COLUMN_TAGS + " NOT LIKE ?"
//                    splited[i] = splited[i].replace("-+", "")
//                } else {
//                    query += DatabaseOpenHelper.Companion.COLUMN_EMOTE_NAME + " NOT LIKE ?"
//                    splited[i] = splited[i].replace("-", "")
//                }
//            } else query += DatabaseOpenHelper.Companion.COLUMN_EMOTE_NAME + " LIKE ?"
//            splited[i] = "%" + splited[i] + "%"
//            if (i != splited.size - 1) query += " AND "
//        }
//
//        // Cursor cursor = emoteDatabase.rawQuery(query, splited);
//        if (limit == "0") {
//            limit = null
//        }
//        val cursor: Cursor = emoteDatabase.query(
//            DatabaseOpenHelper.Companion.TABLE_EMOTES,
//            arrayOf<String>(DatabaseOpenHelper.Companion.COLUMN_EMOTE_NAME),
//            query,
//            splited,
//            null,
//            null,
//            DatabaseOpenHelper.Companion.COLUMN_EMOTE_NAME,
//            limit
//        )
//        cursor.moveToFirst()
//        while (cursor.isAfterLast() === false) {
//            result.add(cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.Companion.COLUMN_EMOTE_NAME)))
//            cursor.moveToNext()
//        }
//        cursor.close()
//        return result
//    }
//
//    fun getCursorForEmote(emoteName: String): Cursor {
//        val columns = arrayOf<String>(
//            DatabaseOpenHelper.Companion.COLUMN_EMOTE_NAME,
//            DatabaseOpenHelper.Companion.COLUMN_DATE_MODIFIED,
//            DatabaseOpenHelper.Companion.COLUMN_TAGS,
//            DatabaseOpenHelper.Companion.COLUMN_NSFW,
//            DatabaseOpenHelper.Companion.COLUMN_SOURCE
//        )
//        val where: String = DatabaseOpenHelper.Companion.COLUMN_EMOTE_NAME + " = ?"
//        val arguments = arrayOf(emoteName)
//        val cursor: Cursor = emoteDatabase.query(
//            DatabaseOpenHelper.Companion.TABLE_EMOTES, columns, where, arguments, null, null,
//            null
//        )
//        cursor.moveToFirst()
//        return cursor
//    }
//
//    fun getInfoForEmote(emoteName: String): Emote {
//        val tempCursor: Cursor = getCursorForEmote(emoteName)
//        val dateModified: Int =
//            tempCursor.getInt(tempCursor.getColumnIndex(DatabaseOpenHelper.Companion.COLUMN_DATE_MODIFIED))
//        val tags: String =
//            tempCursor.getString(tempCursor.getColumnIndex(DatabaseOpenHelper.Companion.COLUMN_TAGS))
//        val isNSFW: Int =
//            tempCursor.getInt(tempCursor.getColumnIndex(DatabaseOpenHelper.Companion.COLUMN_NSFW))
//        val source: String =
//            tempCursor.getString(tempCursor.getColumnIndex(DatabaseOpenHelper.Companion.COLUMN_SOURCE))
//        tempCursor.close()
//        return Emote(emoteName, tags, isNSFW, dateModified, source)
//    }
//
//    val numberOfRows: Int
//        get() {
//            val cursor: Cursor = emoteDatabase.rawQuery(
//                "SELECT * FROM " + DatabaseOpenHelper.Companion.TABLE_EMOTES,
//                null
//            )
//            cursor.moveToFirst()
//            val numOfRows: Int = cursor.getCount()
//            cursor.close()
//            return numOfRows
//        }
//    val allRows: Cursor
//        get() {
//            val cursor: Cursor = emoteDatabase.rawQuery(
//                "SELECT * FROM " + DatabaseOpenHelper.Companion.TABLE_EMOTES,
//                null
//            )
//            cursor.moveToFirst()
//            return cursor
//        }
//
//    // public ArrayList<Emote> getDifferences(DatabaseController localDB) {
//    fun getDifferences(
//        downloadEmotes: Boolean, downloadMissingEmotes: Boolean, includeNSFW: Boolean,
//        localDB: DatabaseController?
//    ): ArrayList<String?> {
//        val newCursor: Cursor = allRows
//        val newEmotes: ArrayList<Emote> = ArrayList<Emote>(newCursor.getCount())
//        val toDownload: ArrayList<Emote> = ArrayList<Emote>(newCursor.getCount())
//        val toDelete: ArrayList<String> = ArrayList<String>(newCursor.getCount())
//        Log.i("database", "Starting the comparison")
//        while (newCursor.isAfterLast() === false) {
//            val emoteName: String =
//                newCursor.getString(newCursor.getColumnIndex(DatabaseOpenHelper.Companion.COLUMN_EMOTE_NAME))
//            val dateModified: Int =
//                newCursor.getInt(newCursor.getColumnIndex(DatabaseOpenHelper.Companion.COLUMN_DATE_MODIFIED))
//            val tags: String =
//                newCursor.getString(newCursor.getColumnIndex(DatabaseOpenHelper.Companion.COLUMN_TAGS))
//            val isNSFW: Int =
//                newCursor.getInt(newCursor.getColumnIndex(DatabaseOpenHelper.Companion.COLUMN_NSFW))
//            val source: String =
//                newCursor.getString(newCursor.getColumnIndex(DatabaseOpenHelper.Companion.COLUMN_SOURCE))
//            val newEmote = Emote(emoteName, tags, isNSFW, dateModified, source)
//            if (!newEmote.isNSFW || includeNSFW) {
//                val oldCursor: Cursor = localDB!!.getCursorForEmote(emoteName)
//
//                // Check to see if the emote exists in old table
//                if (oldCursor.getCount() > 0) {
//                    // String oldEmoteName =
//                    // oldCursor.getString(oldCursor.getColumnIndex(DatabaseOpenHelper.COLUMN_EMOTE_NAME));
//                    val oldDateModified: Int = oldCursor.getInt(
//                        oldCursor
//                            .getColumnIndex(DatabaseOpenHelper.Companion.COLUMN_DATE_MODIFIED)
//                    )
//                    // System.out.println("Checking to see if " + oldEmoteName +
//                    // " needs updating by comparing " + oldDateModified +
//                    // " to " + dateModified);
//                    if (dateModified > oldDateModified) {
//                        // System.out.println("Old version of: " + emoteName);
//                        newEmotes.add(newEmote)
//                    } else {
//                        // The file is in the table and up to date
//
//                        // Check to see if emote is missing and to download it
//                        if (downloadMissingEmotes) {
//                            if (!FileIO.Companion.emoteExistsInStorage(emoteName)) {
//                                // Log.i("database", "Downloading missing: " +
//                                // emoteName);
//                                toDownload.add(newEmote)
//                            }
//                        }
//                    }
//                } else {
//                    // Since the emote isn't in the table, add to the database
//                    // and download
//
//                    // System.out.println("Not in local database: " +
//                    // emoteName);
//                    // localDB.addEmote(emoteName, tags, dateModified, source);
//                    newEmotes.add(newEmote)
//                }
//                oldCursor.close()
//            } else {
//                toDelete.add(emoteName)
//                if (FileIO.Companion.emoteExistsInStorage(emoteName)) {
//                    FileIO.Companion.deleteEmote(emoteName)
//                    Log.i("database", "Deleting: $emoteName")
//                }
//            }
//            newCursor.moveToNext()
//        }
//        newCursor.close()
//        Log.i("database", "Adding to database " + newEmotes.size + " emotes.")
//        localDB!!.batchDeleteEmotes(newEmotes)
//        localDB.batchDeleteEmotes(toDelete)
//        localDB.batchAddEmotes(newEmotes)
//        val stringNewEmotes = ArrayList<String?>(newEmotes.size)
//        val stringToDownload = ArrayList<String?>(toDownload.size)
//        for (i in newEmotes.indices) stringNewEmotes.add(newEmotes[i].emoteName)
//        for (i in toDownload.indices) stringToDownload.add(toDownload[i].emoteName)
//
//        // TODO: update this code. Implement the check somewhere else.
//        // SOMETHING...
//        return if (downloadEmotes) {
//            if (downloadMissingEmotes) stringNewEmotes.addAll(stringToDownload)
//            Log.i(
//                "database",
//                "Need to download: " + newEmotes.size + " out of " + numberOfRows.toString()
//            )
//            stringNewEmotes
//        } else if (downloadMissingEmotes) {
//            Log.i(
//                "database",
//                "Need to download: " + newEmotes.size + " out of " + numberOfRows.toString()
//            )
//            stringToDownload
//        } else ArrayList()
//    }
//}