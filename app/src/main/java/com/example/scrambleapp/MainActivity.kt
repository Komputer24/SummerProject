package com.example.scrambleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.benchmark.perfetto.Row
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.runtime.*

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.scrambleapp.ui.theme.ScrambleAppTheme
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ScrambleAppTheme {
                var name by remember{
                    mutableStateOf("")
                }
                var names by remember{
                    mutableStateOf(listOf<String>())
                }
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                ){
                    Text(
                        text = "Scrambler App",
                        fontSize = 30.sp,
                        modifier = Modifier
                            .padding(10.dp)
                    )
                    Text(
                        text = "by Kaleab Beteselassie",
                        fontSize = 10.sp,
                        modifier = Modifier
                            .padding(10.dp)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ){
                        OutlinedTextField(
                            value = name,
                            onValueChange = {
                                text -> name = text
                            }
                        )
                        Button(onClick = {
                            if(name.isNotBlank()){
                                names = names + name
                            }
                        }){
                            Text(text = "Add")
                        }
                    }
                    LazyColumn{
                        items(names){ currentName ->
                            Text(text = currentName)
                        }
                    }
                }
            }
        }
    }
}