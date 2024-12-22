package com.example.scratchcardcompose.ui.examples

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.scratchcardcompose.R
import com.example.scratchcardcompose.ui.scratch_card.ScratchCard
import com.example.scratchcardcompose.ui.theme.ScratchCardComposeTheme

@Composable
fun ScratchCardScreenExampleMovie() {
    val isCardScratched = remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ScratchCard(
            modifier = Modifier
                .height(470.dp)
                .fillMaxWidth(0.9f),
            overlayImage = ImageBitmap.imageResource(R.drawable.scratch_here_overlay),
            baseImage = painterResource(R.drawable.spiderman),
            onScratchComplete = {
                isCardScratched.value = true
            },
            isScratched = isCardScratched.value,
            shape = RoundedCornerShape(12.dp)
        )
        RestartButton(
            onRestart = {
                isCardScratched.value = false
            }
        )
    }
}

@Composable
fun RestartButton(onRestart: () -> Unit) {
    Button(
        onClick = onRestart,
        modifier = Modifier
            .padding(top = 32.dp)
            .fillMaxWidth(0.6f)
    ) {
        Text(
            text = "Restart",
            modifier = Modifier.padding(vertical = 8.dp),
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ScreenExamplePreview() {
    ScratchCardComposeTheme {
        ScratchCardScreenExampleMovie()
    }
}