package cleancode.ui.view

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cleancode.model.CategoryModel
import com.nygar.common.Constants
import com.nygar.designsystem.components.CharacterRow
import com.nygar.designsystem.components.skeleton.SkeletonGridRow
import com.nygar.designsystem.theme.ThemeConfig

@Composable
fun CategoryListScreen() {

    val categoryList: List<CategoryModel> = arrayListOf()

    LazyColumn {
        if(categoryList.isNullOrEmpty()) {
            items(Constants.PAGE_SIZE) {
                SkeletonGridRow(
                    modifier = Modifier.padding(all = ThemeConfig.theme.spacing.sizeSpacing8),
                )
            }
        }else {
                items(categoryList.size){index ->
                    categoryList[index]?.let { category ->
                        CharacterRow(
                            modifier = Modifier.padding( all = ThemeConfig.theme.spacing.sizeSpacing8),
                            characterName = category.name,
                            characterAvatar = category.imageUrl,
                            numComics = category.categoryId,
                            onViewCLickListener = {
                                //onCharacterItemClick(character.id)
                            }
                        )
                    }
                }
            }
        }
    }