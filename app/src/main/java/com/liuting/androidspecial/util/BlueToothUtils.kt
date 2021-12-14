package com.liuting.androidspecial.util

import android.bluetooth.BluetoothAdapter
import java.lang.Exception
import java.lang.reflect.Method


object BlueToothUtils {
    private val TAG="BlueToothUtils"
    /**
     * 开启蓝牙永久可见性
     */
    fun setDiscoverableTimeout() {
        val adapter = BluetoothAdapter.getDefaultAdapter()
        try {
            val setDiscoverableTimeout: Method =
                BluetoothAdapter::class.java.getMethod(
                    "setDiscoverableTimeout",
                    Int::class.javaPrimitiveType
                )
            setDiscoverableTimeout.isAccessible = true
            val setScanMode: Method = BluetoothAdapter::class.java.getMethod(
                "setScanMode",
                Int::class.javaPrimitiveType,
                Int::class.javaPrimitiveType
            )
            setScanMode.isAccessible = true
            setDiscoverableTimeout.invoke(adapter, 0)
            setScanMode.invoke(adapter, BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE, 0)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    /**
     * 关闭蓝牙永久可见性
     */
    fun closeDiscoverableTimeout() {
        val adapter = BluetoothAdapter.getDefaultAdapter()
        try {
            val setDiscoverableTimeout: Method =
                BluetoothAdapter::class.java.getMethod(
                    "setDiscoverableTimeout",
                    Int::class.javaPrimitiveType
                )
            setDiscoverableTimeout.isAccessible = true
            val setScanMode: Method = BluetoothAdapter::class.java.getMethod(
                "setScanMode",
                Int::class.javaPrimitiveType,
                Int::class.javaPrimitiveType
            )
            setScanMode.isAccessible = true
            setDiscoverableTimeout.invoke(adapter, 1)
            setScanMode.invoke(adapter, BluetoothAdapter.SCAN_MODE_CONNECTABLE, 1)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}