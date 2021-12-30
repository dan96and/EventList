package com.example.eventlist.interfaces

import android.app.Activity
import android.content.Context

interface ImportExportBackupInterface {

    interface ImportExportBackupView {
        fun showToastMessage(message:String)
    }

    interface ImportExportBackupPresenter {

        //CHECK
        fun checkPermissions(appContext: Context, activity: Activity)
        fun requestWriteExternalStoragePermission(appContext: Context, activity: Activity)

        //PRESENTER-INTERACTOR
        fun exportCSV(appContext: Context)

        //INTERACTOR-PRESENTER
        fun backUpSuccessful()
    }

    interface ImportExportBackupInteractor {
        fun exportCSV(appContext: Context)
    }
}