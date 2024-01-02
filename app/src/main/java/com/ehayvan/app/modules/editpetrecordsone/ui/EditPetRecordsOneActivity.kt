package com.ehayvan.app.modules.editpetrecordsone.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.ehayvan.app.R
import com.ehayvan.app.appcomponents.base.BaseActivity
import com.ehayvan.app.databinding.ActivityEditPetRecordsOneBinding
import com.ehayvan.app.modules.editpetrecordsone.`data`.viewmodel.EditPetRecordsOneVM
import com.ehayvan.app.modules.editpetrecordstwo.ui.EditPetRecordsTwoActivity
import kotlin.String
import kotlin.Unit

class EditPetRecordsOneActivity :
    BaseActivity<ActivityEditPetRecordsOneBinding>(R.layout.activity_edit_pet_records_one) {
  private val viewModel: EditPetRecordsOneVM by viewModels<EditPetRecordsOneVM>()
  private lateinit var petID: String
  private lateinit var vetID: String
  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    petID = intent.extras?.getString("petID").toString()
    vetID = intent.extras?.getString("vetID").toString()
    viewModel.editPetRecordsOneModel.value?.txtPetName = intent.extras?.getString("petName").toString()
    binding.editPetRecordsOneVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.imageArrowleft.setOnClickListener {
      onBackPressed()
    }
    binding.linearContent.setOnClickListener {
      val bundle = Bundle()
      val intent = Intent(this, EditPetRecordsTwoActivity::class.java)
      bundle.putString("petName", viewModel.editPetRecordsOneModel.value?.txtPetName)
      bundle.putString("petID", petID)
      bundle.putString("vetID", vetID)
      bundle.putString("medType", "Vaccines")
      intent.putExtras(bundle)
      startActivity(intent)
    }
    binding.linearContent1.setOnClickListener {
      val bundle = Bundle()
      val intent = Intent(this, EditPetRecordsTwoActivity::class.java)
      bundle.putString("petName", viewModel.editPetRecordsOneModel.value?.txtPetName)
      bundle.putString("petID", petID)
      bundle.putString("vetID", vetID)
      bundle.putString("medType", "Drops")
      intent.putExtras(bundle)
      startActivity(intent)
    }
    binding.linearContent2.setOnClickListener {
      val bundle = Bundle()
      val intent = Intent(this, EditPetRecordsTwoActivity::class.java)
      bundle.putString("petName", viewModel.editPetRecordsOneModel.value?.txtPetName)
      bundle.putString("petID", petID)
      bundle.putString("medType", "Medications")
      bundle.putString("vetID", vetID)
      intent.putExtras(bundle)
      startActivity(intent)
    }
  }

  companion object {
    const val TAG: String = "EDIT_PET_RECORDS_ONE_ACTIVITY"

  }
}
