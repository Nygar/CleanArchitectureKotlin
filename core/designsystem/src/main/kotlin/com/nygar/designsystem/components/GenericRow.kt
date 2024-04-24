package com.nygar.designsystem.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.nygar.common.BuildConfig
import com.nygar.common.ConstantsTesting
import com.nygar.designsystem.R
import com.nygar.designsystem.theme.ThemeConfig

@Composable
fun GenericRow(
    modifier: Modifier = Modifier,
    title: String = "",
    image: String = "",
    id: Int = 0,
    type: TypeRow,
    onViewCLickListener: ((Int) -> Unit)? = null,
) {
    Card(
        modifier =
            modifier
                .testTag(ConstantsTesting.TEST_TAG_COMIC_ROW)
                .fillMaxWidth()
                .clickable { onViewCLickListener?.invoke(id) },
        elevation =
            CardDefaults.cardElevation(
                defaultElevation = ThemeConfig.theme.spacing.sizeSpacing2,
            ),
        shape = RoundedCornerShape(ThemeConfig.theme.spacing.sizeSpacing8),
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val placeholder =
                when (type) {
                    is TypeRow.UserRow -> {
                        R.drawable.ic_person_black_24dp
                    }
                    is TypeRow.CategoryRow -> {
                        R.drawable.ic_message_black_24dp
                    }
                    is TypeRow.MassageRow -> {
                        R.drawable.ic_message_black_24dp
                    }
                }

            val imageToShow =
                if (image == BuildConfig.URL_BASE) {
                    placeholder
                } else {
                    image
                }

            AsyncImage(
                model = imageToShow,
                placeholder = painterResource(id = placeholder),
                contentDescription = null,
                modifier =
                    Modifier
                        .size(ThemeConfig.theme.spacing.sizeSpacing45)
                        .padding(horizontal = ThemeConfig.theme.spacing.sizeSpacing4),
            )

            Text(
                modifier = Modifier,
                text = title,
                fontFamily = ThemeConfig.theme.font.comicHelvetic,
                fontWeight = FontWeight.Medium,
            )
        }
    }
}

sealed interface TypeRow {
    data object UserRow : TypeRow

    data object CategoryRow : TypeRow

    data object MassageRow : TypeRow
}

@Preview()
@Composable
fun PreviewGenericRow() {
    Column {
        GenericRow(
            title = "name",
            image = "image",
            id = 0,
            type = TypeRow.UserRow,
        )
    }
}
