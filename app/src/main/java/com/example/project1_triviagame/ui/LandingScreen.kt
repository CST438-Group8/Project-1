@file:Suppress("COMPOSE_APPLIER_CALL_MISMATCH")

package com.example.project1_triviagame.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import com.example.project1_triviagame.ui.theme.AmaticSC
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlin.math.abs

@Composable
fun LandingScreen(
    categories: List<TriviaCategory>,
    wins: Int,
    losses: Int,
    onPlay: (Int) -> Unit,
    onRefreshStats: suspend () -> Unit,
    onLogout: () -> Unit
) {
    val bg = Color(0xFF0B2A4A)
    val glowBlue = Color(0xFF00E5FF)
    val taupe = Color(0xFF8C817A)

    val cardWidth = 240.dp
    val cardHeight = 380.dp
    val carouselHeight = 420.dp

    val arrowSize = 72.dp
    val arrowGap = 8.dp
    val itemSpacing = 16.dp

    val panelShape = RoundedCornerShape(28.dp)

    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    var selectedIndex by remember { mutableIntStateOf(0) }

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo }
            .map { layoutInfo ->
                val center = (layoutInfo.viewportStartOffset + layoutInfo.viewportEndOffset) / 2
                val closest = layoutInfo.visibleItemsInfo.minByOrNull { info ->
                    abs((info.offset + info.size / 2) - center)
                }
                closest?.index ?: 0
            }
            .distinctUntilChanged()
            .collect { selectedIndex = it.coerceIn(0, categories.lastIndex) }
    }

    LaunchedEffect(Unit) { onRefreshStats() }

    val hasPrev = selectedIndex > 0
    val hasNext = selectedIndex < categories.lastIndex

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(bg)
    ) {
        val sideReserved = arrowSize + arrowGap
        val viewportWidth = maxWidth - (sideReserved * 2)
        val sidePadding = (viewportWidth - cardWidth) / 2

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CurvedTitleCompose(
                text = "HANGCEPTION",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
            )

            Spacer(Modifier.height(6.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(carouselHeight),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier.size(arrowSize),
                    contentAlignment = Alignment.Center
                ) {
                    if (hasPrev) {
                        GlowArrow(
                            direction = ArrowDir.LEFT,
                            glowColor = glowBlue,
                            size = arrowSize
                        ) {
                            val target = (selectedIndex - 1).coerceAtLeast(0)
                            scope.launch { listState.animateScrollToItem(target) }
                        }
                    }
                }

                Spacer(Modifier.width(arrowGap))

                val flingBehavior = rememberSnapFlingBehavior(listState)

                Box(
                    modifier = Modifier
                        .width(viewportWidth.coerceAtLeast(0.dp))
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ) {

                    // Neon glass background only (single frame now)
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .padding(vertical = 12.dp)
                            .shadow(16.dp, panelShape, clip = false)
                            .background(
                                brush = Brush.verticalGradient(
                                    listOf(
                                        Color(0x3329B6F6),
                                        Color(0x1A29B6F6)
                                    )
                                ),
                                shape = panelShape
                            )
                            .border(
                                width = 1.dp,
                                brush = Brush.horizontalGradient(
                                    listOf(
                                        Color.White.copy(alpha = 0.4f),
                                        Color.White.copy(alpha = 0.1f)
                                    )
                                ),
                                shape = panelShape
                            )
                    )

                    LazyRow(
                        state = listState,
                        flingBehavior = flingBehavior,
                        contentPadding = PaddingValues(horizontal = sidePadding.coerceAtLeast(0.dp)),
                        horizontalArrangement = Arrangement.spacedBy(itemSpacing),
                        modifier = Modifier
                            .matchParentSize()
                            .padding(vertical = 16.dp)
                    ) {
                        itemsIndexed(
                            items = categories,
                            key = { _, cat -> cat.id }
                        ) { index, cat ->

                            val layoutInfo = listState.layoutInfo
                            val viewportCenter =
                                (layoutInfo.viewportStartOffset +
                                        layoutInfo.viewportEndOffset) / 2f

                            val info =
                                layoutInfo.visibleItemsInfo.firstOrNull { it.index == index }

                            val itemCenter =
                                if (info != null)
                                    (info.offset + info.size / 2f)
                                else viewportCenter

                            val distance = abs(viewportCenter - itemCenter)
                            val norm = (distance / viewportCenter).coerceIn(0f, 1f)

                            val scale = 0.88f + (1f - norm) * 0.12f
                            val alpha = 0.65f + (1f - norm) * 0.35f

                            CategoryCardCompose(
                                category = cat,
                                width = cardWidth,
                                height = cardHeight,
                                scale = scale,
                                alpha = alpha,
                                selected = index == selectedIndex
                            )
                        }
                    }
                }

                Spacer(Modifier.width(arrowGap))

                Box(
                    modifier = Modifier.size(arrowSize),
                    contentAlignment = Alignment.Center
                ) {
                    if (hasNext) {
                        GlowArrow(
                            direction = ArrowDir.RIGHT,
                            glowColor = glowBlue,
                            size = arrowSize
                        ) {
                            val target =
                                (selectedIndex + 1).coerceAtMost(categories.lastIndex)
                            scope.launch { listState.animateScrollToItem(target) }
                        }
                    }
                }
            }

            // Move Play up slightly (only adjusting vertical spacing here)
            Spacer(modifier = Modifier.weight(0.65f))

            Button(
                onClick = { onPlay(categories[selectedIndex].id) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(62.dp)
                    .padding(horizontal = 28.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = taupe)
            ) {
                Text(
                    text = "Play",
                    color = Color.White,
                    fontSize = 28.sp,
                    fontFamily = AmaticSC,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(Modifier.height(14.dp))

            Button(
                onClick = { onLogout() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(62.dp)
                    .padding(horizontal = 28.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F))
            ) {
                Text(
                    text = "Logout",
                    color = Color.White,
                    fontSize = 28.sp,
                    fontFamily = AmaticSC,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(Modifier.height(22.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 14.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Wins: $wins",
                    color = Color.White,
                    fontSize = 26.sp,
                    fontFamily = AmaticSC,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(16.dp)
                )
                Text(
                    text = "Losses: $losses",
                    color = Color.White,
                    fontSize = 26.sp,
                    fontFamily = AmaticSC,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

private enum class ArrowDir { LEFT, RIGHT }

@Composable
private fun GlowArrow(
    direction: ArrowDir,
    glowColor: Color,
    size: Dp,
    onClick: () -> Unit
) {
    val arrow = if (direction == ArrowDir.LEFT) "◀" else "▶"
    val interaction = remember { MutableInteractionSource() }

    Box(
        modifier = Modifier
            .size(size)
            .clickable(
                interactionSource = interaction,
                indication = null
            ) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = arrow,
            color = glowColor,
            fontSize = 36.sp,
            fontWeight = FontWeight.Black,
            style = TextStyle(
                shadow = Shadow(
                    color = glowColor,
                    blurRadius = 22f
                )
            )
        )
    }
}

@Composable
private fun CategoryCardCompose(
    category: TriviaCategory,
    width: Dp,
    height: Dp,
    scale: Float,
    alpha: Float,
    selected: Boolean
) {
    val shape = RoundedCornerShape(22.dp)

    Surface(
        modifier = Modifier
            .width(width)
            .height(height)
            .scale(scale)
            .alpha(alpha)
            .shadow(
                elevation = if (selected) 18.dp else 8.dp,
                shape = shape,
                clip = false
            ),
        shape = shape,
        color = Color.Transparent,
        shadowElevation = 0.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = category.imageRes),
                contentDescription = category.name,
                modifier = Modifier.size(240.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(Modifier.height(14.dp))

            Text(
                text = category.name,
                color = Color.White,
                fontSize = 32.sp,
                fontFamily = AmaticSC,
                fontWeight = FontWeight.SemiBold,
                style = TextStyle(
                    shadow = Shadow(
                        color = Color.White,
                        blurRadius = 18f
                    )
                ),
                maxLines = 1
            )
        }
    }
}