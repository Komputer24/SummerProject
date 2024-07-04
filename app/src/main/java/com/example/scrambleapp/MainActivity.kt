package com.example.scrambleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.benchmark.perfetto.Row
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

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
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(
                            text = "ToDo List:",
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp,
                            modifier = Modifier
                                .padding(top = 30.dp, start = 10.dp)
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                    ){
                        OutlinedTextField(
                            value = name,
                            onValueChange = {
                                text -> name = text
                            },
                            Modifier
                                .fillMaxWidth(0.75f)
                                .padding(10.dp),
                        )
                        Button(onClick = {
                                    if(name.isNotBlank()){
                                        names = names + name
                                        name = ""
                                    }
                                },
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.LightGray
                                ),
                                modifier = Modifier.padding(10.dp).offset(x = -10.dp, y = 0.dp)
                        ){
                            Text(
                                text = "+",
                                style = TextStyle(fontSize = 33.sp),
                            )
                        }

                    }
                    LazyColumn {
                        items(names) { currentName ->
                            var clickedName by remember { mutableStateOf<String?>(null) }
                            val isSelected = currentName == clickedName
                            val backgroundColor = if (isSelected) Color.Yellow else Color.Transparent

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { clickedName = currentName }
                                    .background(backgroundColor)
                                    .padding(16.dp),  // Optional: Add padding to the Row
                                verticalAlignment = Alignment.CenterVertically,  // Center content vertically
                                horizontalArrangement = Arrangement.SpaceBetween  // Space out the children
                            ) {
                                Text(
                                    text = currentName,
                                    modifier = Modifier
                                        .weight(1f)  // Make Text take the remaining space
                                        .padding(end = 16.dp)  // Optional: Add padding to the end of the Text
                                )
                                Button(
                                    onClick = { names = names.filter { it != currentName } },
                                    shape = RoundedCornerShape(8.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color.LightGray
                                    )
                                ) {
                                    Text(
                                        text = "X",
                                        style = TextStyle(fontSize = 15.sp)
                                    )
                                }
                            }
                            Divider()
                        }
                    }
                }
            }
        }
    }
}