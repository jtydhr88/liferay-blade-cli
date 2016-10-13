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
import static org.junit.Assert.assertTrue;

import com.liferay.blade.api.FileMigrator;
import com.liferay.blade.api.Problem;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Filter;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

public class ActionCommandImportsTest {

	final File sayHelloActionCommandFile = new File(
			"projects/actioncommand-demo-portlet/docroot/WEB-INF/src/com/liferay/demo/portlet/action/SayHelloActionCommand.java");
	final File sayHelloActionCommandFile2 = new File(
			"projects/actioncommand-demo-portlet/docroot/WEB-INF/src/com/liferay/demo/portlet/action/SayHelloActionCommand2.java");

	final BundleContext context = FrameworkUtil.getBundle(this.getClass()).getBundleContext();

	ServiceReference<FileMigrator>[] fileMigrators;

	@Before
	public void beforeTest() throws Exception {
		Filter filter = context.createFilter("(implName=MVCPortletActionCommandImports)");

		ServiceTracker<FileMigrator, FileMigrator> fileMigratorTracker = new ServiceTracker<FileMigrator, FileMigrator>(context, filter, null);

		fileMigratorTracker.open();

		fileMigrators = fileMigratorTracker.getServiceReferences();

		assertNotNull(fileMigrators);

		assertTrue(fileMigrators.length > 0);
	}

	@Test
	public void sayHelloActionCommandFile() throws Exception {
		FileMigrator fmigrator = context.getService(fileMigrators[0]);

		List<Problem> problems = fmigrator.analyze(sayHelloActionCommandFile);

		context.ungetService(fileMigrators[0]);

		assertNotNull(problems);
		assertEquals(1, problems.size());
	}

	@Test
	public void sayHelloActionCommandFile2() throws Exception {
		FileMigrator fmigrator = context.getService(fileMigrators[0]);

		List<Problem> problems = fmigrator.analyze(sayHelloActionCommandFile2);

		context.ungetService(fileMigrators[0]);

		assertNotNull(problems);
		assertEquals(2, problems.size());
	}

	@Test
	public void sayHelloActionCommandFile2x() throws Exception {
		FileMigrator fmigrator = context.getService(fileMigrators[0]);

		List<Problem> problems = fmigrator.analyze(sayHelloActionCommandFile);

		problems = fmigrator.analyze(sayHelloActionCommandFile);

		context.ungetService(fileMigrators[0]);

		assertNotNull(problems);
		assertEquals(1, problems.size());
	}

}
