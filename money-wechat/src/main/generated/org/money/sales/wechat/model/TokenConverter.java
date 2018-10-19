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

package org.money.sales.wechat.model;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;

/**
 * Converter for {@link org.money.sales.wechat.model.Token}.
 *
 * NOTE: This class has been automatically generated from the {@link org.money.sales.wechat.model.Token} original class using Vert.x codegen.
 */
public class TokenConverter {

  public static void fromJson(JsonObject json, Token obj) {
    if (json.getValue("access_token") instanceof String) {
      obj.setAccess_token((String)json.getValue("access_token"));
    }
    if (json.getValue("errcode") instanceof String) {
      obj.setErrcode((String)json.getValue("errcode"));
    }
    if (json.getValue("errmsg") instanceof String) {
      obj.setErrmsg((String)json.getValue("errmsg"));
    }
    if (json.getValue("expire_in") instanceof String) {
      obj.setExpire_in((String)json.getValue("expire_in"));
    }
  }

  public static void toJson(Token obj, JsonObject json) {
    if (obj.getAccess_token() != null) {
      json.put("access_token", obj.getAccess_token());
    }
    if (obj.getErrcode() != null) {
      json.put("errcode", obj.getErrcode());
    }
    if (obj.getErrmsg() != null) {
      json.put("errmsg", obj.getErrmsg());
    }
    if (obj.getExpire_in() != null) {
      json.put("expire_in", obj.getExpire_in());
    }
    json.put("success", obj.isSuccess());
  }
}