package app.seb3thehacker.gearslip.ui

import androidx.compose.ui.res.stringResource
import app.seb3thehacker.gearslip.R
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

/** The project's remaining links outside the app. */
internal object GearslipLinks {
    const val MATRIX = "https://matrix.to/#/!mf0_mgBd5KqKpc9FfX_wHp1jFCcwAtPrndIn_oGIyMk?via=matrix.org"
    const val SOURCE = "https://github.com/Apollo-40418/Apollo-AA"
    const val CARS = "$SOURCE/blob/main/CARS.md"
    const val PRIVACY = "$SOURCE/blob/main/PRIVACY.md"
    const val TRANSLATE = "https://hosted.weblate.org/engage/gearslip/"

    fun open(context: Context, url: String) {
        runCatching {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
        }
    }
}

/** The About section's links: the chat room, translations, the source code, and credits. */
@Composable
internal fun AboutLinkRows() {
    val context = LocalContext.current
    val arrow = @Composable {
        Icon(
            Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
    SettingsRow(
        stringResource(R.string.about_matrix),
        Modifier.clickable { GearslipLinks.open(context, GearslipLinks.MATRIX) },
        subtitle = stringResource(R.string.about_matrix_sub),
        icon = Icons.AutoMirrored.Filled.Send,
        trailing = arrow,
    )
    SettingsDivider()
    SettingsRow(
        stringResource(R.string.about_translate),
        Modifier.clickable { GearslipLinks.open(context, GearslipLinks.TRANSLATE) },
        subtitle = stringResource(R.string.about_translate_sub),
        icon = Icons.Filled.Edit,
        trailing = arrow,
    )
    SettingsDivider()
    SettingsRow(
        stringResource(R.string.about_source),
        Modifier.clickable { GearslipLinks.open(context, GearslipLinks.SOURCE) },
        subtitle = stringResource(R.string.about_source_sub),
        icon = Icons.Filled.Build,
        trailing = arrow,
    )
    SettingsDivider()
    SettingsRow(
        stringResource(R.string.privacy_policy),
        Modifier.clickable { GearslipLinks.open(context, GearslipLinks.PRIVACY) },
        subtitle = stringResource(R.string.privacy_policy_sub),
        icon = Icons.Filled.Lock,
        trailing = arrow,
    )
    SettingsDivider()
    SettingsRow(
        stringResource(R.string.about_voice_credits),
        subtitle = stringResource(R.string.about_voice_credits_sub),
        icon = Icons.Filled.Favorite,
    )
}
