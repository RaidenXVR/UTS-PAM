package lat.pam.utsproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ConfirmationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_confirmation)

        val foodNameText = findViewById<TextView>(R.id.confirFoodName)
        val servingsText = findViewById<TextView>(R.id.confirServings)
        val orderByText = findViewById<TextView>(R.id.confirOrderBy)
        val notesText = findViewById<TextView>(R.id.confirNotes)
        val extras = intent.extras
        if (extras != null) {
            val foodName = extras.getString("foodOrderName") ?: ""
            val servings = extras.getString("servings") ?: ""
            val orderBy = extras.getString("orderBy") ?: ""
            val notes = extras.getString("notes") ?: ""

            foodNameText.text = "Food Name: "+ foodName
            servingsText.text = "Number of Servings: "+servings
            orderByText.text = "Ordering Name: "+orderBy
            notesText.text = "Additional Notes: "+ notes

        }

        val back_butt = findViewById<Button>(R.id.backtoMenu)
        back_butt.setOnClickListener {
            val intent = Intent(this,ListFoodActivity::class.java)
            finish()
            startActivity(intent)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}