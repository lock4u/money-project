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
 * Converter for {@link org.money.sales.api.user.model.Role}.
 *
 * NOTE: This class has been automatically generated from the {@link org.money.sales.api.user.model.Role} original class using Vert.x codegen.
 */
public class RoleConverter {

  public static void fromJson(JsonObject json, Role obj) {
    if (json.getValue("create_time") instanceof Number) {
      obj.setCreate_time(((Number)json.getValue("create_time")).longValue());
    }
    if (json.getValue("id") instanceof Number) {
      obj.setId(((Number)json.getValue("id")).longValue());
    }
    if (json.getValue("role") instanceof String) {
      obj.setRole((String)json.getValue("role"));
    }
    if (json.getValue("uid") instanceof Number) {
      obj.setUid(((Number)json.getValue("uid")).longValue());
    }
    if (json.getValue("update_time") instanceof Number) {
      obj.setUpdate_time(((Number)json.getValue("update_time")).longValue());
    }
  }

  public static void toJson(Role obj, JsonObject json) {
    json.put("create_time", obj.getCreate_time());
    if (obj.getId() != null) {
      json.put("id", obj.getId());
    }
    if (obj.getRole() != null) {
      json.put("role", obj.getRole());
    }
    if (obj.getUid() != null) {
      json.put("uid", obj.getUid());
    }
    json.put("update_time", obj.getUpdate_time());
  }
}