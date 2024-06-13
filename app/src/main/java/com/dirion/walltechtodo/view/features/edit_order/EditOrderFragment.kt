package com.dirion.walltechtodo.view.features.edit_order

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.children
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.dirion.walltechtodo.App
import com.dirion.walltechtodo.R
import com.dirion.walltechtodo.data.models.network.rest.ORDER
import com.dirion.walltechtodo.databinding.FragmentEditTaskBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class EditOrderFragment: BottomSheetDialogFragment(){

    private lateinit var binding: FragmentEditTaskBinding


    private var orderId = ""
    private var reloadData: (() -> Unit)? = null

    @Inject
    lateinit var viewModelFactory: EditTaskViewModelFactory

    private val viewModel: EditTaskViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.taskComponent.editTaskFragmentComponentBuilder()
            .build()
            .inject(this@EditOrderFragment)

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
        init()
        observeState()
        setEditTaskListener()

        //PrePay
        val prePayList = listOf("Да", "Нет")
        val prePayAdapter = ArrayAdapter(requireContext(), R.layout.item_format, prePayList)
        binding.spnPrepayment.adapter = prePayAdapter

        return binding.root
    }

    private fun init() {
        viewModel.fetchTask(orderId)
    }


    private fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = FragmentEditTaskBinding.inflate(inflater, container, false)
        binding.root.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            App.displayMetrics.heightPixels
        )
    }


    private fun observeState() {
        viewModel.data
            .onEach {
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

                binding.etAtDate.setText(
                    it.model.acceptance_date
                )
                binding.etCoDate.setText(
                    it.model.completion_date
                )
                binding.etEdition.setText(
                    it.model.edition
                )
                binding.etPages.setText(
                    it.model.pages_count.toString()
                )
                binding.etPaperType.setText(
                    it.model.paper_type
                )
                binding.etPrinting.setText(
                    it.model.printing.toString()
                )
                binding.etProductPrice.setText(
                    it.model.product_price.toString()
                )
                binding.etProductType.setText(
                    it.model.product_type
                )
            }
            .launchIn(lifecycleScope)
    }

    private fun setEditTaskListener() {
        binding.tvSaveOrder.setOnClickListener {
            viewModel.updateTask().invokeOnCompletion {
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


        binding.spnCustomer.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                viewModel.updateCustomer(p0!!.adapter.getItem(p2).toString())

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }

        binding.etEdition.doOnTextChanged { text, start, before, count ->
            viewModel.updateEdition(text.toString())
        }

        binding.spnUser.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                viewModel.updateEmployer(p0!!.adapter.getItem(p2).toString())
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                viewModel.updateEmployer(p0!!.adapter.getItem(0).toString())
            }

        }


        binding.spnFromat.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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

        binding.spnPrepayment.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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

        binding.spnTypography.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                viewModel.updateTipographyId(p0!!.adapter.getItem(p2).toString())
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                viewModel.updateTipographyId(p0!!.adapter.getItem(0).toString())
            }

        }
    }


    companion object {
        fun newInstance(
            orderId: String,
            reloadData: () -> Unit
        ) = EditOrderFragment().apply {
            this.orderId = orderId
            this.reloadData = reloadData
        }
    }

}