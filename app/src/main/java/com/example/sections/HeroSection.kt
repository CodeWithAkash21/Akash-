package com.example.sections

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.AccentRed
import com.example.ui.theme.DarkBackground
import com.example.ui.theme.DarkBorder
import com.example.ui.theme.DarkCardBg
import com.example.ui.theme.MutedText
import com.example.ui.theme.OnlineGreen
import com.example.ui.theme.PrimaryText

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HeroSection(
  onEnterWorld: () -> Unit,
  onExploreWork: () -> Unit,
  onLaunchPlayground: () -> Unit,
  modifier: Modifier = Modifier
) {
  val infiniteTransition = rememberInfiniteTransition(label = "hero_glow")
  val pulseGlow by infiniteTransition.animateFloat(
    initialValue = 0.3f,
    targetValue = 0.9f,
    animationSpec = infiniteRepeatable(
      animation = tween(2200, easing = LinearEasing),
      repeatMode = RepeatMode.Reverse
    ),
    label = "pulseGlow"
  )

  Box(
    modifier = modifier
      .fillMaxWidth()
      .background(DarkBackground)
  ) {
    // Abstract digital background matrix with red glowing nodes
    Canvas(
      modifier = Modifier
        .matchParentSize()
    ) {
      val canvasWidth = size.width
      val canvasHeight = size.height

      // Subtle tech grid lines
      val step = 48.dp.toPx()
      var x = 0f
      while (x < canvasWidth) {
        drawLine(
          color = Color(0x0AFFFFFF),
          start = Offset(x, 0f),
          end = Offset(x, canvasHeight),
          strokeWidth = 1f
        )
        x += step
      }

      var y = 0f
      while (y < canvasHeight) {
        drawLine(
          color = Color(0x0AFFFFFF),
          start = Offset(0f, y),
          end = Offset(canvasWidth, y),
          strokeWidth = 1f
        )
        y += step
      }

      // Glowing red nodal points
      val nodes = listOf(
        Offset(canvasWidth * 0.15f, canvasHeight * 0.20f),
        Offset(canvasWidth * 0.85f, canvasHeight * 0.12f),
        Offset(canvasWidth * 0.78f, canvasHeight * 0.65f),
        Offset(canvasWidth * 0.22f, canvasHeight * 0.78f),
        Offset(canvasWidth * 0.50f, canvasHeight * 0.40f)
      )

      nodes.forEach { node ->
        drawCircle(
          color = AccentRed.copy(alpha = 0.15f * pulseGlow),
          radius = 32.dp.toPx(),
          center = node
        )
        drawCircle(
          color = AccentRed.copy(alpha = 0.65f * pulseGlow),
          radius = 3.dp.toPx(),
          center = node
        )
      }
    }

    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 20.dp, vertical = 24.dp)
    ) {
      // Small identity badge & status
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Box(
          modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(Color(0x1AFF2A2A))
            .border(1.dp, Color(0x33FF2A2A), RoundedCornerShape(4.dp))
            .padding(horizontal = 10.dp, vertical = 4.dp)
        ) {
          Text(
            text = "DIGITAL WORLD // 2026",
            color = AccentRed,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace,
            letterSpacing = 1.sp
          )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
          Box(
            modifier = Modifier
              .size(6.dp)
              .clip(CircleShape)
              .background(OnlineGreen)
          )
          Spacer(modifier = Modifier.width(6.dp))
          Text(
            text = "BUILDING IN PUBLIC",
            color = MutedText,
            fontSize = 10.sp,
            fontFamily = FontFamily.Monospace
          )
        }
      }

      Spacer(modifier = Modifier.height(28.dp))

      // Giant Editorial Name
      Text(
        text = "AKASH",
        color = PrimaryText,
        fontSize = 48.sp,
        fontWeight = FontWeight.Black,
        lineHeight = 46.sp,
        letterSpacing = (-1.5).sp
      )
      Text(
        text = "PUJAR",
        color = Color(0xFFD4D4D8),
        fontSize = 48.sp,
        fontWeight = FontWeight.Black,
        lineHeight = 46.sp,
        letterSpacing = (-1.5).sp
      )

      Spacer(modifier = Modifier.height(18.dp))

      // Core Creed Statements
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .clip(RoundedCornerShape(8.dp))
          .background(Color(0x22111116))
          .border(1.dp, DarkBorder, RoundedCornerShape(8.dp))
          .padding(14.dp)
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Text(
            text = "I BUILD.",
            color = AccentRed,
            fontSize = 22.sp,
            fontWeight = FontWeight.Black,
            letterSpacing = 1.sp
          )
          Spacer(modifier = Modifier.width(10.dp))
          Text(
            text = "I CREATE.",
            color = PrimaryText,
            fontSize = 22.sp,
            fontWeight = FontWeight.Black,
            letterSpacing = 1.sp
          )
          Spacer(modifier = Modifier.width(10.dp))
          Text(
            text = "I EXPLORE.",
            color = Color(0xFFA1A1AA),
            fontSize = 22.sp,
            fontWeight = FontWeight.Black,
            letterSpacing = 1.sp
          )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
          text = "TECH × AI × CREATIVE × IDEAS",
          color = AccentRed,
          fontSize = 12.sp,
          fontWeight = FontWeight.Bold,
          letterSpacing = 2.sp,
          fontFamily = FontFamily.Monospace
        )
      }

      Spacer(modifier = Modifier.height(18.dp))

      // Supporting narrative copy
      Text(
        text = "\"Welcome to my digital world — a collection of the things I build, create, learn and explore.\"",
        color = PrimaryText,
        fontSize = 15.sp,
        lineHeight = 22.sp,
        fontWeight = FontWeight.Medium
      )

      Spacer(modifier = Modifier.height(10.dp))

      Text(
        text = "Developer, creator and lifelong experimenter building software products, visual experiences and scalable ideas across technology and human creativity. Akash is not one box.",
        color = MutedText,
        fontSize = 13.sp,
        lineHeight = 19.sp
      )

      Spacer(modifier = Modifier.height(24.dp))

      // Action Buttons
      Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.fillMaxWidth()
      ) {
        Button(
          onClick = onEnterWorld,
          modifier = Modifier
            .fillMaxWidth()
            .testTag("hero_enter_world_btn"),
          colors = ButtonDefaults.buttonColors(
            containerColor = AccentRed,
            contentColor = PrimaryText
          ),
          shape = RoundedCornerShape(8.dp)
        ) {
          Text(
            text = "ENTER MY WORLD",
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace,
            letterSpacing = 1.sp
          )
          Spacer(modifier = Modifier.width(8.dp))
          Icon(
            imageVector = Icons.Default.ArrowForward,
            contentDescription = null,
            modifier = Modifier.size(16.dp)
          )
        }

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          OutlinedButton(
            onClick = onExploreWork,
            modifier = Modifier
              .weight(1f)
              .testTag("hero_explore_work_btn"),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = PrimaryText)
          ) {
            Icon(Icons.Default.Code, contentDescription = null, modifier = Modifier.size(14.dp))
            Spacer(modifier = Modifier.width(6.dp))
            Text("EXPLORE WORK", fontSize = 11.sp, fontFamily = FontFamily.Monospace)
          }

          OutlinedButton(
            onClick = onLaunchPlayground,
            modifier = Modifier
              .weight(1f)
              .testTag("hero_playground_btn"),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = AccentRed)
          ) {
            Icon(Icons.Default.Terminal, contentDescription = null, modifier = Modifier.size(14.dp), tint = AccentRed)
            Spacer(modifier = Modifier.width(6.dp))
            Text("TRY LAB", fontSize = 11.sp, color = AccentRed, fontFamily = FontFamily.Monospace)
          }
        }
      }

      Spacer(modifier = Modifier.height(24.dp))

      // Micro Footer Info
      FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
      ) {
        BadgeTag("BASED IN INDIA")
        BadgeTag("BUILDING IN PUBLIC")
        BadgeTag("BCA GRADUATE")
        BadgeTag("GREPTOOLS CREATOR")
      }
    }
  }
}

@Composable
private fun BadgeTag(text: String) {
  Box(
    modifier = Modifier
      .clip(RoundedCornerShape(4.dp))
      .background(DarkCardBg)
      .border(0.5.dp, DarkBorder, RoundedCornerShape(4.dp))
      .padding(horizontal = 8.dp, vertical = 4.dp)
  ) {
    Text(
      text = text,
      color = MutedText,
      fontSize = 10.sp,
      fontFamily = FontFamily.Monospace,
      letterSpacing = 0.5.sp
    )
  }
}
