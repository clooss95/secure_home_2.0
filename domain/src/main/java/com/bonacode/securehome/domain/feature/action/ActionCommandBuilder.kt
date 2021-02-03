package com.bonacode.securehome.domain.feature.action

interface ActionCommandBuilder {
    fun startAlarm(): String
    fun startAlarmHome(): String
    fun startAlarmGroup(group: String): String
    fun stopAlarm(): String
    fun startAlarmPartition(partition: Int): String
    fun startAlarmPartitionHome(partition: Int): String
    fun startAlarmGroupInPartition(group: String, partition: Int): String
    fun stopAlarmPartition(partition: Int): String
    fun blockLine(line: Int): String
    fun unblockLine(line: Int): String
    fun activateEntry(entry: Int): String
    fun inactivateEntry(entry: Int): String
    fun checkSystemStatus(): String
    fun checkLastAlarm(): String
    fun checkSimCredit(): String
}
