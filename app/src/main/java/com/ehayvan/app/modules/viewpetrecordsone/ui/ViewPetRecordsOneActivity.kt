package com.ehayvan.app.modules.viewpetrecordsone.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.ehayvan.app.R
import com.ehayvan.app.appcomponents.base.BaseActivity
import com.ehayvan.app.databinding.ActivityViewPetRecordsOneBinding
import com.ehayvan.app.modules.editpetrecordstwo.ui.EditPetRecordsTwoActivity
import com.ehayvan.app.modules.viewpetrecordsone.`data`.viewmodel.ViewPetRecordsOneVM
import com.ehayvan.app.modules.viewpetrecordstwo.ui.ViewPetRecordsTwoActivity
import kotlin.String
import kotlin.Unit

class ViewPetRecordsOneActivity :
    BaseActivity<ActivityViewPetRecordsOneBinding>(R.layout.activity_view_pet_records_one) {
  private val viewModel: ViewPetRecordsOneVM by viewModels<ViewPetRecordsOneVM>()
  private lateinit var petID: String
  private lateinit var ownerID: String
  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    viewModel.viewPetRecordsOneModel.value?.txtPetName = intent.extras?.getString("petName")
    petID = intent.extras?.getString("petID").toString()
    ownerID = intent.extras?.getString("ownerID").toString()

    binding.viewPetRecordsOneVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.imageArrowleft.setOnClickListener {
      onBackPressed()
    }
    binding.linearContent.setOnClickListener {
      val bundle = Bundle()
      val intent = Intent(this, ViewPetRecordsTwoActivity::class.java)
      bundle.putString("petName", viewModel.viewPetRecordsOneModel.value?.txtPetName)
      bundle.putString("petID", petID)
      bundle.putString("ownerID", ownerID)
      bundle.putString("medType", "Vaccines")
      intent.putExtras(bundle)
      startActivity(intent)
    }
    binding.linearContent1.setOnClickListener {
      val bundle = Bundle()
      val intent = Intent(this, ViewPetRecordsTwoActivity::class.java)
      bundle.putString("petName", viewModel.viewPetRecordsOneModel.value?.txtPetName)
      bundle.putString("petID", petID)
      bundle.putString("ownerID", ownerID)
      bundle.putString("medType", "Drops")
      intent.putExtras(bundle)
      startActivity(intent)
    }
    binding.linearContent2.setOnClickListener {
      val bundle = Bundle()
      val intent = Intent(this, ViewPetRecordsTwoActivity::class.java)
      bundle.putString("petName", viewModel.viewPetRecordsOneModel.value?.txtPetName)
      bundle.putString("petID", petID)
      bundle.putString("medType", "Medications")
      bundle.putString("ownerID", ownerID)
      intent.putExtras(bundle)
      startActivity(intent)
    }
  }

  companion object {
    const val TAG: String = "VIEW_PET_RECORDS_ONE_ACTIVITY"

  }
}
