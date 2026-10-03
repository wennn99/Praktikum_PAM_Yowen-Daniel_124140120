package com.example.praktikum3pam

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import praktikum3pam.shared.generated.resources.Res
import praktikum3pam.shared.generated.resources.compose_multiplatform
import praktikum3pam.shared.generated.resources.yowen

@Composable
@Preview
fun App() {
    MaterialTheme {
        ProfileScreen(
            name = "Yowen Daniel",
            nim = "124140120",
            bio = "Mahasiswa semester 5 Teknik Informatika yang berkuliah di Institut Teknologi Sumatera.",
            email = "yowen.124140120@student.itera.ac.id",
            phone = "089661511300",
            location = "Lampung Selatan",
        )
    }
}

@Composable
fun ProfileScreen(
    name: String,
    nim: String,
    bio: String,
    email: String,
    phone: String,
    location: String,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2F3B45))
            .padding(horizontal = 20.dp, vertical = 18.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ProfileHeader(
            name = name,
            nim = nim,
            bio = bio,
        )

        Spacer(modifier = Modifier.height(18.dp))

        ProfileInfoItem(label = "Email", value = email)
        ProfileInfoItem(label = "Phone", value = phone)
        ProfileInfoItem(label = "Location", value = location)

        Spacer(modifier = Modifier.height(18.dp))

        ProfileActionButton(label = "button")
    }
}

@Composable
fun ProfileHeader(
    name: String,
    nim: String,
    bio: String,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "MY PROFILE",
                color = Color(0xFF1F1F1F),
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF4CAF50), shape = RoundedCornerShape(8.dp))
        )

        Spacer(modifier = Modifier.height(18.dp))

        Image(
            painter = painterResource(Res.drawable.yowen),
            contentDescription = "Foto profil",
            modifier = Modifier
                .size(110.dp)
                .clip(CircleShape)
                .border(3.dp, Color(0xFF4CAF50), CircleShape),
            contentScale = ContentScale.Crop,
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = name,
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
        )

        Text(
            text = "NIM: $nim",
            color = Color(0xFFE0E0E0),
            fontSize = 14.sp,
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = bio,
            color = Color(0xFFD7D7D7),
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
fun ProfileInfoItem(label: String, value: String) {
    Text(
        text = "$label: $value",
        color = Color(0xFFE9E9E9),
        fontSize = 15.sp,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF4A5C65), RoundedCornerShape(10.dp))
            .padding(horizontal = 16.dp, vertical = 10.dp),
    )
}

@Composable
fun ProfileActionButton(label: String) {
    Button(
        onClick = { },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF4CAF50),
            contentColor = Color.White,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        shape = RoundedCornerShape(0.dp),
    ) {
        Text(text = label)
    }
}