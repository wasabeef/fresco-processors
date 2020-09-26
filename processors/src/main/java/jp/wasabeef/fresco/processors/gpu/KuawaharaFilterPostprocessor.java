package jp.wasabeef.fresco.processors.gpu;

/**
 * Copyright (C) 2020 Wasabeef
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Context;

import com.facebook.cache.common.CacheKey;
import com.facebook.cache.common.SimpleCacheKey;

import jp.co.cyberagent.android.gpuimage.filter.GPUImageKuwaharaFilter;

/**
 * Kuwahara all the colors in the image.
 *
 * The radius to sample from when creating the brush-stroke effect, with a default of 25.
 * The larger the radius, the slower the filter.
 */
public class KuawaharaFilterPostprocessor extends GPUFilterPostprocessor {

    private final int radius;

    public KuawaharaFilterPostprocessor(Context context) {
        this(context, 25);
    }

    public KuawaharaFilterPostprocessor(Context context, int radius) {
        super(context, new GPUImageKuwaharaFilter());
        this.radius = radius;

        GPUImageKuwaharaFilter filter = getFilter();
        filter.setRadius(radius);
    }

    @Override
    public CacheKey getPostprocessorCacheKey() {
        return new SimpleCacheKey("radius=" + radius);
    }
}