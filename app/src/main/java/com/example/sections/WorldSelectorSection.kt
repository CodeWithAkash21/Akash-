package com.example.sections

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Brush
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.components.SectionHeader
import com.example.ui.theme.AccentRed
import com.example.ui.theme.DarkBorder
import com.example.ui.theme.DarkCardBg
import com.example.ui.theme.DarkSecondary
import com.example.ui.theme.MutedText
import com.example.ui.theme.PrimaryText

data class WorldCardData(
  val number: String,
  val title: String,
  val subtitle: String,
  val tags: List<String>,
  val icon: ImageVector,
  val previewText: String
)

@Composable
fun WorldSelectorSection(
  onNavigateToWorld: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  var expandedIndex by remember { mutableStateOf(0) }

  val worlds = remember {
    listOf(
      WorldCardData(
        number = "01",
        title = "BUILD",
        subtitle = "Software, AI, SaaS and products.",
        tags = listOf("grepTools.dev", "StudyShare AI", "SaaS Experiments"),
        icon = Icons.Default.Code,
        previewText = "Turning abstract logic into deployable utilities people rely on daily."
      ),
      WorldCardData(
        number = "02",
        title = "CREATE",
        subtitle = "Video, design, motion and visual storytelling.",
        tags = listOf("DaVinci Resolve", "After Effects", "UI Systems"),
        icon = Icons.Default.Brush,
        previewText = "Breathing cinematic life into ideas with dynamic pacing, motion and clean design."
      ),
      WorldCardData(
        number = "03",
        title = "EXPLORE",
        subtitle = "Experiments, ideas, AI and new technologies.",
        tags = listOf("AI Lab", "Gemini & Nova", "Hackathons"),
        icon = Icons.Default.Explore,
        previewText = "Pushing boundaries under pressure, probing autonomous agents and LLM architectures."
      ),
      WorldCardData(
        number = "04",
        title = "JOURNEY",
        subtitle = "The person behind the work.",
        tags = listOf("Origins", "Mindset", "8.2 CGPA", "Continuous Build"),
        icon = Icons.Default.Person,
        previewText = "A builder who rejects single-category labels. Learning by doing, shipping and iterating."
      )
    )
  }

  Column(
    modifier = modifier
      .fillMaxWidth()
      .background(DarkSecondary)
      .padding(horizontal = 20.dp, vertical = 24.dp)
  ) {
    SectionHeader(
      tag = "UNIVERSAL NAVIGATION",
      title = "WELCOME TO MY WORLD.",
      subtitle = "Akash is not one box. Choose a realm to explore."
    )

    Spacer(modifier = Modifier.height(16.dp))

    Column(
      verticalArrangement = Arrangement.spacedBy(14.dp),
      modifier = Modifier.fillMaxWidth()
    ) {
      worlds.forEachIndexed { index, world ->
        val isExpanded = expandedIndex == index
        val borderColor by animateColorAsState(
          targetValue = if (isExpanded) AccentRed else DarkBorder,
          animationSpec = tween(250),
          label = "borderColor"
        )
        val cardElevation by animateDpAsState(
          targetValue = if (isExpanded) 6.dp else 0.dp,
          animationSpec = tween(250),
          label = "elevation"
        )

        Box(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(DarkCardBg)
            .border(
              width = if (isExpanded) 1.5.dp else 1.dp,
              brush = if (isExpanded) {
                Brush.horizontalGradient(listOf(AccentRed, Color(0x33FF2A2A)))
              } else {
                Brush.linearGradient(listOf(DarkBorder, DarkBorder))
              },
              shape = RoundedCornerShape(12.dp)
            )
            .clickable {
              if (expandedIndex == index) {
                onNavigateToWorld(world.title)
              } else {
                expandedIndex = index
              }
            }
            .padding(18.dp)
            .testTag("world_card_${world.title.lowercase()}")
        ) {
          Column(modifier = Modifier.fillMaxWidth()) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                  modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .background(if (isExpanded) Color(0x26FF2A2A) else Color(0x11FFFFFF))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                  Text(
                    text = world.number,
                    color = if (isExpanded) AccentRed else MutedText,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace
                  )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                  text = world.title,
                  color = PrimaryText,
                  fontSize = 20.sp,
                  fontWeight = FontWeight.Black,
                  letterSpacing = 1.sp
                )
              }

              Icon(
                imageVector = world.icon,
                contentDescription = null,
                tint = if (isExpanded) AccentRed else MutedText,
                modifier = Modifier.size(20.dp)
              )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
              text = world.subtitle,
              color = if (isExpanded) PrimaryText else MutedText,
              fontSize = 14.sp,
              lineHeight = 20.sp,
              fontWeight = if (isExpanded) FontWeight.Medium else FontWeight.Normal
            )

            AnimatedVisibility(visible = isExpanded) {
              Column(
                modifier = Modifier
                  .fillMaxWidth()
                  .padding(top = 12.dp)
              ) {
                Text(
                  text = world.previewText,
                  color = Color(0xFFD4D4D8),
                  fontSize = 13.sp,
                  lineHeight = 18.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                  horizontalArrangement = Arrangement.spacedBy(6.dp),
                  verticalAlignment = Alignment.CenterVertically
                ) {
                  world.tags.forEach { tag ->
                    Box(
                      modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color(0x33050505))
                        .border(0.5.dp, DarkBorder, RoundedCornerShape(4.dp))
                        .padding(horizontal = 7.dp, vertical = 3.dp)
                    ) {
                      Text(
                        text = tag,
                        color = MutedText,
                        fontSize = 10.sp,
                        fontFamily = FontFamily.Monospace
                      )
                    }
                  }
                }

                Spacer(modifier = Modifier.height(14.dp))

                Row(
                  verticalAlignment = Alignment.CenterVertically,
                  modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0x1AFF2A2A))
                    .clickable { onNavigateToWorld(world.title) }
                    .padding(horizontal = 10.dp, vertical = 6.dp)
                ) {
                  Text(
                    text = "EXPLORE ${world.title} REALM",
                    color = AccentRed,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                    letterSpacing = 1.sp
                  )
                  Spacer(modifier = Modifier.width(6.dp))
                  Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    tint = AccentRed,
                    modifier = Modifier.size(13.dp)
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
