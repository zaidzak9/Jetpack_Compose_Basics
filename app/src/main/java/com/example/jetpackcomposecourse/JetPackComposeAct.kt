package com.example.jetpackcomposecourse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import java.time.format.TextStyle
import kotlin.random.Random

class JetPackComposeAct : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//           ColumnsUI()
//            ==============
//           RowsUI()
//            ===============
//           ColumnsWithModifier()
//            ===================
//                ImageCard(
//                    painter = painterResource(id = R.drawable.ic_wallet),
//                    contentDescription = "Empty wallet",
//                    title = "Image of empty wallet"
//                )
//            ===================
//            StylingText()
//            ================
//            ColorBoxWithState(modifier = Modifier.fillMaxSize())
//            ==============
            TextFieldButtonsSnackbar()
//            ===================

        }
    }
}


@Composable
fun TextFieldButtonsSnackbar() {
    val scaffoldState = rememberScaffoldState()
    //using kotlin delegate we can return a string comapred to using val = which will return a Mutable<String>
    var textFieldState by remember {
        mutableStateOf("")
    }
    val scope = rememberCoroutineScope()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
        ) {
            TextField(
                value = textFieldState, label = {
                    Text(text = "Enter your name")
                },
                onValueChange = {
                    textFieldState = it
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar("Hello $textFieldState")
                }
            }) {
                Text(text = "please greet me")
            }
        }
    }
}

@Composable
fun ColorBoxWithState(modifier: Modifier = Modifier) {
    //remember will remember the color it was changed to rather than resetting always
    val colorToChange = remember {
        mutableStateOf(Color.Green)
    }
    Box(modifier = modifier
        .background(colorToChange.value)
        //to change color of box onclick, to achieve this we use mutableStateOf()
        .clickable {
            colorToChange.value = Color(
                Random.nextFloat(),
                Random.nextFloat(),
                Random.nextFloat(),
                1f
            )
        }) {

    }
}

@Composable
fun StylingText() {
    //way to include custom fonts, best practice is lowercase and underscore for names
    val fontFamily = FontFamily(
        Font(R.font.windsong_medium, FontWeight.Medium),
        Font(R.font.windsong_regular, FontWeight.Normal)
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF101010))
    ) {
        Text(
            //used to modify certain parts of strings
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Green,
                        fontSize = 10.sp
                    )
                ) {
                    append("Jetpack")
                }
                //anything after withStyle will follow the default design mentioned below
                append(" Compose")
            },
            color = Color.White,
            fontSize = 50.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center,
            textDecoration = TextDecoration.Underline
        )
    }
}

//Composable is way to create re usable ui as functions which can be called in on create
@Composable
fun ColumnsUI() {
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
fun RowsUI() {
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
    Box(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .padding(16.dp)
    ) {
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