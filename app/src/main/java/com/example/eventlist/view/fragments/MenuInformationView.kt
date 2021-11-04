package com.example.eventlist.view.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.eventlist.databinding.FragmentMenuInformationViewBinding
import com.example.eventlist.interfaces.MenuInformationInterface
import com.example.eventlist.presenter.MenuInformationPresenter
import com.example.eventlist.util.Util
import com.example.eventlist.view.activities.MainActivity

class MenuInformationView() : Fragment(), MenuInformationInterface.MenuInformationView {

    private var _binding: FragmentMenuInformationViewBinding? = null
    private val binding get() = _binding!!

    private val presenter = MenuInformationPresenter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMenuInformationViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogOut.setOnClickListener {
            Log.v(Util.TAG_REGISTER, "Vista llamando al Presenter..")
            presenter.logOut()
        }

        binding.btnDeleteAccount.setOnClickListener {
            showDialogConfirmDeleteAccount()
        }
    }
    
    //Muestra el dialog de confirmar la eliminacion de la cuenta
    private fun showDialogConfirmDeleteAccount() {
        val dialogWarning = SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
        dialogWarning.setTitleText("Estas seguro?")
            .setContentText("Recuerda que si eliminas la cuenta no la vas a poder recuperar")
            .setCancelText("Cancelar")
            .setConfirmText("Confirmar")
            .showCancelButton(true)
            .setConfirmClickListener(SweetAlertDialog.OnSweetClickListener {
                Log.v(Util.TAG_DELETEACCOUNT, "View comunicando con el presenter..")
                presenter.deleteAccount()
            })
            .show()
    }

    //Muestra el dialog de que la cuenta se ha eliminado correctamente
    override fun showDialogCompleteDeleteAccount() {
        val dialogSuccess = SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
        dialogSuccess.setTitleText("Cuenta eliminada :(")
            .setContentText("Esperemos volver a verte pronto")
            .setConfirmText("OK")
            .showCancelButton(false)
            .setConfirmClickListener(SweetAlertDialog.OnSweetClickListener {
                goToHomeLoginScreen()
            })
            .show()
    }

    override fun goToHomeLoginScreen() {
        val intent = Intent(context, MainActivity::class.java)
        intent.flags =
            Intent.FLAG_ACTIVITY_CLEAR_TASK  // Limpia el Stack, y en el la activity en la que estamos situados la hace la raiz
        activity?.finish()
        context?.startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}