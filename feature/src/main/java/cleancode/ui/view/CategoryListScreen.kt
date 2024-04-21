package cleancode.ui.view

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import cleancode.viewmodel.MessageCategoryViewModel
import com.nygar.common.Constants
import com.nygar.designsystem.components.GenericRow
import com.nygar.designsystem.components.TypeRow
import com.nygar.designsystem.components.skeleton.SkeletonGridRow
import com.nygar.designsystem.theme.ThemeConfig

@Composable
fun CategoryListScreen(
    viewModel: MessageCategoryViewModel = hiltViewModel(),
) {
    val lazyState = rememberLazyListState()
    val categoryList = viewModel.categoryList

    LazyColumn(
        state = lazyState
    ) {
        if(categoryList.isEmpty()) {
            items(Constants.PAGE_SIZE) {
                SkeletonGridRow(
                    modifier = Modifier.padding(all = ThemeConfig.theme.spacing.sizeSpacing8),
                )
            }
        }else {
                items(categoryList.size){index ->
                    categoryList[index]?.let { category ->
                        GenericRow(
                            modifier = Modifier.padding( all = ThemeConfig.theme.spacing.sizeSpacing8),
                            title = category.name,
                            image = category.imageUrl,
                            type = TypeRow.CategoryRow,
                            onViewCLickListener = {
                                //onCharacterItemClick(character.id)
                            }
                        )
                    }
                }
            }
        }
    }