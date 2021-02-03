package com.bonacode.securehome.ui.feature.main.actionHistory

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bonacode.securehome.architecture.SingleEvent
import com.bonacode.securehome.architecture.mvvm.BaseViewModel
import com.bonacode.securehome.domain.common.invoke
import com.bonacode.securehome.domain.feature.actionhistory.model.ActionHistoryModel
import com.bonacode.securehome.domain.feature.actionhistory.model.DateSelectionState
import com.bonacode.securehome.domain.feature.actionhistory.usecase.ClearActionHistory
import com.bonacode.securehome.domain.feature.actionhistory.usecase.DeleteActionHistory
import com.bonacode.securehome.domain.feature.actionhistory.usecase.GetActionHistoryList
import com.bonacode.securehome.domain.feature.actionhistory.usecase.ResendAction
import com.bonacode.securehome.ui.feature.main.activity.callback.ActionSentCallback
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate

class ActionHistoryViewModel @ViewModelInject constructor(
    getActionHistoryList: GetActionHistoryList,
    private val resendAction: ResendAction,
    private val clearActionHistory: ClearActionHistory,
    private val deleteActionHistory: DeleteActionHistory,
    private val actionSentCallback: ActionSentCallback
) : BaseViewModel() {

    private val dateSelectionState: MutableLiveData<DateSelectionState> =
        MutableLiveData(DateSelectionState.NotSelected)

    private val _showDialogQuestionClearHistoryEvent = MutableLiveData<SingleEvent<Unit>>()
    val showDialogQuestionClearHistoryEvent: LiveData<SingleEvent<Unit>> =
        _showDialogQuestionClearHistoryEvent

    val actionHistoryList: LiveData<PagingData<ActionHistoryModel>> =
        Transformations.switchMap(dateSelectionState) {
            getActionHistoryList.invoke(GetActionHistoryList.Params(it)).cachedIn(viewModelScope)
                .asLiveData(viewModelScope.coroutineContext)
        }

    val showClearFiltersButton: LiveData<Boolean> = Transformations.map(dateSelectionState) {
        it is DateSelectionState.Selected
    }

    fun retryClicked(model: ActionHistoryModel) {
        viewModelScope.launch {
            val result = resendAction.invoke(ResendAction.Params(model = model))
            actionSentCallback.actionSent(
                action = model,
                source = ActionSentCallback.ActionSentSource.HISTORY,
                result = result
            )
        }
    }

    fun showDialogQuestionClearHistory() {
        _showDialogQuestionClearHistoryEvent.postValue(
            SingleEvent(
                Unit
            )
        )
    }

    fun clearHistory() {
        viewModelScope.launch {
            clearActionHistory.invoke()
        }
    }

    fun deleteActionHistory(actionHistoryModel: ActionHistoryModel) {
        viewModelScope.launch {
            deleteActionHistory.invoke(DeleteActionHistory.Params(actionHistoryModel.id))
        }
    }

    fun dateSelected(year: Int, monthOfYear: Int, dayOfMonth: Int) {
        dateSelectionState.postValue(
            DateSelectionState.Selected(
                LocalDate.of(year, monthOfYear + 1, dayOfMonth)
            )
        )
    }

    fun clearFilters() {
        dateSelectionState.postValue(DateSelectionState.NotSelected)
    }
}
