package app.seb3thehacker.gearslip.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import app.seb3thehacker.gearslip.car.CarSettings
import app.seb3thehacker.gearslip.car.theme.gearslipColorScheme
import app.seb3thehacker.gearslip.car.theme.isDark

/**
 * Material You: on Android 12 and later, the palette comes from the wallpaper,
 * with the same surfaces as the car screen, so the two look like one app.
 */
@Composable
fun GearslipTheme(content: @Composable () -> Unit) {
    val context = LocalContext.current
    val theme by CarSettings.appTheme.collectAsState()
    val darkStyle by CarSettings.darkStyle.collectAsState()
    val scheme = gearslipColorScheme(context, theme, isSystemInDarkTheme(), darkStyle)
    MaterialTheme(colorScheme = scheme, content = content)
}

/** Whether the phone app is dark: the car's App theme setting, so Light means light everywhere. */
@Composable
fun gearslipDark(): Boolean {
    val theme by CarSettings.appTheme.collectAsState()
    return theme.isDark(isSystemInDarkTheme())
}
