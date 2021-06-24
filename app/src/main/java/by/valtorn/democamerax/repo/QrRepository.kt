package by.valtorn.democamerax.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object QrRepository {
    private val mQrBase = MutableLiveData<MutableSet<String>>()
    val qrBase: LiveData<MutableSet<String>> = mQrBase

    fun addToBase(str: String) {
        mQrBase.value = qrBase.value.orEmpty().plus("$str\n").toMutableSet()
    }
}