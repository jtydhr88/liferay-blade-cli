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
import com.liferay.blade.test.Util;
import com.liferay.blade.upgrade.liferay70.apichanges.SocialNetworkingLegacyAPI;

import java.io.File;
import java.util.List;

import org.junit.Test;
import org.osgi.framework.ServiceReference;

public class SocialNetworkingLegacyAPITest extends BaseAPIChangesTest {
	final File testFile = new File(
			"projects/legacy-apis-ant-portlet/docroot/WEB-INF/src/com/liferay/GroupModelListener.java");
	final File testFile2 = new File(
			"projects/legacy-apis-ant-portlet/docroot/WEB-INF/src/com/liferay/MeetupsPortlet.java");

	@Test
	public void socialNetworkingLegacyAPITest() throws Exception {
		List<Problem> problems = null;

		for (ServiceReference<FileMigrator> fm : fileMigrators) {
			final FileMigrator fmigrator = context.getService(fm);

			if (fmigrator instanceof SocialNetworkingLegacyAPI) {
				problems = fmigrator.analyze(testFile);
			}

			context.ungetService(fm);
		}

		assertNotNull(problems);
		assertEquals(2, problems.size());

		Problem problem = problems.get(0);

		assertEquals(20, problem.lineNumber);

		if (Util.isWindows()) {
			assertEquals(781, problem.startOffset);
			assertEquals(843, problem.endOffset);
		} else {
			assertEquals(762, problem.startOffset);
			assertEquals(824, problem.endOffset);
		}

		problem = problems.get(1);

		assertEquals(31, problem.lineNumber);

		if (Util.isWindows()) {
			assertEquals(1086, problem.startOffset);
			assertEquals(1149, problem.endOffset);
		} else {
			assertEquals(1056, problem.startOffset);
			assertEquals(1119, problem.endOffset);
		}

		problems = null;

		for (ServiceReference<FileMigrator> fm : fileMigrators) {
			final FileMigrator fmigrator = context.getService(fm);

			if (fmigrator instanceof SocialNetworkingLegacyAPI) {
				problems = fmigrator.analyze(testFile2);
			}

			context.ungetService(fm);
		}

		assertNotNull(problems);
		assertEquals(6, problems.size());

		problem = problems.get(0);

		assertEquals(24, problem.lineNumber);

		if (Util.isWindows()) {
			assertEquals(1005, problem.startOffset);
			assertEquals(1070, problem.endOffset);
		} else {
			assertEquals(982, problem.startOffset);
			assertEquals(1047, problem.endOffset);
		}

		problem = problems.get(1);

		assertEquals(57, problem.lineNumber);

		if (Util.isWindows()) {
			assertEquals(1892, problem.startOffset);
			assertEquals(1955, problem.endOffset);
		} else {
			assertEquals(1836, problem.startOffset);
			assertEquals(1899, problem.endOffset);
		}

		problem = problems.get(2);

		assertEquals(128, problem.lineNumber);

		if (Util.isWindows()) {
			assertEquals(4271, problem.startOffset);
			assertEquals(4570, problem.endOffset);
		} else {
			assertEquals(4144, problem.startOffset);
			assertEquals(4439, problem.endOffset);
		}

		problem = problems.get(3);

		assertEquals(135, problem.lineNumber);

		if (Util.isWindows()) {
			assertEquals(4591, problem.startOffset);
			assertEquals(4914, problem.endOffset);
		} else {
			assertEquals(4457, problem.startOffset);
			assertEquals(4775, problem.endOffset);
		}

		problem = problems.get(4);

		assertEquals(25, problem.lineNumber);

		if (Util.isWindows()) {
			assertEquals(1080, problem.startOffset);
			assertEquals(1152, problem.endOffset);
		} else {
			assertEquals(1056, problem.startOffset);
			assertEquals(1128, problem.endOffset);
		}

		problem = problems.get(5);

		assertEquals(156, problem.lineNumber);

		if (Util.isWindows()) {
			assertEquals(5378, problem.startOffset);
			assertEquals(5504, problem.endOffset);
		} else {
			assertEquals(5223, problem.startOffset);
			assertEquals(5348, problem.endOffset);
		}
	}

}
