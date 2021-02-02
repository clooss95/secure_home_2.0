package com.bonacode.securehome.application.smscommunication.builder.impl

import com.bonacode.securehome.application.config.PIN_CODE_PATTERN
import com.bonacode.securehome.application.smscommunication.builder.ActionCommandBuilder

object ActionCommandBuilderPolishSystem : ActionCommandBuilder {
    override fun startAlarm(): String = "${PIN_CODE_PATTERN}U"

    override fun startAlarmHome(): String = "${PIN_CODE_PATTERN}D"

    override fun startAlarmGroup(group: String): String = "${PIN_CODE_PATTERN}G$group"

    override fun stopAlarm(): String = "${PIN_CODE_PATTERN}R"

    override fun startAlarmPartition(partition: Int): String =
        "${PIN_CODE_PATTERN}U$partition"

    override fun startAlarmPartitionHome(partition: Int): String =
        "${PIN_CODE_PATTERN}D$partition"

    override fun startAlarmGroupInPartition(group: String, partition: Int): String =
        "${PIN_CODE_PATTERN}G$group$partition"

    override fun stopAlarmPartition(partition: Int): String = "${PIN_CODE_PATTERN}R$partition"

    override fun blockLine(line: Int): String = "${PIN_CODE_PATTERN}BL${createLineString(line)}"

    override fun unblockLine(line: Int): String = "${PIN_CODE_PATTERN}ODB${createLineString(line)}"

    override fun activateEntry(entry: Int): String = "${PIN_CODE_PATTERN}WYON$entry"

    override fun inactivateEntry(entry: Int): String = "${PIN_CODE_PATTERN}WYOFF$entry"

    override fun checkSystemStatus(): String = "${PIN_CODE_PATTERN}STAN"

    override fun checkLastAlarm(): String = "${PIN_CODE_PATTERN}AL"

    override fun checkSimCredit(): String = "${PIN_CODE_PATTERN}KR"

    private fun createLineString(line: Int): String = if (line < 10 && line != 0) {
        "0$line"
    } else {
        "$line"
    }
}
