package com.ehayvan.app.modules.launchscreen.ui

import androidx.activity.viewModels
import com.ehayvan.app.R
import com.ehayvan.app.appcomponents.base.BaseActivity
import com.ehayvan.app.databinding.ActivityLaunchScreenBinding
import com.ehayvan.app.modules.launchscreen.`data`.viewmodel.LaunchScreenVM
import kotlin.String
import kotlin.Unit

class LaunchScreenActivity :
    BaseActivity<ActivityLaunchScreenBinding>(R.layout.activity_launch_screen) {
  private val viewModel: LaunchScreenVM by viewModels<LaunchScreenVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.launchScreenVM = viewModel
  }

  override fun setUpClicks(): Unit {
  }

  companion object {
    const val TAG: String = "LAUNCH_SCREEN_ACTIVITY"

  }
}
