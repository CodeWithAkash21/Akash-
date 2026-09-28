package com.example.sections

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
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Memory
import androidx.compose.material.icons.filled.Psychology
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
import com.example.data.HackathonEntry
import com.example.data.PortfolioRepository
import com.example.ui.theme.AccentRed
import com.example.ui.theme.DarkBackground
import com.example.ui.theme.DarkBorder
import com.example.ui.theme.DarkCardBg
import com.example.ui.theme.MutedText
import com.example.ui.theme.PrimaryText
import com.example.ui.theme.TerminalCyan

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ExploreSection(modifier: Modifier = Modifier) {
  val interests = remember { PortfolioRepository.interestConstellation }
  val aiLabItems = remember { PortfolioRepository.aiLab }
  val hackathons = remember { PortfolioRepository.hackathons }

  Column(
    modifier = modifier
      .fillMaxWidth()
      .background(DarkBackground)
      .padding(horizontal = 20.dp, vertical = 24.dp)
  ) {
    SectionHeader(
      tag = "03 / EXPLORE",
      title = "I'M CURIOUS.",
      subtitle = "I learn by exploring things that don't always fit into one category."
    )

    Spacer(modifier = Modifier.height(14.dp))

    // Constellation of Interests
    Text(
      text = "CONSTELLATION OF INTERESTS",
      color = PrimaryText,
      fontSize = 15.sp,
      fontWeight = FontWeight.Black,
      letterSpacing = 1.sp,
      fontFamily = FontFamily.Monospace
    )

    Spacer(modifier = Modifier.height(10.dp))

    FlowRow(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      interests.forEach { item ->
        val statusColor = when (item.status) {
          "BUILDING" -> AccentRed
          "EXPERIMENTING" -> Color(0xFFF59E0B)
          "EXPLORING" -> TerminalCyan
          else -> Color(0xFF10B981)
        }

        Box(
          modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(DarkCardBg)
            .border(0.5.dp, DarkBorder, RoundedCornerShape(8.dp))
            .padding(horizontal = 10.dp, vertical = 7.dp)
        ) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
              modifier = Modifier
                .size(6.dp)
                .clip(CircleShape)
                .background(statusColor)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text = item.name,
              color = PrimaryText,
              fontSize = 12.sp,
              fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text = "• ${item.status}",
              color = statusColor,
              fontSize = 9.sp,
              fontWeight = FontWeight.Bold,
              fontFamily = FontFamily.Monospace
            )
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(28.dp))

    // AI LAB Section
    Row(verticalAlignment = Alignment.CenterVertically) {
      Icon(Icons.Default.Psychology, contentDescription = null, tint = AccentRed, modifier = Modifier.size(20.dp))
      Spacer(modifier = Modifier.width(8.dp))
      Text(
        text = "AI LAB // EXPERIMENTAL",
        color = PrimaryText,
        fontSize = 16.sp,
        fontWeight = FontWeight.Black,
        letterSpacing = 1.sp,
        fontFamily = FontFamily.Monospace
      )
    }
    Spacer(modifier = Modifier.height(6.dp))
    Text(
      text = "Researching autonomous reasoning, structured output workflows, document AI and ML models.",
      color = MutedText,
      fontSize = 13.sp
    )

    Spacer(modifier = Modifier.height(14.dp))

    Column(
      verticalArrangement = Arrangement.spacedBy(12.dp),
      modifier = Modifier.fillMaxWidth()
    ) {
      aiLabItems.forEach { lab ->
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(DarkCardBg)
            .border(1.dp, Brush.horizontalGradient(listOf(DarkBorder, Color(0x3306B6D4))), RoundedCornerShape(10.dp))
            .padding(14.dp)
        ) {
          Column {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text(
                text = lab.name,
                color = PrimaryText,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
              )
              Box(
                modifier = Modifier
                  .clip(RoundedCornerShape(4.dp))
                  .background(Color(0x1A06B6D4))
                  .padding(horizontal = 6.dp, vertical = 2.dp)
              ) {
                Text(
                  text = lab.tag,
                  color = TerminalCyan,
                  fontSize = 9.sp,
                  fontWeight = FontWeight.Bold,
                  fontFamily = FontFamily.Monospace
                )
              }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
              text = lab.description,
              color = Color(0xFFD4D4D8),
              fontSize = 12.sp,
              lineHeight = 17.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            FlowRow(
              horizontalArrangement = Arrangement.spacedBy(6.dp),
              verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
              lab.tech.forEach { t ->
                Box(
                  modifier = Modifier
                    .clip(RoundedCornerShape(3.dp))
                    .background(Color(0xFF141418))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                  Text(
                    text = t,
                    color = MutedText,
                    fontSize = 10.sp,
                    fontFamily = FontFamily.Monospace
                  )
                }
              }
            }
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(28.dp))

    // HACKATHON / CHALLENGE WALL
    Row(verticalAlignment = Alignment.CenterVertically) {
      Icon(Icons.Default.Bolt, contentDescription = null, tint = AccentRed, modifier = Modifier.size(20.dp))
      Spacer(modifier = Modifier.width(8.dp))
      Text(
        text = "BUILD UNDER PRESSURE",
        color = PrimaryText,
        fontSize = 16.sp,
        fontWeight = FontWeight.Black,
        letterSpacing = 1.sp,
        fontFamily = FontFamily.Monospace
      )
    }
    Spacer(modifier = Modifier.height(6.dp))
    Text(
      text = "Verified hackathon & sprint participations testing new model boundaries under tight deadlines.",
      color = MutedText,
      fontSize = 13.sp
    )

    Spacer(modifier = Modifier.height(14.dp))

    Column(
      verticalArrangement = Arrangement.spacedBy(12.dp),
      modifier = Modifier.fillMaxWidth()
    ) {
      hackathons.forEach { hack ->
        HackathonCard(hack = hack)
      }
    }
  }
}

@Composable
private fun HackathonCard(hack: HackathonEntry) {
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(12.dp))
      .background(DarkCardBg)
      .border(1.dp, Brush.horizontalGradient(listOf(Color(0x33FF2A2A), DarkBorder)), RoundedCornerShape(12.dp))
      .padding(16.dp)
      .testTag("hackathon_${hack.event.lowercase().replace(" ", "_")}")
  ) {
    Column {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = hack.event,
          color = PrimaryText,
          fontSize = 14.sp,
          fontWeight = FontWeight.Black,
          fontFamily = FontFamily.Monospace,
          letterSpacing = 0.5.sp
        )

        Box(
          modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(Color(0x2210B981))
            .border(0.5.dp, Color(0xFF10B981), RoundedCornerShape(4.dp))
            .padding(horizontal = 7.dp, vertical = 2.dp)
        ) {
          Text(
            text = "${hack.status} • ${hack.year}",
            color = Color(0xFF10B981),
            fontSize = 9.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace
          )
        }
      }

      Spacer(modifier = Modifier.height(6.dp))

      Text(
        text = "FOCUS: ${hack.projectOrIdea}",
        color = AccentRed,
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = FontFamily.Monospace
      )

      Spacer(modifier = Modifier.height(6.dp))

      Text(
        text = hack.details,
        color = Color(0xFFD4D4D8),
        fontSize = 12.sp,
        lineHeight = 17.sp
      )
    }
  }
}
