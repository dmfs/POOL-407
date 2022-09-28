package org.example;

import org.apache.commons.pool2.impl.GenericKeyedObjectPool;

public final class FooPool extends GenericKeyedObjectPool<String, Foo> {
	public FooPool() {
		super(new PoolFactory(), new FooPoolConfig());
	}
}
