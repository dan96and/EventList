package com.example.eventlist.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.eventlist.databinding.FragmentDialogDeleteEventViewBinding
import com.example.eventlist.interfaces.DialogDeleteEventInterface
import com.example.eventlist.presenter.DialogDeleteEventPresenter
import com.example.eventlist.util.Util

class DialogDeleteEventFragmentView(private val idEvent: String) : DialogFragment(),
    DialogDeleteEventInterface.DialogDeleteEventView {

    private var _binding: FragmentDialogDeleteEventViewBinding? = null
    private val binding get() = _binding!!
    private val presenter = DialogDeleteEventPresenter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDialogDeleteEventViewBinding.inflate(inflater, container, false)

        binding.btnCancel.setOnClickListener {
            dismiss()
        }

        binding.btnConfirm.setOnClickListener {
            Log.v(Util.TAG_SHOW_DELETEEVENT,"View comunicando con el presenter..")
            presenter.deleteEvent(idEvent)
        }

        return binding.root
    }

    override fun closeActivityShowMessage(message: String) {
        activity?.finish()
        Util.showToast(context, message)
    }

    override fun closeFragmentShowMessage(message: String) {
        dismiss()
        Util.showToast(context, message)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}