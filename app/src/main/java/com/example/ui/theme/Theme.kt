package com.example.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val AkashDarkColorScheme = darkColorScheme(
  primary = AccentRed,
  onPrimary = PrimaryText,
  primaryContainer = SecondaryRed,
  onPrimaryContainer = PrimaryText,
  secondary = SecondaryRed,
  onSecondary = PrimaryText,
  background = DarkBackground,
  onBackground = PrimaryText,
  surface = DarkSurface,
  onSurface = PrimaryText,
  surfaceVariant = DarkSurfaceVariant,
  onSurfaceVariant = MutedText,
  outline = DarkBorder,
)

@Composable
fun MyApplicationTheme(
  darkTheme: Boolean = true, // Always default to Akash's signature dark aesthetic
  content: @Composable () -> Unit,
) {
  val view = LocalView.current
  if (!view.isInEditMode) {
    SideEffect {
      val window = (view.context as? Activity)?.window
      if (window != null) {
        window.statusBarColor = DarkBackground.toArgb()
        window.navigationBarColor = DarkBackground.toArgb()
        val insetsController = WindowCompat.getInsetsController(window, view)
        insetsController.isAppearanceLightStatusBars = false
        insetsController.isAppearanceLightNavigationBars = false
      }
    }
  }

  MaterialTheme(
    colorScheme = AkashDarkColorScheme,
    typography = Typography,
    content = content,
  )
}
