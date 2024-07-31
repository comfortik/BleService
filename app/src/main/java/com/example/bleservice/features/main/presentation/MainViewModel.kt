package com.example.bleservice.features.main.presentation

import android.bluetooth.BluetoothDevice
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bleservice.domain.interactor.dataPacket.DataPacketInteractors
import com.example.bleservice.domain.interactor.device.ScanDevicesInteractor
import com.example.bleservice.domain.interactor.device.ConnectDeviceInteractor
import com.example.bleservice.domain.interactor.device.DeviceInteractors
import com.example.bleservice.domain.interactor.device.DisconnectDeviceInteractor
import com.example.bleservice.domain.model.Error
import com.example.bleservice.domain.utils.ErrorListener
import com.example.bleservice.domain.utils.SuccessListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataPacketInteractors: DataPacketInteractors,
    private val deviceInteractors: DeviceInteractors
): ViewModel(){

    private val _devices = MutableLiveData<List<BluetoothDevice>>()
    val devices: LiveData<List<BluetoothDevice>> = _devices

    private val _connectState = MutableLiveData<MainState>()
    val connectState :LiveData<MainState> = _connectState

    val deviceList= HashMap<String, BluetoothDevice>()
    fun scanDevices() {
        deviceInteractors
            .scanDevicesInteractor()
            .execute { result ->
                deviceList[result.address] = result
            _devices.value = deviceList.map { it.value}
        }
    }
    fun stopScan(){
        deviceInteractors.stopScanInteractor().execute()
    }
    fun connectDevice(device: BluetoothDevice) {
        _connectState.value = MainState.LOADING
        deviceInteractors.connectDeviceInteractor().execute(
            device,
            object : SuccessListener<Boolean> {
                override fun onSuccess(result: Boolean) {
                    if(result){
                        _connectState.postValue(MainState.SUCCESS)
                    }else{
                        _connectState.postValue(MainState.ERROR)
                    }
                }
            },
            object :ErrorListener{
                override fun onError(error: Error) {
                    _connectState.value = MainState.ERROR
                }

            }
        )
    }

    fun disconnectDevice(device: BluetoothDevice) {
        deviceInteractors.disconnectDeviceInteractor().execute(device)
    }
}
