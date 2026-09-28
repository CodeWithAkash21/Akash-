package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.components.AkashOsDialog
import com.example.components.FloatingWorldPillDock
import com.example.components.NavWorld
import com.example.components.TopNavigationBar
import com.example.sections.AchievementsSection
import com.example.sections.BuildSection
import com.example.sections.ContactSection
import com.example.sections.CreateSection
import com.example.sections.ExploreSection
import com.example.sections.HeroSection
import com.example.sections.JourneySection
import com.example.sections.PlaygroundSection
import com.example.sections.ToolboxSection
import com.example.sections.WorldSelectorSection
import com.example.ui.theme.DarkBackground
import com.example.ui.theme.MyApplicationTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      MyApplicationTheme {
        AkashDigitalWorldApp()
      }
    }
  }
}

@Composable
fun AkashDigitalWorldApp() {
  var isAkashOsOpen by remember { mutableStateOf(false) }
  var activeWorld by remember { mutableStateOf(NavWorld.HERO) }
  var playgroundTargetTool by remember { mutableStateOf<String?>(null) }

  val scrollState = rememberScrollState()
  val coroutineScope = rememberCoroutineScope()

  BackHandler(enabled = isAkashOsOpen) {
    isAkashOsOpen = false
  }

  Scaffold(
    modifier = Modifier
      .fillMaxSize()
      .background(DarkBackground),
    containerColor = DarkBackground,
    topBar = {
      TopNavigationBar(
        activeWorld = activeWorld,
        onSelectWorld = { world ->
          activeWorld = world
          coroutineScope.launch {
            when (world) {
              NavWorld.HERO -> scrollState.animateScrollTo(0)
              NavWorld.BUILD -> scrollState.animateScrollTo(1400)
              NavWorld.CREATE -> scrollState.animateScrollTo(2800)
              NavWorld.EXPLORE -> scrollState.animateScrollTo(4200)
              NavWorld.JOURNEY -> scrollState.animateScrollTo(5600)
              NavWorld.LAB -> scrollState.animateScrollTo(7200)
              NavWorld.CONTACT -> scrollState.animateScrollTo(9000)
            }
          }
        },
        onOpenAkashOs = { isAkashOsOpen = true },
        modifier = Modifier.statusBarsPadding()
      )
    },
    bottomBar = {
      FloatingWorldPillDock(
        activeWorld = activeWorld,
        onSelectWorld = { world ->
          activeWorld = world
          coroutineScope.launch {
            when (world) {
              NavWorld.HERO -> scrollState.animateScrollTo(0)
              NavWorld.BUILD -> scrollState.animateScrollTo(1400)
              NavWorld.CREATE -> scrollState.animateScrollTo(2800)
              NavWorld.EXPLORE -> scrollState.animateScrollTo(4200)
              NavWorld.JOURNEY -> scrollState.animateScrollTo(5600)
              NavWorld.LAB -> scrollState.animateScrollTo(7200)
              NavWorld.CONTACT -> scrollState.animateScrollTo(9000)
            }
          }
        },
        modifier = Modifier.navigationBarsPadding()
      )
    }
  ) { innerPadding ->
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
    ) {
      Column(
        modifier = Modifier
          .fillMaxSize()
          .verticalScroll(scrollState)
      ) {
        // 01. Hero Entry
        HeroSection(
          onEnterWorld = {
            activeWorld = NavWorld.BUILD
            coroutineScope.launch { scrollState.animateScrollTo(800) }
          },
          onExploreWork = {
            activeWorld = NavWorld.BUILD
            coroutineScope.launch { scrollState.animateScrollTo(1400) }
          },
          onLaunchPlayground = {
            activeWorld = NavWorld.LAB
            coroutineScope.launch { scrollState.animateScrollTo(7200) }
          }
        )

        // 02. The World Selector
        WorldSelectorSection(
          onNavigateToWorld = { worldName ->
            coroutineScope.launch {
              when (worldName) {
                "BUILD" -> {
                  activeWorld = NavWorld.BUILD
                  scrollState.animateScrollTo(1400)
                }
                "CREATE" -> {
                  activeWorld = NavWorld.CREATE
                  scrollState.animateScrollTo(2800)
                }
                "EXPLORE" -> {
                  activeWorld = NavWorld.EXPLORE
                  scrollState.animateScrollTo(4200)
                }
                "JOURNEY" -> {
                  activeWorld = NavWorld.JOURNEY
                  scrollState.animateScrollTo(5600)
                }
              }
            }
          }
        )

        // 03. BUILD Realm (grepTools.dev, StudyShare AI, SaaS Experiments)
        BuildSection(
          onLaunchPlaygroundTool = { tool ->
            playgroundTargetTool = tool
            activeWorld = NavWorld.LAB
            coroutineScope.launch { scrollState.animateScrollTo(7200) }
          }
        )

        // 04. CREATE Realm (Video, Design, Motion, AI Visuals)
        CreateSection()

        // 05. EXPLORE Realm (AI Lab, Machine Learning, Hackathons: Amazon Nova & Gemini)
        ExploreSection()

        // 06. ACHIEVEMENTS & MILESTONES (BCA 8.2 CGPA, grepTools shipped)
        AchievementsSection()

        // 07. JOURNEY, WHO I AM & MINDSET
        JourneySection()

        // 08. INTERACTIVE PLAYGROUND (Real working JSON, UUID, Base64, URL, AI Prompt Lab)
        PlaygroundSection(initialTool = playgroundTargetTool)

        // 09. TOOLBOX (The things I use: Code, Web, AI, Backend, Design, Video, Deployment)
        ToolboxSection()

        // 10. CONTACT, BUILD LOG, PHILOSOPHY & FOOTER
        ContactSection()

        Spacer(modifier = Modifier.height(30.dp))
      }

      // AKASH OS Command Center HUD
      AkashOsDialog(
        isOpen = isAkashOsOpen,
        onDismiss = { isAkashOsOpen = false },
        onOpenPlayground = {
          activeWorld = NavWorld.LAB
          coroutineScope.launch { scrollState.animateScrollTo(7200) }
        }
      )
    }
  }
}
