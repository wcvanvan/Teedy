package com.sismics.docs.resource;

import android.content.Context;

import com.loopj.android.http.RequestParams;
import com.sismics.docs.listener.HttpCallback;
import com.sismics.docs.listener.JsonHttpResponseHandler;

import java.util.Set;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Access to /document API.
 * 
 * @author bgamard
 */
public class DocumentResource extends BaseResource {
    /**
     * GET /document/list.
     *
     * @param context Context
     * @param offset Offset
     * @param query Search query
     * @param callback Callback
     */
    public static void list(Context context, int offset, String query, HttpCallback callback) {
        Request request = new Request.Builder()
                .url(HttpUrl.parse(getApiUrl(context) + "/document/list")
                        .newBuilder()
                        .addQueryParameter("limit", "20")
                        .addQueryParameter("offset", Integer.toString(offset))
                        .addQueryParameter("sort_column", "3")
                        .addQueryParameter("asc", "false")
                        .addQueryParameter("search", query)
                        .build())
                .build();
        buildOkHttpClient(context)
                .newCall(request)
                .enqueue(HttpCallback.buildOkHttpCallback(callback));
    }

    /**
     * GET /document/id.
     *
     * @param context Context
     * @param id ID
     * @param responseHandler Callback
     */
    public static void get(Context context, String id, JsonHttpResponseHandler responseHandler) {
        init(context);

        client.get(getApiUrl(context) + "/document/" + id, responseHandler);
    }

    /**
     * DELETE /document/id.
     *
     * @param context Context
     * @param id ID
     * @param responseHandler Callback
     */
    public static void delete(Context context, String id, JsonHttpResponseHandler responseHandler) {
        init(context);

        client.delete(getApiUrl(context) + "/document/" + id, responseHandler);
    }

    /**
     * PUT /document.
     *
     * @param context Context
     * @param title Title
     * @param description Description
     * @param tagIdList Tags ID list
     * @param language Language
     * @param createDate Create date
     * @param responseHandler Callback
     */
    public static void add(Context context, String title, String description,
                           Set<String> tagIdList, String language, long createDate, JsonHttpResponseHandler responseHandler) {
        init(context);

        RequestParams params = new RequestParams();
        params.put("title", title);
        params.put("description", description);
        params.put("tags", tagIdList);
        params.put("language", language);
        params.put("create_date", createDate);
        client.put(getApiUrl(context) + "/document", params, responseHandler);
    }

    /**
     * POST /document/id.
     *
     * @param context Context
     * @param id ID
     * @param title Title
     * @param description Description
     * @param tagIdList Tags ID list
     * @param language Language
     * @param createDate Create date
     * @param responseHandler Callback
     */
    public static void edit(Context context, String id, String title, String description,
                           Set<String> tagIdList, String language, long createDate, JsonHttpResponseHandler responseHandler) {
        init(context);

        RequestParams params = new RequestParams();
        params.put("title", title);
        params.put("description", description);
        params.put("tags", tagIdList);
        params.put("language", language);
        params.put("create_date", createDate);
        client.post(getApiUrl(context) + "/document/" + id, params, responseHandler);
    }

    /**
     * Cancel pending requests.
     *
     * @param context Context
     */
    public static void cancel(Context context) {
        client.cancelRequests(context, true);
    }
}
