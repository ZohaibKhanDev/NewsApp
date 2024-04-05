package com.example.newsapp.detailscreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun DetailScreen(
    navController: NavController,
    image: String?,
    tittle: String?,
    des: String?,
    newdes: String?,
    date: Int?,
    about: String?,
    write: String?
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {

            AsyncImage(
                model = image,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.Crop
            )
            Card(
                modifier = Modifier
                    .padding(bottom = 175.dp)
                    .fillMaxWidth()
                    .height(90.dp)
                    .align(Alignment.Center),
                colors = CardDefaults.cardColors(Color.LightGray.copy(alpha = 0.45f))
            ) {
                Text(text = "$date",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Text(text = "$about",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Text(text = "$write",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }
            Card(
                modifier = Modifier
                    .padding(top = 314.dp)
                    .fillMaxWidth()
                    .height(400.dp)
                    .align(Alignment.Center),
                shape = RoundedCornerShape(30.dp)
            ) {
                Text(
                    text = "$tittle", modifier =
                    Modifier.padding(20.dp)
                )

                Text(
                    text = "$des", modifier =
                    Modifier.padding(16.dp)
                )

                Text(
                    text = "$newdes", modifier =
                    Modifier.padding(16.dp)
                )
            }

        }


    }


}