package by.valtorn.democamerax.barcode

import android.content.Context
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.android.gms.tasks.Task
import com.google.mlkit.vision.barcode.Barcode
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage

/** Barcode Detector Demo.  */
class BarcodeScannerProcessor(context: Context, exchangeScannedData: ExchangeScannedData): ImageAnalysis.Analyzer {

    private var exchangeScannedData: ExchangeScannedData? = null

    private val barcodeScanner: BarcodeScanner = BarcodeScanning.getClient(
        BarcodeScannerOptions.Builder()
            .setBarcodeFormats(Barcode.FORMAT_QR_CODE, Barcode.FORMAT_CODE_39)
            .build()
    )

    fun stop() {
        barcodeScanner.close()
    }

    fun detectInImage(image: InputImage): Task<List<Barcode>> {
        return barcodeScanner.process(image)
    }

    fun onSuccess(barcodes: List<Barcode>) {
        for (i in barcodes.indices) {
            val barcode = barcodes[i]
            logExtrasForTesting(barcode)
        }
    }

    fun onFailure(e: Exception) {
        Log.e(TAG, "Barcode detection failed $e")
    }

    companion object {
        private const val TAG = "BarcodeProcessor"

        private fun logExtrasForTesting(barcode: Barcode?) {
            Log.e(TAG, "Barcode detection $barcode")
        }
    }

    override fun analyze(image: ImageProxy) {
        val mediaImage = image.image
        if (mediaImage != null) {
            val image = InputImage.fromMediaImage(mediaImage, image.imageInfo.rotationDegrees)
            val options = BarcodeScannerOptions.Builder()
                .setBarcodeFormats(
                    Barcode.FORMAT_QR_CODE,
                    Barcode.FORMAT_AZTEC)
                .build()
            val scanner = BarcodeScanning.getClient(options)
            // Pass image to an ML Kit Vision API
            // ...
        }
    }
}