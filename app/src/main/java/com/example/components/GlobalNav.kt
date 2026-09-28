package com.example.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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

enum class NavWorld(val label: String) {
  HERO("WORLD"),
  BUILD("BUILD"),
  CREATE("CREATE"),
  EXPLORE("EXPLORE"),
  JOURNEY("JOURNEY"),
  LAB("LAB / PLAYGROUND"),
  CONTACT("CONTACT")
}

@Composable
fun TopNavigationBar(
  activeWorld: NavWorld,
  onSelectWorld: (NavWorld) -> Unit,
  onOpenAkashOs: () -> Unit,
  modifier: Modifier = Modifier
) {
  Box(
    modifier = modifier
      .fillMaxWidth()
      .background(Color(0xE6050505))
      .border(width = 0.5.dp, color = DarkBorder)
      .padding(horizontal = 16.dp, vertical = 10.dp)
  ) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      // Left Brand Monogram
      Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
          .clickable { onSelectWorld(NavWorld.HERO) }
          .testTag("nav_brand")
      ) {
        Box(
          modifier = Modifier
            .size(8.dp)
            .clip(CircleShape)
            .background(AccentRed)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
          text = "AKASH",
          color = PrimaryText,
          fontSize = 15.sp,
          fontWeight = FontWeight.Black,
          letterSpacing = 2.sp,
          fontFamily = FontFamily.Monospace
        )
      }

      // Right: OS Launcher + Online Status
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        // Floating AKASH OS Button
        Box(
          modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0x33FF2A2A))
            .border(1.dp, AccentRed, RoundedCornerShape(20.dp))
            .clickable { onOpenAkashOs() }
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .testTag("nav_open_os"),
          contentAlignment = Alignment.Center
        ) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
              modifier = Modifier
                .size(6.dp)
                .clip(CircleShape)
                .background(AccentRed)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
              text = "AKASH OS",
              color = AccentRed,
              fontSize = 10.sp,
              fontWeight = FontWeight.Bold,
              fontFamily = FontFamily.Monospace
            )
          }
        }

        // Online Indicator
        Box(
          modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(DarkCardBg)
            .border(0.5.dp, DarkBorder, RoundedCornerShape(20.dp))
            .padding(horizontal = 8.dp, vertical = 5.dp)
        ) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
              modifier = Modifier
                .size(6.dp)
                .clip(CircleShape)
                .background(OnlineGreen)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
              text = "ONLINE",
              color = OnlineGreen,
              fontSize = 9.sp,
              fontWeight = FontWeight.Bold,
              fontFamily = FontFamily.Monospace
            )
          }
        }
      }
    }
  }
}

@Composable
fun FloatingWorldPillDock(
  activeWorld: NavWorld,
  onSelectWorld: (NavWorld) -> Unit,
  modifier: Modifier = Modifier
) {
  val scrollState = rememberScrollState()

  Box(
    modifier = modifier
      .fillMaxWidth()
      .padding(horizontal = 12.dp, vertical = 8.dp),
    contentAlignment = Alignment.Center
  ) {
    Row(
      modifier = Modifier
        .clip(RoundedCornerShape(30.dp))
        .background(Color(0xEE0E0E12))
        .border(1.dp, DarkBorder, RoundedCornerShape(30.dp))
        .padding(horizontal = 6.dp, vertical = 5.dp)
        .horizontalScroll(scrollState),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
      NavWorld.entries.forEach { world ->
        val isSelected = world == activeWorld
        Box(
          modifier = Modifier
            .clip(RoundedCornerShape(24.dp))
            .background(if (isSelected) AccentRed else Color.Transparent)
            .clickable { onSelectWorld(world) }
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .testTag("nav_pill_${world.name.lowercase()}"),
          contentAlignment = Alignment.Center
        ) {
          Text(
            text = world.label,
            color = if (isSelected) PrimaryText else MutedText,
            fontSize = 11.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            fontFamily = FontFamily.Monospace
          )
        }
      }
    }
  }
}
