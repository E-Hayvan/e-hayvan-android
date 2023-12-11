package com.ehayvan.app.modules.viewpetrecordstwo.ui

import androidx.activity.viewModels
import com.ehayvan.app.R
import com.ehayvan.app.appcomponents.base.BaseActivity
import com.ehayvan.app.databinding.ActivityViewPetRecordsTwoBinding
import com.ehayvan.app.modules.editpetrecordstwo.data.model.ListPetRecordsRowModel
import com.ehayvan.app.modules.editpetrecordstwo.ui.ListPetRecordsAdapter
import com.ehayvan.app.modules.viewpetrecordstwo.`data`.viewmodel.ViewPetRecordsTwoVM
import kotlin.String
import kotlin.Unit

class ViewPetRecordsTwoActivity :
    BaseActivity<ActivityViewPetRecordsTwoBinding>(R.layout.activity_view_pet_records_two) {
  private val viewModel: ViewPetRecordsTwoVM by viewModels<ViewPetRecordsTwoVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val listPetRecordsAdapter = ListPetRecordsAdapter(viewModel.listPetRecords.value ?: mutableListOf())
    binding.recyclerListPetRecords.adapter = listPetRecordsAdapter
    listPetRecordsAdapter.setOnItemClickListener(object : ListPetRecordsAdapter.OnItemClickListener {
      override fun onItemClick(view: android.view.View, position: kotlin.Int, item: ListPetRecordsRowModel) {
        onClickRecyclerListPetRecords(view, position, item)
      }
    })
    viewModel.listPetRecords.observe(this) {
      listPetRecordsAdapter.updateData(it)
    }
    binding.viewPetRecordsTwoVM = viewModel
  }

  fun onClickRecyclerListPetRecords(
    view: android.view.View,
    position: kotlin.Int,
    item: ListPetRecordsRowModel
  ): Unit {
    // Handle click events if needed
    when (view.id) {
    }
  }

  override fun setUpClicks(): Unit {
    binding.imageArrowleft.setOnClickListener {
      finish()
    }
  }

  companion object {
    const val TAG: String = "VIEW_PET_RECORDS_TWO_ACTIVITY"

  }
}
