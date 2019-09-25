/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.liferay.extensions.languageserver.properties;

import java.io.File;

/**
 * @author Terry Jia
 */
public class LiferayWorkspaceGradleProperties extends PropertiesFile {

	public LiferayWorkspaceGradleProperties(File file) {
		super(file);
	}

	public boolean checkPossibleKeys() {
		return true;
	}

	@Override
	public String getStorageFileName() {
		return "/liferay-workspace-gradle.properties";
	}

	@Override
	public boolean match() {
		String fileName = getFile().getName();

		return fileName.equals("gradle.properties");
	}

}