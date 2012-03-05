/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.mahout.knn.generate;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

public class EmpiricalTest {
  @Test
  public void testSimpleDist() {
    Empirical z = new Empirical(true, true, 3, 0, 1, 0.5, 2, 1, 3.0);
    List<Double> r = Lists.newArrayList();
    for (int i = 0; i < 10001; i++) {
      r.add(z.sample());
    }
    Collections.sort(r);
    Assert.assertEquals(2.0, r.get(5000), 3e-2);
  }

  @Test
  public void testBadArguments() {
    try {
      new Empirical(true, false, 20, 0, 1, 0.5, 2, 0.9, 9, 0.99, 10.0);
      Assert.fail("Should have caught that");
    } catch (IllegalArgumentException e) {
    }
    try {
      new Empirical(false, true, 20, 0.1, 1, 0.5, 2, 0.9, 9, 1, 10.0);
      Assert.fail("Should have caught that");
    } catch (IllegalArgumentException e) {
    }
    try {
      new Empirical(true, true, 20, -0.1, 1, 0.5, 2, 0.9, 9, 1, 10.0);
      Assert.fail("Should have caught that");
    } catch (IllegalArgumentException e) {
    }
    try {
      new Empirical(true, true, 20, 0, 1, 0.5, 2, 0.9, 9, 1.2, 10.0);
      Assert.fail("Should have caught that");
    } catch (IllegalArgumentException e) {
    }
    try {
      new Empirical(true, true, 20, 0, 1, 0.5, 2, 0.4, 9, 1, 10.0);
      Assert.fail("Should have caught that");
    } catch (IllegalArgumentException e) {
    }
  }
}