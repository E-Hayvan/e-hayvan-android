package com.ehayvan.app.modules.petownerdashboard.ui

import androidx.activity.viewModels
import com.ehayvan.app.R
import com.ehayvan.app.appcomponents.base.BaseActivity
import com.ehayvan.app.databinding.ActivityPetOwnerDashboardBinding
import com.ehayvan.app.modules.petownerdashboard.`data`.viewmodel.PetOwnerDashboardVM
import kotlin.String
import kotlin.Unit

class PetOwnerDashboardActivity :
    BaseActivity<ActivityPetOwnerDashboardBinding>(R.layout.activity_pet_owner_dashboard) {
  private val viewModel: PetOwnerDashboardVM by viewModels<PetOwnerDashboardVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.petOwnerDashboardVM = viewModel
  }

  override fun setUpClicks(): Unit {
  }

  companion object {
    const val TAG: String = "PET_OWNER_DASHBOARD_ACTIVITY"

  }
}
