package com.ehayvan.app.modules.editpetrecordsone.ui

import androidx.activity.viewModels
import com.ehayvan.app.R
import com.ehayvan.app.appcomponents.base.BaseActivity
import com.ehayvan.app.databinding.ActivityEditPetRecordsOneBinding
import com.ehayvan.app.modules.editpetrecordsone.`data`.viewmodel.EditPetRecordsOneVM
import kotlin.String
import kotlin.Unit

class EditPetRecordsOneActivity :
    BaseActivity<ActivityEditPetRecordsOneBinding>(R.layout.activity_edit_pet_records_one) {
  private val viewModel: EditPetRecordsOneVM by viewModels<EditPetRecordsOneVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.editPetRecordsOneVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.imageArrowleft.setOnClickListener {
      onBackPressed()
    }
  }

  companion object {
    const val TAG: String = "EDIT_PET_RECORDS_ONE_ACTIVITY"

  }
}
