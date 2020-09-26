package jp.wasabeef.fresco.processors.gpu;

/**
 * Copyright (C) 2017 Wasabeef
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

import jp.co.cyberagent.android.gpuimage.filter.GPUImageBrightnessFilter;

/**
 * brightness value ranges from -1.0 to 1.0, with 0.0 as the normal level
 */
public class BrightnessFilterPostprocessor extends GPUFilterPostprocessor {

    private final float brightness;

    public BrightnessFilterPostprocessor(Context context) {
        this(context, 0.0f);
    }

    public BrightnessFilterPostprocessor(Context context, float brightness) {
        super(context, new GPUImageBrightnessFilter());
        this.brightness = brightness;

        GPUImageBrightnessFilter filter = getFilter();
        filter.setBrightness(brightness);
    }

    @Override
    public CacheKey getPostprocessorCacheKey() {
        return new SimpleCacheKey("brightness=" + brightness);
    }
}