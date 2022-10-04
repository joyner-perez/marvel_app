package com.joyner.marvelapp.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.joyner.marvelapp.R

@Composable
fun LogoAppScreen(modifier: Modifier) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Image(painter = painterResource(id = R.mipmap.ic_launcher_round),
            contentDescription = "LogoApp",
            modifier = modifier
        )
        Text(text = stringResource(id = R.string.app_name),
            color = MaterialTheme.colors.onSurface,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LogoAppScreen(Modifier.scale(1f))
}