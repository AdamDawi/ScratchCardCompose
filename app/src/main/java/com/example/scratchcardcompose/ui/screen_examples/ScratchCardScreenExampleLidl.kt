package com.example.scratchcardcompose.ui.screen_examples

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.scratchcardcompose.R
import com.example.scratchcardcompose.ui.scratch_card.ScratchCard

@Composable
fun ScratchCardScreenExampleLidl() {
    val isCardScratched = remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.horizontalGradient(
                    colorStops = arrayOf(
                        0.0f to Color(0xFF003f7e),
                        0.3f to Color(0xFF0675c9),
                        0.7f to Color(0xFF0675c9),
                        1.0f to Color(0xFF003f7e)
                    )
                )
            )
    ) {
        ScratchCard(
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .height(200.dp)
                .width(300.dp)
                .align(Alignment.Center),
            overlayImage = ImageBitmap.imageResource(R.drawable.lidl_overlay),
            baseImage = painterResource(R.drawable.lidl_base),
            onScratchComplete = {
                isCardScratched.value = true
            },
            isScratched = isCardScratched.value,
            shape = RoundedCornerShape(3.dp),
            scratchLineWidth = 26.dp,
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()

                .align(Alignment.BottomCenter),
            onClick = {
                isCardScratched.value = false
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF64befa),
                contentColor = Color.White
            ),
            shape = RectangleShape
        ){
            Text(
                modifier = Modifier.padding(8.dp),
                text = "Restart",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}