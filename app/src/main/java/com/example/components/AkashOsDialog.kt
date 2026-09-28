package com.example.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.window.Dialog
import com.example.ui.theme.AccentRed
import com.example.ui.theme.DarkBackground
import com.example.ui.theme.DarkBorder
import com.example.ui.theme.DarkCardBg
import com.example.ui.theme.MutedText
import com.example.ui.theme.OnlineGreen
import com.example.ui.theme.PrimaryText

@Composable
fun AkashOsDialog(
  isOpen: Boolean,
  onDismiss: () -> Unit,
  onOpenPlayground: () -> Unit,
  modifier: Modifier = Modifier
) {
  if (!isOpen) return

  val context = LocalContext.current

  Dialog(onDismissRequest = onDismiss) {
    Surface(
      modifier = modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(16.dp))
        .border(1.dp, Brush.verticalGradient(listOf(AccentRed, DarkBorder)), RoundedCornerShape(16.dp))
        .testTag("akash_os_modal"),
      color = DarkBackground,
      tonalElevation = 8.dp
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(20.dp)
      ) {
        // Top Header
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
              modifier = Modifier
                .size(8.dp)
                .clip(CircleShape)
                .background(AccentRed)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
              text = "AKASH OS // COMMAND CENTER",
              color = AccentRed,
              fontSize = 12.sp,
              fontWeight = FontWeight.Bold,
              letterSpacing = 1.5.sp,
              fontFamily = FontFamily.Monospace
            )
          }
          IconButton(
            onClick = onDismiss,
            modifier = Modifier
              .size(32.dp)
              .testTag("akash_os_close")
          ) {
            Icon(
              imageVector = Icons.Default.Close,
              contentDescription = "Close HUD",
              tint = MutedText,
              modifier = Modifier.size(18.dp)
            )
          }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // System telemetry block
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(DarkCardBg)
            .border(1.dp, DarkBorder, RoundedCornerShape(10.dp))
            .padding(14.dp)
        ) {
          TelemetryRow(label = "IDENTITY", value = "Akash Pujar")
          Spacer(modifier = Modifier.height(8.dp))
          TelemetryRow(label = "SYSTEM MODE", value = "Building & Shipping")
          Spacer(modifier = Modifier.height(8.dp))
          TelemetryRow(label = "CORE FOCUS", value = "AI + Software + Creative")
          Spacer(modifier = Modifier.height(8.dp))
          TelemetryRow(label = "LATEST BUILD", value = "grepTools.dev (Live)")
          Spacer(modifier = Modifier.height(8.dp))
          TelemetryRow(label = "EXPLORING", value = "AI / SaaS / Creative Tech")
          Spacer(modifier = Modifier.height(8.dp))
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(
              text = "STATUS",
              color = MutedText,
              fontSize = 11.sp,
              fontFamily = FontFamily.Monospace
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
              Box(
                modifier = Modifier
                  .size(6.dp)
                  .clip(CircleShape)
                  .background(OnlineGreen)
              )
              Spacer(modifier = Modifier.width(6.dp))
              Text(
                text = "ONLINE & BUILDING",
                color = OnlineGreen,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace
              )
            }
          }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Quick Command Actions
        Text(
          text = "FAST ACTIONS",
          color = MutedText,
          fontSize = 11.sp,
          fontWeight = FontWeight.Bold,
          letterSpacing = 1.sp,
          fontFamily = FontFamily.Monospace
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(
          onClick = {
            onDismiss()
            onOpenPlayground()
          },
          modifier = Modifier
            .fillMaxWidth()
            .testTag("os_action_playground"),
          colors = ButtonDefaults.buttonColors(
            containerColor = AccentRed,
            contentColor = PrimaryText
          ),
          shape = RoundedCornerShape(8.dp)
        ) {
          Icon(Icons.Default.Terminal, contentDescription = null, modifier = Modifier.size(16.dp))
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = "LAUNCH IN-APP PLAYGROUND",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace
          )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          OutlinedButton(
            onClick = {
              val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
              val clip = ClipData.newPlainText("Akash Email", "akash.pujar.dev@gmail.com")
              clipboard.setPrimaryClip(clip)
              Toast.makeText(context, "Copied: akash.pujar.dev@gmail.com", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier
              .weight(1f)
              .testTag("os_action_copy_email"),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = PrimaryText)
          ) {
            Icon(Icons.Default.ContentCopy, contentDescription = null, modifier = Modifier.size(14.dp))
            Spacer(modifier = Modifier.width(6.dp))
            Text("COPY EMAIL", fontSize = 11.sp, fontFamily = FontFamily.Monospace)
          }

          OutlinedButton(
            onClick = {
              try {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://greptools.vercel.app/"))
                context.startActivity(intent)
              } catch (e: Exception) {
                Toast.makeText(context, "Could not open browser", Toast.LENGTH_SHORT).show()
              }
            },
            modifier = Modifier
              .weight(1f)
              .testTag("os_action_greptools"),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = PrimaryText)
          ) {
            Icon(Icons.Default.OpenInNew, contentDescription = null, modifier = Modifier.size(14.dp))
            Spacer(modifier = Modifier.width(6.dp))
            Text("GREPTOOLS", fontSize = 11.sp, fontFamily = FontFamily.Monospace)
          }
        }
      }
    }
  }
}

@Composable
private fun TelemetryRow(label: String, value: String) {
  Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
  ) {
    Text(
      text = label,
      color = MutedText,
      fontSize = 11.sp,
      fontFamily = FontFamily.Monospace
    )
    Text(
      text = value,
      color = PrimaryText,
      fontSize = 11.sp,
      fontWeight = FontWeight.SemiBold,
      fontFamily = FontFamily.Monospace
    )
  }
}
