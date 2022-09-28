package org.example;

import org.apache.commons.pool2.BaseKeyedPooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

public final class PoolFactory extends BaseKeyedPooledObjectFactory<String, Foo> {

	@Override
	public Foo create(String key) {
		// this is key to the test, creation failed and returns null
		// for instance see https://github.com/openhab/openhab-core/blob/main/bundles/org.openhab.core.io.transport.modbus/src/main/java/org/openhab/core/io/transport/modbus/internal/pooling/ModbusSlaveConnectionFactoryImpl.java#L163
		// the test passes when this returns new Foo();
		return null;
	}

	@Override
	public PooledObject<Foo> wrap(Foo value) {
		return new DefaultPooledObject<>(value);
	}

	@Override
	public boolean validateObject(String key, PooledObject<Foo> p) {
		return true;
	}
}
