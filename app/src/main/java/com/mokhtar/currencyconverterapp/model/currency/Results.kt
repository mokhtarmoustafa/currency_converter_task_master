package com.mokhtar.currencyconverterapp.model.currency

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

//@Entity(tableName = TABLE_NAME)
@Parcelize
data class Results(
    @SerializedName("AED")
    val aED: Currency,
    @SerializedName("AFN")
    val aFN: Currency,
    @SerializedName("ALL")
    val aLL: Currency,
    @SerializedName("AMD")
    val aMD: Currency,
    @SerializedName("ANG")
    val aNG: Currency,
    @SerializedName("AOA")
    val aOA: Currency,
    @SerializedName("ARS")
    val aRS: Currency,
    @SerializedName("AUD")
    val aUD: Currency,
    @SerializedName("AWG")
    val aWG: Currency,
    @SerializedName("AZN")
    val aZN: Currency,
    @SerializedName("BAM")
    val bAM: Currency,
    @SerializedName("BBD")
    val bBD: Currency,
    @SerializedName("BDT")
    val bDT: Currency,
    @SerializedName("BGN")
    val bGN: Currency,
    @SerializedName("BHD")
    val bHD: Currency,
    @SerializedName("BIF")
    val bIF: Currency,
    @SerializedName("BMD")
    val bMD: Currency,
    @SerializedName("BND")
    val bND: Currency,
    @SerializedName("BOB")
    val bOB: Currency,
    @SerializedName("BRL")
    val bRL: Currency,
    @SerializedName("BSD")
    val bSD: Currency,
    @SerializedName("BTC")
    val bTC: Currency,
    @SerializedName("BTN")
    val bTN: Currency,
    @SerializedName("BWP")
    val bWP: Currency,
    @SerializedName("BYN")
    val bYN: Currency,
    @SerializedName("BYR")
    val bYR: Currency,
    @SerializedName("BZD")
    val bZD: Currency,
    @SerializedName("CAD")
    val cAD: Currency,
    @SerializedName("CDF")
    val cDF: Currency,
    @SerializedName("CHF")
    val cHF: Currency,
    @SerializedName("CLF")
    val cLF: Currency,
    @SerializedName("CLP")
    val cLP: Currency,
    @SerializedName("CNY")
    val cNY: Currency,
    @SerializedName("COP")
    val cOP: Currency,
    @SerializedName("CRC")
    val cRC: Currency,
    @SerializedName("CUC")
    val cUC: Currency,
    @SerializedName("CUP")
    val cUP: Currency,
    @SerializedName("CVE")
    val cVE: Currency,
    @SerializedName("CZK")
    val cZK: Currency,
    @SerializedName("DJF")
    val dJF: Currency,
    @SerializedName("DKK")
    val dKK: Currency,
    @SerializedName("DOP")
    val dOP: Currency,
    @SerializedName("DZD")
    val dZD: Currency,
    @SerializedName("EGP")
    val eGP: Currency,
    @SerializedName("ERN")
    val eRN: Currency,
    @SerializedName("ETB")
    val eTB: Currency,
    @SerializedName("EUR")
    val eUR: Currency,
    @SerializedName("FJD")
    val fJD: Currency,
    @SerializedName("FKP")
    val fKP: Currency,
    @SerializedName("GBP")
    val gBP: Currency,
    @SerializedName("GEL")
    val gEL: Currency,
    @SerializedName("GGP")
    val gGP: Currency,
    @SerializedName("GHS")
    val gHS: Currency,
    @SerializedName("GIP")
    val gIP: Currency,
    @SerializedName("GMD")
    val gMD: Currency,
    @SerializedName("GNF")
    val gNF: Currency,
    @SerializedName("GTQ")
    val gTQ: Currency,
    @SerializedName("GYD")
    val gYD: Currency,
    @SerializedName("HKD")
    val hKD: Currency,
    @SerializedName("HNL")
    val hNL: Currency,
    @SerializedName("HRK")
    val hRK: Currency,
    @SerializedName("HTG")
    val hTG: Currency,
    @SerializedName("HUF")
    val hUF: Currency,
    @SerializedName("IDR")
    val iDR: Currency,
    @SerializedName("ILS")
    val iLS: Currency,
    @SerializedName("IMP")
    val iMP: Currency,
    @SerializedName("INR")
    val iNR: Currency,
    @SerializedName("IQD")
    val iQD: Currency,
    @SerializedName("IRR")
    val iRR: Currency,
    @SerializedName("ISK")
    val iSK: Currency,
    @SerializedName("JEP")
    val jEP: Currency,
    @SerializedName("JMD")
    val jMD: Currency,
    @SerializedName("JOD")
    val jOD: Currency,
    @SerializedName("JPY")
    val jPY: Currency,
    @SerializedName("KES")
    val kES: Currency,
    @SerializedName("KGS")
    val kGS: Currency,
    @SerializedName("KHR")
    val kHR: Currency,
    @SerializedName("KMF")
    val kMF: Currency,
    @SerializedName("KPW")
    val kPW: Currency,
    @SerializedName("KRW")
    val kRW: Currency,
    @SerializedName("KWD")
    val kWD: Currency,
    @SerializedName("KYD")
    val kYD: Currency,
    @SerializedName("KZT")
    val kZT: Currency,
    @SerializedName("LAK")
    val lAK: Currency,
    @SerializedName("LBP")
    val lBP: Currency,
    @SerializedName("LKR")
    val lKR: Currency,
    @SerializedName("LRD")
    val lRD: Currency,
    @SerializedName("LSL")
    val lSL: Currency,
    @SerializedName("LVL")
    val lVL: Currency,
    @SerializedName("LYD")
    val lYD: Currency,
    @SerializedName("MAD")
    val mAD: Currency,
    @SerializedName("MDL")
    val mDL: Currency,
    @SerializedName("MGA")
    val mGA: Currency,
    @SerializedName("MKD")
    val mKD: Currency,
    @SerializedName("MMK")
    val mMK: Currency,
    @SerializedName("MNT")
    val mNT: Currency,
    @SerializedName("MOP")
    val mOP: Currency,
    @SerializedName("MRO")
    val mRO: Currency,
    @SerializedName("MUR")
    val mUR: Currency,
    @SerializedName("MVR")
    val mVR: Currency,
    @SerializedName("MWK")
    val mWK: Currency,
    @SerializedName("MXN")
    val mXN: Currency,
    @SerializedName("MYR")
    val mYR: Currency,
    @SerializedName("MZN")
    val mZN: Currency,
    @SerializedName("NAD")
    val nAD: Currency,
    @SerializedName("NGN")
    val nGN: Currency,
    @SerializedName("NIO")
    val nIO: Currency,
    @SerializedName("NOK")
    val nOK: Currency,
    @SerializedName("NPR")
    val nPR: Currency,
    @SerializedName("NZD")
    val nZD: Currency,
    @SerializedName("OMR")
    val oMR: Currency,
    @SerializedName("PAB")
    val pAB: Currency,
    @SerializedName("PEN")
    val pEN: Currency,
    @SerializedName("PGK")
    val pGK: Currency,
    @SerializedName("PHP")
    val pHP: Currency,
    @SerializedName("PKR")
    val pKR: Currency,
    @SerializedName("PLN")
    val pLN: Currency,
    @SerializedName("PYG")
    val pYG: Currency,
    @SerializedName("QAR")
    val qAR: Currency,
    @SerializedName("RON")
    val rON: Currency,
    @SerializedName("RSD")
    val rSD: Currency,
    @SerializedName("RUB")
    val rUB: Currency,
    @SerializedName("RWF")
    val rWF: Currency,
    @SerializedName("SAR")
    val sAR: Currency,
    @SerializedName("SBD")
    val sBD: Currency,
    @SerializedName("SCR")
    val sCR: Currency,
    @SerializedName("SDG")
    val sDG: Currency,
    @SerializedName("SEK")
    val sEK: Currency,
    @SerializedName("SGD")
    val sGD: Currency,
    @SerializedName("SHP")
    val sHP: Currency,
    @SerializedName("SLL")
    val sLL: Currency,
    @SerializedName("SOS")
    val sOS: Currency,
    @SerializedName("SRD")
    val sRD: Currency,
    @SerializedName("STD")
    val sTD: Currency,
    @SerializedName("SVC")
    val sVC: Currency,
    @SerializedName("SYP")
    val sYP: Currency,
    @SerializedName("SZL")
    val sZL: Currency,
    @SerializedName("THB")
    val tHB: Currency,
    @SerializedName("TJS")
    val tJS: Currency,
    @SerializedName("TMT")
    val tMT: Currency,
    @SerializedName("TND")
    val tND: Currency,
    @SerializedName("TOP")
    val tOP: Currency,
    @SerializedName("TRY")
    val tRY: Currency,
    @SerializedName("TTD")
    val tTD: Currency,
    @SerializedName("TWD")
    val tWD: Currency,
    @SerializedName("TZS")
    val tZS: Currency,
    @SerializedName("UAH")
    val uAH: Currency,
    @SerializedName("UGX")
    val uGX: Currency,
    @SerializedName("USD")
    val uSD: Currency,
    @SerializedName("UYU")
    val uYU: Currency,
    @SerializedName("UZS")
    val uZS: Currency,
    @SerializedName("VEF")
    val vEF: Currency,
    @SerializedName("VND")
    val vND: Currency,
    @SerializedName("VUV")
    val vUV: Currency,
    @SerializedName("WST")
    val wST: Currency,
    @SerializedName("XAF")
    val xAF: Currency,
    @SerializedName("XAG")
    val xAG: Currency,
    @SerializedName("XCD")
    val xCD: Currency,
    @SerializedName("XDR")
    val xDR: Currency,
    @SerializedName("XOF")
    val xOF: Currency,
    @SerializedName("XPF")
    val xPF: Currency,
    @SerializedName("YER")
    val yER: Currency,
    @SerializedName("ZAR")
    val zAR: Currency,
    @SerializedName("ZMK")
    val zMK: Currency,
    @SerializedName("ZMW")
    val zMW: Currency,
    @SerializedName("ZWL")
    val zWL: Currency
):Parcelable
//{
//    @PrimaryKey(autoGenerate = false)
//    var id = 0
//    companion object
//    {
//        const val TABLE_NAME="TBL_Currencies"
//    }
//}