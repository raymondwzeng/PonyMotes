//package com.dinsfire.ponymotes
//
//import android.annotation.SuppressLint
//import java.io.File
//import java.lang.Exception
//import java.util.ArrayList
//
//class SearchFragment : Fragment() {
//    enum class SearchMode {
//        NORMAL, RETURN_PICTURE, RETURN_CODE
//    }
//
//    var currentMode: SearchMode? = null
//    private var searchResults: TextView? = null
//    private var searchField: EditText? = null
//    private var searchButton: ImageView? = null
//    private var gridView: GridView? = null
//    private var emoteAdapter: EmoteAdapter? = null
//    private var dbController: DatabaseController? = null
//    private var prefs: Prefrences? = null
//    fun setPickPictureMode() {
//        currentMode = SearchMode.RETURN_PICTURE
//    }
//
//    fun setPickEmoteCode() {
//        currentMode = SearchMode.RETURN_CODE
//    }
//
//    fun setNormalMode() {
//        currentMode = SearchMode.NORMAL
//    }
//
//    fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        val rootView: View = inflater.inflate(R.layout.fragment_main, container, false)
//        prefs = Prefrences(PreferenceManager.getDefaultSharedPreferences(getActivity()))
//
//        // dbController = ((MainActivity)this.getActivity()).getDBController();
//        dbController = DatabaseController(getActivity())
//        searchField = rootView.findViewById(R.id.searchField) as EditText
//        searchResults = rootView.findViewById(R.id.numOfResults) as TextView
//        searchButton = rootView.findViewById(R.id.searchButton) as ImageView
//        emoteAdapter = EmoteAdapter(getActivity())
//        gridView = rootView.findViewById(R.id.gridview) as GridView
//        gridView.setAdapter(emoteAdapter)
//        gridView.setOnItemClickListener(object : OnItemClickListener() {
//            fun onItemClick(parent: AdapterView<*>?, v: View?, position: Int, id: Long) {
//                if (currentMode == SearchMode.RETURN_PICTURE) {
//                    val data = Intent()
//                    data.setType("image/*")
//                    if (prefs!!.altSharingMethod()) data.setData(
//                        Uri.fromFile(
//                            FileIO.Companion.getEmoteByName(
//                                emoteAdapter!!.getItem(position)
//                            )
//                        )
//                    ) else data.setData(
//                        Uri.fromFile(
//                            File(
//                                FileIO.Companion.copyTempEmote(
//                                    emoteAdapter!!.getItem(position)
//                                )
//                            )
//                        )
//                    )
//                    getActivity().setResult(Activity.RESULT_OK, data)
//                    getActivity().finish()
//                } else if (currentMode == SearchMode.RETURN_CODE) {
//                    val data = Intent()
//                    data.putExtra(
//                        Intent.EXTRA_STREAM,
//                        "[](/" + emoteAdapter!!.getItem(position) + ") "
//                    )
//                    getActivity().setResult(Activity.RESULT_OK, data)
//                    getActivity().finish()
//                } else {
//                    Toast.makeText(
//                        getView().getContext(),
//                        emoteAdapter!!.getItem(position) + " copied to clipboard.",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                    copyEmoteToClipboard(emoteAdapter!!.getItem(position))
//                }
//            }
//        })
//        gridView.setOnItemLongClickListener(object : OnItemLongClickListener() {
//            fun onItemLongClick(
//                parent: AdapterView<*>?,
//                view: View?,
//                position: Int,
//                id: Long
//            ): Boolean {
//                if (currentMode == SearchMode.NORMAL) {
//                    // shareEmote(emoteAdapter.getItem(position));
//                    showPopup(emoteAdapter!!.getItem(position))
//                }
//                return true
//            }
//        })
//        searchField.setOnEditorActionListener(object : OnEditorActionListener() {
//            fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
//                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
//                    search(searchField.getText().toString())
//                    return true
//                }
//                return false
//            }
//        })
//        searchButton.setOnClickListener(object : OnClickListener() {
//            fun onClick(v: View?) {
//                search(searchField.getText().toString())
//            }
//        })
//        return rootView
//    }
//
//    fun showPopup(emoteName: String?) {
//        val emoteInfo = EmoteDetailsDialog(getActivity())
//        emoteInfo.setContentView(R.layout.dialog_emote_info)
//        emoteInfo.setTitle(emoteName)
//        emoteInfo.setCancelable(true)
//
//        // Set the emote in the imageview
//        val img: ImageView = emoteInfo.findViewById(R.id.emoteInfoImageView) as ImageView
//        if (FileIO.Companion.emoteExistsInStorage(emoteName)) {
//            val d: Drawable = Drawable.createFromPath(FileIO.Companion.getEmotePath(emoteName))
//            img.setImageDrawable(d)
//        }
//
//        // Get the information about the emote to display
//        dbController!!.open()
//        val emoteInfomation = dbController!!.getInfoForEmote(emoteName!!)
//        dbController!!.close()
//
//        // TextView emoteNameTextView = (TextView)
//        // emoteInfo.findViewById(R.id.emoteName);
//        // emoteNameTextView.setText(emoteName);
//
//        // ListView emoteListView = (ListView)
//        // emoteInfo.findViewById(R.id.emoteListView);
//        emoteInfo.show()
//    }
//
//    fun shareEmote(emoteName: String) {
//        val share = Intent(Intent.ACTION_SEND)
//        share.setType("image/*")
//        Log.i("shareDebug", "prefs: " + prefs!!.altSharingMethod().toString())
//        Log.i("shareDebug", "emoteName: $emoteName")
//        if (prefs!!.altSharingMethod()) share.putExtra(
//            Intent.EXTRA_STREAM,
//            Uri.fromFile(FileIO.Companion.getEmoteByName(emoteName))
//        ) else {
//            Log.i(
//                "shareDebug",
//                "FileIO Return: " + FileIO.Companion.getEmoteByName(emoteName).toString()
//            )
//            Log.i(
//                "shareDebug", "Emote File Exists: " + FileIO.Companion.getEmoteByName(emoteName)!!
//                    .exists().toString()
//            )
//            Log.i(
//                "shareDebug",
//                "URI Creation: " + Uri.fromFile(FileIO.Companion.getEmoteByName(emoteName))
//            )
//            val tempEmote: File = File(FileIO.Companion.copyTempEmote(emoteName))
//            Log.i("shareDebug", "temp exists: " + tempEmote.exists().toString())
//            share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(tempEmote))
//        }
//        startActivity(Intent.createChooser(share, "Share emote via"))
//    }
//
//    @SuppressLint("NewApi")
//    fun copyEmoteToClipboard(emoteName: String?) {
//        var emoteName = emoteName
//        val sdk: Int = android.os.Build.VERSION.SDK_INT
//        emoteName = "[](/$emoteName) "
//        if (sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
//            val clipboard: android.text.ClipboardManager = getView().getContext()
//                .getSystemService(Context.CLIPBOARD_SERVICE) as android.text.ClipboardManager
//            clipboard.setText(emoteName)
//        } else {
//            val clipboard: android.content.ClipboardManager = getView().getContext()
//                .getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
//            val clip: android.content.ClipData =
//                android.content.ClipData.newPlainText("ponymotes", emoteName)
//            clipboard.setPrimaryClip(clip)
//        }
//    }
//
//    @SuppressLint("NewApi")
//    fun search(searchTerm: String?) {
//        val currentapiVersion: Int = android.os.Build.VERSION.SDK_INT
//        if (currentapiVersion >= android.os.Build.VERSION_CODES.HONEYCOMB) {
//            try {
//                SearchASyncTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, searchTerm)
//            } catch (e: Exception) {
//            }
//        } else {
//            SearchASyncTask().execute(searchTerm)
//        }
//    }
//
//    private inner class SearchASyncTask : AsyncTask<String?, Void?, ArrayList<String?>?>() {
//        protected fun doInBackground(vararg params: String): ArrayList<String?>? {
//            dbController!!.open()
//            val result = dbController!!.searchFor(params[0], prefs!!.searchLimit())
//            dbController!!.close()
//            return result
//        }
//
//        protected fun onPostExecute(result: ArrayList<String>) {
//            gridView.setNumColumns(prefs!!.numOfColumns())
//            searchResults.setText(
//                result.size.toString() + " "
//                        + getView().getContext().getString(R.string.numOfResults)
//            )
//            emoteAdapter!!.setItems(result)
//            gridView.setSelection(0)
//            val imm: InputMethodManager =
//                getActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//            imm.hideSoftInputFromWindow(searchField.getWindowToken(), 0)
//        }
//    }
//
//    init {
//        setNormalMode()
//    }
//}