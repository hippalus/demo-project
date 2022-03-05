package org.example.demo.config;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.task.AsyncTaskExecutor;


@Slf4j
@RequiredArgsConstructor
public class ExceptionHandlingAsyncTaskExecutor implements AsyncTaskExecutor, InitializingBean, DisposableBean {

  private final AsyncTaskExecutor executor;

  @Override
  public void execute(@NotNull Runnable runnable) {
    this.executor.execute(this.createWrappedRunnable(runnable));
  }


  @Override
  public void execute(@NotNull Runnable runnable, long startTimeout) {
    this.executor.execute(this.createWrappedRunnable(runnable));
  }

  private Runnable createWrappedRunnable(Runnable task) {
    return () -> {
      try {
        task.run();
      } catch (Exception ex) {
        this.handle(ex);
      }
    };
  }

  protected void handle(Exception e) {
    log.error("Caught async exception", e);
  }

  @Override
  public @NotNull Future<?> submit(@NotNull Runnable runnable) {
    return this.executor.submit(this.createWrappedRunnable(runnable));
  }

  @Override
  public <T> @NotNull Future<T> submit(@NotNull Callable<T> callable) {
    return this.executor.submit(this.createCallable(callable));
  }

  private <T> Callable<T> createCallable(Callable<T> callable) {
    return () -> {
      try {
        return callable.call();
      } catch (Exception ex) {
        this.handle(ex);
        throw ex;
      }
    };
  }


  @Override
  public void destroy() throws Exception {
    if (this.executor instanceof DisposableBean disposableBean) {
      disposableBean.destroy();
    }
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    if (this.executor instanceof InitializingBean initializingBean) {
      initializingBean.afterPropertiesSet();
    }

  }


}