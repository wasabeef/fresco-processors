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

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.facebook.cache.common.CacheKey;
import com.facebook.cache.common.SimpleCacheKey;
import com.facebook.imagepipeline.request.BasePostprocessor;

import jp.wasabeef.fresco.processors.internal.FastBlur;
import jp.wasabeef.fresco.processors.internal.RSBlur;

public class BlurPostprocessor extends BasePostprocessor {

  private static final int MAX_RADIUS = 25;
  private static final int DEFAULT_DOWN_SAMPLING = 1;

  private final Context context;
  private final int radius;
  private final int sampling;

  public BlurPostprocessor(Context context) {
    this(context, MAX_RADIUS, DEFAULT_DOWN_SAMPLING);
  }

  public BlurPostprocessor(Context context, int radius) {
    this(context, radius, DEFAULT_DOWN_SAMPLING);
  }

  public BlurPostprocessor(Context context, int radius, int sampling) {
    this.context = context.getApplicationContext();
    this.radius = radius;
    this.sampling = sampling;
  }

  @Override
  public void process(Bitmap dest, Bitmap source) {

    int width = source.getWidth();
    int height = source.getHeight();
    int scaledWidth = width / sampling;
    int scaledHeight = height / sampling;

    Bitmap blurredBitmap = Bitmap.createBitmap(scaledWidth, scaledHeight, Bitmap.Config.ARGB_8888);

    Canvas canvas = new Canvas(blurredBitmap);
    canvas.scale(1 / (float) sampling, 1 / (float) sampling);
    Paint paint = new Paint();
    paint.setFlags(Paint.FILTER_BITMAP_FLAG);
    canvas.drawBitmap(source, 0, 0, paint);

    try {
      blurredBitmap = RSBlur.blur(context, blurredBitmap, radius);
    } catch (android.renderscript.RSRuntimeException e) {
      blurredBitmap = FastBlur.blur(blurredBitmap, radius, true);
    }

    Bitmap scaledBitmap =
      Bitmap.createScaledBitmap(blurredBitmap, dest.getWidth(), dest.getHeight(), true);
    blurredBitmap.recycle();

    super.process(dest, scaledBitmap);
  }

  @Override
  public String getName() {
    return getClass().getSimpleName();
  }

  @Override
  public CacheKey getPostprocessorCacheKey() {
    return new SimpleCacheKey("radius=" + radius + ",sampling=" + sampling);
  }
}
