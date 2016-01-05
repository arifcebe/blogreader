package com.pratamawijaya.blog.injection.module;

import android.app.Application;
import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pratamawijaya.blog.data.Migration;
import com.pratamawijaya.blog.data.local.DatabaseHelper;
import com.pratamawijaya.blog.data.network.PratamaService;
import com.pratamawijaya.blog.injection.ApplicationContext;
import com.squareup.okhttp.OkHttpClient;
import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import javax.inject.Singleton;

/**
 * Created by : pratama - set.mnemonix@gmail.com
 * Date : 12/31/15
 * Project : PratamaBlogDagger2
 */
@Module public class ApplicationModule {
  private static final long DATABASE_VERSION = 1;
  protected final Application application;

  public ApplicationModule(Application application) {
    this.application = application;
  }

  @Provides Application provideApplication() {
    return application;
  }

  @Provides @ApplicationContext Context provideContext() {
    return application;
  }

  @Provides @Singleton static OkHttpClient providesOkHttpClient() {
    OkHttpClient okHttpClient = new OkHttpClient();
    return okHttpClient;
  }

  @Provides @Singleton static Gson providesGson() {
    Gson gson = new GsonBuilder().create();
    return gson;
  }

  @Provides @Singleton static PratamaService provideService(OkHttpClient okHttpClient, Gson gson) {
    return PratamaService.Creator.newPratamaService(okHttpClient, gson);
  }

  @Provides @Singleton static DatabaseHelper provideDatabaseHelper(Realm realm) {
    return new DatabaseHelper(realm);
  }

  @Provides static Realm provideRealm(@ApplicationContext Context context) {
    RealmConfiguration configuration =
        new RealmConfiguration.Builder(context).name("pratamablog.realm")
            .schemaVersion(DATABASE_VERSION)
            .migration(new Migration())
            .build();

    return Realm.getInstance(configuration);
  }
}
