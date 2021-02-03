package com.bonacode.securehome.ui.feature.main.controlpanel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bonacode.securehome.architecture.SingleEvent
import com.bonacode.securehome.architecture.mvvm.BaseViewModel
import com.bonacode.securehome.ui.views.ExpandableControlPanelView

class ControlPanelMainViewModel @ViewModelInject constructor() : BaseViewModel() {
    private val _sectionExpandedEvent = MutableLiveData<SingleEvent<ExpandableControlPanelView>>()
    val sectionExpandedEvent: LiveData<SingleEvent<ExpandableControlPanelView>> = _sectionExpandedEvent

    fun onSectionExpanded(section: ExpandableControlPanelView) {
        _sectionExpandedEvent.postValue(SingleEvent(section))
    }
}
