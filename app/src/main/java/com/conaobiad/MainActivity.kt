package com.conaobiad

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.conaobiad.data.model.Obiad
import com.conaobiad.ui.AppContent



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Testowa lista obiadów
            val testObiady = listOf(
                Obiad(
                    alergeny = "1,3,7",
                    dataDnia = "2024-12-02",
                    dataTygodnia = "02 grudnia -06 grudnia",
                    dzien = "Poniedziałek",
                    zupa = "Barszcz ukraiński (250ml) Kluski śląskie z sosem pieczarkowym, marchewka gotowana (200g/100g)",
                    drugie_danie="aaa",
                    skladniki = "Buraki 50g, marchew 10g, seler 10g, ..."
                ),
                Obiad(
                    alergeny = "1,3,7,9",
                    dataDnia = "2024-12-03",
                    dataTygodnia = "02 grudnia -06 grudnia",
                    dzien = "Wtorek",
                    zupa = "Rosół z lanymi kluseczkami (250ml) Gyros z kurczaka, ryż, sałatka wiosenna (120g/180g/80g)",
                    drugie_danie = "aaa",
                    skladniki = "Udko z kurczaka 50g, marchew 20g, pietruszka 20g, ..."
                )
            )

            AppContent(context = this, obiady = testObiady)
        }
    }

}


