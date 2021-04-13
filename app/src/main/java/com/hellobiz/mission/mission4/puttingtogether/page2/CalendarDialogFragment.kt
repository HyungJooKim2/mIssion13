package com.hellobiz.mission.mission4.puttingtogether.page2

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.CalendarView
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import com.hellobiz.mission.databinding.DialogLayout2Binding
import com.hellobiz.mission.store_computerization.MainActivity
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.properties.Delegates

/*
날짜 Dialog Fragment
 */
class CalendarDialogFragment() : DialogFragment(), View.OnClickListener {
    private var mBinding: DialogLayout2Binding? = null
    private val binding get() = mBinding!!
    private var listener2: DialClickListener2? = null
    private var sendYear by Delegates.notNull<Int>()
    private var sendMonth by Delegates.notNull<Int>()
    private var sendDate by Delegates.notNull<Int>()

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DialogLayout2Binding.inflate(inflater, container, false)
        //본 다이얼로그 생성 스타일을 따르지 않고 생성
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //액션바 타이틀 제거
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        getToday()      //default값 현재 날짜로 설정
        binding.okay.setOnClickListener(this)
        binding.cancel.setOnClickListener(this)

        binding.bookDatePicker.setOnDateChangedListener(object : CalendarView.OnDateChangeListener,
            DatePicker.OnDateChangedListener {
            override fun onSelectedDayChange(
                view: CalendarView,
                year: Int,
                month: Int,
                dayOfMonth: Int
            ) {

            }

            //선택한 년,월,일을 받아오는 리스너
            override fun onDateChanged(
                view: DatePicker?,
                year: Int,
                monthOfYear: Int,
                dayOfMonth: Int
            ) {
                sendYear = year
                sendMonth = monthOfYear
                sendDate = dayOfMonth
            }

        })

        return binding.root
    }

    //년,월,일을 알려주는 리스너 콜백을 정의
    interface DialClickListener2 {
        fun DialMemberClick(year: Int, month: Int, date: Int)
    }

    //리스너에 클릭리스너 연결
    fun setOnItemClickListener(listener2: DialClickListener2) {
        this.listener2 = listener2
    }

    //default값 현재 날짜로 설정
    @RequiresApi(Build.VERSION_CODES.O)
    private fun getToday() {
        val now = LocalDate.now()
        val Strnow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        val arr = Strnow.split("-")
        sendYear = arr[0].toInt()
        sendMonth = arr[1].toInt() - 1
        sendDate = arr[2].toInt()
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.okay -> {
                //선택한 년,월,일 을 activity로 보냄
                if (listener2 != null) {
                    listener2!!.DialMemberClick(sendYear, sendMonth, sendDate)
                }
                dismiss()
            }
            binding.cancel -> {
                dismiss()
            }
        }
    }
}