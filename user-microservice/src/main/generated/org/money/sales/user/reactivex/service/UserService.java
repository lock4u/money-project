/*
 * Copyright 2014 Red Hat, Inc.
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

package org.money.sales.user.reactivex.service;

import java.util.Map;
import io.reactivex.Observable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.vertx.reactivex.core.Vertx;
import org.money.sales.user.model.User;
import io.vertx.core.json.JsonObject;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

/**
 * Created by Lee on 2018/10/22.
 *
 * <p/>
 * NOTE: This class has been automatically generated from the {@link org.money.sales.user.service.UserService original} non RX-ified interface using Vert.x codegen.
 */

@io.vertx.lang.reactivex.RxGen(org.money.sales.user.service.UserService.class)
public class UserService {

  @Override
  public String toString() {
    return delegate.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserService that = (UserService) o;
    return delegate.equals(that.delegate);
  }
  
  @Override
  public int hashCode() {
    return delegate.hashCode();
  }

  public static final io.vertx.lang.reactivex.TypeArg<UserService> __TYPE_ARG = new io.vertx.lang.reactivex.TypeArg<>(
    obj -> new UserService((org.money.sales.user.service.UserService) obj),
    UserService::getDelegate
  );

  private final org.money.sales.user.service.UserService delegate;
  
  public UserService(org.money.sales.user.service.UserService delegate) {
    this.delegate = delegate;
  }

  public org.money.sales.user.service.UserService getDelegate() {
    return delegate;
  }

  public static UserService create(Vertx vertx, JsonObject config) { 
    UserService ret = UserService.newInstance(org.money.sales.user.service.UserService.create(vertx.getDelegate(), config));
    return ret;
  }

  public UserService findByName(String name, Handler<AsyncResult<User>> handler) { 
    delegate.findByName(name, handler);
    return this;
  }

  public Single<User> rxFindByName(String name) { 
    return new io.vertx.reactivex.core.impl.AsyncResultSingle<User>(handler -> {
      findByName(name, handler);
    });
  }


  public static  UserService newInstance(org.money.sales.user.service.UserService arg) {
    return arg != null ? new UserService(arg) : null;
  }
}
