package Mal.storage;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import Mal.task.*;
import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {

    @Test
    public void storage_saveAndLoad_preservesDataIntegrity() throws IOException {
        File tempFile = File.createTempFile("test_storage", ".txt");
        Storage storage = new Storage(tempFile.getAbsolutePath());

        ArrayList<Task> originalList = new ArrayList<>();
        TodoTask todo = new TodoTask("Read Book");

        originalList.add(todo);

        storage.save(originalList);

        ArrayList<Task> loadedList = storage.load();

        assertEquals(1, loadedList.size());
        assertEquals(originalList.get(0).toString(), loadedList.get(0).toString());

        tempFile.delete();
    }

    @Test
    public void load_malformedFile_skipsCorruptLines() throws IOException {
        File tempFile = File.createTempFile("corrupt_test", ".txt");
        String content = "T|0| Good Task\nTHIS_IS_CORRUPT_DATA_WITHOUT_PIPES\n";
        Files.writeString(tempFile.toPath(), content);

        Storage storage = new Storage(tempFile.getAbsolutePath());
        ArrayList<Task> list = storage.load();

        assertEquals(1, list.size());
        assertTrue(list.get(0).toString().contains("Good Task"));

        tempFile.delete();
    }

}