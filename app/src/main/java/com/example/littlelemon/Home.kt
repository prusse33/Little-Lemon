package com.example.littlelemon

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

data class Dish(val name: String, val description: String, val price: String, val category: String)

val menu = listOf(
    Dish("Greek Salad", "Crispy lettuce, peppers, olives and feta cheese.", "$12.99", "Starters"),
    Dish("Bruschetta", "Grilled bread with garlic, tomato and olive oil.", "$7.99", "Starters"),
    Dish("Grilled Fish", "Fresh fish grilled with lemon and herbs.", "$19.99", "Mains"),
    Dish("Pasta", "Penne with creamy parmesan sauce.", "$18.99", "Mains"),
    Dish("Lemon Dessert", "Traditional homemade lemon cake.", "$6.99", "Desserts"),
    Dish("Lemonade", "Fresh squeezed lemonade with mint.", "$4.99", "Drinks"),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(nav: NavHostController) {
    var search by remember { mutableStateOf("") }
    var category by remember { mutableStateOf<String?>(null) }
    val items = menu.filter {
        it.name.contains(search, ignoreCase = true) && (category == null || it.category == category)
    }

    Column(Modifier.fillMaxSize()) {
        // Header
        Row(Modifier.fillMaxWidth().padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            Spacer(Modifier.weight(1f))
            Text("LITTLE LEMON", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.weight(1f))
            Box(Modifier.size(40.dp).clip(CircleShape).background(Color.LightGray)
                .clickable { nav.navigate(Profile.route) }, Alignment.Center) { Text("P") }
        }
        // Hero
        Column(Modifier.fillMaxWidth().background(LLGreen).padding(16.dp)) {
            Text("Little Lemon", color = LLYellow, fontSize = 36.sp, fontWeight = FontWeight.Bold)
            Text("Chicago", color = Color.White, fontSize = 22.sp)
            Spacer(Modifier.height(8.dp))
            Text("We are a family owned Mediterranean restaurant, focused on traditional recipes served with a modern twist.",
                color = Color.White, fontSize = 15.sp)
            Spacer(Modifier.height(12.dp))
            OutlinedTextField(search, { search = it }, placeholder = { Text("Enter search phrase") },
                modifier = Modifier.fillMaxWidth().background(Color.White, RoundedCornerShape(8.dp)), singleLine = true)
        }
        // Menu breakdown
        Column(Modifier.padding(16.dp)) {
            Text("ORDER FOR DELIVERY!", fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf("Starters", "Mains", "Desserts", "Drinks").forEach { c ->
                    FilterChip(selected = category == c, onClick = { category = if (category == c) null else c },
                        label = { Text(c, fontSize = 12.sp) })
                }
            }
        }
        // Food menu list
        LazyColumn(Modifier.fillMaxSize().padding(horizontal = 16.dp)) {
            items(items) { dish ->
                Column(Modifier.padding(vertical = 8.dp)) {
                    Text(dish.name, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Text(dish.description, fontSize = 14.sp, color = Color.DarkGray)
                    Text(dish.price, fontWeight = FontWeight.Medium)
                }
                HorizontalDivider()
            }
        }
    }
}
