package app.seb3thehacker.gearslip.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import app.seb3thehacker.gearslip.car.AppTheme
import app.seb3thehacker.gearslip.car.theme.gearslipColorScheme
import app.seb3thehacker.gearslip.car.theme.isDark
import app.seb3thehacker.gearslip.car.theme.DarkStyle

/**
 * Material You: on Android 12 and later, the palette comes from the wallpaper. The Apollo
 * multimedia palette is intentionally confined to the projected car UI.
 */
@Composable
fun GearslipTheme(content: @Composable () -> Unit) {
    val context = LocalContext.current
    val scheme = gearslipColorScheme(context, AppTheme.PHONE, isSystemInDarkTheme(), DarkStyle.BLACK)
    MaterialTheme(colorScheme = scheme, content = content)
}

/** Apollo's car-only palette must not change the phone app's own appearance. */
@Composable
fun gearslipDark(): Boolean {
    return AppTheme.PHONE.isDark(isSystemInDarkTheme())
}
