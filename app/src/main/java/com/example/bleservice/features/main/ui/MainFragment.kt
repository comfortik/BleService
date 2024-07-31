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
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bleservice.databinding.FragmentMainBinding
import com.example.bleservice.features.main.adapter.DeviceAdapter
import com.example.bleservice.features.main.presentation.MainViewModel
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
        adapter = DeviceAdapter(this)
        binding.recyclerView.adapter = adapter

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {

            }
        }


        viewModel.devices.observe(viewLifecycleOwner) {

            adapter.submitList(it)
        }
        viewModel.connectState.observe(viewLifecycleOwner){
            binding.textView.text = it.name
        }

        binding.buttonStart.setOnClickListener {

                viewModel.scanDevices()


        }
        binding.btnStopScan.setOnClickListener {
            viewModel.stopScan()
        }

    }

    override fun onItemClick(device: BluetoothDevice) {
        viewModel.connectDevice(device)
    }


}
