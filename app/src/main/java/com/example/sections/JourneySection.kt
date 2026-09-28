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

@Composable
fun JourneySection(modifier: Modifier = Modifier) {
  val timeline = remember { PortfolioRepository.timeline }
  val mindset = remember { PortfolioRepository.mindset }

  Column(
    modifier = modifier
      .fillMaxWidth()
      .background(DarkBackground)
      .padding(horizontal = 20.dp, vertical = 24.dp)
  ) {
    SectionHeader(
      tag = "04 / HUMAN CONTEXT",
      title = "THE JOURNEY.",
      subtitle = "No corporate biography. Just the authentic sequence of curiosity, code, and iteration."
    )

    Spacer(modifier = Modifier.height(16.dp))

    // Visual Timeline with connecting vertical line
    Column(
      modifier = Modifier.fillMaxWidth()
    ) {
      timeline.forEachIndexed { index, node ->
        Row(
          modifier = Modifier.fillMaxWidth()
        ) {
          // Timeline indicator column
          Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(32.dp)
          ) {
            Box(
              modifier = Modifier
                .size(14.dp)
                .clip(CircleShape)
                .background(AccentRed)
                .border(2.dp, DarkBackground, CircleShape)
            )

            if (index < timeline.size - 1) {
              Box(
                modifier = Modifier
                  .width(2.dp)
                  .height(90.dp)
                  .background(Brush.verticalGradient(listOf(AccentRed, Color(0x33FF2A2A))))
              )
            }
          }

          Spacer(modifier = Modifier.width(12.dp))

          // Content Box
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .clip(RoundedCornerShape(10.dp))
              .background(DarkCardBg)
              .border(0.5.dp, DarkBorder, RoundedCornerShape(10.dp))
              .padding(14.dp)
          ) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text(
                text = node.title,
                color = PrimaryText,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
              )
              Text(
                text = node.tag,
                color = AccentRed,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace
              )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
              text = node.description,
              color = MutedText,
              fontSize = 12.sp,
              lineHeight = 17.sp
            )
          }
        }
        Spacer(modifier = Modifier.height(14.dp))
      }
    }

    Spacer(modifier = Modifier.height(18.dp))

    // Final creed badge
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(8.dp))
        .background(Color(0x26FF2A2A))
        .border(1.dp, AccentRed, RoundedCornerShape(8.dp))
        .padding(14.dp),
      contentAlignment = Alignment.Center
    ) {
      Text(
        text = "\"I'M STILL BUILDING.\"",
        color = AccentRed,
        fontSize = 16.sp,
        fontWeight = FontWeight.Black,
        letterSpacing = 2.sp,
        fontFamily = FontFamily.Monospace
      )
    }

    Spacer(modifier = Modifier.height(32.dp))

    // WHO IS AKASH?
    SectionHeader(
      tag = "CORE IDENTITY",
      title = "WHO IS AKASH?",
      subtitle = "Rejecting artificial boundaries between engineering and creative expression."
    )

    Spacer(modifier = Modifier.height(12.dp))

    val roles = listOf(
      "A DEVELOPER.",
      "A CREATOR.",
      "AN EXPERIMENTER.",
      "A LEARNER.",
      "A BUILDER.",
      "A STORYTELLER.",
      "A PERSON WHO LIKES FIGURING THINGS OUT."
    )

    Column(
      verticalArrangement = Arrangement.spacedBy(8.dp),
      modifier = Modifier.fillMaxWidth()
    ) {
      roles.forEach { role ->
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(6.dp))
            .background(DarkCardBg)
            .border(0.5.dp, DarkBorder, RoundedCornerShape(6.dp))
            .padding(horizontal = 14.dp, vertical = 10.dp)
        ) {
          Text(
            text = role,
            color = PrimaryText,
            fontSize = 14.sp,
            fontWeight = FontWeight.Black,
            letterSpacing = 1.sp,
            fontFamily = FontFamily.Monospace
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    Column(
      modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(8.dp))
        .background(Color(0x18FFFFFF))
        .padding(16.dp)
    ) {
      Text(
        text = "\"I DON'T WANT TO BE DEFINED BY ONE SKILL.\"",
        color = Color(0xFFE4E4E7),
        fontSize = 15.sp,
        fontWeight = FontWeight.Black
      )
      Spacer(modifier = Modifier.height(6.dp))
      Text(
        text = "\"I WANT TO SEE WHAT I CAN BUILD WITH ALL OF THEM.\"",
        color = AccentRed,
        fontSize = 15.sp,
        fontWeight = FontWeight.Black
      )
    }

    Spacer(modifier = Modifier.height(32.dp))

    // HOW I THINK (MINDSET)
    SectionHeader(
      tag = "OPERATING PRINCIPLES",
      title = "HOW I THINK.",
      subtitle = "Seven sequential rules for taking an idea from zero to deployed reality."
    )

    Spacer(modifier = Modifier.height(14.dp))

    Column(
      verticalArrangement = Arrangement.spacedBy(10.dp),
      modifier = Modifier.fillMaxWidth()
    ) {
      mindset.forEach { item ->
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(DarkCardBg)
            .border(0.5.dp, DarkBorder, RoundedCornerShape(8.dp))
            .padding(14.dp)
        ) {
          Row(verticalAlignment = Alignment.Top) {
            Text(
              text = item.number,
              color = AccentRed,
              fontSize = 16.sp,
              fontWeight = FontWeight.Black,
              fontFamily = FontFamily.Monospace
            )
            Spacer(modifier = Modifier.width(14.dp))
            Column {
              Text(
                text = item.title,
                color = PrimaryText,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace
              )
              Spacer(modifier = Modifier.height(4.dp))
              Text(
                text = item.description,
                color = MutedText,
                fontSize = 12.sp,
                lineHeight = 16.sp
              )
            }
          }
        }
      }
    }
  }
}
