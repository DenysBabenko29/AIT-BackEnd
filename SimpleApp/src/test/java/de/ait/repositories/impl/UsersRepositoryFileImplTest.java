package de.ait.repositories.impl;

import de.ait.models.User;
import de.ait.repositories.UsersRepository;
import org.junit.jupiter.api.*;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DisplayName("UsersRepositoryFileImpl is works ...")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
class UsersRepositoryFileImplTest {
    private static final String TEMP_USERS_FILE_NAME = "users_test.txt";
    private UsersRepository usersRepository;

    @BeforeEach
    public void setUp() throws Exception{

        createNewFileForTest(TEMP_USERS_FILE_NAME);
        usersRepository = new UsersRepositoryFileImpl(TEMP_USERS_FILE_NAME);
    }

    @AfterEach
    public void tearDown() throws Exception {
        deleteFileAfterTest(TEMP_USERS_FILE_NAME);
    }

    @DisplayName("save():")
    @Nested
    class SaveTest{
        @Test
        public void save_user_writes_line_to_file() throws Exception{
            User user = new User("user@gmail.com", "1234qwe");
            usersRepository.save(user);
            String expected = "1;user@gmail.com;1234qwe";
            BufferedReader reader = new BufferedReader(new FileReader(TEMP_USERS_FILE_NAME));
            String actual = reader.readLine();
            reader.close();
            assertEquals(expected, actual);
        }
    }

    @DisplayName("findAll():")
    @Nested
    class FindAll{
        @Test
        public void find_all_returns_corrects_list_of_users() throws Exception{
            BufferedWriter writer = new BufferedWriter(new FileWriter(TEMP_USERS_FILE_NAME));
            writer.write("1;user@gmail.com;1234qwe");
            writer.newLine();
            writer.write("2;user2@gmail.com;1234qwe2");
            writer.newLine();

            writer.close();

            List<User> expexted = Arrays.asList(
                    new User(1L, "user@gmail.com", "1234qwe"),
                    new User(2L, "user2@gmail.com", "1234qwe2"));
            List<User> actual = usersRepository.findAll();

            assertEquals(expexted, actual);
        }
    }


    private void createNewFileForTest(String fileName) throws IOException {
        File file = new File(fileName);

        deleteOfExist(file);

        boolean result = file.createNewFile();
        if(!result){
            throw new IllegalArgumentException("Проблема с созданием файла");
        }
    }

    private static void deleteOfExist(File file) {
        if (file.exists()) {
            boolean res = file.delete();
            if (!res){
                throw new IllegalArgumentException("Проблема с удалением файла");
            }
        }
    }

    private void deleteFileAfterTest(String fileName){
        File file = new File(fileName);

        deleteOfExist(file);
    }
}