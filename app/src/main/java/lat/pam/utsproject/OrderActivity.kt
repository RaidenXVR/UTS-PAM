package lat.pam.utsproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class OrderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_order)
        val extra = intent.extras
        var foodName: String = ""
        if (extra != null) {
            foodName = extra.getString("foodName").toString()
        }


        val foodNameText = findViewById<TextView>(R.id.etFoodName)
        foodNameText.text = foodName


        val order_butt = findViewById<Button>(R.id.btnOrder)

        order_butt.setOnClickListener {
            val servings = findViewById<EditText>(R.id.etServings).text.toString()
            val orderBy = findViewById<EditText>(R.id.etName).text.toString()

            if (servings == "" || orderBy == ""){
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Alert!")
                builder.setPositiveButton("Ok"){
                        dialog, _which -> dialog.cancel()
                }
                builder.setMessage("Servings or Order Name cannot be Empty.")
                val alert = builder.show()
                alert.show()
            }
            else{
                val additionalNotes = findViewById<EditText>(R.id.etNotes).text.toString()

                val intent = Intent(this, ConfirmationActivity::class.java)
                intent.putExtra("foodOrderName", foodName)
                intent.putExtra("servings", servings)
                intent.putExtra("orderBy", orderBy)
                if (additionalNotes != ""){
                    intent.putExtra("notes", additionalNotes)
                }
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