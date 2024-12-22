package com.example.scratchcardcompose.ui.scratch_card

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.example.scratchcardcompose.R
import com.example.scratchcardcompose.ui.theme.ScratchCardComposeTheme
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * A customizable scratch card component that allows users to "scratch" an overlay image by dragging their finger over it.
 * It reveals a base image beneath the overlay as the user interacts with the screen.
 *
 * @param modifier Modifier to be applied to the ScratchCard.
 * @param overlayImage The image that will be scratched off by the user, typically a texture or effect that will be revealed underneath.
 * @param baseImage The base image that will be revealed after the overlay is scratched off.
 * @param scratchingThresholdPercentage The percentage of the area that needs to be scratched off to show the base image.
 * @param scratchLineWidth The width of the scratch lines drawn when the user drags their finger across the overlay.
 * @param scratchLineCap The shape of the stroke cap applied to the scratch lines (e.g., Round, Square).
 * @param isScratched Flag that determines whether the scratch card has already been fully scratched. If true, the card is fully scratched and no further scratching is possible.
 * @param onScratchComplete A callback that is triggered when the scratch card is fully scratched, meaning the threshold has been reached.
 * @param shape The shape of the scratch card, typically a rounded rectangle or custom shape.
 */
@Composable
fun ScratchCard(
    modifier: Modifier = Modifier,
    overlayImage: ImageBitmap,
    baseImage: Painter,
    scratchingThresholdPercentage : Float = 0.8f,
    scratchLineWidth : Dp = 32.dp,
    scratchLineCap : StrokeCap = StrokeCap.Round,
    isScratched: Boolean = false,
    onScratchComplete: () -> Unit = {},
    shape: Shape = RoundedCornerShape(12.dp)
) {
    val scratchLines = remember {
        mutableStateListOf<Line>()
    }
    val totalScratchedArea = remember {
        mutableFloatStateOf(0f)
    }

    Box(
        modifier = modifier
            .clip(shape)
    ){
        Image(
            painter = baseImage,
            contentDescription = "Base image",
            modifier = Modifier
                .fillMaxSize()
                .clip(shape),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .graphicsLayer {
                    compositingStrategy = CompositingStrategy.Offscreen //Without it, all the pixels (in this example baseImage) drawn underneath scratchLines will be cleared with scratchLines
                    //now baseImage is considered as a separate layer and masking can be applied
                }
        ) {
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(true) {
                        detectTapGestures(
                            onTap = { position ->
                                val line = Line(
                                    start = position,
                                    end = position,
                                    strokeWidth = scratchLineWidth.toPx()
                                )
                                //accumulate the total scratched area, including overlapping lines
                                totalScratchedArea.floatValue += line.calculateArea()
                                scratchLines.add(line)
                            }
                        )
                    }
                    .pointerInput(true) {
                        detectDragGestures { change, dragAmount ->
                            change.consume()

                            val line = Line(
                                start = change.position - dragAmount,
                                end = change.position,
                                strokeWidth = scratchLineWidth.toPx()
                            )
                            //accumulate the total scratched area, including overlapping lines
                            totalScratchedArea.floatValue += line.calculateArea()

                            scratchLines.add(line)
                        }
                    }
            ) {
                val imageSize = IntSize(width = size.width.toInt(), height = size.height.toInt())
                val maxCanvasArea = this.size.width * this.size.height

                //if total scratched area is below the threshold, show the overlay image
                if(!isScratched && totalScratchedArea.floatValue/maxCanvasArea < scratchingThresholdPercentage) {
                    drawImage(image = overlayImage, dstSize = imageSize)
                    //draw the scratch lines with transparency to "erase" the overlay
                    scratchLines.forEach { line ->
                        drawLine(
                            color = Color.Transparent,
                            start = line.start,
                            end = line.end,
                            strokeWidth = line.strokeWidth,
                            cap = scratchLineCap,
                            blendMode = BlendMode.Clear //clears the pixels covered by the source in the destination
                        )
                    }
                }else{
                    if(totalScratchedArea.floatValue>0) {
                        if(!isScratched){
                            onScratchComplete()
                        }
                        if(isScratched){
                            scratchLines.clear()
                            totalScratchedArea.floatValue = 0f
                        }
                    }
                }
            }
        }
    }
}

private data class Line(
    val start: Offset,
    val end: Offset,
    val strokeWidth: Float
){
    fun calculateArea(): Float {
        val lineLength = calculateLength()
        return lineLength * strokeWidth
    }
    private fun calculateLength(): Float {
        return sqrt((end.x - start.x).pow(2) + (end.y - start.y).pow(2))
    }
}

@Composable
@Preview
private fun ImageScratchPreview() {
    ScratchCardComposeTheme {
        ScratchCard(
            overlayImage = ImageBitmap.imageResource(R.drawable.scratch_here_overlay),
            baseImage = painterResource(R.drawable.spiderman),
            onScratchComplete = {},
            isScratched = false
        )
    }
}