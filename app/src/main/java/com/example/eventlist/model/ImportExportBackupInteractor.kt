package com.example.eventlist.model

import android.content.Context
import android.util.Log
import com.example.eventlist.database.entities.Event
import com.example.eventlist.interfaces.ImportExportBackupInterface
import com.example.eventlist.objects.EventApp
import com.example.eventlist.presenter.ImportExportBackupPresenter
import com.example.eventlist.util.Util
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileWriter

class ImportExportBackupInteractor(val presenter: ImportExportBackupPresenter) :
    ImportExportBackupInterface.ImportExportBackupInteractor {

    override fun exportCSV(appContext: Context) {
        CoroutineScope(Dispatchers.Main).launch {
            val folder = File("/storage/emulated/0/Download")

            val csvFileName = "sqlitebackup.txt"
            val fileNameAndPath = "$folder/$csvFileName"

            lateinit var event: List<Event>
            withContext(Dispatchers.IO) {
                event = EventApp.getDB().eventDao().showAllEvents()
            }
            try {
                val fw = FileWriter(fileNameAndPath)
                for (i in event.indices) {
                    fw.append("" + event[i].name + ", " + event[i].dateCreation + ", " + event[i].date + ", " + event[i].typeEvent + ", " + event[i].notification + ", " + event[i].deleteEvent + ", " + event[i].idEvent + "\n")
                }
                fw.flush()
                fw.close()

                presenter.backUpSuccessful()
            } catch (e: Exception) {
                Log.v(Util.TAG_EXPORTIMPORTBACKUP, e.message.toString())
            }
        }
    }
}