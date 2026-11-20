package app.seb3thehacker.gearslip.car

import org.junit.Assert.assertEquals
import org.junit.Test

class CarThemePresetTest {
    @Test
    fun mapsExistingThemeAndAccentSettingsToCombinedPresets() {
        assertEquals(CarThemePreset.WHITE_GREEN, CarThemePreset.from(AppTheme.LIGHT, CarAccent.GREEN))
        assertEquals(CarThemePreset.BLACK_BLUE, CarThemePreset.from(AppTheme.DARK, CarAccent.SYSTEM))
        assertEquals(
            CarThemePreset.APOLLO_BLACK_PURPLE,
            CarThemePreset.from(AppTheme.APOLLO_PURPLE, CarAccent.SYSTEM),
        )
        assertEquals(CarThemePreset.FOLLOW_PHONE, CarThemePreset.from(AppTheme.PHONE, CarAccent.RED))
    }
}
