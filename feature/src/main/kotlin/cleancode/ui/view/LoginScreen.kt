package cleancode.ui.view

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.constraintlayout.compose.ConstraintLayout
import com.nygar.designsystem.theme.ThemeConfig

interface LoginActivityDelegate {
    fun normalLoginAction()

    fun googleLoginAction()
}

@Composable
fun LoginScreen(delegate: LoginActivityDelegate) {
    ConstraintLayout(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(ThemeConfig.theme.spacing.sizeSpacing5),
    ) {
        val (username, password, contentLogin) = createRefs()

        LoginUserField(
            Modifier.constrainAs(username) {
                top.linkTo(parent.top, margin = ThemeConfig.theme.spacing.sizeSpacing15)
            },
        )
        LoginPassField(
            Modifier.constrainAs(password) {
                top.linkTo(username.bottom, margin = ThemeConfig.theme.spacing.sizeSpacing15)
            },
        )
        Column(
            Modifier.constrainAs(contentLogin) {
                top.linkTo(password.bottom, margin = ThemeConfig.theme.spacing.sizeSpacing15)
                bottom.linkTo(parent.bottom, margin = ThemeConfig.theme.spacing.sizeSpacing15)
            },
        ) {
            GoogleBtn(Modifier, delegate)

            NormalLoginBtn(
                modifier =
                    Modifier.padding(
                        top = ThemeConfig.theme.spacing.sizeSpacing15,
                    ),
                delegate,
            )
        }
    }
}

@Composable
fun GoogleBtn(
    modifier: Modifier,
    delegate: LoginActivityDelegate,
) {
    Button(
        onClick = {
            delegate.googleLoginAction()
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
    delegate: LoginActivityDelegate,
) {
    Button(
        onClick = {
            delegate.normalLoginAction()
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
