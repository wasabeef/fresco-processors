package jp.wasabeef.fresco.processors;

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

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;

import com.facebook.cache.common.CacheKey;
import com.facebook.cache.common.SimpleCacheKey;
import com.facebook.imagepipeline.request.BasePostprocessor;

public class ColorFilterPostprocessor extends BasePostprocessor {

  private final int color;

  public ColorFilterPostprocessor(int color) {
    this.color = color;
  }

  @Override
  public void process(Bitmap dest, Bitmap source) {
    super.process(dest, source);

    Canvas canvas = new Canvas(dest);
    Paint paint = new Paint();
    paint.setAntiAlias(true);
    paint.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP));
    canvas.drawBitmap(source, 0, 0, paint);
  }

  @Override
  public String getName() {
    return getClass().getSimpleName();
  }

  @Override
  public CacheKey getPostprocessorCacheKey() {
    return new SimpleCacheKey("color=" + color);
  }
}
