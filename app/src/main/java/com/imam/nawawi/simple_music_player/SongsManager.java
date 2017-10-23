package com.imam.nawawi.simple_music_player;
/**
 * Created by nawaw on 10/18/2017.
 */
import android.app.Activity;
import android.provider.MediaStore;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;

public class SongsManager extends Activity{

    // SDCard Path
    final String MEDIA_PATH = new String(MediaStore.Audio.Media.getContentUri("sdcard").toString());
//    final String MEDIA_PATH = new String("/storage/sdcard/Musics/");
    private ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();

    // Constructor
    public SongsManager(){

    }

    /**
     * Function to read all mp3 files from sdcard
     * and store the details in ArrayList
     * */
//    public ArrayList<HashMap<String, String>> getPlayList(){
//        File home = new File(MEDIA_PATH);
////
//        if (home.listFiles(new FileExtensionFilter()).length > 0) {
//            for (File file : home.listFiles(new FileExtensionFilter())) {
//                HashMap<String, String> song = new HashMap<String, String>();
//                song.put("songTitle", file.getName().substring(0, (file.getName().length() - 4)));
//                song.put("songPath", file.getPath());
//
////                // Adding each song to SongList
//                songsList.add(song);
//            }
//        }
////        // return songs list array
//        return songsList;
//    }

    public ArrayList<HashMap<String,String>> getPlayList(String rootPath) {
        ArrayList<HashMap<String,String>> fileList = new ArrayList<>();


        try {
            File rootFolder = new File(rootPath);
            File[] files = rootFolder.listFiles(); //here you will get NPE if directory doesn't contains  any file,handle it like this.
            for (File file : files) {
                if (file.isDirectory()) {
                    if (getPlayList(file.getAbsolutePath()) != null) {
                        fileList.addAll(getPlayList(file.getAbsolutePath()));
                    } else {
                        break;
                    }
                } else if (file.getName().endsWith(".mp3")) {
                    HashMap<String, String> song = new HashMap<>();
                    song.put("file_path", file.getAbsolutePath());
                    song.put("file_name", file.getName());
                    fileList.add(song);
                }
            }
            return fileList;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Class to filter files which are having .mp3 extension
     * */
    class FileExtensionFilter implements FilenameFilter {
        public boolean accept(File dir, String name) {
            return (name.endsWith(".mp3") || name.endsWith(".MP3"));
        }
    }


    //method to retrieve song info from device
//    public void getPlayList(){
//        //query external audio
//        ContentResolver musicResolver = getContentResolver();
//        Uri musicUri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
//        Cursor musicCursor = musicResolver.query(musicUri, null, null, null, null);
//        //iterate over results if valid
//        if(musicCursor!=null && musicCursor.moveToFirst()){
//            //get columns
//            int titleColumn = musicCursor.getColumnIndex
//                    (android.provider.MediaStore.Audio.Media.TITLE);
//            int idColumn = musicCursor.getColumnIndex
//                    (android.provider.MediaStore.Audio.Media._ID);
//            int artistColumn = musicCursor.getColumnIndex
//                    (android.provider.MediaStore.Audio.Media.ARTIST);
//            //add songs to list
//            do {
////                long thisId = musicCursor.getLong(idColumn);
////                String thisTitle = musicCursor.getString(titleColumn);
////                String thisArtist = musicCursor.getString(artistColumn);
//////                songList.add(new Song(thisId, thisTitle, thisArtist));
////                HashMap<String, String> song = new HashMap<String, String>();
////                song.put("songTitle", file.getName().substring(0, (file.getName().length() - 4)));
////                song.put("songPath", file.getPath());
////
////                // Adding each song to SongList
////                songsList.add(song);
//            }
//            while (musicCursor.moveToNext());
//        }
//    }
}
