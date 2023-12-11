package com.ehayvan.app.modules.editpetrecordstwo.ui

import androidx.activity.viewModels
import com.ehayvan.app.R
import com.ehayvan.app.appcomponents.base.BaseActivity
import com.ehayvan.app.databinding.ActivityEditPetRecordsTwoBinding
import com.ehayvan.app.modules.editpetrecordstwo.data.model.ListPetRecordsRowModel
import com.ehayvan.app.modules.editpetrecordstwo.data.viewmodel.EditPetRecordsTwoVM
import kotlin.Unit

class EditPetRecordsTwoActivity :
  BaseActivity<ActivityEditPetRecordsTwoBinding>(R.layout.activity_edit_pet_records_two) {
  private val viewModel: EditPetRecordsTwoVM by viewModels<EditPetRecordsTwoVM>()

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
    binding.editPetRecordsTwoVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.imageArrowleft.setOnClickListener {
      finish()
    }
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

  companion object {
    const val TAG: String = "EDIT_PET_RECORDS_TWO_ACTIVITY"
  }
}
