package br.ufpr.flagapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val tvPlayerName: TextView = findViewById(R.id.tvPlayerName)
        val tvScore: TextView = findViewById(R.id.tvScore)
        val tvResultsSummary: TextView = findViewById(R.id.tvResultsSummary)
        val btnPlayAgain: Button = findViewById(R.id.btnPlayAgain)

        val playerName = intent.getStringExtra("PLAYER_NAME")
        val score = intent.getIntExtra("SCORE", 0)
        val resultsSummary = intent.getStringArrayListExtra("RESULTS_SUMMARY")

        tvPlayerName.text = playerName
        tvScore.text = score.toString()
        tvResultsSummary.text = resultsSummary?.joinToString("\n")

        btnPlayAgain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
