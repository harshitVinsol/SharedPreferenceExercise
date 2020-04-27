package com.example.sharedpreferenceexercise

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_registration.*
import java.lang.String.format
import java.text.DateFormat
import java.util.*
/*
Constant values used in Shared Preferences as Keys
 */
const val SHARED_PREF_NAME = "Profile"
const val PROFILE_AVAILABLE = "isProfileAvailable"
const val NAME = "name"
const val ADDRESS = "address"
const val AGE = "age"
const val TIME = "time"
/*
Registration Activity used to enable Registering of a user and time of registration
 */
class RegistrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        validateOnChange()

        val sharedPref = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        if(sharedPref.getBoolean(PROFILE_AVAILABLE, false)){
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            finish()
        }
        /*
        Saving User details along with time of the User when register_button is clicked
         */
        register_button.setOnClickListener{
            if(validate()){
                register_button.isClickable = false

                val name = name.text.toString()
                val address = address.text.toString() +",\n" + city.text.toString() + ",\n" + state.text.toString()
                val time = Date().time
                val age = age.text.toString()

                editor.putString(NAME, name)
                editor.putString(ADDRESS, address)
                editor.putString(AGE, age)
                editor.putLong(TIME, time)
                editor.putBoolean(PROFILE_AVAILABLE, true)
                editor.apply()

                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
                finish()
            }
            else{
                register_button.isClickable = true
            }
        }
    }
    /*
    A boolean Function to check validation of Name, Address, City, State and Age Fields
     */
    private fun validate() = if(!validateName() || !validateAddress() || !validateCity() || !validateState() || !validateAge()) false else true
    /*
    A boolean Function to check the validation of Name
     */
    private fun validateName() : Boolean{
        if(name.text.toString().isBlank()){
            name_input_layout.error = "Enter a proper name"
            name.requestFocus()
            return false
        }
        else{
            name_input_layout.error = null
            return true
        }
    }
    /*
    A boolean Function to check the validation of Address
     */
    private fun validateAddress() : Boolean{
        if(address.text.toString().isBlank()){
            address_input_layout.error = "Enter a proper Address"
            address.requestFocus()
            return false
        }
        else{
            address_input_layout.error = null
            return true
        }
    }
    /*
    A boolean Function to check the validation of City
     */
    private fun validateCity() : Boolean{
        if(city.text.toString().isBlank()){
            city_input_layout.error = "Enter a proper City"
            city.requestFocus()
            return false
        }
        else{
            city_input_layout.error = null
            return true
        }
    }
    /*
    A boolean Function to check the validation of State
     */
    private fun validateState() : Boolean{
        if(state.text.toString().isBlank()){
            state_input_layout.error = "Enter a proper State"
            state.requestFocus()
            return false
        }
        else{
            state_input_layout.error = null
            return true
        }
    }
    /*
    A boolean Function to check the validation of Age
     */
    private fun validateAge() : Boolean{
        if(age.text.toString().isBlank()){
            age_input_layout.error = "Enter a proper Age"
            age.requestFocus()
            return false
        }
        else{
            age_input_layout.error = null
            return true
        }
    }
    /*
    A function to check the validation of fields when text in changed
     */
    private fun validateOnChange(){
        name.onChange {
            validateName()
        }
        address.onChange {
            validateAddress()
        }
        city.onChange {
            validateCity()
        }
        state.onChange {
            validateState()
        }
    }
}
/*
An extension function of EditText that takes a lambda function
*/
fun EditText.onChange(cb: (String) -> Unit) {
    this.addTextChangedListener(object: TextWatcher {
        override fun afterTextChanged(s: Editable?) { cb(s.toString()) }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })
}

