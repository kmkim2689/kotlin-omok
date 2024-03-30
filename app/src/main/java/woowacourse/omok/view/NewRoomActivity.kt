package woowacourse.omok.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import woowacourse.omok.R
import woowacourse.omok.model.database.GameRoomDao
import woowacourse.omok.model.database.Room

class NewRoomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_room)

        val gameRoomDao = GameRoomDao(this)
        val startButton = findViewById<Button>(R.id.btn_start)
        val gameNameInput =
            findViewById<EditText>(R.id.et_new_game).apply {
                addTextChangedListener(
                    onTextChanged = { currentText, _, _, _ ->
                        startButton.isClickable = currentText?.length in 1..30
                    },
                )
            }

        startButton.setOnClickListener {
            val roomInfo = Room(title = gameNameInput.text.toString())
            val generationResult = gameRoomDao.save(roomInfo)
            Intent(this, OmokGameActivity::class.java).also {
                it.putExtra(GAME_ID, generationResult.id)
                it.putExtra(GAME_TITLE, generationResult.title)
                startActivity(it)
                finish()
            }
        }
    }

    companion object {
        private const val GAME_ID = "game_id"
        private const val GAME_TITLE = "game_title"
    }
}