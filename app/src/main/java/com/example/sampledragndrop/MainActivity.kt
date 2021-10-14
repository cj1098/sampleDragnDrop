package com.example.sampledragndrop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.sampledragndrop.ui.theme.SampleDragnDropTheme
import org.burnoutcrew.reorderable.*

class MainActivity : ComponentActivity() {

    val viewModel = ReorderListViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val uiState by viewModel.uiStateLiveData.asFlow().collectAsState(initial = DragAndDropUiState())

            SampleDragnDropTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    topBar = {
                        TopAppBar(title = { Text(stringResource(R.string.app_name)) })
                    },
                ) {
                    ReorderList(uiState = uiState, uiActions = viewModel)
                }
            }
        }
    }
}

@Composable
fun ReorderList(uiState: DragAndDropUiState, uiActions: DragAndDropActions) {
    val state = rememberReorderState()
    Column {
        LazyColumn(
            state = state.listState,
            modifier = Modifier.reorderable(state, onMove = { from, to -> uiActions.moveItem(from, to) })
        ) {

            itemsIndexed(uiState.items) { index, item ->
                if (item.isLocked) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.LightGray)
                    ) {
                        Text(
                            text = item.title,
                            modifier = Modifier.padding(24.dp)
                        )
                    }
                } else {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .draggedItem(state.offsetByIndex(index))
                            .background(MaterialTheme.colors.surface)
                            .detectReorderAfterLongPress(state),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Image(painter = rememberImagePainter(data = item.url), contentDescription = null, modifier = Modifier.size(40.dp))
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = item.title,
                            modifier = Modifier.padding(16.dp)
                        )
                        Divider()
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SampleDragnDropTheme {
        Greeting("Android")
    }
}