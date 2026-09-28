package com.example.components

import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.AccentRed
import com.example.ui.theme.MutedText
import com.example.ui.theme.PrimaryText

@Composable
fun SectionHeader(
  tag: String,
  title: String,
  subtitle: String? = null,
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier
      .fillMaxWidth()
      .padding(vertical = 16.dp)
  ) {
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
      Box(
        modifier = Modifier
          .clip(RoundedCornerShape(4.dp))
          .background(Color(0x1AFF2A2A))
          .padding(horizontal = 8.dp, vertical = 3.dp)
      ) {
        Text(
          text = tag.uppercase(),
          color = AccentRed,
          fontSize = 11.sp,
          fontWeight = FontWeight.Bold,
          letterSpacing = 2.sp,
          fontFamily = FontFamily.Monospace
        )
      }
    }

    Spacer(modifier = Modifier.height(10.dp))

    Text(
      text = title,
      color = PrimaryText,
      fontSize = 24.sp,
      fontWeight = FontWeight.Black,
      lineHeight = 30.sp,
      letterSpacing = (-0.5).sp
    )

    if (subtitle != null) {
      Spacer(modifier = Modifier.height(6.dp))
      Text(
        text = subtitle,
        color = MutedText,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.2.sp
      )
    }

    Spacer(modifier = Modifier.height(12.dp))
    Box(
      modifier = Modifier
        .width(36.dp)
        .height(2.dp)
        .background(AccentRed)
    )
  }
}
