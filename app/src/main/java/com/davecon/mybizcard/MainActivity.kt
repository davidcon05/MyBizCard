package com.davecon.mybizcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.davecon.mybizcard.ui.theme.MyBizCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyBizCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
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
            .fillMaxHeight(),

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
            HeadShotSection(alignment = Alignment.CenterHorizontally)
            Divider(
                modifier = Modifier.padding(start = 8.dp, top = 8.dp, end = 8.dp),
                thickness = 1.dp,
                color = Color.Black
            )
            NameAndTitleSection()
            PortfolioButton()
        }
    }
}

@Composable
fun HeadShotSection(
    modifier: Modifier = Modifier,
    alignment: Alignment.Horizontal = Alignment.CenterHorizontally
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = alignment,
    ) {
        Surface(
            modifier = Modifier
                .padding(8.dp),
            shape = CircleShape,
            border = BorderStroke(0.5.dp, Color.Transparent),
            shadowElevation = 8.dp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
        ) {
            Image(
                painter = painterResource(id = R.drawable.headshot),
                contentDescription = "My headshot image",
                modifier = modifier.size(160.dp),
            )
        }
    }
}

@Composable
fun NameAndTitleSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.Top,
    ) {

        Text(
            "David Contreras",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            fontSize = 36.sp,
            color = MaterialTheme.colorScheme.primary//colorResource(id = R.color.purple_500)
        )

        Text(
            "Android Developer",
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
    val buttonClickedState = remember {
        mutableStateOf(false)
    }
    // TODO: this links out to portfolio but its not web first
//    val url = remember {
//        mutableStateOf("https://github.com/davidcon05")
//    }
//    val context = LocalContext.current
//    val urlIntent = Intent(
//        Intent.ACTION_VIEW,
//        Uri.parse(url.value)
//    )

    Column(
        modifier = Modifier
            .padding(top = 24.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(onClick = {
            // Log.d("tag", "URL IS " + url)
            // TODO: make this link out to my portfolio on github
            // context.startActivity(urlIntent)
            buttonClickedState.value = !buttonClickedState.value
        }) {
            Text(
                "Portfolio",
                style = MaterialTheme.typography.labelMedium,
                fontSize = 24.sp,
            )
        }
        if (buttonClickedState.value) {
            PortfolioBox()
        } else {
            Box { /* No OP - hides PortfolioBox */ }
        }
    }
}

@Composable
fun PortfolioBox() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(8.dp)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            shape = RoundedCornerShape(CornerSize(12.dp)),
            border = BorderStroke(2.dp, Color.LightGray),
        ) {
            PortfolioCard(data = listOf("Project 1", "Project 2", "Project 3"))
        }
    }
}

@Composable
fun PortfolioCard(data: List<String>) {
    LazyColumn(modifier = Modifier.padding(8.dp)) {
        items(data.size) { item ->
            Card(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth(),
                shape = RectangleShape
            ) {
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surface),
                    horizontalArrangement = Arrangement.Start
                ) {
                    HeadShotSection(Modifier.size(64.dp), alignment = Alignment.Start)
                    Text(data[item])
                }
            }
        }
    }
}

@Preview
@Composable
fun PortfolioBoxPreview() {
    PortfolioBox()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CreateBizCard()
}