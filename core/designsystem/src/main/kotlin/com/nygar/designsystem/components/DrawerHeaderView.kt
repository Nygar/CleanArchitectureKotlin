package com.nygar.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.nygar.designsystem.R

@Composable
fun DrawerHeader(
    fullName: String = "",
    avatarUrl: String = "",
    modifier: Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier =
            modifier
                .background(MaterialTheme.colorScheme.secondary)
                .padding(16.dp)
                .fillMaxWidth(),
    ) {
        AsyncImage(
            model = avatarUrl,
            placeholder = painterResource(id = R.drawable.ic_person_black_24dp),
            contentDescription = null,
            modifier =
                modifier
                    .size(70.dp)
                    .clip(CircleShape),
        )

        Spacer(modifier = Modifier.padding(5.dp))

        Text(
            text = fullName,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimary,
        )
    }
}
