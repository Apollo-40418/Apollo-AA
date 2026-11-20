package app.seb3thehacker.gearslip.ui

import android.content.Intent
import android.net.Uri
import android.provider.OpenableColumns
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import java.text.DecimalFormat
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.Box
import app.seb3thehacker.gearslip.R
import app.seb3thehacker.gearslip.car.CarSettings
import app.seb3thehacker.gearslip.car.DrawerBackground
import app.seb3thehacker.gearslip.car.MAX_DRAWER_GIF_SPEED
import app.seb3thehacker.gearslip.car.MIN_DRAWER_GIF_SPEED

@Composable
internal fun rememberDrawerBackgroundPicker(): DrawerBackgroundPickerState {
    val context = LocalContext.current
    var showPickerError by remember { mutableStateOf(false) }
    val picker = rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
        if (uri != null) {
            val name = try {
                context.contentResolver.query(
                    uri,
                    arrayOf(OpenableColumns.DISPLAY_NAME),
                    null,
                    null,
                    null,
                )?.use { cursor ->
                    val column = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                    if (column >= 0 && cursor.moveToFirst()) cursor.getString(column) else null
                }
            } catch (_: SecurityException) {
                null
            }
            if (name.isNullOrBlank()) {
                showPickerError = true
            } else {
                try {
                    context.contentResolver.takePersistableUriPermission(
                        uri,
                        Intent.FLAG_GRANT_READ_URI_PERMISSION,
                    )
                    CarSettings.setDrawerBackground(DrawerBackground(uri.toString(), name))
                } catch (_: SecurityException) {
                    showPickerError = true
                }
            }
        }
    }
    return DrawerBackgroundPickerState(
        choosePhoto = { picker.launch(arrayOf("image/*")) },
        showError = showPickerError,
        dismissError = { showPickerError = false },
    )
}

@Composable
internal fun DrawerBackgroundPickerError(state: DrawerBackgroundPickerState) {
    if (state.showError) {
        AlertDialog(
            onDismissRequest = state.dismissError,
            title = { Text(stringResource(R.string.drawer_background_error_title)) },
            text = { Text(stringResource(R.string.drawer_background_error_message)) },
            confirmButton = {
                TextButton(onClick = state.dismissError) {
                    Text(stringResource(R.string.close))
                }
            },
        )
    }
}

@Composable
internal fun DrawerBackgroundSetting(onChoosePhoto: () -> Unit) {
    val background by CarSettings.drawerBackground.collectAsState()
    var menuOpen by remember { mutableStateOf(false) }
    val defaultId = "drawer_background_default"
    val pickId = "drawer_background_pick"
    val defaultLabel = stringResource(R.string.drawer_background_default)
    val pickLabel = stringResource(R.string.drawer_background_choose)
    val choices = buildList {
        add(defaultId to defaultLabel)
        background?.let { add(it.uri to it.fileName) }
        add(pickId to pickLabel)
    }
    val selectedId = background?.uri ?: defaultId
    val selectedLabel = choices.firstOrNull { it.first == selectedId }?.second.orEmpty()

    SettingsRow(
        title = stringResource(R.string.car_drawer_background),
        modifier = Modifier.clickable { menuOpen = true },
        trailing = {
            Box {
                Text(
                    selectedLabel,
                    modifier = Modifier.widthIn(max = 220.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                if (menuOpen) {
                    DropdownMenu(expanded = true, onDismissRequest = { menuOpen = false }) {
                        choices.forEach { (value, label) ->
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        label,
                                        fontWeight = if (value == selectedId) FontWeight.Bold else FontWeight.Normal,
                                    )
                                },
                                onClick = {
                                    menuOpen = false
                                    when (value) {
                                        defaultId -> CarSettings.setDrawerBackground(null)
                                        pickId -> onChoosePhoto()
                                    }
                                },
                            )
                        }
                    }
                }
            }
        },
    )
}

@Composable
internal fun DrawerGifSpeedSetting() {
    val background by CarSettings.drawerBackground.collectAsState()
    val speed by CarSettings.drawerGifSpeed.collectAsState()
    val isGif = background?.fileName?.substringAfterLast('.', "")?.equals("gif", ignoreCase = true) == true
    if (!isGif) {
        return
    }

    val speedLabel = remember(speed) { DecimalFormat("0.##").format(speed) }
    Column(
        Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 6.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp),
    ) {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                stringResource(R.string.drawer_gif_speed),
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.titleMedium,
            )
            Text(
                stringResource(R.string.drawer_gif_speed_value, speedLabel),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
            )
        }
        Slider(
            value = speed,
            onValueChange = CarSettings::setDrawerGifSpeed,
            valueRange = MIN_DRAWER_GIF_SPEED..MAX_DRAWER_GIF_SPEED,
            steps = 5,
        )
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(stringResource(R.string.drawer_gif_speed_slow), style = MaterialTheme.typography.bodySmall)
            Text(stringResource(R.string.drawer_gif_speed_fast), style = MaterialTheme.typography.bodySmall)
        }
    }
}

internal data class DrawerBackgroundPickerState(
    val choosePhoto: () -> Unit,
    val showError: Boolean,
    val dismissError: () -> Unit,
)
