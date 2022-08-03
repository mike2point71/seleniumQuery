/*
 * Copyright (c) 2017 seleniumQuery authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ht.mikewrig.seleniumquery.by.common.preparser;

import java.util.Map;

public class ArgumentMap {

    private Map<Integer, String> argsMap;

    ArgumentMap(Map<Integer, String> argumentMap) {
        this.argsMap = argumentMap;
    }

    public String get(String index) {
        return argsMap.get(Integer.valueOf(index));
    }

    public int size() {
        return argsMap.size();
    }

}
