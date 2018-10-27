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
 * Converter for {@link org.money.sales.api.user.model.Customer}.
 *
 * NOTE: This class has been automatically generated from the {@link org.money.sales.api.user.model.Customer} original class using Vert.x codegen.
 */
public class CustomerConverter {

  public static void fromJson(JsonObject json, Customer obj) {
    if (json.getValue("create_time") instanceof Number) {
      obj.setCreate_time(((Number)json.getValue("create_time")).longValue());
    }
    if (json.getValue("id") instanceof Number) {
      obj.setId(((Number)json.getValue("id")).longValue());
    }
    if (json.getValue("marriage") instanceof String) {
      obj.setMarriage(org.money.sales.api.user.model.Marriage.valueOf((String)json.getValue("marriage")));
    }
    if (json.getValue("name") instanceof String) {
      obj.setName((String)json.getValue("name"));
    }
    if (json.getValue("phone") instanceof String) {
      obj.setPhone((String)json.getValue("phone"));
    }
    if (json.getValue("update_time") instanceof Number) {
      obj.setUpdate_time(((Number)json.getValue("update_time")).longValue());
    }
  }

  public static void toJson(Customer obj, JsonObject json) {
    json.put("create_time", obj.getCreate_time());
    if (obj.getId() != null) {
      json.put("id", obj.getId());
    }
    if (obj.getMarriage() != null) {
      json.put("marriage", obj.getMarriage().name());
    }
    if (obj.getName() != null) {
      json.put("name", obj.getName());
    }
    if (obj.getPhone() != null) {
      json.put("phone", obj.getPhone());
    }
    json.put("update_time", obj.getUpdate_time());
  }
}