package com.stolbov.emptyprojectstolbov

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.stolbov.emptyprojectstolbov.databinding.ActivityActionBinding
import android.Manifest.permission.CAMERA as CAMERA_PERMISSION

class ActionActivity : AppCompatActivity() {
    private val CAMERA_INTENT = "android.media.action.IMAGE_CAPTURE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityActionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonOpenCam.setOnClickListener {
            val permission = CAMERA_PERMISSION
            handleCheckResult(permission, checkPermission(permission))
        }
    }

    private fun checkPermission(permission: String): CheckPermissionResult{
        return when {
            Build.VERSION.SDK_INT < Build.VERSION_CODES.M -> CheckPermissionResult.GRANTED
            ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED ->
                CheckPermissionResult.GRANTED
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && shouldShowRequestPermissionRationale(permission) ->
                CheckPermissionResult.NEED_TO_EXPLAIN
            else -> CheckPermissionResult.NEED_TO_REQUEST
        }
    }

    private fun handleCheckResult(permission: String, result: CheckPermissionResult) {
        when (result) {
            CheckPermissionResult.GRANTED -> startCameraActivity()
            CheckPermissionResult.DENIED -> failedGracefully()
            CheckPermissionResult.NEED_TO_REQUEST -> askForPermission(permission)
            CheckPermissionResult.NEED_TO_EXPLAIN -> showRationale()
        }
    }

    private fun showRationale() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.camera_permission_text))
            .setMessage(getString(R.string.permission_message))
            .setPositiveButton(getString(R.string.ok)) {_, _, -> askForPermission(CAMERA_PERMISSION)}
            .show()
    }

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){
        isGranted:Boolean ->
        if (isGranted){
            startCameraActivity()
        } else {
            failedGracefully()
        }
    }

    private fun askForPermission(permission: String) {
        requestPermissionLauncher.launch(permission)
    }

    private fun failedGracefully() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.camera_permission_text))
            .setMessage(getString(R.string.reject_text))
            .setPositiveButton(getString(R.string.change_mind_text)) { _, _, -> askForPermission(CAMERA_PERMISSION)}
            .setNegativeButton(getString(R.string.ok), null)
            .show()
    }

    private fun startCameraActivity() {
        startActivity(Intent(CAMERA_INTENT))
    }

    enum class CheckPermissionResult{
        GRANTED,
        DENIED,
        NEED_TO_REQUEST,
        NEED_TO_EXPLAIN
    }
}