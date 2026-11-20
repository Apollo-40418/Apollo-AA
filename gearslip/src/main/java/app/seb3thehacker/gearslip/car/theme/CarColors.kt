package app.seb3thehacker.gearslip.car.theme

import android.content.Context
import android.os.Build
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.lerp
import app.seb3thehacker.gearslip.car.CarAccent
import app.seb3thehacker.gearslip.car.AppTheme

fun AppTheme.isDark(systemDark: Boolean): Boolean = when (this) {
    AppTheme.PHONE -> systemDark
    AppTheme.LIGHT -> false
    AppTheme.DARK, AppTheme.APOLLO_PURPLE -> true
}

internal fun AppTheme.phoneUiTheme(): AppTheme = AppTheme.PHONE

private data class AccentPalette(
    val primary: Color,
    val onPrimary: Color,
    val container: Color,
    val onContainer: Color,
)

private fun accentPalette(accent: CarAccent, dark: Boolean): AccentPalette? = when (accent) {
    CarAccent.SYSTEM -> null
    CarAccent.BLUE -> if (dark) {
        AccentPalette(Color(0xFFADC6FF), Color(0xFF002E69), Color(0xFF004494), Color(0xFFD8E2FF))
    } else {
        AccentPalette(Color(0xFF005AC1), Color.White, Color(0xFFD8E2FF), Color(0xFF001A43))
    }
    CarAccent.RED -> if (dark) {
        AccentPalette(Color(0xFFF2B8B5), Color(0xFF601410), Color(0xFF8C1D18), Color(0xFFF9DEDC))
    } else {
        AccentPalette(Color(0xFFB3261E), Color.White, Color(0xFFF9DEDC), Color(0xFF410E0B))
    }
    CarAccent.GREEN -> if (dark) {
        AccentPalette(Color(0xFF82D99D), Color(0xFF00391D), Color(0xFF00522B), Color(0xFFA5F2C2))
    } else {
        AccentPalette(Color(0xFF006D3B), Color.White, Color(0xFFA5F2C2), Color(0xFF00210F))
    }
    CarAccent.YELLOW -> if (dark) {
        AccentPalette(Color(0xFFE9C349), Color(0xFF3D2F00), Color(0xFF5A4600), Color(0xFFFFE08A))
    } else {
        AccentPalette(Color(0xFF715C00), Color.White, Color(0xFFFFE08A), Color(0xFF241A00))
    }
    CarAccent.PURPLE -> if (dark) {
        AccentPalette(Color(0xFFD0BCFF), Color(0xFF381E72), Color(0xFF4F378B), Color(0xFFEADDFF))
    } else {
        AccentPalette(Color(0xFF6750A4), Color.White, Color(0xFFEADDFF), Color(0xFF21005D))
    }
    CarAccent.ORANGE -> if (dark) {
        AccentPalette(Color(0xFFFFB68C), Color(0xFF522300), Color(0xFF783800), Color(0xFFFFDBC7))
    } else {
        AccentPalette(Color(0xFF9A4600), Color.White, Color(0xFFFFDBC7), Color(0xFF321300))
    }
    CarAccent.PINK -> if (dark) {
        AccentPalette(Color(0xFFFFB1D0), Color(0xFF5A0036), Color(0xFF80004F), Color(0xFFFFD8E8))
    } else {
        AccentPalette(Color(0xFFA00063), Color.White, Color(0xFFFFD8E8), Color(0xFF3A0022))
    }
    CarAccent.TEAL -> if (dark) {
        AccentPalette(Color(0xFF80D5C9), Color(0xFF003731), Color(0xFF005047), Color(0xFF9CF2E2))
    } else {
        AccentPalette(Color(0xFF006A60), Color.White, Color(0xFF9CF2E2), Color(0xFF00201B))
    }
}

internal fun ColorScheme.withCarAccent(accent: CarAccent, dark: Boolean): ColorScheme {
    val palette = accentPalette(accent, dark) ?: return this
    return copy(
        primary = palette.primary,
        onPrimary = palette.onPrimary,
        primaryContainer = palette.container,
        onPrimaryContainer = palette.onContainer,
        inversePrimary = palette.primary,
        secondary = palette.primary,
        onSecondary = palette.onPrimary,
        secondaryContainer = palette.container,
        onSecondaryContainer = palette.onContainer,
        tertiary = palette.primary,
        onTertiary = palette.onPrimary,
        tertiaryContainer = palette.container,
        onTertiaryContainer = palette.onContainer,
        surfaceTint = palette.primary,
    )
}

fun gearslipColorScheme(
    context: Context,
    theme: AppTheme,
    systemDark: Boolean,
    style: DarkStyle,
    accent: CarAccent = CarAccent.SYSTEM,
): ColorScheme {
    if (theme == AppTheme.APOLLO_PURPLE) return apolloPurpleColorScheme()

    val dark = theme.isDark(systemDark)
    val scheme = if (accent != CarAccent.SYSTEM && theme == AppTheme.LIGHT) {
        lightColorScheme()
    } else if (accent != CarAccent.SYSTEM && theme == AppTheme.DARK) {
        darkColorScheme()
    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        if (dark) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    } else {
        if (dark) darkColorScheme() else lightColorScheme()
    }
    return scheme.withCarAccent(accent, dark).withTintedSurfaces(dark, style)
}

internal fun apolloPurpleColorScheme() = darkColorScheme(
    primary = Color(0xFFD0A1FF),
    onPrimary = Color(0xFF2A004C),
    primaryContainer = Color(0xFF592485),
    onPrimaryContainer = Color(0xFFF3DCFF),
    inversePrimary = Color(0xFF7540A2),
    secondary = Color(0xFFD2B8ED),
    onSecondary = Color(0xFF35204B),
    secondaryContainer = Color(0xFF4D3566),
    onSecondaryContainer = Color(0xFFF0DFFF),
    tertiary = Color(0xFFF0B7E8),
    onTertiary = Color(0xFF4B173F),
    tertiaryContainer = Color(0xFF67305B),
    onTertiaryContainer = Color(0xFFFFD8F4),
    background = Color.Black,
    onBackground = Color(0xFFF5EFFA),
    surface = Color(0xFF09060F),
    onSurface = Color(0xFFF5EFFA),
    surfaceVariant = Color(0xFF261B35),
    onSurfaceVariant = Color(0xFFD0C3DE),
    surfaceTint = Color(0xFFD0A1FF),
    inverseSurface = Color(0xFFF5EFFA),
    inverseOnSurface = Color(0xFF211A27),
    outline = Color(0xFF9889A8),
    outlineVariant = Color(0xFF51445E),
    surfaceDim = Color(0xFF05030A),
    surfaceBright = Color(0xFF30233D),
    surfaceContainerLowest = Color(0xFF030107),
    surfaceContainerLow = Color(0xFF0D0914),
    surfaceContainer = Color(0xFF120D1A),
    surfaceContainerHigh = Color(0xFF1B1425),
    surfaceContainerHighest = Color(0xFF261D32),
)

/**
 * The phone's Material You colours, with its accent washed over the surfaces. Material You's own
 * surfaces are nearly neutral, which reads as flat grey on a car screen; tinting them with the
 * phone's primary colour (purple on a purple phone) gives the whole UI the phone's hue, the way
 * Material 3's surface tint does, and it changes with the wallpaper like everything else.
 * Higher containers take more of the tint, so cards still stand off the background.
 */
fun ColorScheme.withTintedSurfaces(dark: Boolean, style: DarkStyle = DarkStyle.GREY): ColorScheme {
    if (dark && style != DarkStyle.GREY) return withDarkStyle(style)
    val base = if (dark) 0.03f else 0.02f
    fun tint(own: Color, step: Int) = primary.copy(alpha = base + step * (if (dark) 0.015f else 0.01f)).compositeOver(own)
    return copy(
        background = tint(background, 0),
        surface = tint(surface, 0),
        surfaceDim = tint(surfaceDim, 0),
        surfaceContainerLowest = tint(surfaceContainerLowest, 0),
        surfaceContainerLow = tint(surfaceContainerLow, 1),
        surfaceContainer = tint(surfaceContainer, 2),
        surfaceContainerHigh = tint(surfaceContainerHigh, 3),
        surfaceContainerHighest = tint(surfaceContainerHighest, 4),
        surfaceBright = tint(surfaceBright, 4),
        surfaceVariant = tint(surfaceVariant, 3),
    )
}

/**
 * How dark the car's dark mode goes. [GREY] is Material You's own dark, with the tint above;
 * the others are darker, and the two colour ones take their hue from the phone's accent.
 */
enum class DarkStyle { GREY, BLACK, DEEP_COLOUR, BLACK_COLOUR }

/**
 * The surfaces rebuilt at [style]'s brightness, from the background up to the highest container.
 * The levels are 0-255 greys; each surface keeps Material You's own slight hue at that level.
 */
private fun ColorScheme.withDarkStyle(style: DarkStyle): ColorScheme {
    val levels = when (style) {
        DarkStyle.BLACK -> listOf(0f, 12f, 17f, 25f, 34f)
        DarkStyle.DEEP_COLOUR -> listOf(11f, 19f, 24f, 32f, 41f)
        else -> listOf(0f, 17f, 22f, 30f, 39f)
    }
    val colour = style != DarkStyle.BLACK
    // The accent's saturated container colour, not the pastel accent itself: tinting with the
    // pastel only makes a surface lighter, where this one gives it colour at the same brightness.
    fun at(own: Color, step: Int): Color {
        val level = levels[step] / 255f
        if (level == 0f) return Color.Black
        val neutral = own.scaledTo(level)
        return if (colour) {
            lerp(neutral, primaryContainer.scaledTo(level), (0.6f + 0.08f * step).coerceAtMost(0.95f))
        } else {
            primary.copy(alpha = 0.02f + 0.015f * step).compositeOver(neutral)
        }
    }
    return copy(
        // Grey text that read fine on grey looks dim on black, so it moves a little towards white.
        onSurfaceVariant = lerp(onSurfaceVariant, onSurface, 0.3f),
        background = at(background, 0),
        surface = at(surface, 0),
        surfaceDim = at(surfaceDim, 0),
        surfaceContainerLowest = at(surfaceContainerLowest, 0),
        surfaceContainerLow = at(surfaceContainerLow, 1),
        surfaceContainer = at(surfaceContainer, 2),
        surfaceContainerHigh = at(surfaceContainerHigh, 3),
        surfaceContainerHighest = at(surfaceContainerHighest, 4),
        surfaceBright = at(surfaceBright, 4),
        surfaceVariant = at(surfaceVariant, 3),
    )
}

/** This colour's hue at an average brightness of [level], 0 to 1. */
private fun Color.scaledTo(level: Float): Color {
    val mean = (red + green + blue) / 3f
    if (mean <= 0f) return Color(level, level, level)
    val k = level / mean
    return Color((red * k).coerceAtMost(1f), (green * k).coerceAtMost(1f), (blue * k).coerceAtMost(1f))
}
