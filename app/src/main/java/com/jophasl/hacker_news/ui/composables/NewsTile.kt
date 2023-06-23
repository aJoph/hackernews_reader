package com.jophasl.hacker_news.ui.composables

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jophasl.hacker_news.data.remote.Story
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Preview
@Composable
fun PreviewNewsTile() {
    NewsTile(
        Story(
            by = "Arnold",
            descendants = 1,
            id = 1,
            kids = listOf(1),
            score = 2,
            time = 12,
            title = "Cool Title Does Cool Things",
            type = "COMMENT",
            url = "https://google.com"
        )
    )
}

@Composable
fun NewsTile(data: Story) {
    var shouldShowOptions by remember { mutableStateOf(false) }
    val date = Date(data.time.toLong() * 1000) // Multiply by 1000 because it expects milliseconds.
    val formattedState = SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.ENGLISH).format(date)

    Card(modifier = Modifier
        .padding(4.dp)
        .clickable {
            shouldShowOptions = !shouldShowOptions
        }
        .animateContentSize()) {
        Column(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
        ) {
            Text(
                data.title, style = MaterialTheme.typography.titleSmall
            )
            ClickableText(text = AnnotatedString(data.url ?: ""), onClick = {
                Log.d(null, "User has clicked a link to a news article.")
            }, style = MaterialTheme.typography.labelSmall)
            Spacer(Modifier.height(8.dp))
            Row {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Trending",
                    modifier = Modifier.height(24.dp)
                )
                Text(data.score.toString(), fontSize = 16.sp)
                Spacer(Modifier.width(32.dp))
                Icon(
                    imageVector = Icons.Filled.MailOutline,
                    contentDescription = "Comments",
                    modifier = Modifier.height(24.dp)
                )
                Text(data.kids.size.toString(), fontSize = 16.sp)
            }
            Spacer(Modifier.height(8.dp))
            Text("In $formattedState by ${data.by}")

            // Options
            if (shouldShowOptions) {
                Spacer(Modifier.height(8.dp))
                Row {
                    Button(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowUp,
                            contentDescription = "Upvote"
                        )
                    }
                    Spacer(Modifier.width(4.dp))
                    Button(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.Share, contentDescription = "Share content"
                        )
                    }
                }
            }
        }
    }
}

