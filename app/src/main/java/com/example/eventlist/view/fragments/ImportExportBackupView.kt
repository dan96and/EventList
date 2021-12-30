package com.example.eventlist.view.fragments

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.eventlist.databinding.FragmentImportExportBackupViewBinding
import com.example.eventlist.interfaces.ImportExportBackupInterface
import com.example.eventlist.presenter.ImportExportBackupPresenter
import com.example.eventlist.view.activities.InformationView

class ImportExportBackupView : Fragment(), ImportExportBackupInterface.ImportExportBackupView {

    private var _binding: FragmentImportExportBackupViewBinding? = null
    private val binding get() = _binding!!

    val presenter = ImportExportBackupPresenter(this)

    val activityInformation = InformationView()

    private lateinit var appContext: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentImportExportBackupViewBinding.inflate(inflater, container, false)

        binding.btnExportSQLToStorage.setOnClickListener {
            //Go to Presenter
            activityInformation.let { it1 -> presenter.checkPermissions(appContext, it1) }
        }

        return binding.root
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 777) { //El permiso ha sido aceptado primera vez
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                presenter.exportCSV(appContext)
            } else {
                //El permiso NO ha sido aceptado por primera vez
                showToastMessage("Permits rejected")
            }
        }
    }

    override fun showToastMessage(message: String) {
        Toast.makeText(appContext, message, Toast.LENGTH_SHORT).show()
    }

    override fun onAttach(context: Context) {
        appContext = context
        super.onAttach(context)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}