/*
 * Copyright (C) 2015-present, Ant Financial Services Group
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alipay.hulu.shared.node.utils;

import android.content.Intent;
import android.content.pm.ResolveInfo;

import com.alipay.hulu.common.application.LauncherApplication;
import com.alipay.hulu.common.tools.CmdTools;

public class AppUtil {
    /**
     * 通过adb启动应用
     * @param appPackage
     * @return
     */
    public static boolean startApp(String appPackage) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setPackage(appPackage);
        ResolveInfo resolveInfo = LauncherApplication.getContext().getPackageManager().resolveActivity(intent, 0);
        if (resolveInfo == null) {
            return false;
        }

        String targetActivity = resolveInfo.activityInfo.name;
        CmdTools.execAdbCmd("am start -n '" + appPackage + "/" + targetActivity + "'", 2000);
        return true;
    }

    /**
     * 强制停止APP
     * @param appPackage
     */
    public static void forceStopApp(String appPackage) {
        CmdTools.execAdbCmd("am force-stop " + appPackage, 2000);
    }

    /**
     * 清理App数据
     * @param appPackage
     */
    public static void clearAppData(String appPackage) {
        CmdTools.execAdbCmd("pm clear " + appPackage, 10 * 1000);
    }
}