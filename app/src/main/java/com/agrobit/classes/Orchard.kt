/*
 * Copyright (c) 2018 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 */

package com.agrobit.classes

import android.content.Context
import org.json.JSONException
import org.json.JSONObject


class Orchard(
    val id:String,
    val name: String,
    val size: String,
    val imageUrl: String,
    val type: String,
    val status:Int,
    val crea:String,
    val lasta:String,
    val lastproblems:String,
    val tareasp:Int,
    val tareaspro:Int,
    val tareasok:Int){

  companion object {
    fun getorchardsFromFile(context: Context?,filename:String,array:String): ArrayList<Orchard> {
      val recipeList = ArrayList<Orchard>()

      try {
        // Load data
        val jsonString = context?.let { loadJsonFromAsset(filename, it) }
        val json = JSONObject(jsonString)
        val orchards = json.getJSONArray(array)

        // Get Recipe objects from data
        (0 until orchards.length()).mapTo(recipeList) {
          Orchard(orchards.getJSONObject(it).getString("id"),
              orchards.getJSONObject(it).getString("name"),
              orchards.getJSONObject(it).getString("size"),
              orchards.getJSONObject(it).getString("image"),
              orchards.getJSONObject(it).getString("type"),
              orchards.getJSONObject(it).getInt("status"),
              orchards.getJSONObject(it).getString("crea"),
              orchards.getJSONObject(it).getString("lasta"),
              orchards.getJSONObject(it).getString("lastproblems"),
              orchards.getJSONObject(it).getInt("tareasp"),
              orchards.getJSONObject(it).getInt("tareaspro"),
              orchards.getJSONObject(it).getInt("tareasok"))
        }
      } catch (e: JSONException) {
        e.printStackTrace()
      }
      return recipeList
    }

    private fun loadJsonFromAsset(filename: String, context: Context): String? {
      var json: String?

      try {
        val inputStream = context.assets.open(filename)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        json = String(buffer, Charsets.UTF_8)
      } catch (ex: java.io.IOException) {
        ex.printStackTrace()
        return null
      }

      return json
    }
  }
}