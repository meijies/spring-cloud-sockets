/*
 *  Copyright 2017 original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package org.springframework.cloud.reactive.socket.handler;

import io.rsocket.AbstractRSocket;
import io.rsocket.Payload;
import io.rsocket.RSocket;
import reactor.core.publisher.Mono;

import org.springframework.cloud.reactive.socket.ExchangeMode;
import org.springframework.cloud.reactive.socket.MethodHandler;

/**
 * @author Vinicius Carvalho
 */
public class RequestOneSocketHandler extends AbstractReactiveSocketMethodHandler {

	@Override
	public Mono<RSocket> invoke(MethodHandler methodHandler) {
		return Mono.just(new AbstractRSocket() {
			@Override
			public Mono<Payload> requestResponse(Payload payload) {
				return Mono.just((Payload)methodHandler.invoke(payload));
			}
		});
	}

	@Override
	public boolean accepts(ExchangeMode exchangeMode) {
		return exchangeMode == ExchangeMode.REQUEST_ONE;
	}
}
