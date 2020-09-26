package jp.wasabeef.fresco.processors;

/**
 * Copyright (C) 2020 Wasabeef
 * <p>
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

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.common.SimpleCacheKey;
import com.facebook.imagepipeline.request.BasePostprocessor;

public class GrayscalePostprocessor extends BasePostprocessor {

  @Override public void process(Bitmap dest, Bitmap source) {
    super.process(dest, source);

    Canvas canvas = new Canvas(dest);
    ColorMatrix saturation = new ColorMatrix();
    saturation.setSaturation(0f);
    Paint paint = new Paint();
    paint.setColorFilter(new ColorMatrixColorFilter(saturation));
    canvas.drawBitmap(source, 0, 0, paint);
  }

  @Override public String getName() {
    return getClass().getSimpleName();
  }

  @Override public CacheKey getPostprocessorCacheKey() {
    return new SimpleCacheKey("gray");
  }
}