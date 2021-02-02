package com.bonacode.securehome.ui.feature.common

import android.app.Dialog
import android.content.Context
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import androidx.annotation.StringRes
import com.bonacode.securehome.R
import com.bonacode.securehome.application.config.ENTRY_COUNT
import com.bonacode.securehome.application.config.GROUPS
import com.bonacode.securehome.application.config.LINE_COUNT
import com.bonacode.securehome.application.config.PARTITION_COUNT

object ValuePickerDialog {

    fun showForPartition(context: Context, onValueSelected: (Int) -> Unit): Dialog =
        showNumberPickerDialog(context, R.string.dialog_select_partition_title, PARTITION_COUNT, onValueSelected)

    fun showForLine(context: Context, onValueSelected: (Int) -> Unit): Dialog =
        showNumberPickerDialog(context, R.string.dialog_select_line_title, LINE_COUNT, onValueSelected)

    fun showForEntry(context: Context, onValueSelected: (Int) -> Unit): Dialog =
        showNumberPickerDialog(context, R.string.dialog_select_entry_title, ENTRY_COUNT, onValueSelected)

    fun showForGroup(
        context: Context,
        onValueSelected: (String) -> Unit
    ): Dialog {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.dialog_value_picker)

        dialog.findViewById<TextView>(R.id.title).setText(R.string.dialog_select_group_title)

        val numberPicker = dialog.findViewById<NumberPicker>(R.id.numberPicker)
        numberPicker.displayedValues = GROUPS
        numberPicker.minValue = 0
        numberPicker.maxValue = GROUPS.size - 1

        dialog.findViewById<Button>(R.id.okButton).setOnClickListener {
            onValueSelected(GROUPS[numberPicker.value])
            dialog.dismiss()
        }

        dialog.show()

        return dialog
    }

    private fun showNumberPickerDialog(
        context: Context,
        @StringRes titleRes: Int,
        maxValue: Int,
        onValueSelected: (Int) -> Unit
    ): Dialog {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.dialog_value_picker)

        dialog.findViewById<TextView>(R.id.title).setText(titleRes)

        val numberPicker = dialog.findViewById<NumberPicker>(R.id.numberPicker)
        numberPicker.maxValue = maxValue
        numberPicker.minValue = 1

        dialog.findViewById<Button>(R.id.okButton).setOnClickListener {
            onValueSelected(numberPicker.value)
            dialog.dismiss()
        }

        dialog.show()

        return dialog
    }
}
