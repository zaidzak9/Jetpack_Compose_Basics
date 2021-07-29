package com.example.jetpackcomposecourse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.format.TextStyle

class JetPackComposeAct : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//           ColumnsFun()
//           RowsFun()
//           ColumnsWithModifier()
                ImageCard(
                    painter = painterResource(id = R.drawable.ic_wallet),
                    contentDescription = "Empty wallet",
                    title = "Image of empty wallet"
                )
        }
    }
}

//Composable is way to create re usable ui as functions which can be called in on create
@Composable
fun ColumnsFun() {
    /*
       but this doesnt really chnage the ui coz compose will make it and wide as it needs to be similar to wrap content
       to fix this we use modifiers with fillMaxSize or fillMaxWidth check  - ColumnsWithModifier()
     */
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Hello")
        Text(text = "World")
    }
}

@Composable
fun RowsFun() {
    Row(
        modifier = Modifier
            .width(300.dp)
            .fillMaxHeight(0.7f)
            .fillMaxSize()
            .background(Color.Green),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Hello row")
        Text(text = "World")
    }
}

@Composable
fun ColumnsWithModifier() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = "Hello")
        Text(text = "World")
    }
}


@Composable
fun ImageCard(
    painter: Painter,//to add image
    contentDescription: String,//to help the disabled read
    title: String,//title of card
    modifier: Modifier = Modifier
) {
    Box(modifier = Modifier.fillMaxWidth(0.5f).padding(16.dp)) {
        Card(
            modifier = modifier.fillMaxWidth(),
            shape = RoundedCornerShape(15.dp),
            elevation = 5.dp
        ) {
            //when we want to stack content and align we use box
            Box(modifier = Modifier.height(200.dp)) {
                Image(
                    painter = painter,
                    contentDescription = contentDescription,
                    contentScale = ContentScale.Crop
                )
                //to draw the black gradient bottom of image to highlight the text
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.Black),
                                startY = 300f
                            )
                        )
                )
                //the text, using box so to align it without disturbing the rest
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    contentAlignment = Alignment.BottomStart
                ) {
                    Text(
                        text = title,
                        style = androidx.compose.ui.text.TextStyle(
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    )
                }
            }
        }
    }


}