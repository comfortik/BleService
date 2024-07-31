package com.example.bleservice.features.main.ui

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanFilter
import android.bluetooth.le.ScanResult
import android.bluetooth.le.ScanSettings
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bleservice.databinding.FragmentMainBinding
import com.example.bleservice.features.main.adapter.DeviceAdapter
import com.example.bleservice.features.main.presentation.MainViewModel
import com.example.bleservice.features.utlis.DataTransferState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(), DeviceAdapter.OnItemClickListener{

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: DeviceAdapter
    private var scanCallback: ScanCallback? = null

    private lateinit var bleAdapter: BluetoothAdapter
    private lateinit var  bluetoothManager: BluetoothManager

    companion object {
        private const val PERMISSION_REQUEST_CODE = 1001
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initObservers()
        initListeners()
    }

    override fun onItemClick(device: BluetoothDevice) {
        viewModel.stopScan()
        viewModel.connectDevice(device)
        (binding.recyclerView.adapter as? DeviceAdapter)?.notifyDataSetChanged()
    }
    fun initAdapter(){
        adapter = DeviceAdapter(this)
        binding.recyclerView.adapter = adapter

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

    }
    fun initObservers(){
        viewModel.devices.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        viewModel.connectState.observe(viewLifecycleOwner){
            binding.textView.text = "Connection state: ${it.name}"
        }
        viewModel.dataPacketState.observe(viewLifecycleOwner){
            binding.textView.text = "Sending data state: ${it}"
        }
    }
    fun initListeners(){
        binding.buttonStart.setOnClickListener {
            adapter.clearItems()
            viewModel.scanDevices()
        }
        binding.btnStopScan.setOnClickListener {
            viewModel.stopScan()
        }

        binding.buttonSendData.setOnClickListener {
            viewModel.sendData("Hello")
        }
    }


}
