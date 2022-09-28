package org.example;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

class FooPoolTest {

	@Test
	void test() throws InterruptedException {
		ExecutorService executor = Executors.newFixedThreadPool(3);

		FooPool pool = new FooPool();

		// start 3 threads that try to borrow a Foo with the same key
		executor.execute(new FooBorrower(pool));
		executor.execute(new FooBorrower(pool));
		executor.execute(new FooBorrower(pool));

		// this never finishes because two threads are stuck forever
		executor.shutdown();
		assertTrue(executor.awaitTermination(10, TimeUnit.SECONDS));
	}

	private static class FooBorrower implements Runnable {
		private final FooPool pool;

		public FooBorrower(FooPool pool) {
			this.pool = pool;
		}

		@Override
		public void run() {
			try {
				Foo foo = pool.borrowObject("key");
				if (foo != null) {
					pool.returnObject("key", foo);
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
}
