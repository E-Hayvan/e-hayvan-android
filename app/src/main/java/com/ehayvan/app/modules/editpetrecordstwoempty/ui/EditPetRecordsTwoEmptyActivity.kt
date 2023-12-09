package com.ehayvan.app.modules.editpetrecordstwoempty.ui

import androidx.activity.viewModels
import com.ehayvan.app.R
import com.ehayvan.app.appcomponents.base.BaseActivity
import com.ehayvan.app.databinding.ActivityEditPetRecordsTwoEmptyBinding
import com.ehayvan.app.modules.editpetrecordstwoempty.`data`.viewmodel.EditPetRecordsTwoEmptyVM
import kotlin.String
import kotlin.Unit

class EditPetRecordsTwoEmptyActivity :
    BaseActivity<ActivityEditPetRecordsTwoEmptyBinding>(R.layout.activity_edit_pet_records_two_empty)
    {
  private val viewModel: EditPetRecordsTwoEmptyVM by viewModels<EditPetRecordsTwoEmptyVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.editPetRecordsTwoEmptyVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.imageArrowleft.setOnClickListener {
      finish()
    }
  }

  companion object {
    const val TAG: String = "EDIT_PET_RECORDS_TWO_EMPTY_ACTIVITY"

  }
}
