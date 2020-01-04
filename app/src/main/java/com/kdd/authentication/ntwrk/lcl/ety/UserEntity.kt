package com.kdd.authentication.ntwrk.lcl.ety

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigInteger

@Entity(tableName = "user")
data class UserEntity(
    var id : Int?,
    var firstname : String?,
    var lastname : String?,
    var email : String?,
    var phonenumber : Long?,
    var password : String?
) {
    @PrimaryKey(autoGenerate = true)
    var uid : Int? = 0
}