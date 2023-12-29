package com.ehayvan.app.modules.login.ui

import android.content.Intent
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import com.ehayvan.app.R
import com.ehayvan.app.appcomponents.base.BaseActivity
import com.ehayvan.app.databinding.ActivityLogInBinding
import com.ehayvan.app.modules.login.`data`.viewmodel.LogInVM
import com.ehayvan.app.modules.register.ui.RegisterActivity
import kotlin.String
import kotlin.Unit

class LogInActivity : BaseActivity<ActivityLogInBinding>(R.layout.activity_log_in) {
  private val viewModel: LogInVM by viewModels<LogInVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val registerBtn = findViewById<TextView>(R.id.txtConfirmation)
    val loginBtn = findViewById<LinearLayout>(R.id.linearPrimaryButton)
    val txtLabel1 = findViewById<EditText>(R.id.txtLabel1)
    val txtLabel2 = findViewById<EditText>(R.id.txtLabel2)
    registerBtn.setOnClickListener {
      val intent = Intent(this, RegisterActivity::class.java)
      startActivity(intent)
      overridePendingTransition(0, 0)
    }
    loginBtn.setOnClickListener {
      // Start the LoginActivity when the button is clicked
      val inputText1 = txtLabel1.text.toString().trim()
      val inputText2 = txtLabel2.text.toString().trim()
      if (inputText1.isEmpty())
        txtLabel1.error = "Email field cannot be empty"
      if (inputText2.isEmpty())
        txtLabel2.error = "Password field cannot be empty"
      if (inputText1.isNotEmpty() && inputText2.isNotEmpty()) {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
        overridePendingTransition(0, 0)
      }
    }
    binding.logInVM = viewModel
  }

  override fun setUpClicks(): Unit {
  }

  companion object {
    const val TAG: String = "LOG_IN_ACTIVITY"

  }
}
