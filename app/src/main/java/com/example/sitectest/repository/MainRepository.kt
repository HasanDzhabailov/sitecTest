package com.example.sitectest.repository

import com.example.sitectest.database.DatabaseAppDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(localDataSource: DatabaseAppDao) {
}