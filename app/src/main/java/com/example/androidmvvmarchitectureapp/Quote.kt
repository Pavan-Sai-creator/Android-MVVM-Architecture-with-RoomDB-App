package com.example.androidmvvmarchitectureapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quote")
data class Quote(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val quoteText: String,
    val author: String
)
