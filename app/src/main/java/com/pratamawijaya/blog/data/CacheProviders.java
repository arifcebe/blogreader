package com.pratamawijaya.blog.data;

import com.pratamawijaya.blog.model.pojo.Post;
import io.rx_cache.DynamicKey;
import io.rx_cache.EvictDynamicKey;
import io.rx_cache.LifeCache;
import java.util.List;
import java.util.concurrent.TimeUnit;
import rx.Observable;

/**
 * Created by pratama on 4/11/16.
 */
public interface CacheProviders {
  @LifeCache(duration = 5, timeUnit = TimeUnit.DAYS) Observable<List<Post>> getListPost(
      Observable<List<Post>> data, DynamicKey key, EvictDynamicKey evictDynamicKey);
}
