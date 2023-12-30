package com.ehayvan.app.modules.viewprofile.`data`.model

import com.ehayvan.app.R
import com.ehayvan.app.appcomponents.di.MyApp
import kotlin.String

data class ViewProfileModel(

  var txtNameAndSurname: String? = MyApp.getInstance().resources.getString(R.string.msg_name_and_surnam)
  ,
  var txtEMail: String? = MyApp.getInstance().resources.getString(R.string.lbl_e_mail_address)
  ,
  var txtUserType: String? = MyApp.getInstance().resources.getString(R.string.lbl_user_type)
  ,
  var txtClinic: String? = MyApp.getInstance().resources.getString(R.string.lbl_user_type)
  ,

)
