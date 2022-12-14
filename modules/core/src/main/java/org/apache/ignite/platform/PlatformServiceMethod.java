/*
 * Copyright 2019 GridGain Systems, Inc. and Contributors.
 *
 * Licensed under the GridGain Community Edition License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.gridgain.com/products/software/community-edition/gridgain-community-edition-license
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.ignite.platform;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for setting mapping between java interface's method and platform service's method.
 * Platform service method name is defined in {@link PlatformServiceMethod#value}
 * <p/>
 * For example, this annotated java inerface method:
 * <pre>
 * &#64;PlatformServiceMethod("SomeMethod")
 * Object someMethod(Object[] args)
 * </pre>
 * will be mapped to {@code SomeMethod}, for example (.NET service):
 * <pre>
 * object SomeMethod(object[] args)
 * </pre>
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface PlatformServiceMethod {
    /**
     * Method name in corresponding platform service.
     */
    String value();
}
