package lat.pam.utsproject

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UsersListModel(application:Application) : AndroidViewModel(application) {
    private val _usernames = MutableLiveData<ArrayList<String>>()

    private val _passwords = MutableLiveData<ArrayList<String>>()
    val live_usernames:LiveData<ArrayList<String>> = _usernames
    val live_passwords:LiveData<ArrayList<String>> = _passwords


    fun setUsername(username:String){
        if (_usernames.value == null){
            _usernames.value = ArrayList()
        }

        _usernames.value!!.add(username)
        Log.d("Add username", _usernames.value!!.toString())
    }

    fun setPassword(password:String){
        if (_passwords.value == null){
            _passwords.value = ArrayList()
        }
        _passwords.value!!.add(password)
    }

}