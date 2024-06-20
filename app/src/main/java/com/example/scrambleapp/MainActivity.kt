package com.example.scrambleapp

import android.R
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column{
                MessageCard("ScramblerApp")
                Text("You can encrypt your texts Here!")
                Image(
                    painter = painterResource(R.drawable.encryptpic),
                    contentDescription = "Contact profile picture",
                )
            }
        }
    }
}

@Composable
fun MessageCard(name: String) {
    Text(text = "Welcome to the $name!")
}

@Preview
@Composable
fun PreviewMessageCard() {
    Column{
        MessageCard("ScramblerApp")
        Text("You can encrypt your texts Here!")
        Image(
            painter = painterResource(R.drawable.encryptpic),
            contentDescription = "Contact profile picture",
        )
        /*Button(onClick= { /* Handle button click */ }) {
            Text("Click me")
        }*/
    }
}