package cleancode.ui.view.camera

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.nygar.designsystem.theme.CleanArchitectureKotlinTheme

class CameraActivity : ComponentActivity() {
    // private var imageCapture: ImageCapture? = null
    // private var videoCapture: VideoCapture<Recorder>? = null
    private var bitmapToShow by mutableStateOf<Bitmap?>(null)

    private var permissionGranted by mutableStateOf(false)
    private val cameraPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission(),
        ) {
            permissionGranted = true
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Ask for camera permissions
        permissionGranted = checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED

        if (!permissionGranted) {
            cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
        }

        setContent {
            CleanArchitectureKotlinTheme {
                val lifecycleOwner = LocalLifecycleOwner.current
                val cameraCurrentSelector = CameraSelector.DEFAULT_BACK_CAMERA

                val cameraController =
                    remember {
                        LifecycleCameraController(this).apply {
                            bindToLifecycle(lifecycleOwner)
                            cameraSelector = cameraCurrentSelector
                        }
                    }

                Box(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    AndroidView(
                        modifier = Modifier.fillMaxSize(),
                        factory = { ctx ->
                            val preview =
                                PreviewView(ctx).apply {
                                    layoutParams =
                                        ViewGroup.LayoutParams(
                                            ViewGroup.LayoutParams.MATCH_PARENT,
                                            ViewGroup.LayoutParams.MATCH_PARENT,
                                        )
                                    scaleType = PreviewView.ScaleType.FILL_CENTER
                                    implementationMode = PreviewView.ImplementationMode.COMPATIBLE
                                    controller = cameraController
                                }

                            cameraController.setImageAnalysisAnalyzer(
                                ContextCompat.getMainExecutor(ctx),
                            ) { imageProxy ->
                                val greyBitmap = toGrayScale(imageProxy.toBitmap())
                                bitmapToShow = greyBitmap
                                imageProxy.close()
                            }
                            preview
                        },
                        onRelease = {
                            cameraController.unbind()
                        },
                    )

                    bitmapToShow?.let {
                        Image(
                            modifier = Modifier.fillMaxSize(),
                            bitmap = it.asImageBitmap(),
                            contentDescription = null,
                        )
                    }
                }
            }
        }
    }
}

fun toGrayScale(inputBitmap: Bitmap): Bitmap {
    val height = inputBitmap.height
    val width = inputBitmap.width

    val bmpGrayscale = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val c = Canvas(bmpGrayscale)
    val paint = Paint()
    val cm = ColorMatrix()
    cm.setToSaturation(0f)
    val f = androidx.compose.ui.graphics.ColorMatrixColorFilter(cm)
    paint.colorFilter = f
    c.drawBitmap(inputBitmap, 0f, 0f, paint.asFrameworkPaint())
    return bmpGrayscale
}
