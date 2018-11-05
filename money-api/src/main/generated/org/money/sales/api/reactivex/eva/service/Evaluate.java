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

package org.money.sales.api.reactivex.eva.service;

import java.util.Map;
import io.reactivex.Observable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.vertx.core.json.JsonObject;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

/**
 * Created by Lee on 2018/10/27.
 *
 * <p/>
 * NOTE: This class has been automatically generated from the {@link org.money.sales.api.eva.service.Evaluate original} non RX-ified interface using Vert.x codegen.
 */

@io.vertx.lang.reactivex.RxGen(org.money.sales.api.eva.service.Evaluate.class)
public class Evaluate {

  @Override
  public String toString() {
    return delegate.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Evaluate that = (Evaluate) o;
    return delegate.equals(that.delegate);
  }
  
  @Override
  public int hashCode() {
    return delegate.hashCode();
  }

  public static final io.vertx.lang.reactivex.TypeArg<Evaluate> __TYPE_ARG = new io.vertx.lang.reactivex.TypeArg<>(
    obj -> new Evaluate((org.money.sales.api.eva.service.Evaluate) obj),
    Evaluate::getDelegate
  );

  private final org.money.sales.api.eva.service.Evaluate delegate;
  
  public Evaluate(org.money.sales.api.eva.service.Evaluate delegate) {
    this.delegate = delegate;
  }

  public org.money.sales.api.eva.service.Evaluate getDelegate() {
    return delegate;
  }

  public void eva(JsonObject js, Handler<AsyncResult<JsonObject>> handler) { 
    delegate.eva(js, handler);
  }

  public Single<JsonObject> rxEva(JsonObject js) { 
    return new io.vertx.reactivex.core.impl.AsyncResultSingle<JsonObject>(handler -> {
      eva(js, handler);
    });
  }

  public void close() { 
    delegate.close();
  }


  public static  Evaluate newInstance(org.money.sales.api.eva.service.Evaluate arg) {
    return arg != null ? new Evaluate(arg) : null;
  }
}
