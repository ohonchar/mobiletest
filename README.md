# mobiletest

This QA Automation framework able to run and test native and web mobile applications. 
Project has two main automation units it is ui and api. UI unit is responsible to create 
and run ui automation tests. API part responsible for the beck-end test running.

It is uses following technologies:

* Java 11
* Gradle v7.6
* TestNG v7
* Appium java client v8.3.0
* Appium environment v1.22.3
* Android Debug Bridge (adb) v1.0.41
* Logging (slf4j)
* Assertion lib (AsserJ v3.x)

## Getting Started

In general, this framework designed to run locally and remotely as well, but for now, for the current 
assignment purposed it can be run only on local unix OS.
To run this framework successful you need some additional prerequisites installed

### Prerequisites
What things you need to run this framework:

```
Some linux based OS
JDK 11
JAVA_HOME specified
Gradle v7.6 installed
Android Debug Bridge (adb) v1.0.41
ANDROID_HOME specified
Appium server
```

### Installing
After you finished to set up things above, you need to connect Android devices (With allowing access to phone data!).

 - For running Ui tests you have to have at least one Android device.
 - For running API test you have to have at least 2 Android devices where one of them via wifi and another via wifi and usb.
Should look like this:

```
➜  ~ adb devices -l                 
List of devices attached
52005535ea3865bb       device usb:2-3.1 product:a6ltexx model:SM_A600FN device:a6lte transport_id:46
192.168.50.162:5555    device product:beyond1ltexx model:SM_G973F device:beyond1 transport_id:50
192.168.50.42:5555     device product:a6ltexx model:SM_A600FN device:a6lte transport_id:47

```

### Running the tests
Run Appium server `appium`.
You should see something like this:
```
➜  ~ appium
[Appium] Welcome to Appium v1.22.3
[Appium] Appium REST http interface listener started on 0.0.0.0:4723
```
#### UI tests
To run UI tests better to be connected to the one device.
Command to run:
```
gradle clean localRunUi --info
```

#### API tests
To run API test you have to be connected at least 2 android devices. Where one of them connected via wifi
and another via wifi and usb
Command to run:
```
gradle clean localRunApi --info
```

## Flow
UI test suite has 2 tests that running on 2 different application files .apk. For the further convenient test design classes
you just put your app file into `resources/apps` and before test class specify annotation `@AppName` with the name of your app.

API test, not exactly the api that we use to have, like using `RestAssured` lib, this test basically use internal Android Debug Bridge
api via bash cli.

All test cases steps described in `TestCasesFlow.txt`