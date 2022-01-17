package singularity.scoutviewer;

import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Files {
    public static void write(String path, String data) {
        try {
            FileOutputStream fos = new FileOutputStream(path);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            //I'm honestly not quite sure how it works, it does seem to though

            osw.write(data);
            osw.flush();
            fos.close();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public static String read(String path) {
        // create a new File with the given path and a StringBuilder
        File file = new File(path);
        StringBuilder text = new StringBuilder();

        // read the file line by line into the StringBuilder, adding a newline character after
        // each line
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            br.close();
        } catch (IOException e) {
            //add error handling here
        }
        return text.toString();
        //return the final StringBuilder after converting to a String
    }


    public static void mkDir(String path) {
        // make a new directory with the given path
        File dir = new File(path);
        // check if the directory already exists
        if (!dir.exists()) {
            Log.i("Files", "Directory does not exist, creating " + path);
            dir.mkdirs();
            // make the directory and any missing parent directories if they do not exist
            // also give feedback if it doesn't exist

            if (dir.exists()) {
                Log.i("Files", "Directory created successfully");
            } else {
                Log.i("Files", "Failed to create directory" + path);
            }
            // check to make sure the directory was created
        }
    }

    public static String[] list(String path) {
        // create a directory with the given path
        File dir = new File(path);
        return dir.list();
        //return an array of file names in the directory with a "/" before them
    }

    public static String[] listWith(String path, boolean slash, boolean extension) {
        String[] files = Files.list(path);
        if (slash) {
            for (int i = 0; i < files.length; i++) {
                files[i] = "/" + files[i];
            }
        }
        if (!extension) {
            for (int i = 0; i < files.length; i++) {
                files[i] = files[i].substring(0, files[i].indexOf('.'));
            }
        }
        return files;
    }

    public static String list(String path, int index) {
        String[] files = Files.list(path);
        return files[index];
        //return the file name at the given index with a "/" before it
    }

    public static String listWith(String path, boolean slash, boolean extension, int index) {
        String[] files = Files.listWith(path, slash, extension);
        return files[index];
        //return the file name at the given index with a "/" before it
    }

    public static void delete(String path) {
        File file = new File(path);
        if (file.delete()) {
            Log.i("Files", "Deleted the file: " + file.getName());
        } else {
            Log.e("Files", "Failed to delete the file " + file.getName());
        }
    }


    static void deleteFolder(String path) {
        File file = new File(path);
        for (File subFile : file.listFiles()) {
            if (subFile.isDirectory()) {
                deleteFolder(subFile.getPath());
            } else {
                subFile.delete();
            }
        }
        file.delete();
    }
}