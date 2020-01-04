package com.kdd.authentication.ntwrk.lcl.dbcls

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kdd.authentication.ntwrk.lcl.dao.UserDao
import com.kdd.authentication.ntwrk.lcl.ety.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 2,
    exportSchema = false
)
abstract class AuthDatabase : RoomDatabase() {

    abstract fun getDao() : UserDao

    companion object {

        @Volatile
        private var mInstance : AuthDatabase? = null
        private val LOCK = Any()

        operator fun invoke(mCOntext : Context) = mInstance ?: synchronized(LOCK) {
            mInstance ?: buildDB(mCOntext).also {
                mInstance = it
            }
        }

        private fun buildDB(mCOntext: Context) = Room.databaseBuilder(
            mCOntext,
            AuthDatabase::class.java,
            "auth"
        ).build()

    }

}