package `as`.volmari.fartapp

import `as`.volmari.fartapp.ui.theme.FartAppTheme
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
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

class MainActivity : ComponentActivity() {

    var farts: List<MediaPlayer> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        farts = (1..8).map {
            resources.getIdentifier("fart_0$it", "raw", packageName)
        }.map {
            MediaPlayer.create(this, it).also {
                it.setOnPreparedListener {
                    Log.w("MediaPlayer", "setOnPreparedListener: ${it.audioSessionId}")

                }
                it.setOnErrorListener { mediaPlayer, i, i2 ->
                    Log.w("MediaPlayer", "setOnErrorListener: $i, $i2")
                    false
                }
                it.setOnInfoListener { mediaPlayer, i, i2 ->
                    Log.w("MediaPlayer", "setOnInfoListener: $i, $i2")
                    false
                }
            }
        }
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

    private fun playFart() {
        val fart = farts.random()
        fart.start()
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