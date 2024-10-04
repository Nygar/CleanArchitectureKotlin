package cleancode.ui.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import cleancode.viewmodel.MessageDetailsViewModel
import com.nygar.designsystem.R
import com.nygar.designsystem.components.LandscapeImage
import com.nygar.designsystem.theme.ThemeConfig

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageDetailsScreen(
    viewModel: MessageDetailsViewModel = hiltViewModel(),
    messageId: Int,
    onNavigateBack: () -> Unit,
) {
    LaunchedEffect(Unit) {
        viewModel.getMessageById(messageId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                navigationIcon = {
                    IconButton(onClick = {
                        onNavigateBack.invoke()
                    }) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                    }
                },
            )
        },
    ) { paddingValues ->

        ConstraintLayout(
            modifier =
                Modifier.fillMaxSize()
                    .padding(paddingValues),
        ) {
            val messageDetails = viewModel.messageSingle

            val (ivCover, tvFullname, tvDescription) = createRefs()

            LandscapeImage(
                image = messageDetails?.imageUrl ?: "",
                modifier =
                    Modifier.constrainAs(ivCover) {
                        top.linkTo(parent.top)
                    },
            )

            Text(
                text = messageDetails?.name ?: "",
                Modifier
                    .padding(horizontal = ThemeConfig.theme.spacing.sizeSpacing15)
                    .constrainAs(tvFullname) {
                        top.linkTo(ivCover.bottom, margin = ThemeConfig.theme.spacing.sizeSpacing5)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
            )
            MessageDetailsGenericField(
                stringResource(id = R.string.view_text_description),
                messageDetails?.description ?: "",
                Modifier
                    .padding(horizontal = ThemeConfig.theme.spacing.sizeSpacing15)
                    .constrainAs(tvDescription) {
                        top.linkTo(tvFullname.bottom, margin = ThemeConfig.theme.spacing.sizeSpacing5)
                    },
            )
        }
    }
}

@Composable
fun MessageDetailsGenericField(
    title: String,
    data: String,
    modifier: Modifier = Modifier,
) {
    ConstraintLayout(
        modifier =
            modifier
                .fillMaxWidth(),
    ) {
        val (tvTitle, tvData) = createRefs()

        Text(
            text = title,
            Modifier.constrainAs(tvTitle) {
                top.linkTo(parent.top, margin = ThemeConfig.theme.spacing.sizeSpacing10)
                start.linkTo(parent.start)
            },
        )
        Text(
            text = data,
            Modifier.constrainAs(tvData) {
                top.linkTo(tvTitle.bottom, margin = ThemeConfig.theme.spacing.sizeSpacing5)
                start.linkTo(parent.start)
            },
        )
    }
}
