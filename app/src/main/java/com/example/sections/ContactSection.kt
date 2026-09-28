package com.example.sections

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.example.ui.theme.DarkSecondary
import com.example.ui.theme.MutedText
import com.example.ui.theme.PrimaryText
import com.example.ui.theme.TerminalCyan

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ContactSection(modifier: Modifier = Modifier) {
  val context = LocalContext.current
  val buildLog = remember { PortfolioRepository.buildLog }

  Column(
    modifier = modifier
      .fillMaxWidth()
      .background(DarkSecondary)
      .padding(horizontal = 20.dp, vertical = 24.dp)
  ) {
    // 24. BUILD LOG (Terminal style)
    Row(verticalAlignment = Alignment.CenterVertically) {
      Icon(Icons.Default.Terminal, contentDescription = null, tint = TerminalCyan, modifier = Modifier.size(18.dp))
      Spacer(modifier = Modifier.width(8.dp))
      Text(
        text = "TERMINAL // BUILD LOG",
        color = TerminalCyan,
        fontSize = 13.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Monospace,
        letterSpacing = 1.sp
      )
    }

    Spacer(modifier = Modifier.height(10.dp))

    Column(
      modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(8.dp))
        .background(Color(0xFF09090C))
        .border(1.dp, Color(0x3306B6D4), RoundedCornerShape(8.dp))
        .padding(14.dp)
    ) {
      buildLog.forEach { log ->
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
          verticalAlignment = Alignment.Top
        ) {
          Text(
            text = log.timestamp,
            color = AccentRed,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = "→",
            color = MutedText,
            fontSize = 11.sp,
            fontFamily = FontFamily.Monospace
          )
          Spacer(modifier = Modifier.width(8.dp))
          Column {
            Text(
              text = log.action,
              color = PrimaryText,
              fontSize = 11.sp,
              fontWeight = FontWeight.Bold,
              fontFamily = FontFamily.Monospace
            )
            Text(
              text = log.detail,
              color = MutedText,
              fontSize = 10.sp,
              fontFamily = FontFamily.Monospace
            )
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(32.dp))

    // 26. PERSONAL PHILOSOPHY (Minimal black section)
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(12.dp))
        .background(DarkBackground)
        .border(1.dp, DarkBorder, RoundedCornerShape(12.dp))
        .padding(24.dp)
    ) {
      Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        Text(
          text = "DON'T JUST LEARN.",
          color = MutedText,
          fontSize = 18.sp,
          fontWeight = FontWeight.Black,
          letterSpacing = 1.sp,
          fontFamily = FontFamily.Monospace
        )
        Text(
          text = "BUILD.",
          color = PrimaryText,
          fontSize = 24.sp,
          fontWeight = FontWeight.Black,
          letterSpacing = 1.sp,
          fontFamily = FontFamily.Monospace
        )
        Text(
          text = "DON'T JUST BUILD.",
          color = MutedText,
          fontSize = 18.sp,
          fontWeight = FontWeight.Black,
          letterSpacing = 1.sp,
          fontFamily = FontFamily.Monospace
        )
        Text(
          text = "SHIP.",
          color = AccentRed,
          fontSize = 24.sp,
          fontWeight = FontWeight.Black,
          letterSpacing = 1.sp,
          fontFamily = FontFamily.Monospace
        )
        Text(
          text = "DON'T JUST SHIP.",
          color = MutedText,
          fontSize = 18.sp,
          fontWeight = FontWeight.Black,
          letterSpacing = 1.sp,
          fontFamily = FontFamily.Monospace
        )
        Text(
          text = "LEARN.",
          color = PrimaryText,
          fontSize = 24.sp,
          fontWeight = FontWeight.Black,
          letterSpacing = 1.sp,
          fontFamily = FontFamily.Monospace
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
          text = "THEN BUILD AGAIN.",
          color = AccentRed,
          fontSize = 20.sp,
          fontWeight = FontWeight.Black,
          letterSpacing = 2.sp,
          fontFamily = FontFamily.Monospace
        )
      }
    }

    Spacer(modifier = Modifier.height(32.dp))

    // 27. FUTURE / WHAT'S NEXT
    SectionHeader(
      tag = "TRAJECTORY",
      title = "WHAT'S NEXT?",
      subtitle = "Scaling curiosity into enduring products, automated systems and creative technology."
    )

    Spacer(modifier = Modifier.height(8.dp))

    // Visual progression chain
    val progression = listOf("LEARN", "CREATE", "BUILD", "SHIP", "GROW", "BUILD SOMETHING BIGGER")
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(8.dp))
        .background(DarkCardBg)
        .border(0.5.dp, DarkBorder, RoundedCornerShape(8.dp))
        .padding(horizontal = 10.dp, vertical = 10.dp),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      progression.forEachIndexed { idx, step ->
        Text(
          text = step,
          color = if (idx == progression.lastIndex) AccentRed else MutedText,
          fontSize = 9.sp,
          fontWeight = FontWeight.Bold,
          fontFamily = FontFamily.Monospace
        )
        if (idx < progression.lastIndex) {
          Text("→", color = DarkBorder, fontSize = 10.sp)
        }
      }
    }

    Spacer(modifier = Modifier.height(32.dp))

    // 28. CONTACT: LET'S MAKE SOMETHING REAL.
    SectionHeader(
      tag = "INITIATE DIALOGUE",
      title = "LET'S MAKE\nSOMETHING REAL.",
      subtitle = "\"Have an idea, product, creative project or interesting problem? Let's talk.\""
    )

    Spacer(modifier = Modifier.height(14.dp))

    // Email direct button
    Button(
      onClick = {
        try {
          val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:akash.pujar.dev@gmail.com")
            putExtra(Intent.EXTRA_SUBJECT, "Hello from Akash Digital World")
          }
          context.startActivity(emailIntent)
        } catch (e: Exception) {
          val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
          val clip = ClipData.newPlainText("Akash Email", "akash.pujar.dev@gmail.com")
          clipboard.setPrimaryClip(clip)
          Toast.makeText(context, "Copied email: akash.pujar.dev@gmail.com", Toast.LENGTH_SHORT).show()
        }
      },
      modifier = Modifier
        .fillMaxWidth()
        .testTag("contact_email_btn"),
      colors = ButtonDefaults.buttonColors(containerColor = AccentRed),
      shape = RoundedCornerShape(8.dp)
    ) {
      Icon(Icons.Default.Email, contentDescription = null, modifier = Modifier.size(16.dp))
      Spacer(modifier = Modifier.width(8.dp))
      Text("EMAIL: akash.pujar.dev@gmail.com", fontSize = 12.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace)
    }

    Spacer(modifier = Modifier.height(10.dp))

    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
      OutlinedButton(
        onClick = {
          try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/CodeWithAkash21"))
            context.startActivity(intent)
          } catch (e: Exception) {
            Toast.makeText(context, "Could not open GitHub", Toast.LENGTH_SHORT).show()
          }
        },
        modifier = Modifier.weight(1f).testTag("contact_github_btn"),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = PrimaryText)
      ) {
        Icon(Icons.Default.Code, contentDescription = null, modifier = Modifier.size(14.dp))
        Spacer(modifier = Modifier.width(6.dp))
        Text("GITHUB", fontSize = 11.sp, fontFamily = FontFamily.Monospace)
      }

      OutlinedButton(
        onClick = {
          val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
          val clip = ClipData.newPlainText("Akash Email", "akash.pujar.dev@gmail.com")
          clipboard.setPrimaryClip(clip)
          Toast.makeText(context, "Email copied to clipboard!", Toast.LENGTH_SHORT).show()
        },
        modifier = Modifier.weight(1f).testTag("contact_copy_btn"),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = PrimaryText)
      ) {
        Icon(Icons.Default.ContentCopy, contentDescription = null, modifier = Modifier.size(14.dp))
        Spacer(modifier = Modifier.width(6.dp))
        Text("COPY EMAIL", fontSize = 11.sp, fontFamily = FontFamily.Monospace)
      }
    }

    Spacer(modifier = Modifier.height(48.dp))

    // 29. FOOTER
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .border(width = 0.5.dp, color = DarkBorder)
        .background(DarkBackground)
        .padding(20.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text(
        text = "AKASH / DIGITAL WORLD",
        color = PrimaryText,
        fontSize = 14.sp,
        fontWeight = FontWeight.Black,
        letterSpacing = 2.sp,
        fontFamily = FontFamily.Monospace
      )

      Spacer(modifier = Modifier.height(6.dp))

      Text(
        text = "BUILD • CREATE • EXPLORE",
        color = AccentRed,
        fontSize = 11.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 1.5.sp,
        fontFamily = FontFamily.Monospace
      )

      Spacer(modifier = Modifier.height(4.dp))

      Text(
        text = "AI × SOFTWARE × CREATIVITY",
        color = MutedText,
        fontSize = 11.sp,
        fontFamily = FontFamily.Monospace
      )

      Spacer(modifier = Modifier.height(12.dp))

      Text(
        text = "© 2026 AKASH PUJAR. ALL RIGHTS RESERVED.",
        color = Color(0xFF52525B),
        fontSize = 10.sp,
        fontFamily = FontFamily.Monospace
      )
    }
  }
}
