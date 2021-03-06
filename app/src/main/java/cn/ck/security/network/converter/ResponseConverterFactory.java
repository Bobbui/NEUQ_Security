package cn.ck.security.network.converter;

import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Json解析转换器
 *
 * @author FanHongyu.
 * @since 18/1/17 22:29.
 * email fanhongyu@hrsoft.net.
 */

public class ResponseConverterFactory extends Converter.Factory{


    private final Gson gson;
    private static final String NULL_GSON = "gson == null";

    private ResponseConverterFactory(Gson gson) {

        if (gson == null) {
            throw new NullPointerException(NULL_GSON);
        }
        this.gson = gson;
    }

    public static ResponseConverterFactory create() {
        return create(new Gson());
    }

    public static ResponseConverterFactory create(Gson gson) {
        return new ResponseConverterFactory(gson);
    }


    @Nullable
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new GsonResponseBodyConverter<>(gson, type);
    }

    @Nullable
    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[]
            methodAnnotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new GsonRequestBodyConverter<>(gson, adapter);
    }
}
