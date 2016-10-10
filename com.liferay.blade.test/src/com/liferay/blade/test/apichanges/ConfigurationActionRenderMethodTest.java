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

package com.liferay.blade.test.apichanges;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.liferay.blade.api.FileMigrator;
import com.liferay.blade.api.Problem;
import com.liferay.blade.upgrade.liferay70.apichanges.ConfigurationActionRenderMethod;

import java.io.File;
import java.util.List;

import org.junit.Test;
import org.osgi.framework.ServiceReference;

public class ConfigurationActionRenderMethodTest extends BaseAPIChangesTest {

	final File configurationActionImplFile = new File(
			"projects/opensocial-portlet-6.2.x/docroot/WEB-INF/src/com/liferay/opensocial/gadget/action/ConfigurationActionImpl.java");
	final File editConfigurationActionFile = new File("projects/filetests/EditConfigurationAction.java");

	@Test
	public void configurationActionImplFile() throws Exception {
		List<Problem> problems = null;

		for (ServiceReference<FileMigrator> fm : fileMigrators) {
			final FileMigrator fmigrator = context.getService(fm);

			if (fmigrator instanceof ConfigurationActionRenderMethod) {
				problems = fmigrator.analyze(configurationActionImplFile);
			}

			context.ungetService(fm);
		}

		assertNotNull(problems);
		assertEquals(1, problems.size());
	}

	@Test
	public void editConfigurationActionFile() throws Exception {
		List<Problem> problems = null;

		for (ServiceReference<FileMigrator> fm : fileMigrators) {
			final FileMigrator fmigrator = context.getService(fm);

			if (fmigrator instanceof ConfigurationActionRenderMethod) {
				problems = fmigrator.analyze(editConfigurationActionFile);
			}

			context.ungetService(fm);
		}

		assertNotNull(problems);
		assertEquals(1, problems.size());
	}

}
