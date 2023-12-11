package com.ehayvan.app.modules.petownerdashboard.ui

import android.view.View
import androidx.activity.viewModels
import com.ehayvan.app.R
import com.ehayvan.app.appcomponents.base.BaseActivity
import com.ehayvan.app.databinding.ActivityPetOwnerDashboardBinding
import com.ehayvan.app.modules.petownerdashboard.`data`.model.ListPetRowModel
import com.ehayvan.app.modules.petownerdashboard.`data`.viewmodel.PetOwnerDashboardVM
import kotlin.String
import kotlin.Unit

class PetOwnerDashboardActivity :
  BaseActivity<ActivityPetOwnerDashboardBinding>(R.layout.activity_pet_owner_dashboard) {

  private val viewModel: PetOwnerDashboardVM by viewModels<PetOwnerDashboardVM>()
  private lateinit var listPetAdapter: ListPetAdapter

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.petOwnerDashboardVM = viewModel

    // Initialize and set up the RecyclerView with the ListPetAdapter
    binding.recyclerListPet.adapter = listPetAdapter

    // Optional: Set up item click listener for RecyclerView items
    listPetAdapter.setOnItemClickListener(object : ListPetAdapter.OnItemClickListener {
      override fun onItemClick(view: android.view.View, position: Int, item: ListPetRowModel) {
        onClickRecyclerListPet(view, position, item)
      }
    })
  }

  override fun setUpClicks(): Unit {
    // Set up any additional click listeners if needed
  }

  fun onClickRecyclerListPet(
    view: View,
    position: Int,
    item: ListPetRowModel
  ): Unit {
    when(view.id) {
      // Handle item click actions if needed
    }
  }

  companion object {
    const val TAG: String = "PET_OWNER_DASHBOARD_ACTIVITY"
  }
}
