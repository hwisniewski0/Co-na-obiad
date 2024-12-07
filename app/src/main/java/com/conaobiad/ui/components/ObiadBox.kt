package com.conaobiad.ui.components

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.conaobiad.data.model.Obiad

@Composable
fun ObiadBox(
    obiad: Obiad,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 7.dp)
            .clickable(
                onClick = { expanded = !expanded },
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            )
            .background(Color(0x00000000)) // Tło bez koloru
            .padding(2.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .background(Color(0xC3FE9635)) // Kolor tła
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            // Tytuł z datą i dniem
            Text(
                text = "${obiad.dzien} - ${obiad.dataDnia}",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            // Krótki opis obiadu
            Text(
                text = "Zupa: ${obiad.zupa}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = 8.dp),
                maxLines = if (expanded) Int.MAX_VALUE else 2
            )
            Text(
                text = "Drugie danie: ${obiad.drugie_danie}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = 8.dp),
                maxLines = if(expanded) Int.MAX_VALUE else 2
            )


            // Dodatkowe informacje z animacją
            AnimatedVisibility(
                visible = expanded,
                enter = fadeIn(animationSpec = tween(500)) + expandVertically(animationSpec = tween(500)),
                exit = fadeOut(animationSpec = tween(500)) + shrinkVertically(animationSpec = tween(500))
            ) {
                Column {
                    HorizontalDivider(color = Color.Black)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Składniki: ${obiad.skladniki}",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Alergeny: ${obiad.alergeny}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}
