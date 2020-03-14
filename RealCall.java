//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package okhttp3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Interceptor.Chain;
import okhttp3.internal.Util;
import okhttp3.internal.cache.CacheInterceptor;
import okhttp3.internal.connection.ConnectInterceptor;
import okhttp3.internal.connection.Exchange;
import okhttp3.internal.connection.Transmitter;
import okhttp3.internal.http.BridgeInterceptor;
import okhttp3.internal.http.CallServerInterceptor;
import okhttp3.internal.http.RealInterceptorChain;
import okhttp3.internal.http.RetryAndFollowUpInterceptor;
import okio.Timeout;

final class RealCall implements Call {
    final OkHttpClient client;
    private Transmitter transmitter;
    final Request originalRequest;
    final boolean forWebSocket;
    private boolean executed;

    private RealCall(OkHttpClient client, Request originalRequest, boolean forWebSocket) {
        this.client = client;
        this.originalRequest = originalRequest;
        this.forWebSocket = forWebSocket;
    }

    static RealCall newRealCall(OkHttpClient client, Request originalRequest, boolean forWebSocket) {
        RealCall call = new RealCall(client, originalRequest, forWebSocket);
        call.transmitter = new Transmitter(client, call);
        return call;
    }

    public Request request() {
        return this.originalRequest;
    }

    public Response execute() throws IOException {
        synchronized(this) {
            if (this.executed) {
                throw new IllegalStateException("Already Executed");
            }

            this.executed = true;
        }

        this.transmitter.timeoutEnter();
        this.transmitter.callStart();

        Response var1;
        try {
            this.client.dispatcher().executed(this);
            var1 = this.getResponseWithInterceptorChain();
        } finally {
            this.client.dispatcher().finished(this);
        }

        return var1;
    }

    public void enqueue(Callback responseCallback) {
        synchronized(this) {
            if (this.executed) {
                throw new IllegalStateException("Already Executed");
            }

            this.executed = true;
        }

        this.transmitter.callStart();
        this.client.dispatcher().enqueue(new okhttp3.RealCall.AsyncCall(this, responseCallback));
    }

    public void cancel() {
        this.transmitter.cancel();
    }

    public Timeout timeout() {
        return this.transmitter.timeout();
    }

    public synchronized boolean isExecuted() {
        return this.executed;
    }

    public boolean isCanceled() {
        return this.transmitter.isCanceled();
    }

    public RealCall clone() {
        return newRealCall(this.client, this.originalRequest, this.forWebSocket);
    }

    String toLoggableString() {
        return (this.isCanceled() ? "canceled " : "") + (this.forWebSocket ? "web socket" : "call") + " to " + this.redactedUrl();
    }

    String redactedUrl() {
        return this.originalRequest.url().redact();
    }

    Response getResponseWithInterceptorChain() throws IOException {
        /**
         * 分类：1、应用拦截器  2、网络拦截器
         */

        List<Interceptor> interceptors = new ArrayList();
        //用户添加的全局拦截器
        interceptors.addAll(this.client.interceptors());
        //错误、重定向拦截器
        interceptors.add(new RetryAndFollowUpInterceptor(this.client));
        //桥接拦截器，桥接应用层与网络层，添加必要的头
        interceptors.add(new BridgeInterceptor(this.client.cookieJar()));
        //缓存处理，Last-Modified、ETag、DiskLruCache等
        interceptors.add(new CacheInterceptor(this.client.internalCache()));
        //连接拦截器
        interceptors.add(new ConnectInterceptor(this.client));
        if (!this.forWebSocket) {
            //通过okHttpClient.Builder#addNetworkInterceptor()传进来的拦截器只对非网页的请求生效
            interceptors.addAll(this.client.networkInterceptors());
        }
        //真正访问服务器的拦截器
        interceptors.add(new CallServerInterceptor(this.forWebSocket));
        Chain chain = new RealInterceptorChain(interceptors, this.transmitter, (Exchange)null, 0, this.originalRequest, this, this.client.connectTimeoutMillis(), this.client.readTimeoutMillis(), this.client.writeTimeoutMillis());
        boolean calledNoMoreExchanges = false;

        Response var5;
        try {
            Response response = chain.proceed(this.originalRequest);
            if (this.transmitter.isCanceled()) {
                Util.closeQuietly(response);
                throw new IOException("Canceled");
            }

            var5 = response;
        } catch (IOException var9) {
            calledNoMoreExchanges = true;
            throw this.transmitter.noMoreExchanges(var9);
        } finally {
            if (!calledNoMoreExchanges) {
                this.transmitter.noMoreExchanges((IOException)null);
            }

        }

        return var5;
    }
}
