package jp.wasabeef.fresco.processors.gpu;

import android.content.Context;
import android.graphics.PointF;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.common.SimpleCacheKey;
import jp.co.cyberagent.android.gpuimage.GPUImageSwirlFilter;

/**
 * Copyright (C) 2015 Wasabeef
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Creates a swirl distortion on the image.
 */
public class SwirlFilterPostprocessor extends GPUFilterPostprocessor {

  private float radius;
  private float angle;
  private PointF center;

  public SwirlFilterPostprocessor(Context context) {
    this(context, 0.5f, 1.0f, new PointF(0.5f, 0.5f));
  }

  /**
   * @param radius from 0.0 to 1.0, default 0.5
   * @param angle minimum 0.0, default 1.0
   * @param center default (0.5, 0.5)
   */
  public SwirlFilterPostprocessor(Context context, float radius, float angle, PointF center) {
    super(context, new GPUImageSwirlFilter());
    this.radius = radius;
    this.angle = angle;
    this.center = center;

    GPUImageSwirlFilter filter = getFilter();
    filter.setRadius(this.radius);
    filter.setAngle(this.angle);
    filter.setCenter(this.center);
  }

  @Override public CacheKey getPostprocessorCacheKey() {
    return new SimpleCacheKey(
        "radius=" + radius + ",angle=" + angle + ",center=" + center.toString());
  }
}