package app.seb3thehacker.gearslip.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import app.seb3thehacker.gearslip.R

private data class CarEntry(
    val name: String,
    val year: String? = null,
    val headUnit: String? = null,
)

private val workingCars = listOf(
    CarEntry("Audi", headUnit = "MIB2+ (Alpine)"),
    CarEntry("Audi", headUnit = "Harman"),
    CarEntry("Chevrolet, with myChevrolet", "2019, 2020, 2021", "myChevrolet"),
    CarEntry("Chevrolet, with MyLink", "2017", "MyLink"),
    CarEntry("Dacia", headUnit = "Media Nav (LG ULC 4.1)"),
    CarEntry("Dacia", headUnit = "LG ULC 4.5"),
    CarEntry("Ford", "2018", "SYNC 3.0 (Panasonic)"),
    CarEntry("Ford Fiesta", "2019", "SYNC 3.2 (Panasonic)"),
    CarEntry("Ford Fusion", "2018", "SYNC 3.1 (Panasonic)"),
    CarEntry("Jeep Cherokee", "2019", "Uconnect 8.4 (Delphi)"),
    CarEntry("MG", "2019", "Desay SV"),
    CarEntry("Ram 1500", "2018", "Uconnect 8.4 (Panasonic)"),
    CarEntry("Renault Clio V", "2019-2024", "Easy Link"),
    CarEntry("Škoda", "2018", "MIB Standard2 (TechniSat)"),
    CarEntry("Subaru Outback", "2020", "Denso"),
    CarEntry("Toyota RAV4", "2020", "Pioneer T1"),
    CarEntry("Volkswagen", "2018", "MIB Standard2 (Delphi)"),
    CarEntry("Volkswagen", "2024", "ICAS3"),
    CarEntry("Volvo XC90", "2022", "SPA Plus (Mitsubishi Electric)"),
    CarEntry("Wuling", "2021", "Desay SV"),
    CarEntry("LIVI (open-source head unit)"),
)

private val carsNotWorkingYet = listOf(
    CarEntry("Honda", "2016"),
    CarEntry("Honda", "2019"),
    CarEntry("Mitsubishi Attrage", "2020"),
)

@Composable
internal fun CarsDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(R.string.help_which_cars)) },
        text = {
            Column(
                Modifier.heightIn(max = 440.dp).verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Text(
                    stringResource(R.string.help_cars_intro),
                    style = MaterialTheme.typography.bodyMedium,
                )
                CarSection(stringResource(R.string.help_cars_works), workingCars)
                CarSection(
                    stringResource(R.string.help_cars_not_working),
                    carsNotWorkingYet,
                    stringResource(R.string.help_cars_connection_drops),
                )
                Text(
                    stringResource(R.string.help_cars_unknown),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) { Text(stringResource(R.string.close)) }
        },
    )
}

@Composable
private fun CarSection(title: String, cars: List<CarEntry>, detail: String? = null) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        cars.forEach { car ->
            Column(
                Modifier.fillMaxWidth().padding(start = 8.dp),
                verticalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                Text(
                    listOfNotNull(car.name, car.year, car.headUnit).joinToString(" - "),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                )
                detail?.let {
                    Text(
                        it,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
        }
    }
}
