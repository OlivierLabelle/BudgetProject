package com.example.android.budgetproject

import java.util.regex.Pattern
import android.text.Editable
import android.widget.EditText
import android.text.TextWatcher
import android.widget.Toast
import com.example.android.budgetproject.popUp.DepenseDetails
import java.util.*


object Util {

    fun petiteToast(leToast: String){
        Toast.makeText(BudgetApplication.getContext(), leToast, Toast.LENGTH_SHORT).show()
    }

    fun dateDisplayer(year: Int, month: Int, day: Int){
        try {
            var monthPlusOne = (month + 1).toString()
            monthPlusOne = if(monthPlusOne.toInt() < 10){
                "0$monthPlusOne"
            }else{
                monthPlusOne
            }
            val date = "$year/$monthPlusOne/$day"
            DepenseDetails.completedDate = date
            DepenseDetails.displayedDate = date.substring(5)
        } catch (e: NumberFormatException) {
            e.printStackTrace()
        }
    }

    fun currentDateForDatePicker(){
        val cal = Calendar.getInstance()
        DepenseDetails.year = cal.get(Calendar.YEAR)
        DepenseDetails.month = cal.get(Calendar.MONTH)
        DepenseDetails.day = cal.get(Calendar.DAY_OF_MONTH)
    }
}

class DecimalInputTextWatcher(private val mEditText: EditText, private val mDigitsAfterZero: Int) : TextWatcher {

    private var mPreviousValue: String? = null
    private var mCursorPosition: Int = 0
    private var mRestoringPreviousValueFlag: Boolean = false

    init {
        mPreviousValue = ""
        mRestoringPreviousValueFlag = false
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        if (!mRestoringPreviousValueFlag) {
            mPreviousValue = s.toString()
            mCursorPosition = mEditText.selectionStart
        }
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(s: Editable) {
        if (!mRestoringPreviousValueFlag) {

            if (!isValid(s.toString())) {
                mRestoringPreviousValueFlag = true
                restorePreviousValue()
            }

        } else {
            mRestoringPreviousValueFlag = false
        }
    }

    private fun restorePreviousValue() {
        mEditText.setText(mPreviousValue)
        mEditText.setSelection(mCursorPosition)
    }

    private fun isValid(s: String): Boolean {
        val patternWithDot = Pattern.compile("[0-9]*((\\.[0-9]{0,$mDigitsAfterZero})?)||(\\.)?")
        val patternWithComma = Pattern.compile("[0-9]*((,[0-9]{0,$mDigitsAfterZero})?)||(,)?")

        val matcherDot = patternWithDot.matcher(s)
        val matcherComa = patternWithComma.matcher(s)

        return matcherDot.matches() || matcherComa.matches()
    }
}