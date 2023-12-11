package com.ehayvan.app.modules.vetsearchscreen.ui

import android.view.View
import androidx.activity.viewModels
import com.ehayvan.app.R
import com.ehayvan.app.appcomponents.base.BaseActivity
import com.ehayvan.app.databinding.ActivityVetSearchScreenBinding
import com.ehayvan.app.modules.vetsearchscreen.`data`.model.ListVetModel
import com.ehayvan.app.modules.vetsearchscreen.`data`.viewmodel.VetSearchScreenVM
import kotlin.String
import kotlin.Unit

class VetSearchScreenActivity :
  BaseActivity<ActivityVetSearchScreenBinding>(R.layout.activity_vet_search_screen) {

  private val viewModel: VetSearchScreenVM by viewModels<VetSearchScreenVM>()
  private lateinit var listVetAdapter: ListVetAdapter

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.vetSearchScreenVM = viewModel

    // Initialize and set up the RecyclerView with the ListVetAdapter
    binding.recyclerListVet.adapter = listVetAdapter

    // Optional: Set up item click listener for RecyclerView items
    listVetAdapter.setOnItemClickListener(object : ListVetAdapter.OnItemClickListener {
      override fun onItemClick(view: View, position: Int, item: ListVetModel) {
        onClickRecyclerVet(view, position, item)
      }
    })
  }

  override fun setUpClicks(): Unit {
    binding.imageArrowleft.setOnClickListener {
      finish()
    }
  }

  fun onClickRecyclerVet(
    view: View,
    position: Int,
    item: ListVetModel
  ): Unit {
    when(view.id) {
      //TODO Write the vet green style with api
    }
  }

  companion object {
    const val TAG: String = "VET_SEARCH_SCREEN_ACTIVITY"
  }
}
