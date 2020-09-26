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

import jp.co.cyberagent.android.gpuimage.filter.GPUImageSketchFilter;

public class SketchFilterPostprocessor extends GPUFilterPostprocessor {

  public SketchFilterPostprocessor(Context context) {
    super(context, new GPUImageSketchFilter());
  }

  @Override
  public CacheKey getPostprocessorCacheKey() {
    return new SimpleCacheKey("sketch");
  }
}
