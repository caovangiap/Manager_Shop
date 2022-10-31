package com.example.android_basic_manager.view.addItems

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.android_basic_manager.MainActivity
import com.example.android_basic_manager.R
import com.example.android_basic_manager.databinding.FragmentAddItemsBinding
import com.example.android_basic_manager.model.AccessoryData
import com.example.android_basic_manager.viewmodel.ViewModelAddItems
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import java.util.*

class FragmentAddItems : Fragment() {

    lateinit var binding: FragmentAddItemsBinding
    lateinit var mView: View
    lateinit var ViewModel: ViewModelAddItems
    lateinit var storage: FirebaseStorage

    // lấy uri từ máy update lên storage
    var ImageUri: Uri? = null
    val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddItemsBinding.inflate(inflater, container, false)
        mView = binding.root
        ViewModel = MainActivity.vMAddItems
        storage = Firebase.storage
        allFuncition()
        return mView
    }

    fun allFuncition() {

        // set up tool bar
        fToolBar()
        // Set up input edit text day of birth
        SetUpDateOfBirth()
        // update ảnh
        addImage()
        // thêm sản phẩm mới
        putDataItems()
        // gọi data tất cả các sản phẩm
        ViewModel.allProduct()
    }

    // set up tool bar
    fun fToolBar() {
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

    fun addImage() {
        val getImage = registerForActivityResult(
            ActivityResultContracts.GetContent(),
            ActivityResultCallback {
                binding.image.setImageURI(it)
                ImageUri = it
                Log.d("url", it.toString())
            }
        )
        binding.selecimage.setOnClickListener {
            getImage.launch("image/*")
        }
    }

    // Put file ảnh lên firebase lấy uri
    fun getUrlStorage(nameProduct: String,timeUpProduct: String, priceProduct : String) {
        if (ImageUri != null) {
            val storageReference = storage.reference.child("Product/Men/NewProduct")
            storageReference.putFile(ImageUri!!).addOnSuccessListener {

                Toast.makeText(MainActivity.ApplicationContext, "success", Toast.LENGTH_SHORT)
                    .show()

                // lấy url ảnh trên file base
                storageReference.downloadUrl.addOnSuccessListener { p0 ->
                    run {
                        Log.d("image1234", "uri " + p0)
                        // url ảnh trên file base
                        val urlImage = p0.toString()

                        // day data new product len
                        val data = AccessoryData("11",nameProduct,priceProduct,"True",urlImage,"10")
                        db.collection("Product").document("Accessory")
                            .collection("AllAccessory")
                            .document(nameProduct)
                            .set(data, SetOptions.merge())
                    }
                }
            }.addOnFailureListener {
                Toast.makeText(MainActivity.ApplicationContext, "failer", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(
                MainActivity.ApplicationContext,
                "vui lòng chọn avatar",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun putDataItems() {

        // check thong tin dau vao và lay san pham
        binding.putdata.setOnClickListener {
            if (binding.inputName.text.toString() != ""
                && binding.inputprice.text.toString() != ""
                && binding.inputTime.text.toString() != ""
            ) {
                getUrlStorage(binding.inputName.text.toString(),
                    binding.inputTime.text.toString(),
                    binding.inputprice.text.toString())

                ViewModel.nextAction.value = "Manager"
            } else {
                Toast.makeText(context, "Nhập Thiếu Thông tin", Toast.LENGTH_SHORT).show()
            }

        }
    }

}