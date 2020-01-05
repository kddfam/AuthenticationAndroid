package com.kdd.authentication.ntwrk.lcl.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kdd.authentication.ntwrk.lcl.ety.UserEntity

@Dao
interface UserDao {

    @Insert
    suspend fun addUser(userEty : UserEntity)

    @Query("SELECT * FROM user")
    suspend fun listUser() : List<UserEntity>

    @Query("UPDATE user SET password=:password")
    suspend fun updatePassword(password : String?)

}