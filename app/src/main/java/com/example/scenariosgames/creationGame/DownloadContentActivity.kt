package com.example.scenariosgames.creationGame

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.activity.ComponentActivity


class DownloadContentActivity : ComponentActivity() {

    companion object {
        fun downloadGamesContent(context: Context, downloadUrl: String, fileName: String): Long {

            val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            val request = DownloadManager.Request(Uri.parse(downloadUrl))

            // Définir le titre de la notification de téléchargement
            request.setTitle("Téléchargement du jeu")

            // Définir la description de la notification de téléchargement
            request.setDescription("Téléchargement en cours")

            // Définir le nom du fichier de destination
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)

            // Autoriser la mise en veille pendant le téléchargement
            request.setAllowedOverMetered(true)
            request.setAllowedOverRoaming(true)

            // Enqueue the download and get the download ID
            val downloadId = downloadManager.enqueue(request)

            return downloadId
        }
    }
}
