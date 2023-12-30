package com.ehayvan.app.modules.editpetrecordsthree.ui

import androidx.activity.viewModels
import com.ehayvan.app.R
import com.ehayvan.app.appcomponents.base.BaseActivity
import com.ehayvan.app.databinding.ActivityEditPetRecordsThreeBinding
import com.ehayvan.app.modules.editpetrecordsthree.`data`.viewmodel.EditPetRecordsThreeVM
import kotlin.String
import kotlin.Unit

class EditPetRecordsThreeActivity :
    BaseActivity<ActivityEditPetRecordsThreeBinding>(R.layout.activity_edit_pet_records_three) {
  private val viewModel: EditPetRecordsThreeVM by viewModels<EditPetRecordsThreeVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.editPetRecordsThreeVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.imageArrowleft.setOnClickListener {
      onBackPressed()
    }
  }

  companion object {
    const val TAG: String = "EDIT_PET_RECORDS_THREE_ACTIVITY"

  }
}
