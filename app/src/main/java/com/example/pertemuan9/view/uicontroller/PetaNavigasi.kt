package com.example.pertemuan9.view.uicontroller

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myroomsiswa.view.DetailSiswaScreen
import com.example.pertemuan9.view.EntrySiswaScreen
import com.example.pertemuan9.view.HomeScreen
import com.example.pertemuan9.view.route.DestinasiDetailSiswa
import com.example.pertemuan9.view.route.DestinasiDetailSiswa.itemIdArg
import com.example.pertemuan9.view.route.DestinasiHome
import com.example.pertemuan9.view.route.DestinasiEntry


// Perbaikan 1: Hapus tanda @ yang dobel
@Composable
fun SiswaApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier // Perbaikan 2: Tambahkan default value = Modifier
) {
    HostNavigasi(
        navController = navController,
        modifier = modifier // Teruskan modifier ke fungsi di bawahnya
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HostNavigasi(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = modifier // Perbaikan 3: Gunakan huruf kecil (parameter), JANGAN Modifier (object)
    ) {
        composable(DestinasiHome.route) {
            HomeScreen(
                navigateToItemEntry = { navController.navigate(DestinasiEntry.route) },
                navigateToItemUpdate = { // Perbaikan typo kecil: navigateToitemUpdate -> navigateToItemUpdate (camelCase)
                    navController.navigate("${DestinasiDetailSiswa.route}/$it")
                }
            )
        }
        composable(DestinasiEntry.route) {
            EntrySiswaScreen(navigateBack = { navController.popBackStack() })
        }
        composable(
            route = DestinasiDetailSiswa.routeWithArgs,
            arguments = listOf(
                // Perbaikan 4: Pastikan referensi itemIdArg lengkap
                navArgument(DestinasiDetailSiswa.itemIdArg) {
                    type = NavType.IntType
                }
            )
        ) {
            DetailSiswaScreen(
                navigateBack = { navController.navigateUp() }
            )
        }
    }
}