package com.example.eventlist.model

import android.util.Log
import com.example.eventlist.interfaces.MenuInformationInterface
import com.example.eventlist.presenter.MenuInformationPresenter
import com.example.eventlist.util.Util
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MenuInformationInteractor(private var presenter: MenuInformationPresenter) :
    MenuInformationInterface.MenuInformationInteractor {

    var auth = FirebaseAuth.getInstance()
    val user = Firebase.auth.currentUser!!


    override fun logOutFirebase() {
        auth.signOut()
        Log.v(Util.TAG_MENU_LOGOUT, "Log out de la cuenta. Interactor llamando al Presenter..")
        presenter.logOutSuccesfull()
    }

    override fun deleteAccountFirebase() {
        user.delete().addOnCompleteListener {
            if (it.isSuccessful) {
                Log.v(Util.TAG_DELETEACCOUNT, "Cuenta eliminada. Interactor comunicando con el presenter..")
                presenter.deleteAccountSuccesfull()
            }
        }
    }
}