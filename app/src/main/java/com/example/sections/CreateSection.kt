package com.example.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brush
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.Visibility
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
import com.example.data.CreativeItem
import com.example.data.PortfolioRepository
import com.example.ui.theme.AccentRed
import com.example.ui.theme.DarkBackground
import com.example.ui.theme.DarkBorder
import com.example.ui.theme.DarkCardBg
import com.example.ui.theme.DarkSecondary
import com.example.ui.theme.MutedText
import com.example.ui.theme.PrimaryText

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CreateSection(modifier: Modifier = Modifier) {
  val creativeItems = remember { PortfolioRepository.creativeItems }

  val designTools = remember {
    listOf(
      "UI DESIGN" to "Figma & Component Kits",
      "VISUAL DESIGN" to "Editorial & Layout Grids",
      "MOTION" to "DaVinci Resolve & After Effects",
      "BRANDING" to "Identity, Typography & Spacing",
      "CANVA & SOCIAL" to "High-engagement graphics & hooks",
      "PRODUCT VISUALS" to "Cinematic Framed Previews"
    )
  }

  Column(
    modifier = modifier
      .fillMaxWidth()
      .background(DarkSecondary)
      .padding(horizontal = 20.dp, vertical = 24.dp)
  ) {
    SectionHeader(
      tag = "02 / CREATE",
      title = "I DON'T JUST BUILD.\nI MAKE THINGS FEEL ALIVE.",
      subtitle = "Video, design, motion and visual storytelling. Bridging engineered logic with human emotion."
    )

    Spacer(modifier = Modifier.height(16.dp))

    // 4 Creative Disciplines Pillar Grid
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .horizontalScroll(rememberScrollState()),
      horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
      DisciplinePill("VIDEO", Icons.Default.Movie, "DaVinci & Premiere")
      DisciplinePill("DESIGN", Icons.Default.Brush, "Figma & Canva")
      DisciplinePill("MOTION", Icons.Default.PlayCircle, "After Effects & Rhythms")
      DisciplinePill("AI VISUALS", Icons.Default.Visibility, "Multimodal Concepts")
    }

    Spacer(modifier = Modifier.height(24.dp))

    // Cinematic Showcase Cards
    Text(
      text = "CINEMATIC SHOWCASE & EXPERIMENTS",
      color = PrimaryText,
      fontSize = 15.sp,
      fontWeight = FontWeight.Black,
      letterSpacing = 1.sp,
      fontFamily = FontFamily.Monospace
    )

    Spacer(modifier = Modifier.height(14.dp))

    Column(
      verticalArrangement = Arrangement.spacedBy(14.dp),
      modifier = Modifier.fillMaxWidth()
    ) {
      creativeItems.forEach { item ->
        CreativeWorkCard(item = item)
      }
    }

    Spacer(modifier = Modifier.height(28.dp))

    // Design Systems & Visual Craft
    Text(
      text = "DESIGN SYSTEMS & PRODUCT CRAFT",
      color = PrimaryText,
      fontSize = 15.sp,
      fontWeight = FontWeight.Black,
      letterSpacing = 1.sp,
      fontFamily = FontFamily.Monospace
    )
    Spacer(modifier = Modifier.height(6.dp))
    Text(
      text = "Treating interfaces as pieces of editorial architecture.",
      color = MutedText,
      fontSize = 13.sp
    )

    Spacer(modifier = Modifier.height(14.dp))

    FlowRow(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(10.dp),
      verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
      designTools.forEach { (title, subtitle) ->
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(DarkCardBg)
            .border(0.5.dp, DarkBorder, RoundedCornerShape(8.dp))
            .padding(14.dp)
        ) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Column {
              Text(
                text = title,
                color = AccentRed,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace,
                letterSpacing = 1.sp
              )
              Spacer(modifier = Modifier.height(2.dp))
              Text(
                text = subtitle,
                color = PrimaryText,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium
              )
            }
            Box(
              modifier = Modifier
                .size(6.dp)
                .clip(CircleShape)
                .background(AccentRed)
            )
          }
        }
      }
    }
  }
}

@Composable
private fun DisciplinePill(
  name: String,
  icon: androidx.compose.ui.graphics.vector.ImageVector,
  sub: String
) {
  Box(
    modifier = Modifier
      .clip(RoundedCornerShape(10.dp))
      .background(DarkCardBg)
      .border(1.dp, DarkBorder, RoundedCornerShape(10.dp))
      .padding(horizontal = 14.dp, vertical = 10.dp)
  ) {
    Column {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, contentDescription = null, tint = AccentRed, modifier = Modifier.size(16.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Text(
          text = name,
          color = PrimaryText,
          fontSize = 12.sp,
          fontWeight = FontWeight.Black,
          fontFamily = FontFamily.Monospace,
          letterSpacing = 1.sp
        )
      }
      Spacer(modifier = Modifier.height(4.dp))
      Text(
        text = sub,
        color = MutedText,
        fontSize = 11.sp,
        fontFamily = FontFamily.Monospace
      )
    }
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun CreativeWorkCard(item: CreativeItem) {
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(12.dp))
      .background(DarkCardBg)
      .border(1.dp, Brush.horizontalGradient(listOf(DarkBorder, Color(0x33FF2A2A))), RoundedCornerShape(12.dp))
      .padding(16.dp)
      .testTag("creative_card_${item.id}")
  ) {
    Column(modifier = Modifier.fillMaxWidth()) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Box(
          modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(Color(0x22FF2A2A))
            .padding(horizontal = 8.dp, vertical = 3.dp)
        ) {
          Text(
            text = item.discipline,
            color = AccentRed,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace,
            letterSpacing = 1.sp
          )
        }

        Box(
          modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(Color(0x1AFFFFFF))
            .padding(horizontal = 8.dp, vertical = 3.dp)
        ) {
          Text(
            text = item.status,
            color = MutedText,
            fontSize = 9.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace
          )
        }
      }

      Spacer(modifier = Modifier.height(10.dp))

      Text(
        text = item.title,
        color = PrimaryText,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold
      )

      Spacer(modifier = Modifier.height(6.dp))

      Text(
        text = item.description,
        color = Color(0xFFD4D4D8),
        fontSize = 13.sp,
        lineHeight = 18.sp
      )

      Spacer(modifier = Modifier.height(12.dp))

      FlowRow(
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
      ) {
        item.tools.forEach { tool ->
          Box(
            modifier = Modifier
              .clip(RoundedCornerShape(4.dp))
              .background(Color(0xFF141418))
              .padding(horizontal = 7.dp, vertical = 3.dp)
          ) {
            Text(
              text = tool,
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
