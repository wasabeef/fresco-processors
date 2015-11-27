package jp.wasabeef.fresco.processors;

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

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.Drawable;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.common.SimpleCacheKey;
import com.facebook.imagepipeline.request.BasePostprocessor;
import jp.wasabeef.fresco.processors.internal.Utils;

public class MaskPostprocessor extends BasePostprocessor {

  private static Paint paint = new Paint();
  private Context context;
  private int maskId;

  static {
    paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
  }

  /**
   * @param maskId If you change the mask file, please also rename the mask file, or Glide will get
   * the cache with the old mask. Because getId() return the same values if using the
   * same make file name. If you have a good idea please tell us, thanks.
   */
  public MaskPostprocessor(Context context, int maskId) {
    this.context = context.getApplicationContext();
    this.maskId = maskId;
  }

  @Override public void process(Bitmap dest, Bitmap source) {

    Bitmap result =
        Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);

    Drawable mask = Utils.getMaskDrawable(context, maskId);

    Canvas canvas = new Canvas(result);
    mask.setBounds(0, 0, source.getWidth(), source.getHeight());
    mask.draw(canvas);
    canvas.drawBitmap(source, 0, 0, paint);

    super.process(dest, result);
  }

  @Override public String getName() {
    return getClass().getSimpleName();
  }

  @Override public CacheKey getPostprocessorCacheKey() {
    return new SimpleCacheKey("mask=" + context.getResources().getResourceEntryName(maskId));
  }
}
