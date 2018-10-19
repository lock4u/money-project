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

package org.money.sales.wechat.reactivex.service;

import io.reactivex.Single;
import io.vertx.reactivex.core.Vertx;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import org.money.sales.wechat.model.Token;

/**
 * Created by Lee on 2018/10/18.
 *
 * <p/>
 * NOTE: This class has been automatically generated from the {@link org.money.sales.wechat.service.TokenService original} non RX-ified interface using Vert.x codegen.
 */

@io.vertx.lang.reactivex.RxGen(org.money.sales.wechat.service.TokenService.class)
public class TokenService {

  @Override
  public String toString() {
    return delegate.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TokenService that = (TokenService) o;
    return delegate.equals(that.delegate);
  }
  
  @Override
  public int hashCode() {
    return delegate.hashCode();
  }

  public static final io.vertx.lang.reactivex.TypeArg<TokenService> __TYPE_ARG = new io.vertx.lang.reactivex.TypeArg<>(
    obj -> new TokenService((org.money.sales.wechat.service.TokenService) obj),
    TokenService::getDelegate
  );

  private final org.money.sales.wechat.service.TokenService delegate;
  
  public TokenService(org.money.sales.wechat.service.TokenService delegate) {
    this.delegate = delegate;
  }

  public org.money.sales.wechat.service.TokenService getDelegate() {
    return delegate;
  }

  public void refresh(Handler<AsyncResult<Token>> handler) { 
    delegate.refresh(handler);
  }

  public Single<Token> rxRefresh() { 
    return new io.vertx.reactivex.core.impl.AsyncResultSingle<Token>(handler -> {
      refresh(handler);
    });
  }

  public static TokenService create(Vertx vertx) { 
    TokenService ret = TokenService.newInstance(org.money.sales.wechat.service.TokenService.create(vertx.getDelegate()));
    return ret;
  }

  public void close() { 
    delegate.close();
  }


  public static  TokenService newInstance(org.money.sales.wechat.service.TokenService arg) {
    return arg != null ? new TokenService(arg) : null;
  }
}
