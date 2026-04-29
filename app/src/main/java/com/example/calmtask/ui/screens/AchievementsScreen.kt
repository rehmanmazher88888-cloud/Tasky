package com.example.calmtask.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.calmtask.ui.theme.*

@Composable
fun AchievementsScreen(
    averageCompletion: Float,
    streak: Int,
    weeklyData: List<Float>, // last 7 days completion
    onShare: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp).statusBarsPadding()) {
        Text("Achievements", style = MaterialTheme.typography.headlineMedium, color = Color.White)
        Spacer(modifier = Modifier.height(24.dp))
        // Ring and streak
        Row(verticalAlignment = Alignment.CenterVertically) {
            com.example.calmtask.ui.components.ProgressRing(progress = averageCompletion, modifier = Modifier.size(80.dp))
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text("${(averageCompletion * 100).toInt()}% Average", color = Color.White, fontWeight = FontWeight.Bold)
                Text("$streak days in a row 🔥", color = Color.White)
                val message = when {
                    streak == 0 -> "Every day is a fresh start 🌱"
                    streak < 3 -> "You’re building momentum! 💪"
                    streak < 7 -> "One week strong! 🔥"
                    else -> "You’re unstoppable 🏆"
                }
                Text(message, color = TextSecondary)
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        Text("This Week", color = Color.White, fontWeight = FontWeight.Bold)
        // Simple bar chart
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            weeklyData.forEachIndexed { i, percent ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        Modifier
                            .width(20.dp)
                            .height(60.dp * percent)
                            .background(AccentDefault, shape = MaterialTheme.shapes.small)
                    )
                    Text("${(percent*100).toInt()}%", color = TextSecondary, fontSize = MaterialTheme.typography.labelMedium.fontSize)
                }
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onShare, colors = ButtonDefaults.buttonColors(containerColor = AccentDefault)) {
            Text("Share Achievements")
        }
    }
}
