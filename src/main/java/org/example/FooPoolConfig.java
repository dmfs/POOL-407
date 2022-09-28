package org.example;

import org.apache.commons.pool2.impl.GenericKeyedObjectPoolConfig;

public final class FooPoolConfig extends GenericKeyedObjectPoolConfig<Foo> {
	public FooPoolConfig() {

		setBlockWhenExhausted(true);
		setMaxTotalPerKey(1);
		setMaxWaitMillis(-1);
	}
}
