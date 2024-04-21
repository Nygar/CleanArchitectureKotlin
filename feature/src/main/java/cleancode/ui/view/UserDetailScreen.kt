package cleancode.ui.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.nygar.designsystem.R

@Composable
fun ImageFull(dataUrl: String, modifier: Modifier){
    AsyncImage(
        modifier = modifier.fillMaxWidth().heightIn(max = 140.dp),
        placeholder = painterResource(R.drawable.ic_person_black_24dp),
        model = ImageRequest.Builder(LocalContext.current)
            .data(dataUrl)
            .crossfade(true)
            .build(),
        contentDescription = null,

        )
}

@Composable
fun UserDetailsGenericField(title: String, data: String, modifier: Modifier){
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
    ){
        val (tvTitle, tvData) = createRefs()

        Text(text = title, Modifier.constrainAs(tvTitle){
            top.linkTo(parent.top, margin = 10.dp)
            start.linkTo(parent.start)
        })
        Text(text = data, Modifier.constrainAs(tvData){
            top.linkTo(tvTitle.bottom, margin = 5.dp)
            start.linkTo(parent.start)
        })
    }
}