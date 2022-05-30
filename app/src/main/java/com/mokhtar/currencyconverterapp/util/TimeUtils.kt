package com.mokhtar.currencyconverterapp.util

import java.text.SimpleDateFormat
import java.util.*

 fun getDate(previousDays:Int=0, cal: Calendar = Calendar.getInstance()): String
{
    if(previousDays>0)
    cal.add(Calendar.DAY_OF_MONTH,previousDays*-1)

    val format1 = SimpleDateFormat("yyyy-MM-dd")
    return format1.format(cal.time)
}

