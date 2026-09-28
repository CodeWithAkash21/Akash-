package com.example.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Grade
import androidx.compose.material.icons.filled.RocketLaunch
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.components.SectionHeader
import com.example.data.PortfolioRepository
import com.example.ui.theme.AccentRed
import com.example.ui.theme.DarkBorder
import com.example.ui.theme.DarkCardBg
import com.example.ui.theme.DarkSecondary
import com.example.ui.theme.MutedText
import com.example.ui.theme.PrimaryText

@Composable
fun AchievementsSection(modifier: Modifier = Modifier) {
  val milestones = remember { PortfolioRepository.milestones }

  Column(
    modifier = modifier
      .fillMaxWidth()
      .background(DarkSecondary)
      .padding(horizontal = 20.dp, vertical = 24.dp)
  ) {
    SectionHeader(
      tag = "VERIFIED / TRACK RECORD",
      title = "MILESTONES.",
      subtitle = "Solid academic foundation, public software shipped and competitive endurance."
    )

    Spacer(modifier = Modifier.height(16.dp))

    // 2x2 or Vertical Metric Cards
    Column(
      verticalArrangement = Arrangement.spacedBy(14.dp),
      modifier = Modifier.fillMaxWidth()
    ) {
      milestones.forEach { item ->
        val icon = when (item.iconName) {
          "School" -> Icons.Default.School
          "Grade" -> Icons.Default.Grade
          "RocketLaunch" -> Icons.Default.RocketLaunch
          else -> Icons.Default.Bolt
        }

        Box(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(DarkCardBg)
            .border(1.dp, Brush.horizontalGradient(listOf(DarkBorder, Color(0x33FF2A2A))), RoundedCornerShape(12.dp))
            .padding(18.dp)
            .testTag("milestone_${item.title.lowercase().replace(" ", "_")}")
        ) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
          ) {
            Column(modifier = Modifier.weight(1f)) {
              Box(
                modifier = Modifier
                  .clip(RoundedCornerShape(4.dp))
                  .background(Color(0x1AFF2A2A))
                  .padding(horizontal = 7.dp, vertical = 3.dp)
              ) {
                Text(
                  text = item.category,
                  color = AccentRed,
                  fontSize = 10.sp,
                  fontWeight = FontWeight.Bold,
                  fontFamily = FontFamily.Monospace,
                  letterSpacing = 1.sp
                )
              }

              Spacer(modifier = Modifier.height(8.dp))

              Text(
                text = item.title,
                color = PrimaryText,
                fontSize = 17.sp,
                fontWeight = FontWeight.Black
              )

              Spacer(modifier = Modifier.height(6.dp))

              Text(
                text = item.subtitle,
                color = MutedText,
                fontSize = 12.sp,
                lineHeight = 17.sp
              )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Large metric callout
            Column(
              horizontalAlignment = Alignment.End
            ) {
              Box(
                modifier = Modifier
                  .size(36.dp)
                  .clip(CircleShape)
                  .background(Color(0x15FFFFFF)),
                contentAlignment = Alignment.Center
              ) {
                Icon(icon, contentDescription = null, tint = AccentRed, modifier = Modifier.size(20.dp))
              }

              Spacer(modifier = Modifier.height(8.dp))

              Text(
                text = item.metricOrLabel,
                color = AccentRed,
                fontSize = 26.sp,
                fontWeight = FontWeight.Black,
                fontFamily = FontFamily.Monospace,
                letterSpacing = (-0.5).sp
              )
            }
          }
        }
      }
    }
  }
}
