package com.example.eventlist.presenter

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.core.content.ContextCompat
import com.example.eventlist.interfaces.ImportExportBackupInterface
import com.example.eventlist.model.ImportExportBackupInteractor
import com.example.eventlist.view.fragments.ImportExportBackupView

class ImportExportBackupPresenter (val view : ImportExportBackupView) : ImportExportBackupInterface.ImportExportBackupPresenter {

    val interactor = ImportExportBackupInteractor (this)

    //Comprobamos que los permisos han sido aceptado
    override fun checkPermissions(appContext: Context, activity: Activity) {
        if(ContextCompat.checkSelfPermission(appContext, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            //Permiso NO han sido aceptados
            requestWriteExternalStoragePermission(appContext, activity)
        }else{
            //Permiso SI han sido aceptados
            interactor.exportCSV(appContext)
        }
    }

    override fun requestWriteExternalStoragePermission(appContext: Context, activity: Activity) {
        if(shouldShowRequestPermissionRationale(activity, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            //El usuario ya ha rechazado los permisos, no volvera aparecer el mensaje, tendra que hacerlo de manera manual
            view.showToastMessage("Permits rejected")
        }else{
            //Solicitamos los permisos
            requestPermissions(activity,arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), 777)
        }
    }

    override fun exportCSV(appContext: Context) {
        interactor.exportCSV(appContext)
    }

    override fun backUpSuccessful() {
        view.showToastMessage("Back done")
    }
}