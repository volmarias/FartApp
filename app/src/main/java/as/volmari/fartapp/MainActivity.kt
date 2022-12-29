package `as`.volmari.fartapp

import `as`.volmari.fartapp.ui.theme.FartAppTheme
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import kotlin.random.Random
import kotlin.random.nextInt

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FartAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Poop {
                        playFart()
                    }
                }
            }
        }
    }

    fun playFart() {
        val fart = when(Random.nextInt(1..4)) {
                1 -> R.raw.fart_01
                2 -> R.raw.fart_02
                3 -> R.raw.fart_03
                4 -> R.raw.fart_04
            else -> R.raw.fart_01
        }
        MediaPlayer.create(this, fart).apply {
            this.setOnCompletionListener { this.release() }
            start()
        }
    }
}

@Composable
fun Poop(click: () -> Unit = {}) {
    Box(modifier = Modifier.fillMaxSize()) {
        Button(onClick = click, modifier = Modifier.align(Center)) {
            Image(
                painter = painterResource(id = R.drawable.noto_emoji_kitkat_1f4a9),
                contentDescription = "poo",
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FartAppTheme {
        Poop {}
    }
}