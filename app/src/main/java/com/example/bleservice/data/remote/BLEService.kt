package com.example.bleservice.data.remote

import android.Manifest
import android.app.Service
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCallback
import android.bluetooth.BluetoothGattCharacteristic
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothProfile
import android.bluetooth.BluetoothSocket
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanFilter
import android.bluetooth.le.ScanResult
import android.bluetooth.le.ScanSettings
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Binder
import android.os.IBinder
import android.util.Log
import androidx.core.app.ActivityCompat
import dagger.hilt.android.AndroidEntryPoint
import java.util.UUID
import javax.inject.Inject


class BLEService : Service() {

    private val binder = LocalBinder()
    private var scanCallback: ScanCallback? = null
    private lateinit var adapter:BluetoothAdapter
    private lateinit var  bluetoothManager:BluetoothManager
    private var gatt: BluetoothGatt? = null

    inner class LocalBinder : Binder() {
        fun getService(): BLEService = this@BLEService
    }

    override fun onCreate() {
        super.onCreate()
        bluetoothManager = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        Log.d("BLES", "Bluetooth initialized: ")
    }


    override fun onBind(intent: Intent): IBinder {
        Log.d("BLES", "Service bind")
        return binder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("BLES", "Service started")
        return super.onStartCommand(intent, flags, startId)
    }

    fun scanForDevices(callback: (BluetoothDevice) -> Unit) {

        adapter = BluetoothAdapter.getDefaultAdapter()
        if (!adapter.isEnabled) {
            Log.e("BLES", "Bluetooth is not enabled")
            return
        }
        scanCallback = object : ScanCallback() {
            override fun onScanResult(callbackType: Int, result: ScanResult) {
                super.onScanResult(callbackType, result)
//                if(!result.device.name.isNullOrBlank())
                    callback(result.device)
            }

            override fun onBatchScanResults(results: List<ScanResult>) {
                super.onBatchScanResults(results)

            }

            override fun onScanFailed(errorCode: Int) {
                super.onScanFailed(errorCode)
                Log.e("BLES", "Scan failed with error code: $errorCode")
            }
        }



        adapter.bluetoothLeScanner.startScan(scanCallback)
        Log.d("BLES", "Scanning started")
    }

    fun stopScanning() {
        Log.d("BLEService", "Scan stopped")
            adapter.bluetoothLeScanner.stopScan(scanCallback)


    }
    fun connectDevice(device: BluetoothDevice) {
        gatt = device.connectGatt(this, false, gattCallback)
    }

    private val gattCallback = object : BluetoothGattCallback() {
        override fun onConnectionStateChange(gatt: BluetoothGatt, status: Int, newState: Int) {
            if (newState == BluetoothProfile.STATE_CONNECTED) {
                Log.d("BLEService", "Connected to GATT server")
                gatt.discoverServices()
            } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                Log.d("BLEService", "Disconnected from GATT server")
            }
        }

        override fun onServicesDiscovered(gatt: BluetoothGatt, status: Int) {
            if (status == BluetoothGatt.GATT_SUCCESS) {
                Log.d("BLEService", "GATT services discovered")
            } else {
                Log.w("BLEService", "onServicesDiscovered received: $status")
            }
        }

        override fun onCharacteristicRead(gatt: BluetoothGatt, characteristic: BluetoothGattCharacteristic, status: Int) {
            if (status == BluetoothGatt.GATT_SUCCESS) {
                Log.d("BLEService", "Characteristic read: ${characteristic.value}")
            }
        }

        override fun onCharacteristicWrite(gatt: BluetoothGatt, characteristic: BluetoothGattCharacteristic, status: Int) {
            if (status == BluetoothGatt.GATT_SUCCESS) {
                Log.d("BLEService", "Characteristic written: ${characteristic.value}")
            }
        }

        override fun onCharacteristicChanged(gatt: BluetoothGatt, characteristic: BluetoothGattCharacteristic) {
            Log.d("BLEService", "Characteristic changed: ${characteristic.value}")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        gatt?.close()
        stopScanning()
        Log.d("BLEService", "Service destroyed")
    }


}
