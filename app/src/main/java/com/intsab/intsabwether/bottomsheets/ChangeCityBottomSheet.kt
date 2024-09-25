package com.intsab.intsabwether.bottomsheets

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.intsab.intsabwether.databinding.ChangeCityLayoutBinding

/**
 * Created by intsabhaider
 * on 25,September,2024
 */
class ChangeCityBottomSheet : BottomSheetDialogFragment() {
    private lateinit var binding: ChangeCityLayoutBinding
    private var onCitySaved: ((String) -> Unit)? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = ChangeCityLayoutBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.icClose.setOnClickListener {
            this.dismiss()
        }
        binding.saveCity.setOnClickListener {
            val city = binding.etCity.text.toString()
            if (city.isEmpty()) {
                Toast.makeText(requireContext(), "Please Enter Something", Toast.LENGTH_SHORT)
                    .show()
            } else {
                onCitySaved?.invoke(city)
                dismiss()
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        dialog?.setOnShowListener { it ->
            val d = it as BottomSheetDialog
            val bottomSheet =
                d.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet?.let {
                val behavior = BottomSheetBehavior.from(it)
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        return super.onCreateDialog(savedInstanceState)
    }

    fun setOnCitySavedListener(listener: (String) -> Unit) {
        this.onCitySaved = listener
    }

    companion object {
        const val TAG = "ChangeCityBottomSheet"
    }
}