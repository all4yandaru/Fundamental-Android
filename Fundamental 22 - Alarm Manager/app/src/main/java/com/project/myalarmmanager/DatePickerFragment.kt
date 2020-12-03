package com.project.myalarmmanager

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*

// TODO 7: buat kelas utk mengambil tanggal
class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    private var mListener : DialogDateListener? = null // dialog date listener itu interface buatan sendiri

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context!=null){
            mListener = context as DialogDateListener?
        }
    }

    override fun onDetach() {
        super.onDetach()
        if (context!=null){
            mListener = null
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val date = calendar.get(Calendar.DATE)

        return DatePickerDialog(activity as Context, this, year, month, date)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        mListener?.onDialogDataSet(tag, year, month, dayOfMonth)
    }

    interface DialogDateListener {
        fun onDialogDataSet(tag: String?, year: Int, month: Int, dayOfMonth: Int)
    }

}