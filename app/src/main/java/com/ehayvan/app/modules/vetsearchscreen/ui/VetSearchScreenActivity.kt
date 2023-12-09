package com.ehayvan.app.modules.vetsearchscreen.ui

import androidx.activity.viewModels
import com.ehayvan.app.R
import com.ehayvan.app.appcomponents.base.BaseActivity
import com.ehayvan.app.databinding.ActivityVetSearchScreenBinding
import com.ehayvan.app.modules.vetsearchscreen.`data`.viewmodel.VetSearchScreenVM
import kotlin.String
import kotlin.Unit

class VetSearchScreenActivity :
    BaseActivity<ActivityVetSearchScreenBinding>(R.layout.activity_vet_search_screen) {
  private val viewModel: VetSearchScreenVM by viewModels<VetSearchScreenVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.vetSearchScreenVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.imageArrowleft.setOnClickListener {
      finish()
    }
  }

  companion object {
    const val TAG: String = "VET_SEARCH_SCREEN_ACTIVITY"

  }
}
