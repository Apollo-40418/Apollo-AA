package app.seb3thehacker.gearslip.car

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import app.seb3thehacker.gearslip.R
import app.seb3thehacker.gearslip.car.theme.GsThemes

internal enum class DrawerIconStyle {
    DEFAULT,
    TRANSPARENT_BORDER,
    ROUND,
    SQUARE,
    ;

    val buttonChoiceId: String
        get() = "drawer_icon_${name.lowercase()}"

    companion object {
        fun fromPreference(value: String): DrawerIconStyle =
            entries.firstOrNull { it.name == value }
                ?: DEFAULT

        fun fromButtonChoice(id: String): DrawerIconStyle? =
            entries.firstOrNull { it != DEFAULT && it.buttonChoiceId == id }
    }
}

internal enum class DrawerTileColor {
    DEFAULT,
    BLUE,
    RED,
    GREEN,
    YELLOW,
    PURPLE,
    ORANGE,
    PINK,
    ;

    val choiceId: String
        get() = "drawer_color_${name.lowercase()}"

    val color: Color?
        get() = when (this) {
            DEFAULT -> null
            BLUE -> Color(0xFF2196F3)
            RED -> Color(0xFFE53935)
            GREEN -> Color(0xFF43A047)
            YELLOW -> Color(0xFFF9A825)
            PURPLE -> Color(0xFF8E24AA)
            ORANGE -> Color(0xFFEF6C00)
            PINK -> Color(0xFFD81B60)
        }

    companion object {
        fun fromPreference(value: String): DrawerTileColor =
            entries.firstOrNull { it.name == value } ?: DEFAULT

        fun fromChoiceId(id: String): DrawerTileColor? =
            entries.firstOrNull { it.choiceId == id }
    }
}

@Composable
internal fun carButtonChoices(): List<Pair<String, String>> =
    GsThemes.builtIn.map { it.id to it.name } +
        listOf(
            DrawerIconStyle.TRANSPARENT_BORDER.buttonChoiceId to stringResource(R.string.drawer_icon_style_border),
            DrawerIconStyle.ROUND.buttonChoiceId to stringResource(R.string.drawer_icon_style_round),
            DrawerIconStyle.SQUARE.buttonChoiceId to stringResource(R.string.drawer_icon_style_square),
        )

@Composable
internal fun carDrawerColorChoices(): List<Pair<String, String>> =
    listOf(
        DrawerTileColor.DEFAULT.choiceId to stringResource(R.string.drawer_color_default),
        DrawerTileColor.BLUE.choiceId to stringResource(R.string.drawer_color_blue),
        DrawerTileColor.RED.choiceId to stringResource(R.string.drawer_color_red),
        DrawerTileColor.GREEN.choiceId to stringResource(R.string.drawer_color_green),
        DrawerTileColor.YELLOW.choiceId to stringResource(R.string.drawer_color_yellow),
        DrawerTileColor.PURPLE.choiceId to stringResource(R.string.drawer_color_purple),
        DrawerTileColor.ORANGE.choiceId to stringResource(R.string.drawer_color_orange),
        DrawerTileColor.PINK.choiceId to stringResource(R.string.drawer_color_pink),
    )

internal fun selectedButtonChoice(themeId: String, drawerIconStyle: DrawerIconStyle): String =
    if (drawerIconStyle == DrawerIconStyle.DEFAULT) themeId else drawerIconStyle.buttonChoiceId

internal fun selectedDrawerColorChoice(color: DrawerTileColor): String = color.choiceId
