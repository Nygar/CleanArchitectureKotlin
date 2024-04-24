package cleancode.ui.view

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import cleancode.viewmodel.LoginViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.nygar.designsystem.components.LottieLoadingView
import com.nygar.designsystem.theme.ThemeConfig

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onNavigateToMain: () -> Unit,
) {
    val mGoogleSignInClient: GoogleSignInClient = GoogleSignIn.getClient(LocalContext.current, viewModel.gso)

    val resultLogIn =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            result.data?.let {
                viewModel.authWithGoogle(it) {
                    viewModel.isLogingFinished = true
                }
            }
        }

    LaunchedEffect(viewModel.isAllowLoginNavigate) {
        if (viewModel.isAllowLoginNavigate) {
            onNavigateToMain.invoke()
            viewModel.isShowingAnimation = false
            viewModel.isLogingFinished = false
            viewModel.isAnimationComplete = false
        }
    }

    ConstraintLayout(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(ThemeConfig.theme.spacing.sizeSpacing5),
    ) {
        val (loadingView, userFields, contentLogin) = createRefs()
        val chain = createVerticalChain(userFields, contentLogin, chainStyle = ChainStyle.Spread)

        constrain(chain) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
        }

        Column(
            Modifier.constrainAs(userFields) {
                top.linkTo(parent.top, margin = ThemeConfig.theme.spacing.sizeSpacing15)
            },
        ) {
            LoginUserField(
                Modifier.padding(top = ThemeConfig.theme.spacing.sizeSpacing15),
            )
            LoginPassField(
                Modifier.padding(top = ThemeConfig.theme.spacing.sizeSpacing15),
            )
        }
        Column(
            Modifier.constrainAs(contentLogin) {
                top.linkTo(userFields.bottom, margin = ThemeConfig.theme.spacing.sizeSpacing15)
                bottom.linkTo(parent.bottom, margin = ThemeConfig.theme.spacing.sizeSpacing15)
            },
        ) {
            GoogleBtn(Modifier) {
                viewModel.isLogingFinished = false
                viewModel.isShowingAnimation = true
                resultLogIn.launch(mGoogleSignInClient.signInIntent)
            }

            NormalLoginBtn(
                modifier =
                    Modifier.padding(
                        top = ThemeConfig.theme.spacing.sizeSpacing15,
                    ),
            ) {
                viewModel.isLogingFinished = true
                viewModel.isShowingAnimation = true
            }
        }

        if (viewModel.isShowingAnimation) {
            LottieLoadingView(
                modifier =
                    Modifier.constrainAs(loadingView) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    },
            ) {
                viewModel.isAnimationComplete = true
            }
        }
    }
}

@Composable
fun GoogleBtn(
    modifier: Modifier,
    onGoogleLogin: () -> Unit,
) {
    Button(
        onClick = {
            onGoogleLogin.invoke()
        },
        modifier =
            modifier
                .fillMaxWidth()
                .padding(horizontal = ThemeConfig.theme.spacing.sizeSpacing15),
        shape = RoundedCornerShape(ThemeConfig.theme.spacing.sizeSpacing5),
        colors =
            ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White,
            ),
    ) {
        Image(
            painter = painterResource(id = com.google.firebase.appcheck.interop.R.drawable.googleg_standard_color_18),
            contentDescription = null,
        )
        Text(
            text = stringResource(id = com.nygar.designsystem.R.string.btn_text_login_google),
            modifier = Modifier.padding(ThemeConfig.theme.spacing.sizeSpacing5),
        )
    }
}

@Composable
fun NormalLoginBtn(
    modifier: Modifier,
    onNormalLogin: () -> Unit,
) {
    Button(
        onClick = {
            onNormalLogin.invoke()
        },
        modifier =
            modifier
                .fillMaxWidth()
                .padding(horizontal = ThemeConfig.theme.spacing.sizeSpacing15),
        shape = RoundedCornerShape(ThemeConfig.theme.spacing.sizeSpacing5),
        colors =
            ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White,
            ),
    ) {
        Text(
            text = stringResource(id = com.nygar.designsystem.R.string.btn_text_login),
            modifier = Modifier.padding(ThemeConfig.theme.spacing.sizeSpacing5),
        )
    }
}

@Composable
fun LoginUserField(modifier: Modifier) {
    var usernameTextState by rememberSaveable { mutableStateOf("") }
    TextField(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(horizontal = ThemeConfig.theme.spacing.sizeSpacing15),
        value = usernameTextState,
        onValueChange = { usernameTextState = it },
        label = {
            Text(
                text = stringResource(id = com.nygar.designsystem.R.string.hint_text_email),
            )
        },
        keyboardOptions =
            KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done,
            ),
    )
}

@Composable
fun LoginPassField(modifier: Modifier) {
    var passTextState by rememberSaveable { mutableStateOf("") }
    TextField(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(horizontal = ThemeConfig.theme.spacing.sizeSpacing15),
        value = passTextState,
        onValueChange = { passTextState = it },
        label = {
            Text(
                text = stringResource(id = com.nygar.designsystem.R.string.hint_text_password),
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
    )
}
