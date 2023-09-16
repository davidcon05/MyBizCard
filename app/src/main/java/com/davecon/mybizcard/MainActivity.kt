package com.davecon.mybizcard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import android.net.Uri
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType.Companion.Uri
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.davecon.mybizcard.ui.theme.MyBizCardTheme
import com.google.firebase.inappmessaging.model.Text

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyBizCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CreateBizCard()
                }
            }
        }
    }
}

@Composable
fun CreateBizCard() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {

        Card(
            modifier = Modifier
                .padding(16.dp)
                .width(200.dp)
                .height(392.dp),
            shape = RoundedCornerShape(CornerSize(16.dp)),
            //colors = CardDefaults.cardColors(containerColor = Color.LightGray),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {

            HeadShotSection()

            Divider(modifier = Modifier.padding(16.dp), thickness = 1.dp, color = Color.Black)

            NameAndTitleSection()

            PortfolioButton()
        }

    }
}

@Composable
fun HeadShotSection() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Surface(
            modifier = Modifier
                .size(160.dp, 160.dp)
                .padding(8.dp),
            shape = CircleShape,
            border = BorderStroke(0.5.dp, Color.Transparent),
            shadowElevation = 8.dp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
        ) {

            Image(
                painter = painterResource(id = R.drawable.headshot),
                contentDescription = "My headshot image",
                modifier = Modifier.size(120.dp),
            )
        }
    }
}

@Composable
fun NameAndTitleSection() {
    Column(
        modifier = Modifier
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
    ) {

        Text(
            "David Contreras",
            style = MaterialTheme.typography.headlineMedium,
            fontSize = 36.sp,
            color = MaterialTheme.colorScheme.primary//colorResource(id = R.color.purple_500)
        )

        Text(
            "Software Engineer",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 24.sp,
        )

        Text(
            "https://github.com/davidcon05",
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 16.sp,
        )
    }
}

@Composable
fun PortfolioButton() {
    val url = "https://github.com/davidcon05"
    val urlIntent = Intent(
        Intent.ACTION_VIEW,
        //Uri.(url)
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        Button(onClick = {
            Log.d("tag", "URL IS " + url)
            //startActivity(urlIntent)
        }) {
            Text(
                "Portfolio",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 24.sp,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CreateBizCard()
}