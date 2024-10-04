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
import cleancode.viewmodel.UserDetailsViewModel
import com.nygar.designsystem.R
import com.nygar.designsystem.components.LandscapeImage
import com.nygar.designsystem.theme.ThemeConfig

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailsScreen(
    viewModel: UserDetailsViewModel = hiltViewModel(),
    userId: Int,
    onNavigateBack: () -> Unit,
) {
    LaunchedEffect(Unit) {
        viewModel.getUserById(userId)
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
            val userDetails = viewModel.userSingle

            val (ivCover, tvFullname, tvEmail, tvFollowers, tvDescription) = createRefs()

            LandscapeImage(
                image = userDetails?.coverUrl ?: "",
                modifier =
                    Modifier.constrainAs(ivCover) {
                        top.linkTo(parent.top)
                    },
            )

            Text(
                text = userDetails?.fullName ?: "",
                Modifier
                    .padding(horizontal = ThemeConfig.theme.spacing.sizeSpacing15)
                    .constrainAs(tvFullname) {
                        top.linkTo(ivCover.bottom, margin = ThemeConfig.theme.spacing.sizeSpacing5)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
            )
            UserDetailsGenericField(
                stringResource(id = R.string.view_text_email),
                userDetails?.email ?: "",
                Modifier
                    .padding(horizontal = ThemeConfig.theme.spacing.sizeSpacing15)
                    .constrainAs(tvEmail) {
                        top.linkTo(tvFullname.bottom, margin = ThemeConfig.theme.spacing.sizeSpacing5)
                        start.linkTo(parent.start)
                    },
            )
            UserDetailsGenericField(
                stringResource(id = R.string.view_text_followers),
                userDetails?.followers.toString(),
                Modifier
                    .padding(horizontal = ThemeConfig.theme.spacing.sizeSpacing15)
                    .constrainAs(tvFollowers) {
                        top.linkTo(tvEmail.bottom, margin = ThemeConfig.theme.spacing.sizeSpacing5)
                        start.linkTo(parent.start)
                    },
            )
            UserDetailsGenericField(
                stringResource(id = R.string.view_text_description),
                userDetails?.description ?: "",
                Modifier
                    .padding(horizontal = ThemeConfig.theme.spacing.sizeSpacing15)
                    .constrainAs(tvDescription) {
                        top.linkTo(tvFollowers.bottom, margin = ThemeConfig.theme.spacing.sizeSpacing5)
                    },
            )
        }
    }
}

@Composable
fun UserDetailsGenericField(
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
