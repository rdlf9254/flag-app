package br.ufpr.flagapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class QuizActivity : AppCompatActivity() {

    private lateinit var playerName: String
    private var score = 0
    private var currentQuestionIndex = 0

    private lateinit var questions: List<Question>
    private val resultsSummary = ArrayList<String>()

    private lateinit var tvQuestionCount: TextView
    private lateinit var imgFlag: ImageView
    private lateinit var etAnswer: EditText
    private lateinit var tvFeedback: TextView
    private lateinit var btnSubmit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        playerName = intent.getStringExtra("PLAYER_NAME").toString()

        tvQuestionCount = findViewById(R.id.tvQuestionCount)
        imgFlag = findViewById(R.id.imgFlag)
        etAnswer = findViewById(R.id.etAnswer)
        tvFeedback = findViewById(R.id.tvFeedback)
        btnSubmit = findViewById(R.id.btnSubmit)

        setupQuestions()
        loadNextQuestion()

        btnSubmit.setOnClickListener {
            if (btnSubmit.text == "Enviar") {
                checkAnswer()
            } else {
                loadNextQuestion()
            }
        }
    }

    private fun setupQuestions() {
        val allQuestions = listOf(
            Question(R.drawable.flag_albania, "Albania"),
            Question(R.drawable.flag_brasil, "Brasil"),

            Question(R.drawable.flag_canada, "Canada"),

            Question(R.drawable.flag_israel, "Israel"),

            Question(R.drawable.flag_nigeria, "Nigeria"),

            Question(R.drawable.flag_gibraltar, "Gibraltar"),

            Question(R.drawable.flag_ilhachristimas, "Ilha Christimas"),

            Question(R.drawable.flag_paisbasco, "Pais Basco"),
            Question(R.drawable.flag_saomartinho, "São Martinho"),
            Question(R.drawable.flag_wallisefutuna, "Wallis e Futuna"),

            Question(R.drawable.flag_armenia, "Armenia"),
            Question(R.drawable.flag_australia, "Australia"),
            Question(R.drawable.flag_bulgaria, "Bulgaria"),
            Question(R.drawable.flag_croacia, "Croacia"),
            Question(R.drawable.flag_dinamarca, "Dinamarca"),
            Question(R.drawable.flag_estonia, "Estônia"),
            Question(R.drawable.flag_finlandia, "Finlândia"),

            )
        questions = allQuestions.shuffled().take(5)
    }

    private fun loadNextQuestion() {
        if (currentQuestionIndex < questions.size) {
            val question = questions[currentQuestionIndex]
            tvQuestionCount.text = "${currentQuestionIndex + 1} / ${questions.size}"
            imgFlag.setImageResource(question.image)
            etAnswer.text.clear()
            etAnswer.isEnabled = true
            tvFeedback.visibility = View.GONE
            btnSubmit.text = "Enviar"
            currentQuestionIndex++
        } else {
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("PLAYER_NAME", playerName)
            intent.putExtra("SCORE", score)
            intent.putStringArrayListExtra("RESULTS_SUMMARY", resultsSummary)
            startActivity(intent)
            finish()
        }
    }

    private fun checkAnswer() {
        val question = questions[currentQuestionIndex - 1]
        val userAnswer = etAnswer.text.toString()

        if (userAnswer.equals(question.countryName, ignoreCase = true)) {
            score += 20
            tvFeedback.text = "Acertou!"
            resultsSummary.add("Pais ${question.countryName}: Acertou")
        } else {
            tvFeedback.text = "Errou! Era ${question.countryName}"
            resultsSummary.add("Pais ${question.countryName}: Errou")
        }

        tvFeedback.visibility = View.VISIBLE
        etAnswer.isEnabled = false
        btnSubmit.text = "Próxima"

        if (currentQuestionIndex == questions.size) {
            btnSubmit.text = "Resultado"
        }
    }
}
