package com.example.photoviewer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.photoviewer.ui.theme.PhotoViewerTheme

data class Picture(var image: Int, var description: String, var address: String)

class MainActivity : ComponentActivity() {

    private val pictures = listOf(
        Picture(R.drawable.duomo, "Duomo di Milano", "P.za del Duomo, 20122 Milano MI, Italia"),
        Picture(R.drawable.museo_del_novencento, "Museo del Novencento", "P.za del Duomo, 8, 20123 Milano MI, Italia"),
        Picture(R.drawable.casa_galimberti, "Casa Galimberti", "Via Marcello Malpighi, 3, 20129 Milano MI, Italia"),
        Picture(R.drawable.bosco_verticale, "Bosco Verticale", "Via Gaetano de Castillia, 11, 20124 Milano MI, Italia"),
        Picture(R.drawable.dinosaur, "Museo di Storia Naturale", "Corso Venezia, 55, 20121 Milano MI, Italia"),
        Picture(R.drawable.castello_sforzesco, "Castello Sforzesco", "Piazza Castello, 20121 Milano MI, Italia"),
        Picture(R.drawable.cannoli, "Cannoli", "Viale Ercole Marelli, 320, 20099 Sesto San Giovanni MI, Italia"),
        Picture(R.drawable.castello_pozzi, "Castello Pozzi", "V.le Berengario, 8, 20149 Milano MI, Italia"),
        Picture(R.drawable.arco_della_pace, "Arco della Pace", "Piazza Sempione, 20154 Milano MI, Italia"),
    )

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
        var pictureIndex by remember {
            mutableIntStateOf(0)
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(Color.LightGray)
                .padding(horizontal = 48.dp)
                .padding(top = 36.dp)
                .fillMaxSize()
        ) {
            Title(
                title = "MILAN LANDMARKS",
                modifier = Modifier
                    .padding(vertical = 30.dp)
            )
            PhotoWall(
                image = pictures[pictureIndex].image,
                description = pictures[pictureIndex].description,
                address = pictures[pictureIndex].address,
                modifier = Modifier
                    .padding(bottom = 8.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 100.dp)
            ) {
                NavigationButtons(
                    onPreviousClicked = { pictureIndex = (pictureIndex - 1 + pictures.size) % pictures.size },
                    onNextClicked = { pictureIndex = (pictureIndex + 1) % pictures.size },
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                )
            }


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
                .shadow(10.dp)
                .background(Color.White)
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = description,
                modifier = Modifier.padding(24.dp)
            )
            Text(
                text = description,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
            Row(modifier = modifier.padding(all = 16.dp)) {
                Icon(imageVector = Icons.Default.LocationOn, contentDescription = null)
                Text(
                    text = address,
                    textAlign = TextAlign.Center
                )
            }
        }
    }

    @Composable
    private fun NavigationButtons(
        onPreviousClicked: () -> Unit,
        onNextClicked: () -> Unit,
        modifier: Modifier = Modifier
    ){
        Row(modifier = modifier) {
            Button(
                onClick = onPreviousClicked,
                shape = RectangleShape,
                modifier = Modifier.width(130.dp)
            ) {
                Text(text = "Previous")
            }
            Button(
                onClick = onNextClicked,
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