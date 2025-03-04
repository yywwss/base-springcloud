/**
 * P6Spy
 *
 * Copyright (C) 2002 - 2018 P6Spy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zhwl.txlcn.tc.support.p6spy.wrapper;

/**
 * Marker interface added to all proxies generated by P6Spy.  Any object which implements this interface
 * can be considered to be a proxy created by P6Spy.
 */
public interface P6Proxy {

  /**
   * Returns the underlying object for the proxy.
   * <p>
   * WARNING: This is an internal method for P6Spy.  This method should not be called directly.  Use the methods
   * on the {@link java.sql.Wrapper} interface instead!
   * @return the underlying object being proxied
   */
  Object unwrapP6SpyProxy();
}
