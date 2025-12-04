package com.example.pertemuan9.viewmodel

import androidx.compose.runtime.getValue // <-- Perlu ini agar 'by' bekerja
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue // <-- Perlu ini agar bisa di-assign ulang
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pertemuan9.repositori.RepositoriSiswa
import com.example.pertemuan9.view.route.DestinasiDetailSiswa // Cek catatan di bawah
import com.example.pertemuan9.view.route.DestinasiEditSiswa // Kemungkinan kamu butuh ini
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditViewModel (
    savedStateHandle: SavedStateHandle,
    private val repositoriSiswa: RepositoriSiswa
): ViewModel() {

    // Gunakan import getValue & setValue agar 'by' tidak error
    var uiStateSiswa by mutableStateOf(UIStateSiswa())
        private set

    // Catatan: Pastikan argumen kuncinya benar.
    // Jika di NavHost kamu pakai DestinasiEditSiswa.itemIdArg, ganti yang di bawah ini.
    private val idSiswa: Int = checkNotNull(savedStateHandle[DestinasiDetailSiswa.itemIdArg])

    init {
        viewModelScope.launch {
            uiStateSiswa = repositoriSiswa.getSiswaStream(idSiswa)
                .filterNotNull()
                .first()
                .toUiStateSiswa(true)
        }
    }

    fun updateUiState(detailSiswa: DetailSiswa) {
        uiStateSiswa =
            UIStateSiswa(detailSiswa = detailSiswa, isEntryValid = validasiInput(detailSiswa))
    }

    private fun validasiInput(uiState: DetailSiswa = uiStateSiswa.detailSiswa ): Boolean {
        return with(uiState) {
            nama.isNotBlank() && alamat.isNotBlank() && telpon.isNotBlank()
        }
    }

    suspend fun updateSiswa() {
        if (validasiInput(uiStateSiswa.detailSiswa)) {
            repositoriSiswa.updateSiswa(uiStateSiswa.detailSiswa.toSiswa())
        }
    }
}