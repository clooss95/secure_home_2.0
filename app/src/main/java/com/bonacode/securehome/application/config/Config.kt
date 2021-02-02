package com.bonacode.securehome.application.config

// data
const val DATABASE_VERSION = 1
const val DATABASE_NAME = "securehome-db"
const val SHARED_PREFS_FILE_NAME = "encrypted_shared_prefs"
const val FAVOURITE_ACTIONS_LIMIT = 10
const val DEFAULT_PAGE_SIZE_FOR_PAGINATION = 20

// date time
const val DATE_PATTERN = "dd.MM.yyyy"
const val TIME_PATTERN = "HH:mm:ss"

// alarm system
const val PARTITION_COUNT = 4
const val LINE_COUNT = 32
const val ENTRY_COUNT = 32
const val PIN_CODE_PATTERN = "{pinCode}"
val GROUPS = arrayOf("A", "B", "C", "D")
