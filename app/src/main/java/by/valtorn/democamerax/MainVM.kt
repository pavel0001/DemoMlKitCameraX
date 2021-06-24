package by.valtorn.democamerax

import androidx.lifecycle.ViewModel
import by.valtorn.democamerax.repo.QrRepository

class MainVM : ViewModel() {
    val qrBase = QrRepository.qrBase

    fun addToBase(str: String) = QrRepository.addToBase(str)
}