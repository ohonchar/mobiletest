package com.sasha.api;

import com.sasha.utils.annotations.AppName;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@AppName(appName = "ApiDemos-debug")
public class AdbExecuteTest extends ApiBaseTest {

    @Test(groups = {"all_api"})
    public void disableOneAvailableWiFiConnectedDevice() {
        Map<String, JSONObject> mapWiFiConnected = retrieveDevicesByParameter("-v 'usb'");
        ArrayList<String> nameListOfWifiConnected = new ArrayList<>() {
            {
                mapWiFiConnected.forEach((x, y) -> add(x));
            }
        };
        log.info("[STEP]::Name list of WiFi connected devices: \n{}", Arrays.toString(nameListOfWifiConnected.toArray()));

        Map<String, JSONObject> mapUsbConnected = retrieveDevicesByParameter("usb");
        ArrayList<String> listOfUsbConnectedAndroidDevices = new ArrayList<>() {
            {
                mapUsbConnected.forEach((x, y) -> add(x));
            }
        };
        JSONObject androidIds = getAndroidIds(listOfUsbConnectedAndroidDevices);
        log.info("[STEP]::List of android Ids: \n{}", androidIds.toString(4));

        assertThat(nameListOfWifiConnected.size())
                .as("Amount of wifi connected devices should be more then 0")
                .isGreaterThan(0);

        String deviceToDisconnect = nameListOfWifiConnected.get(0);
        disconnectDevice(deviceToDisconnect);
        log.info("[STEP]::Disconnected device name: {}", deviceToDisconnect);

        Map<String, JSONObject> mapWiFiConnectedDevicesAfterDisconnect = retrieveDevicesByParameter("-v 'usb'");
        assertThat(mapWiFiConnectedDevicesAfterDisconnect)
                .as("List of connected devices should not contains device: ".concat(deviceToDisconnect))
                .doesNotContainKey(deviceToDisconnect);
        log.info("[TEST]::Disable one of the available devices that connected through WiFi");
    }
}
