package com.example.sections

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.util.Base64
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.DataArray
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.components.SectionHeader
import com.example.ui.theme.AccentRed
import com.example.ui.theme.DarkBackground
import com.example.ui.theme.DarkBorder
import com.example.ui.theme.DarkCardBg
import com.example.ui.theme.MutedText
import com.example.ui.theme.OnlineGreen
import com.example.ui.theme.PrimaryText
import org.json.JSONArray
import org.json.JSONObject
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.util.UUID

enum class PlaygroundTool(val label: String) {
  JSON("JSON FORMATTER"),
  UUID_GEN("UUID GENERATOR"),
  BASE64("BASE64"),
  URL_ENC("URL ENCODER"),
  AI_PROMPT("AI PROMPT LAB")
}

@Composable
fun PlaygroundSection(
  initialTool: String? = null,
  modifier: Modifier = Modifier
) {
  val context = LocalContext.current
  var activeTool by remember(initialTool) {
    mutableStateOf(
      when (initialTool) {
        "JSON" -> PlaygroundTool.JSON
        "UUID" -> PlaygroundTool.UUID_GEN
        "BASE64" -> PlaygroundTool.BASE64
        "URL" -> PlaygroundTool.URL_ENC
        "AI" -> PlaygroundTool.AI_PROMPT
        else -> PlaygroundTool.JSON
      }
    )
  }

  Column(
    modifier = modifier
      .fillMaxWidth()
      .background(DarkBackground)
      .padding(horizontal = 20.dp, vertical = 24.dp)
  ) {
    SectionHeader(
      tag = "INTERACTIVE LAB",
      title = "PLAYGROUND.",
      subtitle = "\"Don't just look around. Try something.\" Real developer utilities and micro-experiments."
    )

    Spacer(modifier = Modifier.height(14.dp))

    // Tool Selector Pills
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .horizontalScroll(rememberScrollState()),
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      PlaygroundTool.entries.forEach { tool ->
        val isSelected = tool == activeTool
        Box(
          modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(if (isSelected) AccentRed else DarkCardBg)
            .border(0.5.dp, if (isSelected) AccentRed else DarkBorder, RoundedCornerShape(8.dp))
            .clickable { activeTool = tool }
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .testTag("playground_tab_${tool.name.lowercase()}")
        ) {
          Text(
            text = tool.label,
            color = if (isSelected) PrimaryText else MutedText,
            fontSize = 11.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            fontFamily = FontFamily.Monospace
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(20.dp))

    // Active Tool Interactive Card
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(12.dp))
        .background(DarkCardBg)
        .border(1.dp, DarkBorder, RoundedCornerShape(12.dp))
        .padding(16.dp)
    ) {
      when (activeTool) {
        PlaygroundTool.JSON -> JsonFormatterTool(context)
        PlaygroundTool.UUID_GEN -> UuidGeneratorTool(context)
        PlaygroundTool.BASE64 -> Base64Tool(context)
        PlaygroundTool.URL_ENC -> UrlEncoderTool(context)
        PlaygroundTool.AI_PROMPT -> AiPromptLabTool(context)
      }
    }
  }
}

@Composable
private fun JsonFormatterTool(context: Context) {
  var inputJson by remember {
    mutableStateOf("{\"name\":\"Akash Pujar\",\"role\":\"Multidisciplinary Creator\",\"status\":\"Building\",\"projects\":[\"grepTools.dev\",\"StudyShare AI\"]}")
  }
  var outputJson by remember { mutableStateOf("") }
  var errorMessage by remember { mutableStateOf<String?>(null) }

  Column(modifier = Modifier.fillMaxWidth()) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(Icons.Default.DataArray, contentDescription = null, tint = AccentRed, modifier = Modifier.size(16.dp))
        Spacer(modifier = Modifier.width(6.dp))
        Text("JSON FORMATTER & VALIDATOR", color = PrimaryText, fontSize = 13.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace)
      }
      Text("grepTools engine", color = MutedText, fontSize = 10.sp, fontFamily = FontFamily.Monospace)
    }

    Spacer(modifier = Modifier.height(10.dp))

    OutlinedTextField(
      value = inputJson,
      onValueChange = {
        inputJson = it
        errorMessage = null
      },
      modifier = Modifier
        .fillMaxWidth()
        .height(120.dp)
        .testTag("json_input_field"),
      textStyle = androidx.compose.ui.text.TextStyle(color = PrimaryText, fontSize = 12.sp, fontFamily = FontFamily.Monospace),
      colors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = AccentRed,
        unfocusedBorderColor = DarkBorder,
        focusedContainerColor = DarkBackground,
        unfocusedContainerColor = DarkBackground
      ),
      shape = RoundedCornerShape(8.dp)
    )

    Spacer(modifier = Modifier.height(10.dp))

    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      Button(
        onClick = {
          try {
            val trimmed = inputJson.trim()
            outputJson = if (trimmed.startsWith("{")) {
              JSONObject(trimmed).toString(2)
            } else if (trimmed.startsWith("[")) {
              JSONArray(trimmed).toString(2)
            } else {
              throw IllegalArgumentException("Input must begin with { or [")
            }
            errorMessage = null
          } catch (e: Exception) {
            errorMessage = "Invalid JSON: ${e.message}"
            outputJson = ""
          }
        },
        colors = ButtonDefaults.buttonColors(containerColor = AccentRed),
        shape = RoundedCornerShape(6.dp),
        modifier = Modifier.weight(1f).testTag("json_prettify_btn")
      ) {
        Text("PRETTIFY", fontSize = 11.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace)
      }

      OutlinedButton(
        onClick = {
          try {
            val trimmed = inputJson.trim()
            outputJson = if (trimmed.startsWith("{")) {
              JSONObject(trimmed).toString()
            } else if (trimmed.startsWith("[")) {
              JSONArray(trimmed).toString()
            } else {
              throw IllegalArgumentException("Input must begin with { or [")
            }
            errorMessage = null
          } catch (e: Exception) {
            errorMessage = "Invalid JSON: ${e.message}"
          }
        },
        shape = RoundedCornerShape(6.dp),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = PrimaryText),
        modifier = Modifier.weight(1f).testTag("json_minify_btn")
      ) {
        Text("MINIFY", fontSize = 11.sp, fontFamily = FontFamily.Monospace)
      }
    }

    if (errorMessage != null) {
      Spacer(modifier = Modifier.height(8.dp))
      Text(errorMessage!!, color = AccentRed, fontSize = 11.sp, fontFamily = FontFamily.Monospace)
    }

    if (outputJson.isNotEmpty()) {
      Spacer(modifier = Modifier.height(12.dp))
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text("OUTPUT:", color = MutedText, fontSize = 10.sp, fontFamily = FontFamily.Monospace)
        Row(
          modifier = Modifier.clickable {
            val clip = ClipData.newPlainText("Formatted JSON", outputJson)
            (context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager).setPrimaryClip(clip)
            Toast.makeText(context, "JSON Copied to clipboard", Toast.LENGTH_SHORT).show()
          },
          verticalAlignment = Alignment.CenterVertically
        ) {
          Icon(Icons.Default.ContentCopy, contentDescription = null, tint = AccentRed, modifier = Modifier.size(12.dp))
          Spacer(modifier = Modifier.width(4.dp))
          Text("COPY", color = AccentRed, fontSize = 10.sp, fontFamily = FontFamily.Monospace)
        }
      }
      Spacer(modifier = Modifier.height(4.dp))
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .clip(RoundedCornerShape(6.dp))
          .background(DarkBackground)
          .border(0.5.dp, DarkBorder, RoundedCornerShape(6.dp))
          .padding(10.dp)
      ) {
        Text(outputJson, color = Color(0xFF34D399), fontSize = 11.sp, fontFamily = FontFamily.Monospace)
      }
    }
  }
}

@Composable
private fun UuidGeneratorTool(context: Context) {
  var generatedUuids by remember { mutableStateOf(listOf(UUID.randomUUID().toString())) }
  var quantity by remember { mutableIntStateOf(1) }

  Column(modifier = Modifier.fillMaxWidth()) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(Icons.Default.Fingerprint, contentDescription = null, tint = AccentRed, modifier = Modifier.size(16.dp))
        Spacer(modifier = Modifier.width(6.dp))
        Text("UUID V4 GENERATOR", color = PrimaryText, fontSize = 13.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace)
      }
      Text("RFC 4122 Compliant", color = MutedText, fontSize = 10.sp, fontFamily = FontFamily.Monospace)
    }

    Spacer(modifier = Modifier.height(12.dp))

    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      listOf(1, 3, 5).forEach { qty ->
        Box(
          modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .background(if (quantity == qty) AccentRed else DarkBackground)
            .border(0.5.dp, DarkBorder, RoundedCornerShape(6.dp))
            .clickable { quantity = qty }
            .padding(horizontal = 14.dp, vertical = 6.dp)
        ) {
          Text("Count: $qty", color = PrimaryText, fontSize = 11.sp, fontFamily = FontFamily.Monospace)
        }
      }
    }

    Spacer(modifier = Modifier.height(12.dp))

    Button(
      onClick = {
        generatedUuids = (1..quantity).map { UUID.randomUUID().toString() }
      },
      colors = ButtonDefaults.buttonColors(containerColor = AccentRed),
      shape = RoundedCornerShape(6.dp),
      modifier = Modifier.fillMaxWidth().testTag("uuid_generate_btn")
    ) {
      Icon(Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(14.dp))
      Spacer(modifier = Modifier.width(6.dp))
      Text("GENERATE NEW UUID", fontSize = 11.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace)
    }

    Spacer(modifier = Modifier.height(14.dp))

    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
      generatedUuids.forEach { uuid ->
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(6.dp))
            .background(DarkBackground)
            .border(0.5.dp, DarkBorder, RoundedCornerShape(6.dp))
            .padding(horizontal = 10.dp, vertical = 8.dp),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(uuid, color = PrimaryText, fontSize = 11.sp, fontFamily = FontFamily.Monospace)
          Icon(
            imageVector = Icons.Default.ContentCopy,
            contentDescription = "Copy UUID",
            tint = AccentRed,
            modifier = Modifier
              .size(16.dp)
              .clickable {
                val clip = ClipData.newPlainText("UUID", uuid)
                (context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager).setPrimaryClip(clip)
                Toast.makeText(context, "Copied UUID", Toast.LENGTH_SHORT).show()
              }
          )
        }
      }
    }
  }
}

@Composable
private fun Base64Tool(context: Context) {
  var textInput by remember { mutableStateOf("Hello Akash Digital World") }
  var resultText by remember { mutableStateOf("") }
  var isEncodingMode by remember { mutableStateOf(true) }

  Column(modifier = Modifier.fillMaxWidth()) {
    Row(verticalAlignment = Alignment.CenterVertically) {
      Icon(Icons.Default.Lock, contentDescription = null, tint = AccentRed, modifier = Modifier.size(16.dp))
      Spacer(modifier = Modifier.width(6.dp))
      Text("BASE64 ENCODER / DECODER", color = PrimaryText, fontSize = 13.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace)
    }

    Spacer(modifier = Modifier.height(10.dp))

    OutlinedTextField(
      value = textInput,
      onValueChange = { textInput = it },
      modifier = Modifier.fillMaxWidth().height(90.dp).testTag("base64_input_field"),
      textStyle = androidx.compose.ui.text.TextStyle(color = PrimaryText, fontSize = 12.sp, fontFamily = FontFamily.Monospace),
      colors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = AccentRed,
        unfocusedBorderColor = DarkBorder,
        focusedContainerColor = DarkBackground,
        unfocusedContainerColor = DarkBackground
      ),
      shape = RoundedCornerShape(8.dp)
    )

    Spacer(modifier = Modifier.height(10.dp))

    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      Button(
        onClick = {
          isEncodingMode = true
          resultText = Base64.encodeToString(textInput.toByteArray(StandardCharsets.UTF_8), Base64.NO_WRAP)
        },
        colors = ButtonDefaults.buttonColors(containerColor = AccentRed),
        shape = RoundedCornerShape(6.dp),
        modifier = Modifier.weight(1f).testTag("base64_encode_btn")
      ) {
        Text("ENCODE", fontSize = 11.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace)
      }

      OutlinedButton(
        onClick = {
          isEncodingMode = false
          try {
            val bytes = Base64.decode(textInput, Base64.DEFAULT)
            resultText = String(bytes, StandardCharsets.UTF_8)
          } catch (e: Exception) {
            resultText = "Error: Invalid Base64 string"
          }
        },
        shape = RoundedCornerShape(6.dp),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = PrimaryText),
        modifier = Modifier.weight(1f).testTag("base64_decode_btn")
      ) {
        Text("DECODE", fontSize = 11.sp, fontFamily = FontFamily.Monospace)
      }
    }

    if (resultText.isNotEmpty()) {
      Spacer(modifier = Modifier.height(12.dp))
      Text("RESULT (${if (isEncodingMode) "ENCODED" else "DECODED"}):", color = MutedText, fontSize = 10.sp, fontFamily = FontFamily.Monospace)
      Spacer(modifier = Modifier.height(4.dp))
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .clip(RoundedCornerShape(6.dp))
          .background(DarkBackground)
          .border(0.5.dp, DarkBorder, RoundedCornerShape(6.dp))
          .padding(10.dp)
      ) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(resultText, color = PrimaryText, fontSize = 12.sp, fontFamily = FontFamily.Monospace, modifier = Modifier.weight(1f))
          Icon(
            imageVector = Icons.Default.ContentCopy,
            contentDescription = "Copy Result",
            tint = AccentRed,
            modifier = Modifier.size(16.dp).clickable {
              val clip = ClipData.newPlainText("Base64", resultText)
              (context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager).setPrimaryClip(clip)
              Toast.makeText(context, "Copied result", Toast.LENGTH_SHORT).show()
            }
          )
        }
      }
    }
  }
}

@Composable
private fun UrlEncoderTool(context: Context) {
  var urlInput by remember { mutableStateOf("https://greptools.vercel.app?query=ai studio&category=developer tools") }
  var outputUrl by remember { mutableStateOf("") }

  Column(modifier = Modifier.fillMaxWidth()) {
    Row(verticalAlignment = Alignment.CenterVertically) {
      Icon(Icons.Default.Link, contentDescription = null, tint = AccentRed, modifier = Modifier.size(16.dp))
      Spacer(modifier = Modifier.width(6.dp))
      Text("URL ENCODER / DECODER", color = PrimaryText, fontSize = 13.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace)
    }

    Spacer(modifier = Modifier.height(10.dp))

    OutlinedTextField(
      value = urlInput,
      onValueChange = { urlInput = it },
      modifier = Modifier.fillMaxWidth().height(80.dp),
      textStyle = androidx.compose.ui.text.TextStyle(color = PrimaryText, fontSize = 12.sp, fontFamily = FontFamily.Monospace),
      colors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = AccentRed,
        unfocusedBorderColor = DarkBorder,
        focusedContainerColor = DarkBackground,
        unfocusedContainerColor = DarkBackground
      ),
      shape = RoundedCornerShape(8.dp)
    )

    Spacer(modifier = Modifier.height(10.dp))

    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      Button(
        onClick = {
          try {
            outputUrl = URLEncoder.encode(urlInput, StandardCharsets.UTF_8.toString())
          } catch (e: Exception) {
            outputUrl = "Encoding error: ${e.message}"
          }
        },
        colors = ButtonDefaults.buttonColors(containerColor = AccentRed),
        shape = RoundedCornerShape(6.dp),
        modifier = Modifier.weight(1f)
      ) {
        Text("URL ENCODE", fontSize = 11.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace)
      }

      OutlinedButton(
        onClick = {
          try {
            outputUrl = URLDecoder.decode(urlInput, StandardCharsets.UTF_8.toString())
          } catch (e: Exception) {
            outputUrl = "Decoding error: ${e.message}"
          }
        },
        shape = RoundedCornerShape(6.dp),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = PrimaryText),
        modifier = Modifier.weight(1f)
      ) {
        Text("URL DECODE", fontSize = 11.sp, fontFamily = FontFamily.Monospace)
      }
    }

    if (outputUrl.isNotEmpty()) {
      Spacer(modifier = Modifier.height(12.dp))
      Text("TRANSFORMED URL:", color = MutedText, fontSize = 10.sp, fontFamily = FontFamily.Monospace)
      Spacer(modifier = Modifier.height(4.dp))
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .clip(RoundedCornerShape(6.dp))
          .background(DarkBackground)
          .border(0.5.dp, DarkBorder, RoundedCornerShape(6.dp))
          .padding(10.dp)
      ) {
        Text(outputUrl, color = Color(0xFF60A5FA), fontSize = 11.sp, fontFamily = FontFamily.Monospace)
      }
    }
  }
}

@Composable
private fun AiPromptLabTool(context: Context) {
  var promptInput by remember {
    mutableStateOf("You are an autonomous AI agent in Akash's digital universe. Analyze the architecture of grepTools.dev and recommend modular SaaS expansion pathways.")
  }

  val charCount = promptInput.length
  val estimatedTokens = (charCount / 4.0).toInt().coerceAtLeast(1)

  Column(modifier = Modifier.fillMaxWidth()) {
    Row(verticalAlignment = Alignment.CenterVertically) {
      Icon(Icons.Default.AutoAwesome, contentDescription = null, tint = AccentRed, modifier = Modifier.size(16.dp))
      Spacer(modifier = Modifier.width(6.dp))
      Text("AI PROMPT LAB & TOKENIZER", color = PrimaryText, fontSize = 13.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace)
    }

    Spacer(modifier = Modifier.height(6.dp))

    Text("Inspect prompt metrics, estimated LLM tokens, and agent context load.", color = MutedText, fontSize = 11.sp)

    Spacer(modifier = Modifier.height(10.dp))

    OutlinedTextField(
      value = promptInput,
      onValueChange = { promptInput = it },
      modifier = Modifier.fillMaxWidth().height(100.dp),
      textStyle = androidx.compose.ui.text.TextStyle(color = PrimaryText, fontSize = 12.sp, fontFamily = FontFamily.Monospace),
      colors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = AccentRed,
        unfocusedBorderColor = DarkBorder,
        focusedContainerColor = DarkBackground,
        unfocusedContainerColor = DarkBackground
      ),
      shape = RoundedCornerShape(8.dp)
    )

    Spacer(modifier = Modifier.height(12.dp))

    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      MetricChip("CHARS", charCount.toString())
      MetricChip("EST. TOKENS", "~$estimatedTokens")
      MetricChip("MODEL", "Gemini 2.5 / Nova")
    }
  }
}

@Composable
private fun MetricChip(label: String, value: String) {
  Box(
    modifier = Modifier
      .clip(RoundedCornerShape(6.dp))
      .background(DarkBackground)
      .border(0.5.dp, DarkBorder, RoundedCornerShape(6.dp))
      .padding(horizontal = 10.dp, vertical = 6.dp)
  ) {
    Column {
      Text(label, color = MutedText, fontSize = 9.sp, fontFamily = FontFamily.Monospace)
      Text(value, color = AccentRed, fontSize = 12.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace)
    }
  }
}
