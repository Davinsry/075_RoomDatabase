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
import com.example.pertemuan9.view.route.DestinasiHome
import com.example.pertemuan9.view.route.DestinasiEntry


@Composable
fun Siswa_App(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = modifier
    ) {
        // Halaman Home
        composable(route = DestinasiHome.route) {
            HomeScreen(
                navigateToItemEntry = { navController.navigate(DestinasiEntry.route) },
                // Tambahkan koma di atas ^
                navigateToItemUpdate = {
                    navController.navigate("${DestinasiDetailSiswa.route}/$it")
                }
            )
        }

        // Halaman Entry
        composable(route = DestinasiEntry.route) {
            EntrySiswaScreen(navigateBack = { navController.popBackStack() })
        }

        // Halaman Detail
        composable(
            route = DestinasiDetailSiswa.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiDetailSiswa.itemIdArg) { // Hapus titik (.) sebelum navArgument
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HostNavigasi(
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    NavHost(navController=navController, startDestination = DestinasiHome.route, modifier = Modifier)
    {
        composable(DestinasiHome.route){
            HomeScreen(
                navigateToItemEntry = {navController.navigate(DestinasiEntry.route)},
            )
        }
        composable(DestinasiEntry.route){
            EntrySiswaScreen(navigateBack = { navController.popBackStack()})
        }

    }
}