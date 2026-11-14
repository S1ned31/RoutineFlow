package com.example.articles.postInfo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.articles.postList.model.UiInfoPost
import com.busol.habittracker.core.ui.theme.AppTheme
import com.busol.habittracker.feature.routinedetails.R

@Composable
fun PostList(
    modifier: Modifier = Modifier,
    list: List<UiInfoPost>,
    onClickPost: (Long) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(list, key = { item -> item.id }) { item ->
            PostListItem(
                modifier = Modifier.fillMaxSize(),
                image = item.image,
                title = item.title,
                text = item.text,
                onClickItem = {
                    onClickPost(item.id)
                },
                readingTime = item.readingTime,
            )
            HorizontalDivider(
                thickness = 1.dp, color = AppTheme.colors.contentColor,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
            )
        }
    }
}

@Composable
fun PostListItem(
    modifier: Modifier = Modifier,
    image: String,
    title: String,
    text: String,
    readingTime: String,
    onClickItem: () -> Unit,
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .clickable { onClickItem() },
        colors = CardDefaults.cardColors(
            containerColor = AppTheme.colors.background,
        ),
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(230.dp)
                    .clip(RoundedCornerShape(16.dp)),
            ) {
                Image(
                    painter = painterResource(R.drawable.image),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                )

                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxSize(),
                ) {
                    Row(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(
                                bottom = AppTheme.dimensions.spacingDefault,
                                start = AppTheme.dimensions.spacingDefault,
                            )
                            .clip(RoundedCornerShape(8.dp))
                            .background(AppTheme.colors.contentColor)
                            .padding(4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.book_open),
                            modifier = Modifier.size(24.dp),
                            contentDescription = null,
                            tint = AppTheme.colors.containerColor,
                        )
                        Spacer(modifier = Modifier.padding(start = 10.dp))
                        Text(
                            text = readingTime,
                            style = AppTheme.fonts.textFieldTitle,
                            color = AppTheme.colors.containerColor,
                        )
                    }
                }
            }

            Text(
                text = title,
                style = AppTheme.fonts.h2,
                color = AppTheme.colors.contentColor,
                modifier = Modifier.padding(8.dp),
            )

            Text(
                text = text,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = AppTheme.fonts.textFieldTitle,
                color = AppTheme.colors.contentColor,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            )
        }
    }
}