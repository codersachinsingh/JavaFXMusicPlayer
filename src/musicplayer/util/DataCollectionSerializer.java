package musicplayer.util;

import javafx.application.Application;

import java.io.*;
import java.util.Collection;

public class DataCollectionSerializer<T extends Collection> {
    private final String filepath;
    private File systemAppDataDir;
    public DataCollectionSerializer(String file) {
        /* Get the Application Data Directory from the System. */
        String appDataDir = System.getenv("LOCALAPPDATA");

        /* Create JavaFXMusicPlayer own Directory. */
        File applicationDir = new File(appDataDir + "/" + AppUtils.APPLICATION_NAME);

        if (!applicationDir.exists()) {
            boolean mkdirs = applicationDir.mkdirs();
            if (mkdirs)
                System.out.println("Directories created successfully");
        }
        File saveFile = new File(applicationDir, file);

        if (!saveFile.exists()) {
            // Just create the file.
            try (FileOutputStream fos = new FileOutputStream(saveFile)) {
                System.err.println("Created File " + file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.filepath = saveFile.getAbsolutePath();
    }

    public void saveState(T collection) {
        File file = new File(filepath);
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(
                             new BufferedOutputStream(
                                     new FileOutputStream(file))))
        {
            oos.writeObject(collection);

        }
        catch(IOException e) {
            System.err.println("Error while serializing..");
            e.printStackTrace();
        }
    }

    public T retrieveState() {
        File file = new File(filepath);
        T collection = null;
        try (ObjectInputStream ois =
                new ObjectInputStream(
                        new BufferedInputStream(
                                new FileInputStream(file))))
        {
            collection = (T) ois.readObject();
        }
        catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

        return collection;
    }
}
