package app.seb3thehacker.gearslip.car.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import app.seb3thehacker.gearslip.car.AppTheme
import app.seb3thehacker.gearslip.car.CarAccent
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class CarColorsTest {
    @Test
    fun apolloPaletteIsBlackWithPurpleAccents() {
        val colors = apolloPurpleColorScheme()

        assertEquals(Color.Black, colors.background)
        assertEquals(Color(0xFFD0A1FF), colors.primary)
    }

    @Test
    fun multimediaThemesDoNotReplaceThePhoneUiTheme() {
        assertEquals(AppTheme.PHONE, AppTheme.APOLLO_PURPLE.phoneUiTheme())
        assertEquals(AppTheme.PHONE, AppTheme.LIGHT.phoneUiTheme())
        assertEquals(AppTheme.PHONE, AppTheme.DARK.phoneUiTheme())
    }

    @Test
    fun accentPresetsChangeAccentsWithoutReplacingTheBaseSurfaces() {
        val light = lightColorScheme()
        val dark = darkColorScheme()

        val redLight = light.withCarAccent(CarAccent.RED, dark = false)
        val redDark = dark.withCarAccent(CarAccent.RED, dark = true)

        assertEquals(Color(0xFFB3261E), redLight.primary)
        assertEquals(light.background, redLight.background)
        assertEquals(Color(0xFFF2B8B5), redDark.primary)
        assertEquals(dark.background, redDark.background)
    }

    @Test
    fun systemAccentKeepsTheExistingPalette() {
        val colors = darkColorScheme()

        val unchanged = colors.withCarAccent(CarAccent.SYSTEM, dark = true)

        assertEquals(colors.primary, unchanged.primary)
        assertEquals(colors.background, unchanged.background)
        assertEquals(colors.surface, unchanged.surface)
    }

    @Test
    fun everyAccentPresetKeepsReadableTextOnItsButtons() {
        for (accent in CarAccent.entries.filter { it != CarAccent.SYSTEM }) {
            for (dark in listOf(false, true)) {
                val base = if (dark) darkColorScheme() else lightColorScheme()
                val colors = base.withCarAccent(accent, dark)

                assertTrue(
                    "$accent primary text contrast in dark=$dark",
                    contrastRatio(colors.onPrimary, colors.primary) >= 4.5f,
                )
                assertTrue(
                    "$accent selected text contrast in dark=$dark",
                    contrastRatio(colors.onPrimaryContainer, colors.primaryContainer) >= 4.5f,
                )
            }
        }
    }

    private fun contrastRatio(first: Color, second: Color): Float {
        val lighter = maxOf(first.luminance(), second.luminance())
        val darker = minOf(first.luminance(), second.luminance())
        return (lighter + 0.05f) / (darker + 0.05f)
    }
}
