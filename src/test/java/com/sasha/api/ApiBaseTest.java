package com.sasha.api;

import com.sasha.utils.adb.AdbUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class ApiBaseTest extends AdbUtils {
    protected static Logger log = LogManager.getRootLogger();
}
