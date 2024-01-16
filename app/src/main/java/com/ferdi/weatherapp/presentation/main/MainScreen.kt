package com.ferdi.weatherapp.presentation.main

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.ferdi.weatherapp.R
import com.ferdi.weatherapp.core.getCurrentDate
import com.ferdi.weatherapp.core.getCurrentTime
import com.ferdi.weatherapp.domain.weatherinfostate.WeatherInfoState
import com.ferdi.weatherapp.ui.theme.BackgroundColor
import com.ferdi.weatherapp.utils.locationPermission
import kotlinx.coroutines.delay


@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val latLngState by viewModel.currentLatLng.collectAsState()
    val weatherDetail by viewModel.weatherDetail.collectAsState()
    val serviceState by viewModel.isServiceAvailable.collectAsState()
    val weatherInfoState by viewModel.weatherInfoState.collectAsState()
    val permission = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { mappedPermission ->
        if (mappedPermission.map { it.value }.all { data -> data }) {
            viewModel.getServiceState(true)
            viewModel.getCurrentLocation()
        } else {
            viewModel.getServiceState(false)
        }

    }
    LaunchedEffect(key1 = Unit) {
        permission.launch(locationPermission.toTypedArray())
    }
    LaunchedEffect(key1 = latLngState) {
        if (latLngState.latitude != 0.0 && latLngState.longitude != 0.0) {
            viewModel.getCurrentWeatherData(latLngState.latitude, latLngState.longitude)
        }
    }
    Column(
        modifier = modifier
            .background(BackgroundColor)
            .padding(16.dp)
            .statusBarsPadding()
            .navigationBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val currentDate = remember {
            getCurrentDate()
        }
        val currentTime by viewModel.currentTick.collectAsState()
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = currentDate, color = Color.White)
            Spacer(modifier = Modifier.weight(1F))
            Text(text = currentTime, color = Color.White)
        }
        Spacer(modifier = Modifier.weight(1F))
        AnimatedVisibility(
            visible = serviceState && weatherInfoState == WeatherInfoState.SUCCESS,
            enter = fadeIn(tween(200)) + scaleIn(tween(400),
                initialScale = 0.9F)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.weight(0.5F))
                Text(
                    text = weatherDetail.cityName,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(16.dp))
                Card(
                    modifier = Modifier.size(250.dp),
                    shape = CircleShape,
                    colors = CardDefaults.cardColors(
                        Color.LightGray
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AsyncImage(
                            model = weatherDetail.icon,
                            contentDescription = "icon",
                            modifier = Modifier.size(100.dp),
                            contentScale = ContentScale.Crop
                        )
                        Text(
                            text = buildAnnotatedString {
                                withStyle(SpanStyle()) {
                                    append(weatherDetail.temperature.toString())
                                    append(" ")
                                }
                                withStyle(SpanStyle()) {
                                    append("°")
                                }
                                withStyle(SpanStyle()) {
                                    append("C")
                                }
                            },
                            maxLines = 1,
                            color = Color.Black,
                            fontWeight = FontWeight.Light, fontSize = 26.sp
                        )
                        Text(
                            text = buildAnnotatedString {
                                withStyle(SpanStyle()) {
                                    append("Feels like")
                                    append(" ")
                                    append(weatherDetail.feelsLike.toString())
                                }
                                withStyle(SpanStyle()) {
                                    append("°")
                                }
                                withStyle(SpanStyle()) {
                                    append("C")
                                }
                            },
                            maxLines = 1,
                            color = Color.Black,
                            fontWeight = FontWeight.Light, fontSize = 12.sp
                        )

                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = weatherDetail.description,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.weight(1F))
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = stringResource(R.string.wind_speed),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(text = weatherDetail.windSpeed.toString(), maxLines = 1)
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = stringResource(R.string.humidity),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(text = weatherDetail.humidity.toString(), maxLines = 1)
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = stringResource(R.string.visibility),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(text = weatherDetail.visibility.toString(), maxLines = 1)
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = stringResource(R.string.air_pressure),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(text = weatherDetail.airPressure.toString(), maxLines = 1)
                    }
                }
            }

        }
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        AnimatedVisibility(
            visible = weatherInfoState == WeatherInfoState.LOADING && serviceState,
            modifier = Modifier.wrapContentSize(),
            exit = fadeOut(tween(100))
        ) {
            CircularProgressIndicator(modifier = Modifier.size(50.dp))
        }
        AnimatedVisibility(
            visible = !serviceState && weatherInfoState == WeatherInfoState.ERROR,
            enter = fadeIn(tween(400)),
            exit = fadeOut(tween(400))
        ) {
            WeatherUnavailable()
        }
    }
}

@Composable
private fun WeatherUnavailable() {
    Column {
        Spacer(modifier = Modifier.weight(1F))
        Text(text = "Weather service are currently unavailable")
        Spacer(modifier = Modifier.weight(1F))
    }
}