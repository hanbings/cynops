/*
 * Copyright (c) 2021 Hanbings / hanbings Cynops Toolbox.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *           http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.hanbings.cynops.io;

import java.io.File;
import java.io.IOException;

@SuppressWarnings("unused")
public class FileUtils {

    /**
     * 初始化一个文件，包括创建它的父文件夹与创建它本身。
     * @param file 要初始化的文件。
     * @return 初始化成功则返回传入的参数，否则返回 {@code null}。
     */
    public static File file(File file) {
        if (!file.exists()) {
            folder(file.getParentFile());
            try {
                if (!file.createNewFile()) {
                    return null;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return file;
    }

    /**
     * 初始化一个文件夹，包括创建它的父文件夹与创建它本身。
     * @param folder 要初始化的文件夹。
     * @return 初始化成功则返回传入的参数，否则返回 {@code null}。
     */
    public static File folder(File folder) {
        if (!folder.exists()) {
            if (folder.isFile() || !folder.mkdirs()) {
                return null;
            }
        }
        return folder;
    }

}
