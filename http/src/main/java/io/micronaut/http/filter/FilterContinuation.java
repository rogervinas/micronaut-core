package io.micronaut.http.filter;

import io.micronaut.core.annotation.Experimental;
import io.micronaut.http.HttpRequest;

/**
 * A filter continuation gives can be declared as a parameter on a
 * {@link io.micronaut.http.annotation.RequestFilter filter method}. The filter method gets
 * "delayed" access to the parameter it is requesting. For example, a request filter can declare a
 * continuation that returns the response. When the filter calls the continuation, other downstream
 * filters and the final request call will be executed. The continuation will return once the
 * response has been received and processed by downstream filters.<br>
 * A continuation can either return the value immediately (e.g.
 * {@code FilterContinuation<HttpResponse<?>>}), in which case the call to {@link #proceed()} will
 * block, or it can return a reactive wrapper (e.g.
 * {@code FilterContinuation<Publisher<HttpResponse<?>>>}). With a reactive wrapper,
 * {@link #proceed()} will not block, and downstream processing will happen asynchronously (after
 * the reactive stream is subscribed to).<br>
 *
 * @param <R> The type to return in {@link #proceed()}
 */
@Experimental
public interface FilterContinuation<R> {
    /**
     * Update the request for downstream processing
     *
     * @param request The new request
     * @return This continuation, for call chaining
     */
    FilterContinuation<R> request(HttpRequest<?> request);

    /**
     * Proceed processing downstream of this filter. If {@link R} is not a reactive type, this
     * method will block until downstream processing completes, and may throw an exception if there
     * is a failure. <b>Blocking netty event loop threads can lead to bugs, so any filter that
     * may block in the netty HTTP server should use
     * {@link io.micronaut.scheduling.annotation.ExecuteOn} to avoid running on the event loop.</b>
     * <br>
     * If {@link R} is a reactive type, this method will return immediately. Downstream processing
     * will happen when the reactive stream is subscribed to, and the reactive stream will produce
     * the downstream result when available.
     *
     * @return The downstream result, or reactive stream wrapper thereof
     */
    R proceed();
}
