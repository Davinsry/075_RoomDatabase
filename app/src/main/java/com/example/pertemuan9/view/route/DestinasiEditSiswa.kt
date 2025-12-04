package com.example.pertemuan9.view.route

import com.example.pertemuan9.R

// Perhatikan huruf 'n' kecil pada Destinasinavigasi
object DestinasiEditSiswa : Destinasinavigasi {
    override val route = "item_edit"
    override val titleRes = R.string.edit_siswa
    const val itemIdArg = "idSiswa"

    // Tambahkan kurung kurawal } di akhir string ini
    val routeWithArgs = "$route/{$itemIdArg}"
}