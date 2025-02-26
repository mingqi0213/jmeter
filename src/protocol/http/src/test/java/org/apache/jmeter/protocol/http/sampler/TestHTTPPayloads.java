/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to you under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

 package org.apache.jmeter.protocol.http.sampler;

 import static org.junit.jupiter.api.Assertions.assertEquals;
 import org.apache.jmeter.protocol.http.util.HTTPConstants;
 import org.junit.jupiter.api.Test;
 
 public class TestHTTPPayloads {
     @Test
     public void testPayloadSizes() throws Exception {
         // Empty payload
         HTTPSamplerBase emptySampler = new HTTPNullSampler();
         emptySampler.setMethod(HTTPConstants.POST);
         emptySampler.setPath("/upload");
         emptySampler.setDomain("example.com");
         assertEquals("http://example.com/upload", emptySampler.getUrl().toString());
 
         // Small payload (5 KB)
         HTTPSamplerBase smallSampler = new HTTPNullSampler();
         smallSampler.setMethod(HTTPConstants.POST);
         smallSampler.setPath("/upload");
         smallSampler.setDomain("example.com");
         String smallData = "a".repeat(5 * 1024);
         smallSampler.addNonEncodedArgument("data", smallData, "");
         assertEquals(5 * 1024, smallData.length());
 
         // Large payload (2 MB)
         HTTPSamplerBase largeSampler = new HTTPNullSampler();
         largeSampler.setMethod(HTTPConstants.POST);
         largeSampler.setPath("/upload");
         largeSampler.setDomain("example.com");
         String largeData = "a".repeat(2 * 1024 * 1024);
         largeSampler.addNonEncodedArgument("data", largeData, "");
         assertEquals(2 * 1024 * 1024, largeData.length());
     }
 }
 