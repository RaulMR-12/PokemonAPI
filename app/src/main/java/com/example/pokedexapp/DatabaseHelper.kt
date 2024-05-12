package com.example.pokedexapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DatabaseHelper (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "PokemonFavorites.db"
        private const val TABLE_NAME = "favorites"
        private const val COLUMN_ID = "id"
        private const val COLUMN_POKEDEX_NUMBER = "pokedex_number"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_IMAGE_URL = "image_url"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE_FAVORITES = ("CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_POKEDEX_NUMBER + " INTEGER,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_IMAGE_URL + " TEXT"
                + ")")
        db.execSQL(CREATE_TABLE_FAVORITES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addPokemonToFavorite(pokedexNumber: Int, name: String, imageUrl: String): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_POKEDEX_NUMBER, pokedexNumber)
        values.put(COLUMN_NAME, name)
        values.put(COLUMN_IMAGE_URL, imageUrl)
        return db.insert(TABLE_NAME, null, values)
    }


    fun getFavorites(): ArrayList<PokemonFavorite> {
        val favoritesList = ArrayList<PokemonFavorite>()
        val cursor: Cursor = readableDatabase.rawQuery("SELECT * FROM $TABLE_NAME", null)
        if (cursor.moveToFirst()) {
            val pokedexNumberIndex = cursor.getColumnIndex(COLUMN_POKEDEX_NUMBER)
            val nameIndex = cursor.getColumnIndex(COLUMN_NAME)
            val imageUrlIndex = cursor.getColumnIndex(COLUMN_IMAGE_URL)

            do {
                val pokedexNumber = if (pokedexNumberIndex != -1) cursor.getInt(pokedexNumberIndex) else -1
                val name = if (nameIndex != -1) cursor.getString(nameIndex) else ""
                val imageUrl = if (imageUrlIndex != -1) cursor.getString(imageUrlIndex) else ""
                favoritesList.add(PokemonFavorite(pokedexNumber, name, imageUrl))
            } while (cursor.moveToNext())
        }

        cursor.close()
        return favoritesList
    }

    fun removeFavorite(pokedexNumber: Int) {
        writableDatabase.delete(TABLE_NAME, "$COLUMN_POKEDEX_NUMBER=?", arrayOf(pokedexNumber.toString()))
    }
}

data class PokemonFavorite(val pokedexNumber: Int, val name: String, val imageUrl: String)
