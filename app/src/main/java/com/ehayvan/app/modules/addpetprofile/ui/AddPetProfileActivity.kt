package com.ehayvan.app.modules.addpetprofile.ui

import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.viewModels
import com.ehayvan.app.R
import com.ehayvan.app.appcomponents.base.BaseActivity
import com.ehayvan.app.databinding.ActivityAddPetProfileBinding
import com.ehayvan.app.modules.addpetprofile.`data`.viewmodel.AddPetProfileVM
import kotlin.String
import kotlin.Unit

class AddPetProfileActivity :
    BaseActivity<ActivityAddPetProfileBinding>(R.layout.activity_add_pet_profile) {
  private val viewModel: AddPetProfileVM by viewModels<AddPetProfileVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val mSpinner = findViewById<Spinner>(R.id.spinner)

    // Create a list to display in the Spinner
    val mList = arrayOf<String?>("Dog", "Cat", "Bird", "Other")

    // Create an adapter as shown below
    val mArrayAdapter = ArrayAdapter<Any?>(this, R.layout.spinner_list, mList)
    mArrayAdapter.setDropDownViewResource(R.layout.spinner_list)

    // Set the adapter to the Spinner
    mSpinner.prompt = "Select pet's type";
    mSpinner.adapter = mArrayAdapter
    mSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
      override fun onItemSelected(
        parentView: AdapterView<*>,
        selectedItemView: View,
        position: Int,
        id: Long
      ) {
        val selectedItem = parentView.getItemAtPosition(position).toString()
        Log.d("alfamsı",selectedItem)
      }

      override fun onNothingSelected(parentView: AdapterView<*>?) {
        // Do nothing here
      }
    }
    binding.addPetProfileVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.imageArrowleft.setOnClickListener {
      onBackPressed()
    }
  }

  companion object {
    const val TAG: String = "ADD_PET_PROFILE_ACTIVITY"

  }
}
