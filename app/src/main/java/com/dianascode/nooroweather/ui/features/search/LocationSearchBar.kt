package com.dianascode.nooroweather.ui.features.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dianascode.nooroweather.R
import com.dianascode.nooroweather.ui.common.utils.TestIdentifiers
import com.dianascode.nooroweather.ui.theme.GrayLabel
import com.dianascode.nooroweather.ui.theme.NooroWeatherTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    SearchBar(
        query = query,
        onQueryChange = onQueryChange, // update the value of query
        onSearch = onQueryChange, // the callback to be invoked when the input service triggers the ImeAction.Search action
        active = false,
        onActiveChange = { },
        placeholder = { Text(stringResource(R.string.search_location), color = GrayLabel) },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = GrayLabel
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .testTag(TestIdentifiers.searchBar)
    ) {}
}

@Preview
@Composable
private fun NooroWeatherSearchBarPreview() {
    NooroWeatherTheme {
        LocationSearchBar(
            query = "",
            onQueryChange = {}
        )
    }
}