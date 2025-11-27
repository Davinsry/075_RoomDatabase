package com.example.pertemuan9.room

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Siswa::class], version = 1, exportSchema = false)
abstract class DatabaseSiswa : RoomDatabase() {
    abstract fun siswaDao(): SiswaDao

    companion object{
        @Volatile
        private var INSTANCE: DatabaseSiswa? = null
        fun getDatabase(context: Context): DatabaseSiswa {
            return (Instance ?: synchronized(this) {
)
    }
}
