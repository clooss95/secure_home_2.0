package com.bonacode.securehome.domain.feature.actionhistory.model

import org.threeten.bp.LocalDate

sealed class DateSelectionState {
    object NotSelected : DateSelectionState()
    data class Selected(val date: LocalDate) : DateSelectionState()
}
