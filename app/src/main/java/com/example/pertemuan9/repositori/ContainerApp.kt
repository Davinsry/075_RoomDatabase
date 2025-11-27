package com.example.pertemuan9.repositori

import android.app.Application
import android.content.Context
import com.example.pertemuan9.room.DatabaseSiswa


interface ContainerApp {
    val repositoriSiswa : RepositoriSiswa

}
class ContainerDataApp(private val context: Context):
    ContainerApp {
    override val repositoriSiswa: RepositoriSiswa by lazy {
        OfflineRepositoriSiswa(
            DatabaseSiswa.getDatabase(context).siswaDao()
        )
    }

}

class AplikasiSiswa : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     *
     */
    lateinit var container: ContainerApp

    override fun onCreate() {
        super.onCreate()
        container = ContainerDataApp(this)

    }
}

