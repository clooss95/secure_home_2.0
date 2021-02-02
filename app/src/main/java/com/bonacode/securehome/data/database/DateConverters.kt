package com.bonacode.securehome.data.database

import androidx.room.TypeConverter
import org.threeten.bp.Instant

object DateConverters {
    @TypeConverter
    @JvmStatic
    fun toInstant(value: Long): Instant = Instant.ofEpochMilli(value)

    @TypeConverter
    @JvmStatic
    fun fromInstant(date: Instant): Long = date.toEpochMilli()
}
