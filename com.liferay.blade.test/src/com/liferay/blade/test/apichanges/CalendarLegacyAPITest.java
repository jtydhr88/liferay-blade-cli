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
import com.liferay.blade.test.Util;
import com.liferay.blade.upgrade.liferay70.apichanges.CalendarLegacyAPI;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

public class CalendarLegacyAPITest {
	final File testFile = new File(
			"projects/legacy-apis-ant-portlet/docroot/WEB-INF/src/com/liferay/CalendarPortlet.java");

	final BundleContext context = FrameworkUtil.getBundle(this.getClass()).getBundleContext();

	ServiceTracker<FileMigrator, FileMigrator> fileMigratorTracker;

	FileMigrator fileMigrator;

	ServiceReference<FileMigrator>[] fileMigrators;

	@Before
	public void beforeTest() {
		fileMigratorTracker = new ServiceTracker<FileMigrator, FileMigrator>(context, FileMigrator.class, null);

		fileMigratorTracker.open();

		fileMigrators = fileMigratorTracker.getServiceReferences();

		assertNotNull(fileMigrators);

		assertTrue(fileMigrators.length > 0);
	}

	@Test
	public void calendarLegacyAPITest() throws Exception {
		List<Problem> problems = null;

		for (ServiceReference<FileMigrator> fm : fileMigrators) {
			final FileMigrator fmigrator = context.getService(fm);

			if (fmigrator instanceof CalendarLegacyAPI) {
				problems = fmigrator.analyze(testFile);
			}

			context.ungetService(fm);
		}

		assertNotNull(problems);
		assertEquals(7, problems.size());

		Problem problem = problems.get(0);

		assertEquals(38, problem.lineNumber);

		if (Util.isWindows()) {
			assertEquals(1886, problem.startOffset);
			assertEquals(1946, problem.endOffset);
		} else {
			assertEquals(1849, problem.startOffset);
			assertEquals(1909, problem.endOffset);
		}

		problem = problems.get(1);

		assertEquals(39, problem.lineNumber);

		if (Util.isWindows()) {
			assertEquals(1956, problem.startOffset);
			assertEquals(2011, problem.endOffset);
		} else {
			assertEquals(1918, problem.startOffset);
			assertEquals(1973, problem.endOffset);
		}

		problem = problems.get(2);

		assertEquals(40, problem.lineNumber);

		if (Util.isWindows()) {
			assertEquals(2021, problem.startOffset);
			assertEquals(2074, problem.endOffset);
		} else {
			assertEquals(1982, problem.startOffset);
			assertEquals(2035, problem.endOffset);
		}

		problem = problems.get(3);

		assertEquals(159, problem.lineNumber);

		if (Util.isWindows()) {
			assertEquals(7006, problem.startOffset);
			assertEquals(7143, problem.endOffset);
		} else {
			assertEquals(6848, problem.startOffset);
			assertEquals(6983, problem.endOffset);
		}

		problem = problems.get(4);

		assertEquals(43, problem.lineNumber);

		if (Util.isWindows()) {
			assertEquals(2228, problem.startOffset);
			assertEquals(2276, problem.endOffset);
		} else {
			assertEquals(2186, problem.startOffset);
			assertEquals(2234, problem.endOffset);
		}

		problem = problems.get(5);

		assertEquals(41, problem.lineNumber);

		if (Util.isWindows()) {
			assertEquals(2084, problem.startOffset);
			assertEquals(2152, problem.endOffset);
		} else {
			assertEquals(2044, problem.startOffset);
			assertEquals(2112, problem.endOffset);
		}

		problem = problems.get(6);

		assertEquals(42, problem.lineNumber);

		if (Util.isWindows()) {
			assertEquals(2162, problem.startOffset);
			assertEquals(2218, problem.endOffset);
		} else {
			assertEquals(2121, problem.startOffset);
			assertEquals(2177, problem.endOffset);
		}
	}

}
