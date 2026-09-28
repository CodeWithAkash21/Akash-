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
import com.example.ui.theme.DarkBackground
import com.example.ui.theme.DarkBorder
import com.example.ui.theme.DarkCardBg
import com.example.ui.theme.MutedText
import com.example.ui.theme.PrimaryText

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ToolboxSection(modifier: Modifier = Modifier) {
  val toolGroups = remember { PortfolioRepository.toolbox }

  Column(
    modifier = modifier
      .fillMaxWidth()
      .background(DarkBackground)
      .padding(horizontal = 20.dp, vertical = 24.dp)
  ) {
    SectionHeader(
      tag = "TECHNICAL ARSENAL",
      title = "THE THINGS I USE.",
      subtitle = "No fake percentage bars. Just the production tools, runtimes, and creative suites wielded daily."
    )

    Spacer(modifier = Modifier.height(14.dp))

    Column(
      verticalArrangement = Arrangement.spacedBy(14.dp),
      modifier = Modifier.fillMaxWidth()
    ) {
      toolGroups.forEach { group ->
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(DarkCardBg)
            .border(0.5.dp, DarkBorder, RoundedCornerShape(10.dp))
            .padding(16.dp)
            .testTag("toolbox_${group.category.lowercase().replace(" ", "_")}")
        ) {
          Column {
            Row(
              verticalAlignment = Alignment.CenterVertically
            ) {
              Box(
                modifier = Modifier
                  .size(6.dp)
                  .clip(CircleShape)
                  .background(AccentRed)
              )
              Spacer(modifier = Modifier.width(8.dp))
              Text(
                text = group.category,
                color = AccentRed,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace,
                letterSpacing = 1.sp
              )
            }

            Spacer(modifier = Modifier.height(10.dp))

            FlowRow(
              horizontalArrangement = Arrangement.spacedBy(6.dp),
              verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
              group.items.forEach { tool ->
                Box(
                  modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color(0xFF141418))
                    .border(0.5.dp, Color(0x22FFFFFF), RoundedCornerShape(4.dp))
                    .padding(horizontal = 9.dp, vertical = 5.dp)
                ) {
                  Text(
                    text = tool,
                    color = PrimaryText,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = FontFamily.Monospace
                  )
                }
              }
            }
          }
        }
      }
    }
  }
}
