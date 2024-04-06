package com.dirion.walltechtodo.view.features.notes

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import com.dirion.walltechtodo.App
import com.dirion.walltechtodo.MainActivity
import com.dirion.walltechtodo.R
import com.dirion.walltechtodo.databinding.FragmentNotesBinding
import com.dirion.walltechtodo.view.features.BaseFragment
import com.dirion.walltechtodo.view.features.names.NamesFragment
import javax.inject.Inject

class NotesFragment: BaseFragment<FragmentNotesBinding>(FragmentNotesBinding::inflate){

    @Inject
    lateinit var viewModelFactory: NotesViewModelFactory

    private val viewModel: NotesViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {

        App.presentationComponent.notesFragmentComponentBuilder()
            .build()
            .inject(this@NotesFragment)

        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.etNotes.requestFocus()

        setStartText()
        setOnBackListener()
        setOnNotesChangeListener()
    }

    private fun setOnBackListener() {
        binding.btnBack.setOnClickListener {
            viewModel.saveNotes()
            MainActivity.activityComponent.navigationController().navigate(R.id.action_notesFragment_to_settingsFragment)
        }
    }

    private fun setOnNotesChangeListener() {
        binding.etNotes.doOnTextChanged { text, start, before, count ->
            viewModel.updateData(text.toString())
        }
    }

    private fun setStartText() {
        binding.etNotes.setText(viewModel.data.value)
    }

    companion object {
        fun newInstance() = NamesFragment()
    }

}