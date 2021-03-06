package cn.mycommons.xiaoxiazhihu.app;

import android.app.Application;

import javax.inject.Singleton;

import cn.mycommons.xiaoxiazhihu.business.api.ZhihuApi;
import cn.mycommons.xiaoxiazhihu.business.api.impl.OkHttpUtil;
import cn.mycommons.xiaoxiazhihu.business.api.impl.okhttp.ZhihuApiOkHttpImpl;
import cn.mycommons.xiaoxiazhihu.business.api.impl.retrofit.IZhihuRetorfitApi;
import cn.mycommons.xiaoxiazhihu.business.api.impl.retrofit.RetrofitUtil;
import cn.mycommons.xiaoxiazhihu.business.api.impl.retrofit.ZhihuApiRetrofitImpl;
import cn.mycommons.xiaoxiazhihu.business.domain.ZhihuDomain;
import cn.mycommons.xiaoxiazhihu.business.domain.impl.ZhihuDomainImpl;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * AppModule <br/>
 * Created by xiaqiulei on 2016-06-29.
 */

@Module
public class AppModule {

    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    public ZhihuDomain providerZhihuDomain(ZhihuApi zhihuApi) {
        return new ZhihuDomainImpl(zhihuApi);
    }

    @Provides
    public ZhihuApi providerZhihuApi(IZhihuRetorfitApi zhihuRetorfitApi) {
        return new ZhihuApiRetrofitImpl(zhihuRetorfitApi);
    }

    // @Provides
    public ZhihuApi providerZhihuApi(OkHttpClient client) {
        return new ZhihuApiOkHttpImpl(client);
    }

    @Provides
    @Singleton
    public OkHttpClient providerOkHttpClient() {
        return OkHttpUtil.newOkHttpClient();
    }

    @Provides
    public IZhihuRetorfitApi providerIZhihuRetorfitApi(OkHttpClient client) {
        return RetrofitUtil.createApi(IZhihuRetorfitApi.class, client);
    }
}