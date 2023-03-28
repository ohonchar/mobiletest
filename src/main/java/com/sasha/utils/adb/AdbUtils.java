package com.sasha.utils.adb;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AdbUtils {
    /**
     * Method make cli request to disconnect one of the connected devices
     * @param deviceName -> specify connected device name
     */
    public void disconnectDevice(String deviceName) {
        String adbCommand = String.format("adb disconnect %s", deviceName);
        try {
            Runtime.getRuntime().exec(new String[]{"bash", "-c", adbCommand});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method executes direct shell command to retrieve android_id.
     * This method available only for Android devices
     * @param androidDevicesNames -> list of Android device names
     * @return -> json object where key->deviceName value->android_id
     */
    public JSONObject getAndroidIds(ArrayList<String> androidDevicesNames) {
        String adbCommand = "adb -s %s shell content query --uri content://settings/secure " +
                "--projection value --where \"name=\\'android_id\\'\"";
        JSONObject jsonObject = new JSONObject();
        for (String deviceId : androidDevicesNames) {
            String line;
            try {
                var process = Runtime.getRuntime().exec(new String[]{"bash", "-c", String.format(adbCommand, deviceId)});
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

                while ((line = reader.readLine()) != null) {
                    jsonObject.put(deviceId, line.split("=")[1]);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return jsonObject;
    }

    /**
     * Method retrieves devices by parameter. This method implements algorithm that splitting each line of the
     * device list that contains device data and generate json objects
     * All json objects puts to the map where keys are device name
     * @param parameter -> bash parameter
     * @return -> map key->deviceName value->jsonObject
     */
    public Map<String, JSONObject> retrieveDevicesByParameter(String parameter) {
        String adbCommand = String.format("adb devices -l | grep %s", parameter);
        Map<String, JSONObject> map = new HashMap<>();
        JSONObject jsonObject;
        String line;
        try {
            var process = Runtime.getRuntime().exec(new String[]{"bash", "-c", adbCommand});
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            while ((line = reader.readLine()) != null) {
                if (line.contains("model:")) {
                    String[] tempLineArray = line.split(" ");
                    String deviceId = tempLineArray[0];
                    jsonObject = new JSONObject();
                    for (int i = 1; i < tempLineArray.length; i++) {
                        if (!Objects.equals(tempLineArray[i], "") && tempLineArray[i].contains(":")) {
                            String[] tempItemArray = tempLineArray[i].split(":");
                            jsonObject.put(tempItemArray[0], tempItemArray[1]);
                        }
                    }
                    map.put(deviceId, jsonObject);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return map;
    }
}
