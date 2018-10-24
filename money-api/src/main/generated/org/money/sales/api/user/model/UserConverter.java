/*
 * Copyright (c) 2014 Red Hat, Inc. and others
 *
 * Red Hat licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package org.money.sales.api.user.model;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;

/**
 * Converter for {@link org.money.sales.api.user.model.User}.
 *
 * NOTE: This class has been automatically generated from the {@link org.money.sales.api.user.model.User} original class using Vert.x codegen.
 */
public class UserConverter {

  public static void fromJson(JsonObject json, User obj) {
    if (json.getValue("id") instanceof String) {
      obj.setId((String)json.getValue("id"));
    }
    if (json.getValue("name") instanceof String) {
      obj.setName((String)json.getValue("name"));
    }
    if (json.getValue("password") instanceof String) {
      obj.setPassword((String)json.getValue("password"));
    }
  }

  public static void toJson(User obj, JsonObject json) {
    if (obj.getId() != null) {
      json.put("id", obj.getId());
    }
    if (obj.getName() != null) {
      json.put("name", obj.getName());
    }
    if (obj.getPassword() != null) {
      json.put("password", obj.getPassword());
    }
  }
}