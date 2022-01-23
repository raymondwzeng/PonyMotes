//package com.dinsfire.ponymotes
//
//import android.annotation.SuppressLint
//import java.lang.Exception
//import java.util.ArrayList
//
//class EmoteAdapter(context: Context?) : BaseAdapter() {
//    var items = ArrayList<String>()
//    private val inflater: LayoutInflater
//    fun setItems(items: ArrayList<String>) {
//        this.items = items
//    }
//
//    val count: Int
//        get() = items.size
//
//    fun getItem(i: Int): String {
//        return items[i]
//    }
//
//    fun getItemId(i: Int): Long {
//        return i.toLong()
//    }
//
//    @SuppressLint("NewApi")
//    fun getView(i: Int, view: View?, viewGroup: ViewGroup?): View? {
//        var view: View? = view
//        val holder: ViewHolder
//        if (view == null) {
//            view = inflater.inflate(R.layout.emote_view, viewGroup, false)
//            holder = ViewHolder()
//            holder.emotePicture = view.findViewById(R.id.picture) as ImageView
//            holder.name = view.findViewById(R.id.text) as TextView
//
//            // view.setTag(R.id.picture, view.findViewById(R.id.picture));
//            // view.setTag(R.id.text, view.findViewById(R.id.text));
//            view.setTag(holder)
//        } else {
//            holder = view.getTag()
//            holder.emotePicture.setImageResource(android.R.color.transparent)
//            // holder.name.setText(items.get(i));
//        }
//        holder.position = i
//        val myFadeInAnimation: Animation =
//            AnimationUtils.loadAnimation(inflater.getContext(), R.anim.fade_in)
//        val currentapiVersion: Int = android.os.Build.VERSION.SDK_INT
//        if (currentapiVersion >= android.os.Build.VERSION_CODES.HONEYCOMB) {
//            try {
//                ThumbnailTask().executeOnExecutor(
//                    AsyncTask.THREAD_POOL_EXECUTOR, i, holder, items[i],
//                    myFadeInAnimation
//                )
//            } catch (e: Exception) {
//                // Do nothing as the pool is full, just wait it out.
//            }
//        } else {
//            ThumbnailTask().execute(i, holder, items[i])
//        }
//        return view
//    }
//
//    private class ThumbnailTask : AsyncTask<Any?, Any?, Drawable?>() {
//        private var mPosition = 0
//        private var mHolder: ViewHolder? = null
//        private var mEmoteName: String? = null
//        private var myFadeInAnimation: Animation? = null
//        protected fun doInBackground(vararg params: Any): Drawable? {
//            mPosition = params[0] as Int
//            mHolder = params[1] as ViewHolder
//            mEmoteName = params[2] as String
//            myFadeInAnimation = params[3] as Animation
//            var d: Drawable? = null
//            if (FileIO.Companion.emoteExistsInStorage(mEmoteName)) d =
//                Drawable.createFromPath(FileIO.Companion.getEmotePath(mEmoteName))
//            return d
//        }
//
//        protected fun onPostExecute(d: Drawable?) {
//            if (mHolder!!.position == mPosition) {
//                mHolder!!.emotePicture.setPadding(5, 5, 5, 5)
//                if (d != null) {
//                    mHolder!!.emotePicture.startAnimation(myFadeInAnimation)
//                    mHolder!!.emotePicture.setImageDrawable(d)
//                    mHolder!!.name.setText("")
//                } else {
//                    mHolder!!.emotePicture.setImageResource(R.drawable.question_mark)
//                    mHolder!!.name.setText(mEmoteName)
//                }
//            }
//        }
//    }
//
//    private class ViewHolder {
//        var emotePicture: ImageView? = null
//        var name: TextView? = null
//        var position = 0
//    }
//
//    init {
//        inflater = LayoutInflater.from(context)
//    }
//}