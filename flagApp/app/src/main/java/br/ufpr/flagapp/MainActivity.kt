package br.ufpr.flagapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStart: Button = findViewById(R.id.btnStart)
        val etName: EditText = findViewById(R.id.etName)

        btnStart.setOnClickListener {
            if (etName.text.isNotEmpty()) {
                val intent = Intent(this, QuizActivity::class.java)
                intent.putExtra("PLAYER_NAME", etName.text.toString())
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Por favor, digite seu nome", Toast.LENGTH_SHORT).show()
            }
        }
    }
}