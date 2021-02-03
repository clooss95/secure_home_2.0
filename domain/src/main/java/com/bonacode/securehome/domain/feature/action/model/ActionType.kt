package com.bonacode.securehome.domain.feature.action.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.bonacode.securehome.domain.R

enum class ActionType(@StringRes val label: Int, @DrawableRes val icon: Int) {
    START_ALARM(R.string.sms_action_type_start_alarm, R.drawable.ic_lock_simple),
    START_ALARM_HOME(R.string.sms_action_type_start_alarm_home, R.drawable.ic_lock_simple),
    START_ALARM_GROUP(R.string.sms_action_type_start_alarm_group, R.drawable.ic_lock_simple),
    STOP_ALARM(R.string.sms_action_type_stop_alarm, R.drawable.ic_unlock),

    START_ALARM_PARTITION(
        R.string.sms_action_type_start_alarm_partition,
        R.drawable.ic_lock_simple
    ),
    START_ALARM_PARTITION_HOME(
        R.string.sms_action_type_start_alarm_partition_home,
        R.drawable.ic_lock_simple
    ),
    START_ALARM_GROUP_IN_PARTITION(
        R.string.sms_action_type_start_alarm_group_in_partition,
        R.drawable.ic_lock_simple
    ),
    STOP_ALARM_PARTITION(R.string.sms_action_type_stop_alarm_partition, R.drawable.ic_unlock),

    BLOCK_LINE(R.string.sms_action_type_block_line, R.drawable.ic_block),
    UNBLOCK_LINE(R.string.sms_action_type_unblock_line, R.drawable.ic_check),

    ACTIVATE_ENTRY(R.string.sms_action_type_activate_entry, R.drawable.ic_check),
    INACTIVATE_ENTRY(R.string.sms_action_type_inactivate_entry, R.drawable.ic_block),

    CHECK_SYSTEM_STATUS(R.string.sms_action_type_check_system_status, R.drawable.ic_status),
    CHECK_LAST_ALARM(R.string.sms_action_type_check_last_alarm, R.drawable.ic_alarm),
    CHECK_SIM_CREDIT(R.string.sms_action_type_check_sim_credit, R.drawable.ic_money)
}
