package com.sa7.jobfiy.ui.Onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import com.sa7.jobfiy.R
import com.sa7.jobfiy.data.OnboardingPage
import com.sa7.jobfiy.model.OnboardingModel


@Composable
fun OnboardingScreen(onboardingPage: OnboardingPage, onNextClicked: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF3730A3))
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = onboardingPage.imageRes),
                contentDescription = "Onboarding Image",
                modifier = Modifier.size(250.dp)
            )

            Text(
                modifier = Modifier.padding(top = 32.dp),
                text = onboardingPage.title,
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = onboardingPage.description,
                color = Color.White,
                fontSize = 20.sp
            )
            Button(
                onClick = onNextClicked,
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .padding(top = 38.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(8.dp),
                elevation = ButtonDefaults.elevatedButtonElevation(4.dp)
            ) {
                Text(
                    text = "Next",
                    fontSize = 18.sp
                )
            }
        }

        Image(
            painter = painterResource(id = R.drawable.splashtop),
            contentDescription = "Top Right Splash",
            modifier = Modifier
                .align(Alignment.TopEnd)
        )

        Image(
            painter = painterResource(id = R.drawable.splashbottom),
            contentDescription = "Bottom Left Splash",
            modifier = Modifier
                .align(Alignment.BottomStart)

        )
    }
}


@Composable
fun OnboardingPager(onFinish: () -> Unit) {
    val onboardingModel = OnboardingModel()
    val onboardingPages = onboardingModel.onboardingPages
    val pagerState = rememberPagerState(pageCount = { onboardingPages.size })
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize()
            .fillMaxHeight()
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            OnboardingScreen(onboardingPage = onboardingPages[page]) {
                coroutineScope.launch {
                    if (pagerState.currentPage == onboardingPages.lastIndex) {
                        onFinish()
                    } else {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }

            }
        }

    }
}