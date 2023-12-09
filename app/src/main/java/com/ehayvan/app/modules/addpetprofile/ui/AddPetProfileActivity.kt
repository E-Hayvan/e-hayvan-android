package com.ehayvan.app.modules.addpetprofile.ui

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
    binding.addPetProfileVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.imageArrowleft.setOnClickListener {
      finish()
    }
  }

  companion object {
    const val TAG: String = "ADD_PET_PROFILE_ACTIVITY"

  }
}
