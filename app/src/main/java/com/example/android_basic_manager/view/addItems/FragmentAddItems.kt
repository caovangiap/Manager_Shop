package com.example.android_basic_manager.view.addItems

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.android_basic_manager.MainActivity
import com.example.android_basic_manager.R
import com.example.android_basic_manager.databinding.FragmentAddItemsBinding
import com.example.android_basic_manager.viewmodel.ViewModelAddItems
import java.util.*

class FragmentAddItems : Fragment() {

    lateinit var binding : FragmentAddItemsBinding
    lateinit var mView: View
    lateinit var ViewModel : ViewModelAddItems

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddItemsBinding.inflate(inflater,container,false)
        mView = binding.root
        ViewModel = MainActivity.vMAddItems
        allFuncition()
        return mView
    }

    fun allFuncition(){

        // set up tool bar
        fToolBar()
        // Set up input edit text day of birth
        SetUpDateOfBirth()
    }

    // set up tool bar
    fun fToolBar(){
        binding.myToolbar.inflateMenu(R.menu.alltoolbar)
        binding.myToolbar.setNavigationIcon(R.drawable.back)
        //yêu cầu activity điều hướng quay trở lại man manager
        binding.myToolbar.setNavigationOnClickListener {
            ViewModel.nextAction.postValue("Manager")

        }
    }

    // Set up input edit text day of birth
    @SuppressLint("SetTextI18n")
    fun SetUpDateOfBirth() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        binding.inputTime.setOnClickListener {
            activity?.let {
                DatePickerDialog(
                    it,
                    { view, year, monthOfYear, dayOfMonth ->

                        // Display Selected date in textbox
                        binding.inputTime.setText("$dayOfMonth  $monthOfYear, $year")

                    },
                    year,
                    month,
                    day
                )
            }?.show()
        }
    }
}