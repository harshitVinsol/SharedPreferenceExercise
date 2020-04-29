package com.example.sharedpreferenceexercise

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_profile.*
import java.util.*
import java.util.concurrent.TimeUnit

/*
Profile Activity to show the Profile of the user using SharedPreferences and time duration of the user registration
 */
class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val sharedPref = getSharedPreferences(RegistrationActivity.SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val name = "Name: " + sharedPref.getString(RegistrationActivity.NAME, "Harshit Singh")
        val address = "Address: " + sharedPref.getString(RegistrationActivity.ADDRESS, "Address")
        val age = "Age: " + sharedPref.getString(RegistrationActivity.AGE, "0")

        text_name.text = name
        text_address.text = address
        text_age.text = age

    }

    override fun onResume() {
        super.onResume()
        val sharedPref = getSharedPreferences(RegistrationActivity.SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val time = "Your Profile was made " + convertTimeToDuration(Date().time - (sharedPref.getLong(RegistrationActivity.TIME, 0)))
        text_time.text = time
    }
    /*
    A function to convert time given in long to duration in Days, Hours, Minutes and Seconds format
     */
    private fun convertTimeToDuration(time : Long) : String{
        var duration = ""
        val days = (time/(1000 * 60 * 60 *24)) % 30 as Int
        val hours = (time/(1000 * 60 * 60)) % 24 as Int
        val mins = (time/(1000*60)) % 60 as Int
        val seconds = (time / 1000) % 60 as Int
        val x: Int = 0

        if(days != 0L) {
            duration += "$days days "
        }
        if(hours != 0L){
            duration += "$hours hours "
        }
        if(mins != 0L){
            duration += "$mins minutes "
        }
        duration += "$seconds seconds ago."
        return duration
    }
}
