package com.example.praktikum_pam_minggu4_yowen_daniel_124140120

import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.resources.painterResource
import praktikum_pam_minggu4_yowendaniel_124140120.shared.generated.resources.Res
import praktikum_pam_minggu4_yowendaniel_124140120.shared.generated.resources.yowen

private val ProfileGreen = Color(0xFF63B653)
private val DeepGreen = Color(0xFF37863E)
private val LightColors = lightColorScheme(
    primary = DeepGreen,
    onPrimary = Color.White,
    secondary = ProfileGreen,
    background = Color(0xFFF1F4F1),
    surface = Color.White,
    onSurface = Color(0xFF202B31),
    onBackground = Color(0xFF202B31),
)
private val DarkColors = darkColorScheme(
    primary = ProfileGreen,
    onPrimary = Color(0xFF142019),
    secondary = Color(0xFF8DD17E),
    background = Color(0xFF182228),
    surface = Color(0xFF26343C),
    onSurface = Color(0xFFE7ECEE),
    onBackground = Color(0xFFE7ECEE),
)

@Composable
fun App(profileViewModel: ProfileViewModel = viewModel()) {
    val state by profileViewModel.uiState.collectAsState()
    val colors = if (state.isDarkMode) DarkColors else LightColors

    MaterialTheme(colorScheme = colors) {
        Surface(modifier = Modifier.fillMaxSize(), color = colors.background) {
            ProfileScreen(
                state = state,
                onDarkModeChanged = profileViewModel::setDarkMode,
                onEditClicked = profileViewModel::startEditing,
                onCancelEdit = profileViewModel::cancelEditing,
                onSave = profileViewModel::saveProfile,
                onNameChanged = profileViewModel::updateDraftName,
                onBioChanged = profileViewModel::updateDraftBio,
            )
        }
    }
}

@Composable
private fun ProfileScreen(
    state: ProfileUiState,
    onDarkModeChanged: (Boolean) -> Unit,
    onEditClicked: () -> Unit,
    onCancelEdit: () -> Unit,
    onSave: () -> Unit,
    onNameChanged: (String) -> Unit,
    onBioChanged: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "MY PROFILE",
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(10.dp))
                    .background(ProfileGreen)
                    .padding(horizontal = 14.dp, vertical = 9.dp),
                color = Color(0xFF17221A),
                fontSize = 21.sp,
                fontWeight = FontWeight.Black,
            )
            Spacer(Modifier.width(10.dp))
            Text("Dark", color = MaterialTheme.colorScheme.onBackground, fontSize = 14.sp)
            Switch(
                checked = state.isDarkMode,
                onCheckedChange = onDarkModeChanged,
                modifier = Modifier.padding(start = 4.dp),
            )
        }

        Spacer(Modifier.height(28.dp))
        Box(
            modifier = Modifier
                .size(100.dp)
                .border(3.dp, ProfileGreen, CircleShape)
                .padding(3.dp)
                .clip(CircleShape),
        ) {
            Image(
                painter = painterResource(Res.drawable.yowen),
                contentDescription = "Foto profil Yowen Daniel",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
        }
        Spacer(Modifier.height(12.dp))
        Text(
            text = state.name,
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
        Text(
            text = "NIM: ${state.nim}",
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.72f),
            fontSize = 14.sp,
        )
        Spacer(Modifier.height(12.dp))
        Text(
            text = state.bio,
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.78f),
            fontSize = 14.sp,
            lineHeight = 21.sp,
            textAlign = TextAlign.Center,
        )
        Spacer(Modifier.height(20.dp))

        if (state.isEditing) {
            EditProfileForm(
                state = state,
                onNameChanged = onNameChanged,
                onBioChanged = onBioChanged,
            )
            Spacer(Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Button(
                    onClick = onCancelEdit,
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        contentColor = MaterialTheme.colorScheme.onSurface,
                    ),
                ) { Text("Cancel") }
                Button(onClick = onSave, modifier = Modifier.weight(1f)) { Text("Save") }
            }
        } else {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                ProfileInfoRow("Email", state.email)
                ProfileInfoRow("Phone", state.phone)
                ProfileInfoRow("Location", state.location)
            }
            Spacer(Modifier.height(22.dp))
            Button(
                onClick = onEditClicked,
                modifier = Modifier.fillMaxWidth().height(48.dp),
                shape = RoundedCornerShape(8.dp),
            ) {
                Text("Edit Profile", fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

@Composable
private fun EditProfileForm(
    state: ProfileUiState,
    onNameChanged: (String) -> Unit,
    onBioChanged: (String) -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        OutlinedTextField(
            value = state.draftName,
            onValueChange = onNameChanged,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Nama") },
            singleLine = true,
        )
        OutlinedTextField(
            value = state.draftBio,
            onValueChange = onBioChanged,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Bio") },
            minLines = 3,
        )
    }
}

@Composable
private fun ProfileInfoRow(label: String, value: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp, vertical = 13.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = label,
                modifier = Modifier.width(72.dp),
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold,
                fontSize = 13.sp,
            )
            Text(
                text = value,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 14.sp,
            )
        }
    }
}