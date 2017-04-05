package jp.wasabeef.fresco.processors.internal;

/**
 * Copyright (C) 2017 Wasabeef
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
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.os.Build;

public final class Utils {

  private Utils() {
    // Utility class.
  }

  public static Drawable getMaskDrawable(Context context, int maskId) {
    Drawable drawable = ContextCompat.getDrawable(context, maskId)
    if (drawable == null) {
      throw new IllegalArgumentException("maskId is invalid");
    }
    return drawable;
  }
}
