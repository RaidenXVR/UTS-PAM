package lat.pam.utsproject

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider

class RegisterActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Username or Password Field is Empty")
        builder.setTitle("Alert!")
        builder.setPositiveButton("Ok"){
            dialog, which -> dialog.cancel()
        }
        val alert = builder.create()

        val reg_butt = findViewById<Button>(R.id.button_regis)

        reg_butt.setOnClickListener {
            val username = findViewById<EditText>(R.id.regis_user).text.toString()
            val password = findViewById<EditText>(R.id.regis_password).text.toString()

            if (username == "" || password == ""){
                alert.show()
            }
            else{

                val sharedPreferences = this.getSharedPreferences("USER_DATA",Context.MODE_PRIVATE) ?: return@setOnClickListener
                with (sharedPreferences.edit()){
                    putString(username, password)
                    putString(password, username)
                    apply()
                }
                val intent = Intent(this, MainActivity::class.java)
                finish()
                startActivity(intent)
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}