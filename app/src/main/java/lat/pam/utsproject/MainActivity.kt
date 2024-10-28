package lat.pam.utsproject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import lat.pam.utsproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Alert!")
        builder.setPositiveButton("Ok"){
                dialog, _which -> dialog.cancel()
        }

        val to_regis_butt = findViewById<TextView>(R.id.tvRegister)
        val login_butt = findViewById<Button>(R.id.btnLogin)

        to_regis_butt.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        login_butt.setOnClickListener {
            val username = findViewById<EditText>(R.id.etUsername).text.toString()
            val password = findViewById<EditText>(R.id.etPassword).text.toString()
            val sharedPreferences = this.getSharedPreferences("USER_DATA",Context.MODE_PRIVATE) ?: return@setOnClickListener
            val _password = sharedPreferences.getString(username, "")
            val _username = sharedPreferences.getString(password,"")

            Log.d("username", "${username}, ${_username}")
            if (username == "" || password == ""){
                builder.setMessage("Username or Password Field is Empty")
                val alert = builder.create()

                alert.show()
            }
            else{

                if(username == _username && password ==_password){
                    val intent = Intent(this, ListFoodActivity::class.java)
                    finish()
                    startActivity(intent)

                }
                else{
                    builder.setMessage("Username or Password is not found or Wrong.")
                    val alert = builder.create()

                    alert.show()

                }
            }
        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}