package com.ehayvan.app.modules.editpetrecordstwo.ui

import androidx.activity.viewModels
import com.ehayvan.app.R
import com.ehayvan.app.appcomponents.base.BaseActivity
import com.ehayvan.app.databinding.ActivityEditPetRecordsTwoBinding
import com.ehayvan.app.modules.editpetrecordstwo.`data`.viewmodel.EditPetRecordsTwoVM
import kotlin.String
import kotlin.Unit

class EditPetRecordsTwoActivity :
    BaseActivity<ActivityEditPetRecordsTwoBinding>(R.layout.activity_edit_pet_records_two) {
  private val viewModel: EditPetRecordsTwoVM by viewModels<EditPetRecordsTwoVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.editPetRecordsTwoVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.imageArrowleft.setOnClickListener {
      finish()
    }
  }

  companion object {
    const val TAG: String = "EDIT_PET_RECORDS_TWO_ACTIVITY"

  }
}
