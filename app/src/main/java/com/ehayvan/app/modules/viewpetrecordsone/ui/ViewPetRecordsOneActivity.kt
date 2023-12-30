package com.ehayvan.app.modules.viewpetrecordsone.ui

import androidx.activity.viewModels
import com.ehayvan.app.R
import com.ehayvan.app.appcomponents.base.BaseActivity
import com.ehayvan.app.databinding.ActivityViewPetRecordsOneBinding
import com.ehayvan.app.modules.viewpetrecordsone.`data`.viewmodel.ViewPetRecordsOneVM
import kotlin.String
import kotlin.Unit

class ViewPetRecordsOneActivity :
    BaseActivity<ActivityViewPetRecordsOneBinding>(R.layout.activity_view_pet_records_one) {
  private val viewModel: ViewPetRecordsOneVM by viewModels<ViewPetRecordsOneVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.viewPetRecordsOneVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.imageArrowleft.setOnClickListener {
      onBackPressed()
    }
  }

  companion object {
    const val TAG: String = "VIEW_PET_RECORDS_ONE_ACTIVITY"

  }
}
