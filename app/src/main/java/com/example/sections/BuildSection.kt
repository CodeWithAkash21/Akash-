package com.example.sections

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.components.SectionHeader
import com.example.data.PortfolioRepository
import com.example.data.ProjectItem
import com.example.ui.theme.AccentRed
import com.example.ui.theme.DarkBackground
import com.example.ui.theme.DarkBorder
import com.example.ui.theme.DarkCardBg
import com.example.ui.theme.DarkSurface
import com.example.ui.theme.MutedText
import com.example.ui.theme.OnlineGreen
import com.example.ui.theme.PrimaryText
import com.example.ui.theme.WarningAmber

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BuildSection(
  onLaunchPlaygroundTool: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  val context = LocalContext.current
  var selectedCategory by remember { mutableStateOf("ALL") }

  val categories = remember {
    listOf("ALL", "DEVELOPER TOOLS", "AI / EDTECH", "SAAS", "AUTOMATION", "WEB EXPERIENCES")
  }

  val allProjects = PortfolioRepository.projects
  val filteredProjects = remember(selectedCategory) {
    if (selectedCategory == "ALL") allProjects
    else allProjects.filter { it.category == selectedCategory }
  }

  Column(
    modifier = modifier
      .fillMaxWidth()
      .background(DarkBackground)
      .padding(horizontal = 20.dp, vertical = 24.dp)
  ) {
    SectionHeader(
      tag = "01 / BUILD",
      title = "I LIKE TURNING IDEAS INTO THINGS PEOPLE CAN USE.",
      subtitle = "Software, AI, SaaS, automation and developer utilities with zero friction."
    )

    Spacer(modifier = Modifier.height(14.dp))

    // Category Filter Chips
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .horizontalScroll(rememberScrollState()),
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      categories.forEach { cat ->
        val isSelected = cat == selectedCategory
        Box(
          modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(if (isSelected) AccentRed else DarkCardBg)
            .border(0.5.dp, if (isSelected) AccentRed else DarkBorder, RoundedCornerShape(20.dp))
            .clickable { selectedCategory = cat }
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .testTag("filter_cat_$cat")
        ) {
          Text(
            text = cat,
            color = if (isSelected) PrimaryText else MutedText,
            fontSize = 11.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            fontFamily = FontFamily.Monospace
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(20.dp))

    // FEATURED PROJECT: grepTools.dev
    val greptools = allProjects.first { it.id == "greptools" }
    GrepToolsFeaturedCard(
      project = greptools,
      context = context,
      onTestInPlayground = { onLaunchPlaygroundTool("JSON") }
    )

    Spacer(modifier = Modifier.height(20.dp))

    // FEATURED PROJECT: StudyShare AI / NoteMind
    val studyshare = allProjects.first { it.id == "studyshare" }
    StudyShareFeaturedCard(project = studyshare)

    Spacer(modifier = Modifier.height(28.dp))

    // Section: MORE THINGS I'M BUILDING
    Text(
      text = "MORE THINGS I'M BUILDING",
      color = PrimaryText,
      fontSize = 16.sp,
      fontWeight = FontWeight.Black,
      letterSpacing = 1.sp,
      fontFamily = FontFamily.Monospace
    )
    Spacer(modifier = Modifier.height(6.dp))
    Text(
      text = "Active experiments, pipeline automation and upcoming concept engines.",
      color = MutedText,
      fontSize = 13.sp
    )

    Spacer(modifier = Modifier.height(14.dp))

    Column(
      verticalArrangement = Arrangement.spacedBy(12.dp),
      modifier = Modifier.fillMaxWidth()
    ) {
      allProjects.filter { !it.isFeatured }.forEach { item ->
        GeneralProjectCard(project = item)
      }
    }
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun GrepToolsFeaturedCard(
  project: ProjectItem,
  context: Context,
  onTestInPlayground: () -> Unit
) {
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(14.dp))
      .background(DarkCardBg)
      .border(1.dp, Brush.verticalGradient(listOf(AccentRed, DarkBorder)), RoundedCornerShape(14.dp))
      .testTag("featured_project_greptools")
  ) {
    // Browser Mockup Top Bar
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .background(DarkSurface)
        .padding(horizontal = 14.dp, vertical = 10.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      // Traffic lights
      Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        Box(modifier = Modifier.size(9.dp).clip(CircleShape).background(Color(0xFFFF5F56)))
        Box(modifier = Modifier.size(9.dp).clip(CircleShape).background(Color(0xFFFFBD2E)))
        Box(modifier = Modifier.size(9.dp).clip(CircleShape).background(Color(0xFF27C93F)))
      }

      // Address bar
      Box(
        modifier = Modifier
          .weight(1f)
          .padding(horizontal = 12.dp)
          .clip(RoundedCornerShape(4.dp))
          .background(Color(0xFF08080A))
          .border(0.5.dp, DarkBorder, RoundedCornerShape(4.dp))
          .padding(horizontal = 10.dp, vertical = 4.dp),
        contentAlignment = Alignment.CenterStart
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Icon(Icons.Default.Language, contentDescription = null, tint = MutedText, modifier = Modifier.size(12.dp))
          Spacer(modifier = Modifier.width(6.dp))
          Text(
            text = "https://greptools.vercel.app/",
            color = PrimaryText,
            fontSize = 11.sp,
            fontFamily = FontFamily.Monospace
          )
        }
      }

      // Status badge
      Box(
        modifier = Modifier
          .clip(RoundedCornerShape(4.dp))
          .background(Color(0x2610B981))
          .padding(horizontal = 6.dp, vertical = 2.dp)
      ) {
        Text(
          text = "SHIPPED",
          color = OnlineGreen,
          fontSize = 9.sp,
          fontWeight = FontWeight.Bold,
          fontFamily = FontFamily.Monospace
        )
      }
    }

    // Card Body
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(18.dp)
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
      ) {
        Column(modifier = Modifier.weight(1f)) {
          Text(
            text = project.name,
            color = PrimaryText,
            fontSize = 24.sp,
            fontWeight = FontWeight.Black
          )
          Spacer(modifier = Modifier.height(4.dp))
          Text(
            text = project.tagline,
            color = AccentRed,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold
          )
        }
      }

      Spacer(modifier = Modifier.height(10.dp))

      Text(
        text = project.description,
        color = Color(0xFFD4D4D8),
        fontSize = 13.sp,
        lineHeight = 19.sp
      )

      Spacer(modifier = Modifier.height(14.dp))

      // Integrated Tool Pills
      Text(
        text = "INCLUDED TOOLS IN V1:",
        color = MutedText,
        fontSize = 10.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Monospace,
        letterSpacing = 1.sp
      )
      Spacer(modifier = Modifier.height(6.dp))
      FlowRow(
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
      ) {
        project.toolsOrFeatures.forEach { tool ->
          Box(
            modifier = Modifier
              .clip(RoundedCornerShape(4.dp))
              .background(Color(0x1AFF2A2A))
              .border(0.5.dp, Color(0x33FF2A2A), RoundedCornerShape(4.dp))
              .padding(horizontal = 8.dp, vertical = 4.dp)
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

      Spacer(modifier = Modifier.height(14.dp))

      // Tech Stack
      Row(
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        modifier = Modifier.horizontalScroll(rememberScrollState())
      ) {
        project.techStack.forEach { tech ->
          Box(
            modifier = Modifier
              .clip(RoundedCornerShape(4.dp))
              .background(Color(0xFF141418))
              .padding(horizontal = 6.dp, vertical = 3.dp)
          ) {
            Text(
              text = tech,
              color = MutedText,
              fontSize = 10.sp,
              fontFamily = FontFamily.Monospace
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(18.dp))

      // Action Buttons: View Live & View Source & Test In-App
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        Button(
          onClick = {
            try {
              val intent = Intent(Intent.ACTION_VIEW, Uri.parse(project.liveUrl))
              context.startActivity(intent)
            } catch (e: Exception) {
              Toast.makeText(context, "Could not open browser", Toast.LENGTH_SHORT).show()
            }
          },
          modifier = Modifier.weight(1f).testTag("greptools_live_btn"),
          colors = ButtonDefaults.buttonColors(containerColor = AccentRed),
          shape = RoundedCornerShape(6.dp)
        ) {
          Text("VIEW LIVE", fontSize = 11.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace)
          Spacer(modifier = Modifier.width(4.dp))
          Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(12.dp))
        }

        OutlinedButton(
          onClick = {
            try {
              val intent = Intent(Intent.ACTION_VIEW, Uri.parse(project.githubUrl))
              context.startActivity(intent)
            } catch (e: Exception) {
              Toast.makeText(context, "Could not open browser", Toast.LENGTH_SHORT).show()
            }
          },
          modifier = Modifier.weight(1f).testTag("greptools_source_btn"),
          shape = RoundedCornerShape(6.dp),
          colors = ButtonDefaults.outlinedButtonColors(contentColor = PrimaryText)
        ) {
          Icon(Icons.Default.Code, contentDescription = null, modifier = Modifier.size(12.dp))
          Spacer(modifier = Modifier.width(4.dp))
          Text("SOURCE", fontSize = 11.sp, fontFamily = FontFamily.Monospace)
        }
      }

      Spacer(modifier = Modifier.height(8.dp))

      OutlinedButton(
        onClick = onTestInPlayground,
        modifier = Modifier.fillMaxWidth().testTag("greptools_try_inapp_btn"),
        shape = RoundedCornerShape(6.dp),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = AccentRed)
      ) {
        Icon(Icons.Default.PlayArrow, contentDescription = null, modifier = Modifier.size(14.dp), tint = AccentRed)
        Spacer(modifier = Modifier.width(6.dp))
        Text("TEST TOOLS IN-APP PLAYGROUND", fontSize = 11.sp, color = AccentRed, fontFamily = FontFamily.Monospace)
      }
    }
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun StudyShareFeaturedCard(project: ProjectItem) {
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(14.dp))
      .background(DarkCardBg)
      .border(1.dp, DarkBorder, RoundedCornerShape(14.dp))
      .padding(18.dp)
      .testTag("featured_project_studyshare")
  ) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text(
        text = project.name,
        color = PrimaryText,
        fontSize = 20.sp,
        fontWeight = FontWeight.Black
      )

      Box(
        modifier = Modifier
          .clip(RoundedCornerShape(4.dp))
          .background(Color(0x26F59E0B))
          .border(0.5.dp, WarningAmber, RoundedCornerShape(4.dp))
          .padding(horizontal = 8.dp, vertical = 3.dp)
      ) {
        Text(
          text = "IN DEVELOPMENT",
          color = WarningAmber,
          fontSize = 10.sp,
          fontWeight = FontWeight.Bold,
          fontFamily = FontFamily.Monospace
        )
      }
    }

    Spacer(modifier = Modifier.height(6.dp))

    Text(
      text = project.category,
      color = AccentRed,
      fontSize = 11.sp,
      fontWeight = FontWeight.Bold,
      fontFamily = FontFamily.Monospace
    )

    Spacer(modifier = Modifier.height(8.dp))

    Text(
      text = project.description,
      color = Color(0xFFD4D4D8),
      fontSize = 13.sp,
      lineHeight = 19.sp
    )

    Spacer(modifier = Modifier.height(12.dp))

    FlowRow(
      horizontalArrangement = Arrangement.spacedBy(6.dp),
      verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
      project.toolsOrFeatures.forEach { feature ->
        Box(
          modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(Color(0xFF16161B))
            .padding(horizontal = 7.dp, vertical = 3.dp)
        ) {
          Text(
            text = feature,
            color = MutedText,
            fontSize = 11.sp,
            fontFamily = FontFamily.Monospace
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(12.dp))

    Row(
      horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
      project.techStack.forEach { tech ->
        Box(
          modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(Color(0xFF0F0F13))
            .border(0.5.dp, DarkBorder, RoundedCornerShape(4.dp))
            .padding(horizontal = 6.dp, vertical = 2.dp)
        ) {
          Text(
            text = tech,
            color = Color(0xFFA1A1AA),
            fontSize = 10.sp,
            fontFamily = FontFamily.Monospace
          )
        }
      }
    }
  }
}

@Composable
private fun GeneralProjectCard(project: ProjectItem) {
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
        text = project.name,
        color = PrimaryText,
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold
      )
      Box(
        modifier = Modifier
          .clip(RoundedCornerShape(4.dp))
          .background(Color(0x1FFFFFFF))
          .padding(horizontal = 6.dp, vertical = 2.dp)
      ) {
        Text(
          text = project.status,
          color = MutedText,
          fontSize = 9.sp,
          fontWeight = FontWeight.Bold,
          fontFamily = FontFamily.Monospace
        )
      }
    }

    Spacer(modifier = Modifier.height(4.dp))

    Text(
      text = project.tagline,
      color = AccentRed,
      fontSize = 12.sp
    )

    Spacer(modifier = Modifier.height(6.dp))

    Text(
      text = project.description,
      color = MutedText,
      fontSize = 12.sp,
      lineHeight = 17.sp
    )
  }
}
