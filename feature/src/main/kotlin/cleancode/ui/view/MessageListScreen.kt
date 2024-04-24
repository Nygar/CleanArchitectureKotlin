package cleancode.ui.view

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.hilt.navigation.compose.hiltViewModel
import cleancode.viewmodel.MessageListViewModel
import com.nygar.common.Constants
import com.nygar.designsystem.R
import com.nygar.designsystem.components.GenericRow
import com.nygar.designsystem.components.TypeRow
import com.nygar.designsystem.components.skeleton.SkeletonRow
import com.nygar.designsystem.theme.ThemeConfig

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageListScreen(
    viewModel: MessageListViewModel = hiltViewModel(),
    categoryId: Int,
    onNavigateToMessageDetails: (Int) -> Unit,
    onNavigateBack: () -> Unit,
) {
    val messageList = viewModel.messageList

    LaunchedEffect(Unit) {
        viewModel.getMessageCategory(categoryId)
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

        LazyColumn(
            modifier = Modifier.padding(paddingValues),
        ) {
            if (messageList.isEmpty()) {
                items(Constants.PAGE_SIZE) {
                    SkeletonRow(
                        modifier = Modifier.padding(all = ThemeConfig.theme.spacing.sizeSpacing8),
                    )
                }
            } else {
                items(messageList.size) { index ->
                    messageList[index].let { message ->
                        GenericRow(
                            modifier = Modifier.padding(all = ThemeConfig.theme.spacing.sizeSpacing8),
                            id = message.messageId,
                            title = message.name,
                            image = message.imageUrl,
                            type = TypeRow.UserRow,
                            onViewCLickListener = {
                                onNavigateToMessageDetails(it)
                            },
                        )
                    }
                }
            }
        }
    }
}
