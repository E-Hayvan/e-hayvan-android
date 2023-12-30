package com.ehayvan.app.modules.editpetprofile.ui

import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.viewModels
import com.ehayvan.app.R
import com.ehayvan.app.appcomponents.base.BaseActivity
import com.ehayvan.app.databinding.ActivityEditPetProfileBinding
import com.ehayvan.app.modules.editpetprofile.data.viewmodel.EditPetProfileVM


class EditPetProfileActivity :
    BaseActivity<ActivityEditPetProfileBinding>(R.layout.activity_edit_pet_profile) {
    private val viewModel: EditPetProfileVM by viewModels<EditPetProfileVM>()

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
        binding.editPetProfileVM = viewModel
    }

    override fun setUpClicks(): Unit {
        binding.imageArrowleft.setOnClickListener {
            onBackPressed()
        }
    }

    companion object {
        const val TAG: String = "EDIT_PET_PROFILE_ACTIVITY"

    }
}