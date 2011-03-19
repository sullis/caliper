/*
 * Copyright (C) 2011 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.caliper.runner;

import java.io.PrintWriter;

public class DefaultConsoleWriter implements ConsoleWriter {
  private final PrintWriter writer;

  public DefaultConsoleWriter(PrintWriter writer) {
    this.writer = writer;
  }

  @Override public void flush() {
    writer.flush();
  }

  @Override public void describe(ScenarioSelection selection) {
    writer.println("Scenario selection: ");
    writer.println("  Benchmark methods: " + selection.benchmarkMethods());
    writer.println("  User parameters:   " + selection.userParameters());
    writer.println("  Virtual machines:  " + selection.vms());
    writer.println("  VM parameters:     " + selection.vmArguments());
    writer.println("  Selection type:    " + selection.selectionType());
    writer.println();
  }

  @Override public void beforeDryRun(int scenarioCount) {
    writer.format("This selection yields %s scenarios.%n", scenarioCount);
  }

  @Override public void beforeRun(int trials, int scenarioCount, int estimatedSeconds) {
    writer.format("Measuring %s trials each of %s scenarios. ", trials, scenarioCount);
    if (estimatedSeconds > 0) {
      writer.format("Estimated runtime: %s minutes.%n", (estimatedSeconds + 59) / 60);
    } else {
      writer.println("(Cannot estimate runtime.)");
    }
  }
}