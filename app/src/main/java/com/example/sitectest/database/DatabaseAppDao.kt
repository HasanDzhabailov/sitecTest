package com.example.sitectest.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sitectest.model.UserSuccessAuth

@Dao
interface DatabaseAppDao {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insert(userAuth: UserSuccessAuth)

	@Query("SELECT * FROM auth_success_users ORDER BY dateAuth DESC")
	fun getUserListSuccessAuth(): LiveData<List<UserSuccessAuth>>
}