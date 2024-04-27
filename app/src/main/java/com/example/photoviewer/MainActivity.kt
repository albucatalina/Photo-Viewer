package com.example.photoviewer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.photoviewer.ui.theme.PhotoViewerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PhotoViewerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PhotoViewerLayout()
                }
            }
        }
    }

    @Composable
    private fun PhotoViewerLayout(){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(Color.LightGray)
                .padding(horizontal = 24.dp)
                .padding(top = 80.dp)
                .fillMaxSize()
        ) {
            Title(
                title = "MILAN LANDMARKS",
                modifier = Modifier
                    .padding(vertical = 30.dp)
            )
            PhotoWall(
                image = R.drawable.arco_della_pace,
                description = "Arco della Pace",
                address = "address",
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .background(Color.White)
                    .padding(bottom = 20.dp)
            )
            NavigationButtons(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .padding(top = 32.dp)
            )
        }
    }

    @Composable
    private fun Title(title: String, modifier: Modifier = Modifier){
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            modifier = modifier
        )
    }
    @Composable
    private fun PhotoWall(
        @DrawableRes image: Int,
        description: String,
        address: String,
        modifier: Modifier = Modifier
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = description,
                modifier = Modifier.padding(24.dp)
            )
            Text(
                text = description,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(text = address)
        }
    }

    @Composable
    private fun NavigationButtons(modifier: Modifier = Modifier){
        Row(modifier = modifier) {
            Button(
                onClick = { /*TODO*/ },
                shape = RectangleShape,
                modifier = Modifier.width(130.dp)
            ) {
                Text(text = "Previous")
            }
            Button(onClick = { /*TODO*/ },
                shape = RectangleShape,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.End)
                    .width(130.dp)
            ) {
                Text(text = "Next")
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun PhotoViewLayoutPreview(){
        PhotoViewerTheme {
            PhotoViewerLayout()
        }
    }
}