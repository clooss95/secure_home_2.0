package com.bonacode.securehome.application.common

import com.bonacode.securehome.application.config.DATE_PATTERN
import com.bonacode.securehome.application.config.TIME_PATTERN
import java.util.Locale
import java.util.TimeZone
import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter

object TimeHelper {
    fun getTimeZone(): ZoneId {
        val zoneId = TimeZone.getDefault().id
        return ZoneId.of(zoneId)
    }

    fun toLocalDateTime(instant: Instant): LocalDateTime =
        LocalDateTime.ofInstant(instant, getTimeZone())

    fun formattedDate(instant: Instant): String = toLocalDateTime(instant).format(
        DateTimeFormatter.ofPattern(
            DATE_PATTERN,
            Locale.getDefault()
        )
    )

    fun formattedTime(instant: Instant): String = toLocalDateTime(instant).format(
        DateTimeFormatter.ofPattern(
            TIME_PATTERN,
            Locale.getDefault()
        )
    )
}
