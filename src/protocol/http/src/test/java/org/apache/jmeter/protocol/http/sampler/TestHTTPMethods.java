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
 
 public class TestHTTPMethods {
     @Test
     public void testDifferentHttpMethods() throws Exception {
         // Test GET method
         HTTPSamplerBase getSampler = new HTTPNullSampler();
         getSampler.setProtocol("http");
         getSampler.setMethod(HTTPConstants.GET);
         getSampler.setPath("/index.html");
         getSampler.setDomain("example.com");
         getSampler.addArgument("param1", "value1");
         assertEquals("http://example.com/index.html?param1=value1", getSampler.getUrl().toString());
 
         // Test POST method
         HTTPSamplerBase postSampler = new HTTPNullSampler();
         postSampler.setProtocol("http");
         postSampler.setMethod(HTTPConstants.POST);
         postSampler.setPath("/index.html");
         postSampler.setDomain("example.com");
         postSampler.addArgument("param1", "value1");
         assertEquals("http://example.com/index.html", postSampler.getUrl().toString());
 
         // Test PUT method
         HTTPSamplerBase putSampler = new HTTPNullSampler();
         putSampler.setProtocol("http");
         putSampler.setMethod(HTTPConstants.PUT);
         putSampler.setPath("/index.html");
         putSampler.setDomain("example.com");
         putSampler.addArgument("param1", "value1");
         assertEquals("http://example.com/index.html", putSampler.getUrl().toString());
 
         // Test DELETE method
         HTTPSamplerBase deleteSampler = new HTTPNullSampler();
         deleteSampler.setProtocol("http");
         deleteSampler.setMethod(HTTPConstants.DELETE);
         deleteSampler.setPath("/index.html");
         deleteSampler.setDomain("example.com");
         assertEquals("http://example.com/index.html", deleteSampler.getUrl().toString());
     }
 }