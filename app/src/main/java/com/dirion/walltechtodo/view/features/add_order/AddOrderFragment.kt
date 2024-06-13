package com.dirion.walltechtodo.view.features.add_order

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.*
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.core.view.children
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.dirion.walltechtodo.App
import com.dirion.walltechtodo.R
import com.dirion.walltechtodo.data.models.network.rest.ORDER
import com.dirion.walltechtodo.databinding.FragmentAddTaskBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


class AddOrderFragment: BottomSheetDialogFragment(){

    private lateinit var binding: FragmentAddTaskBinding

    private var reloadData: (() -> Unit)? = null

    @Inject
    lateinit var viewModelFactory: AddOrderViewModelFactory

    private val viewModel: AddOrderViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.taskComponent.addOrderFragmentComponentBuilder()
            .build()
            .inject(this)

        super.onCreate(savedInstanceState)
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(requireContext()).apply {
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        inflateBinding(inflater, container)
        setAddTaskListener()
        observeState()

        // Format Spinner


        //PrePay
        val prePayList = listOf("Да", "Нет")
        val prePayAdapter = ArrayAdapter(requireContext(), R.layout.item_format, prePayList)
        binding.spnPrepayment.adapter = prePayAdapter

        
        return binding.root
    }

    private fun setAddTaskListener() {
        binding.tvSaveOrder.setOnClickListener {
            viewModel.addTask().invokeOnCompletion {
                reloadData!!.invoke()
                this.dismiss()
            }

        }

        binding.etAtDate.doOnTextChanged { text, start, before, count ->
            viewModel.updateAcceptance_date(text.toString())
        }

        binding.etCoDate.doOnTextChanged { text, start, before, count ->
            viewModel.updateCompletion_date(text.toString())
        }


        binding.spnCustomer.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                viewModel.updateCustomer(p0!!.adapter.getItem(p2).toString())

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }

        binding.etEdition.doOnTextChanged { text, start, before, count ->
            viewModel.updateEdition(text.toString())
        }

        binding.spnUser.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                viewModel.updateEmployer(p0!!.adapter.getItem(p2).toString())
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                viewModel.updateEmployer(p0!!.adapter.getItem(0).toString())
            }

        }


        binding.spnFromat.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Log.d("spnFromat", "${p0!!.adapter.getItem(p2)}")
                viewModel.updateFormat(p0!!.adapter.getItem(p2).toString())
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                viewModel.updateFormat(p0!!.adapter.getItem(0).toString())
            }

        }


        binding.etPages.doOnTextChanged { text, start, before, count ->
            viewModel.updatePagesCount(text.toString())
        }

        binding.etPaperType.doOnTextChanged { text, start, before, count ->
            viewModel.updatePaperType(text.toString())
        }

        binding.spnPrepayment.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                viewModel.updatePrepayment(p0!!.adapter.getItem(p2).toString())
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                viewModel.updatePrepayment("нет")
            }

        }

        binding.etPrinting.doOnTextChanged { text, start, before, count ->
            viewModel.updatePrinting(text.toString())
        }

        binding.etProductPrice.doOnTextChanged { text, start, before, count ->
            viewModel.updateProductPrice(text.toString())
        }

        binding.etProductType.doOnTextChanged { text, start, before, count ->
            viewModel.updateProductType(text.toString())
        }

        binding.spnTypography.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                viewModel.updateTipographyId(p0!!.adapter.getItem(p2).toString())
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                viewModel.updateTipographyId(p0!!.adapter.getItem(0).toString())
            }

        }
    }

    private fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        binding.root.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, App.displayMetrics.heightPixels)
    }

    private fun observeState() {
        viewModel.data
            .onEach { state ->
                if (binding.spnUser.children.count() == 0) {
                    val usersList = viewModel.data.value.model.employers
                    val usersAdapter = ArrayAdapter(requireContext(), R.layout.item_format, usersList)
                    binding.spnUser.adapter = usersAdapter
                }
                if (binding.spnTypography.children.count() == 0) {
                    val typList = viewModel.data.value.model.typographyList
                    val typAdapter = ArrayAdapter(requireContext(), R.layout.item_format, typList)
                    binding.spnTypography.adapter = typAdapter
                }
                if (binding.spnFromat.children.count() == 0) {
                    val formatList = ORDER.Format.entries
                        .map {
                            ORDER.Format.convertToString(it)
                        }
                    val formatAdapter = ArrayAdapter(requireContext(), R.layout.item_format, formatList)
                    binding.spnFromat.adapter = formatAdapter
                }
                if (binding.spnCustomer.children.count() == 0) {
                    val custList = viewModel.data.value.model.customersList
                    val custAdapter = ArrayAdapter(requireContext(), R.layout.item_format, custList)
                    binding.spnCustomer.adapter = custAdapter
                }

            }
            .launchIn(lifecycleScope)
    }
    companion object {
        fun newInstance(
            reloadData: () -> Unit
        ) = AddOrderFragment().apply {
            this.reloadData = reloadData
        }
    }

}